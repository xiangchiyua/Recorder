package com.recorder.api;
import android.os.Environment;

import java.io.File;

public class ScreenshotUtils {

    // 获取可能的截屏路径
    public static String getScreenshotsPath() {
        // 尝试获取不同的可能路径
        String[] possiblePaths = {
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/Screenshots",
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Screenshots"
        };

        for (String path : possiblePaths) {
            File dir = new File(path);
            if (dir.exists() && dir.isDirectory()) {
                return path;
            }
        }

        // 如果没有找到截屏路径，返回默认的图片路径
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/Screenshots";
    }
}

