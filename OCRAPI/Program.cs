using BaiduOcrApi.Services;
using Microsoft.AspNetCore;
using Microsoft.AspNetCore.Hosting;

namespace OCRAPI
{
    public class Program
    {
        public static void Main(string[] args)
        {
            /* var builder = WebApplication.CreateBuilder(args);

             // Add services to the container.
             builder.Services.AddControllersWithViews();

             var app = builder.Build();

             // Configure the HTTP request pipeline.
             if (!app.Environment.IsDevelopment())
             {
                 app.UseExceptionHandler("/Home/Error");
                 // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
                 app.UseHsts();
             }

             app.UseHttpsRedirection();
             app.UseStaticFiles();

             app.UseRouting();

             app.UseAuthorization();

             app.MapControllerRoute(
                 name: "default",
                 pattern: "{controller=Home}/{action=Index}/{id?}");

             app.Run();*/


            var builder = WebApplication.CreateBuilder(args);
            builder.WebHost.UseUrls("http://*:5555");
            builder.Services.AddControllers();
            builder.Services.AddScoped<BaiduOcrService>(sp => new BaiduOcrService("fCvgehIJP6msJmDRxB4eNkEy", "QxocsqjZ59fWc0hW4F8jXbKSQSr5565T"));

            var app = builder.Build();

            app.UseHttpsRedirection();
            app.UseAuthorization();
            app.MapControllers();
            app.Run();

        }
    }
}