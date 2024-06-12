package com.recorder;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.recorder.api.*;
import com.recorder.databinding.ActivityMainBinding;
import com.recorder.R;
import com.recorder.ui.home.HomeFragment;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class MainActivity extends AppCompatActivity {
    OkHttpClient client=new OkHttpClient();
    String conn="https://localhost:7162/Account/";
    //ImageRecordManager imageRecordManager=new ImageRecordManager(this);
    ftpHelper ftp=new ftpHelper();

    private ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "SwitchPrefs";
    private static final String SWITCH_STATE_KEY = "SwitchState";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_viewdatas, R.id.navigation_addbills)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        //startActivityForResult(projectionManager.createScreenCaptureIntent(), REQUEST_CODE);

        sharedPreferences =this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        // 恢复Switch状态
        boolean switchState = sharedPreferences.getBoolean(SWITCH_STATE_KEY, false);

        if(switchState){
            Log.d("my", "getting permissions");
            //获取截屏访问权限
            if(!PermissionUtils.hasStoragePermission(this)){
                Log.d("my", "don't has storage permission");
                try {
                    //PermissionUtils.requestStoragePermission(this);
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, PermissionUtils.REQUEST_STORAGE_PERMISSION);
                }
                catch (Exception e){
                    Log.d("my", e.getMessage());
                }
            }
            else{
                Log.d("my","has storage permission");
            }
            //获取ftp权限
            if(!PermissionUtils.hasFTPPermission(this)){
                Log.d("my", "don't has ftp permission");
                try {
                    //PermissionUtils.requestStoragePermission(this);
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PermissionUtils.REQUEST_FTP_PERMISSION);
                }
                catch (Exception e){
                    Log.d("my", e.getMessage());
                }
            }
            else{
                Log.d("my","has ftp permission");
            }
            uploadImages();
        }


    }

    public void uploadImages(){
        ImageRecordManager imageRecordManager=new ImageRecordManager(this);
        //Log.d("my", ScreenshotUtils.getScreenshotsPath());
        //Log.d("my",imageRecordManager.imagePath);
        //Set<String> nowImages=imageRecordManager.getCurrentImagePaths();
        // 获取新增的图片并显示
        List<String> newImages = imageRecordManager.getNewImagePaths();
        if (!newImages.isEmpty()) {
            for (String imagePath : newImages) {
                Log.d("my", imagePath);
                try {
                    ftp.uploadFile(imagePath);
                }catch (Exception e){
                    Log.d("my","Error" + e);
                }
            }
        } else {
            Log.d("my","no changes");
        }

        // 保存当前图片列表以备下次启动时使用
        imageRecordManager.saveCurrentImagePaths();
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionUtils.REQUEST_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 权限被授予，可以继续执行需要权限的操作
                ImageRecordManager imageRecordManager = new ImageRecordManager(this);
                Set<String> paths=imageRecordManager.getCurrentImagePaths();
                for(String path:paths){
                    //Log.d("my", path);
                }
                // 其他操作
            } else {
                // 权限被拒绝，显示解释对话框
                if (shouldShowRequestPermissionRationale(Manifest.permission.READ_MEDIA_IMAGES)) {
                    showPermissionExplanationDialog();
                } else {
                    // 用户选择了不再提示
                    Log.d("my", "Storage Permission Denied");
                    //showSettingsDialog();
                }
            }
        }
        else if(requestCode == PermissionUtils.REQUEST_FTP_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 权限被授予，可以继续执行需要权限的操作
                Log.d("my", "internet permission get");
                // 其他操作
            } else {
                // 权限被拒绝，显示解释对话框
                if (shouldShowRequestPermissionRationale(Manifest.permission.INTERNET)) {
                    showPermissionExplanationDialog();
                } else {
                    // 用户选择了不再提示
                    Log.d("my", "Storage Permission Denied");
                    //showSettingsDialog();
                }
            }
        }
    }


    private void showPermissionExplanationDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Storage Permission Needed")
                .setMessage("This app needs the storage permission to access your screenshots. Please grant the permission.")
                .setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 重新请求权限
                        PermissionUtils.requestStoragePermission(MainActivity.this);
                        //requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, PermissionUtils.REQUEST_STORAGE_PERMISSION);
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
}