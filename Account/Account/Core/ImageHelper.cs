using Account.Controllers;
using System;
using System.IO;
using Serilog;
using Serilog.Events;
namespace Account.Core
{
    public class ImageHelper
    {

        public static string ConvertImageToBase64(string imagePath)
        {
            Log.Logger = new LoggerConfiguration()
            .MinimumLevel.Is(LogEventLevel.Debug)
            .CreateLogger();

            try
            {
                Log.Information("正在转化");
                if (!File.Exists(imagePath))
                {
                    throw new FileNotFoundException("文件未找到", imagePath);
                }
                byte[] imageBytes = File.ReadAllBytes(imagePath);
                string base64String = Convert.ToBase64String(imageBytes);
                return base64String;
            }catch(Exception ex)
            {
                throw new Exception("未能转化");
            }
        }
    }
}


