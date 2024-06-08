package com.recorder.api;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ftpHelper {
    String server="59.110.13.7";
    int port=21;
    String user="vwp";
    String pass="123456";
    String uploadPath="jpgs/";
    public boolean uploadFile(String filePath) {
        FTPClient ftpClient = new FTPClient();
        boolean success = false;

        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            File file = new File(filePath);
            String fileName = file.getName();
            String remoteFilePath = uploadPath + fileName;

            FileInputStream inputStream = new FileInputStream(filePath);
            success = ftpClient.storeFile(remoteFilePath, inputStream);
            inputStream.close();

            ftpClient.logout();
            ftpClient.disconnect();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return success;
    }
}

