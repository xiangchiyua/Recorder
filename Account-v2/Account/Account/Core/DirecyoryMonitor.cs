    using System;
    using System.IO;
    using System.Threading;
    using Account.Controllers;
    using Microsoft.Extensions.Logging;

namespace Account.Core
{


    public class DirectoryMonitor
    {
        private readonly string _path;
        private readonly ILogger<DirectoryMonitor> _logger;

        public DirectoryMonitor(string path, ILogger<DirectoryMonitor> logger)
        {
            _path = path;
            _logger = logger;
        }

        public void Start()
        {
            FileSystemWatcher watcher = new FileSystemWatcher();
            watcher.Path = _path;
            watcher.Filter = "*.jpg"; // Assuming we're watching for JPG images
            watcher.Created += OnNewFileCreated;
            watcher.EnableRaisingEvents = true;
        }

        private void OnNewFileCreated(object sender, FileSystemEventArgs e)
        {
            // Start a new thread to handle the file
            Thread thread = new Thread(() => ProcessNewFile(e.FullPath));
            thread.Start();
        }

        private void ProcessNewFile(string filePath)
        {
            try
            {
                //string billImage = File.ReadAllBytes(filePath);
                // Call the insertBillByImage method here
                AccountController controller = new AccountController(new LoggerFactory().CreateLogger<AccountController>());
                controller.insertBillByImage(filePath);
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, $"Error processing file: {filePath}");
            }
        }
    }

}
