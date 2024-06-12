package com.recorder.api;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ftpHelper {
    String server = "59.110.13.7";
    int port = 21;
    String user = "vwp";
    String pass = "123456";
    String uploadPath = "/bills/";

    public void uploadFile(String filePath) {
        new UploadTask().execute(filePath);
    }

    private class UploadTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String filePath = params[0];
            FTPClient ftpClient = new FTPClient();
            boolean success = false;
            Log.d("my", "uploading");

            try {
                ftpClient.connect(server, port);
                ftpClient.login(user, pass);
                ftpClient.enterLocalPassiveMode();
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                File file = new File(filePath);
                if (!file.exists()) {
                    Log.d("my", "File does not exist: " + filePath);
                    return false;
                }

                String fileName = file.getName();
                String remoteFilePath = uploadPath + fileName;

                FileInputStream inputStream = new FileInputStream(filePath);
                success = ftpClient.storeFile(remoteFilePath, inputStream);
                inputStream.close();

                Log.d("my", "File uploaded successfully: " + success);

                ftpClient.logout();
                ftpClient.disconnect();

            } catch (Exception e) {
                Log.d("my", "Exception: " + e.getMessage());
            }

            return success;
        }
    }
}

