package com.recorder.ui.addbills;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import com.recorder.databinding.FragmentAddbillsBinding;
import com.recorder.R;

public class BottomSheet extends BottomSheetDialogFragment {
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_bottomsheet, container, false);

        Button btnAdd=view.findViewById(R.id.button_ensure);
        TextView textType=view.findViewById(R.id.inputType);
        TextView textMoney=view.findViewById(R.id.inputMoney);
        TextView textRemark=view.findViewById(R.id.inputRemark);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textType.getText().toString().isEmpty()||
                        textMoney.getText().toString().isEmpty()||
                        textRemark.getText().toString().isEmpty()){
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
                    Log.d("my", "nm");
                }
            }
        });


        return view;
    }
}
