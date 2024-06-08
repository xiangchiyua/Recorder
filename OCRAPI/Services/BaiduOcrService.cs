using RestSharp;
using Newtonsoft.Json.Linq;
using System.IO;
using System.Text;
using System.Threading.Tasks;

namespace BaiduOcrApi.Services
{
    public class BaiduOcrService
    {
        private readonly string _apiKey;
        private readonly string _secretKey;
        private readonly string _tokenUrl = "https://aip.baidubce.com/oauth/2.0/token";
        private readonly string _ocrUrl = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";

        public BaiduOcrService(string apiKey, string secretKey)
        {
            _apiKey = apiKey;
            _secretKey = secretKey;
        }

        private async Task<string> GetAccessToken()
        {
            var client = new RestClient(_tokenUrl);
            var request = new RestRequest("", Method.Post);
            request.AddParameter("grant_type", "client_credentials");
            request.AddParameter("client_id", _apiKey);
            request.AddParameter("client_secret", _secretKey);

            var response = await client.ExecuteAsync(request);
            var json = JObject.Parse(response.Content);
            return json["access_token"].ToString();
        }

        public async Task<string> RecognizeText(string imageBase64)
        {
            var token = await GetAccessToken();

            var client = new RestClient(_ocrUrl + "?access_token=" + token);
            var request = new RestRequest("", Method.Post);
            request.AddHeader("Content-Type", "application/x-www-form-urlencoded");
            request.AddParameter("image", imageBase64);

            var response = await client.ExecuteAsync(request);
            var json = JObject.Parse(response.Content);

            StringBuilder result = new StringBuilder();
            foreach (var item in json["words_result"])
            {
                result.AppendLine(item["words"].ToString());
            }
            return result.ToString();
        }

        public async Task<string> RecognizeTextFromFile(Stream fileStream)
        {
            using (var ms = new MemoryStream())
            {
                await fileStream.CopyToAsync(ms);
                var imageBytes = ms.ToArray();
                var base64Image = System.Convert.ToBase64String(imageBytes);
                return await RecognizeText(base64Image);
            }
        }

        public async Task<string> RecognizeTextFromFilePath(string filePath)
        {
            if (!File.Exists(filePath))
                throw new FileNotFoundException("File not found", filePath);

            var imageBytes = await File.ReadAllBytesAsync(filePath);
            var base64Image = System.Convert.ToBase64String(imageBytes);
            return await RecognizeText(base64Image);
        }
    }
}
