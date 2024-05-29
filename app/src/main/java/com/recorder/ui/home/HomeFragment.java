package com.recorder.ui.home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.recorder.databinding.FragmentHomeBinding;
import com.recorder.R;
import com.recorder.model.*;

import java.util.Date;
import java.util.zip.Inflater;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private void addBills(Bill[] bills) {
        LinearLayout linearLayout = getView().findViewById(R.id.linearbills);
        for (int i = 1; i <= 5; i++) {
            String billId = "bills" + Integer.toString(i);
            //Log.d("My", billId);
            //Log.d("My", Integer.toString(getResources().getIdentifier(billId,"id", getActivity().getPackageName())));
            View billView = linearLayout.findViewById(
                    getResources().getIdentifier(billId, "id", getActivity().getPackageName()));
            TextView text = billView.findViewById(R.id.billName);
            text.setText(Integer.toString(i));
        }
        //动态添加
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View newBillView = inflater.inflate(R.layout.bills, linearLayout, false);
        TextView newText = newBillView.findViewById(R.id.billName);
        newText.setText("傻逼");
        linearLayout.addView(newBillView);
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //日期选择的添加：
        Button btnBegin=root.findViewById(R.id.btnBegin);
        Button btnEnd=root.findViewById(R.id.btnEnd);
        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getChildFragmentManager();
                DateDialogFragment dialogBegin = new DateDialogFragment(btnBegin);
                dialogBegin.show(fragmentManager, "myDialog");
            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getChildFragmentManager();
                DateDialogFragment dialogEnd = new DateDialogFragment(btnEnd);
                dialogEnd.show(fragmentManager, "myDialog");
            }
        });
        return root;
    }
    public void onResume() {
        super.onResume();
        addBills(null);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void onDialogDismiss(){
        Log.d("my","onDialogDismiss");
        TextView text=getView().findViewById(R.id.btnBegin);
        if(!text.getText().toString().equals("起始时间")){
            Log.d("my", text.getText().toString());
        }
        onResume();
    }
}