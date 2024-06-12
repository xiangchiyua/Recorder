package com.recorder.api;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.recorder.MainActivity;

public class PermissionUtils {
    public static final int REQUEST_STORAGE_PERMISSION = 1;
    public static final int REQUEST_FTP_PERMISSION = 2;

    public static boolean hasStoragePermission(Activity activity) {
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestStoragePermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.READ_MEDIA_IMAGES}, REQUEST_STORAGE_PERMISSION);
    }
    public static boolean hasFTPPermission(Activity activity) {
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestFTPPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.INTERNET}, REQUEST_FTP_PERMISSION);
    }
    /*
    private void showPermissionExplanationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Storage Permission Needed")
                .setMessage("This app needs the storage permission to access your screenshots. Please grant the permission.")
                .setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 重新请求权限
                        PermissionUtils.requestStoragePermission(MainActivity.this);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

     */

}
