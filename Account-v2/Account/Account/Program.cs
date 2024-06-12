
using Account.Controllers;
using Account.Core;
using Account.Dal;
using Microsoft.Extensions.Logging;

namespace Account
{
    public class Program
    {
        public static void Main(string[] args)
        {
            /*¼à¿Ø*/
            var loggerFactory = LoggerFactory.Create(builder =>
            {
                builder.AddConsole();
            });
            var logger = loggerFactory.CreateLogger<DirectoryMonitor>();

            // Replace here
            DirectoryMonitor monitor = new DirectoryMonitor("E:/picture", logger);
            monitor.Start();
            /**/


            var builder = WebApplication.CreateBuilder(args);

            // Add services to the container.

            builder.Services.AddControllers();
            // Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
            builder.Services.AddEndpointsApiExplorer();
            builder.Services.AddSwaggerGen();

            var app = builder.Build();

            // Configure the HTTP request pipeline.
            //if (app.Environment.IsDevelopment())
            //{
                app.UseSwagger();
                app.UseSwaggerUI();
            //}

            app.UseHttpsRedirection();

            app.UseAuthorization();


            app.MapControllers();

            app.Run();
        }
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddControllers();
            services.AddScoped<AccountController>(); // ×¢²á AccountController
            services.AddScoped<BillDal>(); // ×¢²á BillDal
            services.AddScoped<OcrHelper>(); // ×¢²á OcrHelper
            services.AddSingleton<FileWatcherService>();
            services.AddHostedService<FileWatcherService>();
        }

        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }
            else
            {
                app.UseExceptionHandler("/Home/Error");
                app.UseHsts();
            }

            app.UseHttpsRedirection();
            app.UseStaticFiles();

            app.UseRouting();

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });

        }

    }
}
