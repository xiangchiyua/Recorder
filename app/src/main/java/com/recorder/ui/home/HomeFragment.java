package com.recorder.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
    private void addBills(Bill[] bills){
        LinearLayout linearLayout=getView().findViewById(R.id.linearbills);
        for(int i=1;i<=5;i++){
            String billId="biils"+Integer.toString(i);
            //Log.d("My", billId);
            //Log.d("My", Integer.toString(getResources().getIdentifier(billId,"id", getActivity().getPackageName())));
            View billView=linearLayout.findViewById(
                    getResources().getIdentifier(billId,"id", getActivity().getPackageName()));
            TextView text=billView.findViewById(R.id.billName);
            text.setText(Integer.toString(i));
        }
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
        Date begin;
        Date end;
        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                DateDialogFragment dialogBegin = new DateDialogFragment(btnBegin);
                dialogBegin.show(fragmentManager, "myDialog");
            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
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
}