package com.recorder.ui.addbills;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.recorder.databinding.FragmentAddbillsBinding;
import com.recorder.R;
import com.recorder.ui.addbills.BottomSheet;

import java.util.List;
import java.util.Set;
import com.recorder.api.*;

public class AddbillsFragment extends Fragment {

    private FragmentAddbillsBinding binding;
    private TextView textView;
    private static final String PREFS_NAME = "SwitchPrefs";
    private static final String SWITCH_STATE_KEY = "SwitchState";
    private Switch mySwitch;
    private SharedPreferences sharedPreferences;
    private ftpHelper ftp=new ftpHelper();
    ImageRecordManager imageRecordManager;
    private void showBottomSheetDialog(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        View bottomSheetView = getLayoutInflater().inflate(R.layout.fragment_bottomsheet, null);
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddbillsViewModel addbillsViewModel =
                new ViewModelProvider(this).get(AddbillsViewModel.class);

        binding = FragmentAddbillsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button button = root.findViewById(R.id.button_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showBottomSheetDialog();
                BottomSheet bottomSheet=new BottomSheet();
                bottomSheet.show(getChildFragmentManager(),"BottomSheet");
            }
        });
        /*
        Log.d("my", "555");
        Button btnAdd=root.findViewById(R.id.button_ensure);
        TextView textType=root.findViewById(R.id.inputType);
        TextView textMoney=root.findViewById(R.id.inputMoney);
        TextView textRemark=root.findViewById(R.id.inputRemark);
        Log.d("my", "666");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textType.getText()!=null||textMoney.getText()==null||textRemark.getText()==null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("警告");
                    builder.setMessage("信息不能为空");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{

                }
            }
        });
        Log.d("my", "777");
        */


        mySwitch = root.findViewById(R.id.switchAutoBill);
        sharedPreferences =getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        // 恢复Switch状态
        boolean switchState = sharedPreferences.getBoolean(SWITCH_STATE_KEY, false);
        mySwitch.setChecked(switchState);

        // 设置Switch状态改变监听器
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // 保存Switch状态
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(SWITCH_STATE_KEY, isChecked);
                editor.apply();
            }
        });

        if(switchState){
            //uploadImages();
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void uploadImages(){
        //Log.d("my", ScreenshotUtils.getScreenshotsPath());
        imageRecordManager= new ImageRecordManager(getActivity());
        //Log.d("my",imageRecordManager.imagePath);
        Set<String> nowImages=imageRecordManager.getCurrentImagePaths();
        // 获取新增的图片并显示
        //List<String> newImages = imageRecordManager.getNewImagePaths();
        if (!nowImages.isEmpty()) {
            for (String imagePath : nowImages) {
                Log.d("my", imagePath);
                //ftp.uploadFile(imagePath);
                //Log.d("my", "uploadImages sucess");
            }
        } else {
            //Log.d("my","no changes");
        }

        // 保存当前图片列表以备下次启动时使用
        //imageRecordManager.saveCurrentImagePaths();
    }
}