using Account.Model;
using RestSharp;//依赖版本106.15.0 https://www.nuget.org/packages/RestSharp/106.15.0
using Newtonsoft.Json; //https://www.nuget.org/packages/Newtonsoft.Json
namespace Account.Core
{

    public class OcrHelper
    {
        private static string API_KEY = "zEiQFdvdFBd8vXuzUPdKwQXw";
        private static string SECRET_KEY = "O2jst4mIun13Od5Xzbo5AwKxR28Jn38U";
        public static Bill OcrImage(string image)
        {

            var client = new RestClient($"https://aip.baidubce.com/rest/2.0/ocr/v1/general?access_token={GetAccessToken()}");
            client.Timeout = -1;
            var request = new RestRequest(Method.POST);
            request.AddHeader("Content-Type", "application/x-www-form-urlencoded");
            request.AddHeader("Accept", "application/json");
            request.AddParameter("image", image);
            request.AddParameter("detect_direction", "false");
            request.AddParameter("detect_language", "false");
            request.AddParameter("vertexes_location", "false");
            request.AddParameter("paragraph", "false");
            request.AddParameter("probability", "false");
            IRestResponse response = client.Execute(request);
            Console.WriteLine(response.Content);
            var result = JsonConvert.DeserializeObject<dynamic>(response.Content);
            Bill bill = new Bill();
            List<string> wordsList = new List<string>();

            foreach (var wordsResult in result.words_result)
            {
                string data = wordsResult.words;
                wordsList.Add(data);
            }
            for (int i = 0; i < wordsList.Count(); i++)
            {
                if (wordsList[i] == "当前状态")
                {
                    // -10.00
                    var data = wordsList[i - 1];
                    data = data.Replace("-", "");
                    float.TryParse(data, out float number);
                    bill.Money = number;
                }
                if (wordsList[i] == "支付时间")
                {
                    // 2024年6月5日21：52：56
                    var data = wordsList[i + 1];
                    data = data.Replace("：", ":");
                    string format = "yyyy年M月d日HH:mm:ss";
                    if (DateTime.TryParseExact(data, format, null, System.Globalization.DateTimeStyles.None, out DateTime dateTime1))
                    {
                        bill.DateTime = dateTime1;
                    }
                    else
                    {
                        Console.WriteLine("转换失败:" + data);
                    }
                }
                if (wordsList[i] == "商品")
                {

                    bill.Remarks = wordsList[i + 1];

                }
            }
            return bill;
        }

        // 获取Access Token
        static string GetAccessToken()
        {
            var client = new RestClient($"https://aip.baidubce.com/oauth/2.0/token");
            client.Timeout = -1;
            var request = new RestRequest(Method.POST);
            request.AddParameter("grant_type", "client_credentials");
            request.AddParameter("client_id", API_KEY);
            request.AddParameter("client_secret", SECRET_KEY);
            IRestResponse response = client.Execute(request);
            Console.WriteLine(response.Content);
            var result = JsonConvert.DeserializeObject<dynamic>(response.Content);
            return result.access_token.ToString();
        }
    }

}
