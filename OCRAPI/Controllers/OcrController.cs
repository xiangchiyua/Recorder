using Microsoft.AspNetCore.Mvc;
using BaiduOcrApi.Services;
using System.Threading.Tasks;

namespace BaiduOcrApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class OcrController : ControllerBase
    {
        private readonly BaiduOcrService _baiduOcrService;
        private readonly string _fixedDirectoryPath = "D:/OCRimg"; // 更改为固定目录路径

        public OcrController(BaiduOcrService baiduOcrService)
        {
            _baiduOcrService = baiduOcrService;
        }
        [HttpPost("recognize-fixed-directory")]
        public async Task<IActionResult> RecognizeFixedDirectory()
        {
            if (!Directory.Exists(_fixedDirectoryPath))
                return NotFound("Directory not found.");

            var imageFiles = Directory.GetFiles(_fixedDirectoryPath, "*.*")
                .Where(file => file.ToLower().EndsWith(".jpg") || file.ToLower().EndsWith(".jpeg") || file.ToLower().EndsWith(".png"))
                .ToArray();

            if (imageFiles.Length == 0)
                return NotFound("No image files found in the directory.");

            var ocrResults = new List<string>();

            foreach (var filePath in imageFiles)
            {
                var result = await _baiduOcrService.RecognizeTextFromFilePath(filePath);
                ocrResults.Add($"File: {Path.GetFileName(filePath)}\n{result}\n");
                System.IO.File.Delete(filePath);
            }

            return Ok(string.Join("\n", ocrResults));
        }


        [HttpPost("recognize-file")]
        public async Task<IActionResult> RecognizeFile([FromForm] IFormFile file)
        {
            if (file == null || file.Length == 0)
                return BadRequest("No file uploaded.");

            using (var stream = file.OpenReadStream())
            {
                var result = await _baiduOcrService.RecognizeTextFromFile(stream);
                return Ok(result);
            }
        }
        [HttpPost("recognize")]
        public async Task<IActionResult> Recognize([FromBody] ImageRequest request)
        {
            var result = await _baiduOcrService.RecognizeText(request.ImageBase64);
            return Ok(result);
        }

        [HttpPost("recognize-filepath")]
        public async Task<IActionResult> RecognizeFilePath([FromBody] FilePathRequest request)
        {
            if (string.IsNullOrEmpty(request.FilePath))
                return BadRequest("File path is required.");

            var result = await _baiduOcrService.RecognizeTextFromFilePath(request.FilePath);
            return Ok(result);
        }
    }

    public class ImageRequest
    {
        public string ImageBase64 { get; set; }
    }

    public class FilePathRequest
    {
        public string FilePath { get; set; }
    }
}
