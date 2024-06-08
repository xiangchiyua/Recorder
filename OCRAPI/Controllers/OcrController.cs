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

        public OcrController(BaiduOcrService baiduOcrService)
        {
            _baiduOcrService = baiduOcrService;
        }

        [HttpPost("recognize")]
        public async Task<IActionResult> Recognize([FromBody] ImageRequest request)
        {
            var result = await _baiduOcrService.RecognizeText(request.ImageBase64);
            return Ok(result);
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
