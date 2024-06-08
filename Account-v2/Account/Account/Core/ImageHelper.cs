using System;
using System.IO;
namespace Account.Core
{
public class ImageHelper
{
    public static string ConvertImageToBase64(string imagePath)
    {
        byte[] imageBytes = File.ReadAllBytes(imagePath);
        string base64String = Convert.ToBase64String(imageBytes);
        return base64String;
    }
}
}


