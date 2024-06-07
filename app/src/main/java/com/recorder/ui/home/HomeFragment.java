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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.recorder.api.MyApiService;
import com.recorder.databinding.FragmentHomeBinding;
import com.recorder.R;
import com.recorder.model.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    MyApiService api=new MyApiService();
    private void addBills(List<Bill> bills) {
        LinearLayout linearLayout = getView().findViewById(R.id.linearbills);
        linearLayout.removeAllViews();
        /*
        for (int i = 1; i <= 5; i++) {
            String billId = "bills" + Integer.toString(i);
            //Log.d("My", billId);
            //Log.d("My", Integer.toString(getResources().getIdentifier(billId,"id", getActivity().getPackageName())));
            View billView = linearLayout.findViewById(
                    getResources().getIdentifier(billId, "id", getActivity().getPackageName()));
            TextView text = billView.findViewById(R.id.billName);
            text.setText(Integer.toString(i));
        }
        */
        //动态添加
        /*
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View newBillView = inflater.inflate(R.layout.bills, linearLayout, false);
        TextView newText = newBillView.findViewById(R.id.billName);
        newText.setText("傻逼");
        linearLayout.addView(newBillView);

         */
        if(bills!=null) {
            for (int i = 0; i < bills.size(); i++) {
                //Log.d("my", bills.get(i).toString());
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View newBillView = inflater.inflate(R.layout.bills, linearLayout, false);
                //Texts
                TextView billID = newBillView.findViewById(R.id.billName);
                billID.setText(String.valueOf(bills.get(i).billID));
                TextView billtype = newBillView.findViewById(R.id.billType);
                billtype.setText(bills.get(i).type);
                TextView billMoney = newBillView.findViewById(R.id.billMoney);
                billMoney.setText(Float.toString(bills.get(i).money));
                TextView billTime = newBillView.findViewById(R.id.billCreateTime);
                Date date=bills.get(i).dateTime;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                billTime.setText(dateFormat.format(date));

                ImageView iv=newBillView.findViewById(R.id.imageView);
                linearLayout.addView(newBillView);
            }
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
        //String geturl="queryBillByDate?startDate=2000-01-01&endDate=2030-12-28";
        //addBills(api.get(geturl));
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        //String geturl="queryBillByDate?startDate=2000-01-01&endDate=2030-12-28";
        //addBills(api.get(geturl));
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void onDialogDismiss(){
        String dateBegin,dateEnd;
        TextView textBegin=getView().findViewById(R.id.btnBegin);

        //Log.d("my", textBegin.getText().toString());
        if(!textBegin.getText().toString().equals("起始时间")){
            dateBegin=textBegin.getText().toString();
        }
        else{
            dateBegin="2000-01-01";
        }
        TextView textEnd=getView().findViewById(R.id.btnEnd);

        //Log.d("my", textEnd.getText().toString());
        if(!textEnd.getText().toString().equals("结束时间")){
            dateEnd=textEnd.getText().toString();
        }
        else{
            dateEnd="2030-01-01";
        }
        //api get
        String getUrl="queryBillByDate?";
        getUrl+="startDate="+dateBegin;
        getUrl+="&";
        getUrl+="endDate="+dateEnd;
        List<Bill> bills=api.get(getUrl);
        /*
        if(bills==null){
            Log.d("my", "cnm");
        }
        else {
            for (Bill bill : api.get(getUrl)) {
                Log.d("my", bill.toString());
            }
        }

         */
        //Log.d("my", bills.toString());
        addBills(bills);
        onResume();
    }
}