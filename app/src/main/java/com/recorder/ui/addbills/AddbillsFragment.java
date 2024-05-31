package com.recorder.ui.addbills;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class AddbillsFragment extends Fragment {

    private FragmentAddbillsBinding binding;
    private TextView textView;
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
        /*
        String type= textType.getText().toString();
        String money= textMoney.getText().toString();
        String remark= textRemark.getText().toString();
         */
        //final TextView textView = binding.button;
        //addbillsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}