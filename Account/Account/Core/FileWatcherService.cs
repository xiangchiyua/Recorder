using Account.Controllers;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using System;
using System.IO;
using System.Threading;
using System.Threading.Tasks;

namespace Account.Core
{
    public class FileWatcherService : IHostedService, IDisposable
    {
        private readonly ILogger<FileWatcherService> _logger;
        private readonly IServiceScopeFactory _scopeFactory;
        private FileSystemWatcher _fileWatcher;

        public FileWatcherService(ILogger<FileWatcherService> logger, IServiceScopeFactory scopeFactory)
        {
            _logger = logger;
            _scopeFactory = scopeFactory;
        }

        public Task StartAsync(CancellationToken cancellationToken)
        {
            _logger.LogInformation("FileWatcherService started.");

            _fileWatcher = new FileSystemWatcher
            {
                Path = @"E:\picture\", // 监控的文件路径
                Filter = "*.jpg", // 只监控.jpg文件
                EnableRaisingEvents = true,
                NotifyFilter = NotifyFilters.FileName | NotifyFilters.LastWrite
            };

            _fileWatcher.Created += OnCreated;

            return Task.CompletedTask;
        }

        private void OnCreated(object sender, FileSystemEventArgs e)
        {
            _logger.LogInformation($"File created: {e.FullPath}");

            try
            {
                using (var scope = _scopeFactory.CreateScope())
                {
                    var controller = scope.ServiceProvider.GetRequiredService<AccountController>();
                    controller.insertBillByImage(e.FullPath);
                }
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, "Error processing created file event.");
            }
        }

        public Task StopAsync(CancellationToken cancellationToken)
        {
            _logger.LogInformation("FileWatcherService stopped.");
            _fileWatcher.EnableRaisingEvents = false;
            _fileWatcher.Dispose();
            return Task.CompletedTask;
        }

        public void Dispose()
        {
            _fileWatcher?.Dispose();
        }
    }

}
