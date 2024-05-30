package com.recorder.ui.addbills;

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
        //Log.d("My", "cnm");
        //Log.d("My", Integer.toString(getResources().getIdentifier("button_add","id", getActivity().getPackageName())));
        Button button = root.findViewById(R.id.button_add);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheetDialog();
            }
        });

        Button btnAdd=root.findViewById(R.id.button_ensure);
        TextView textType=root.findViewById(R.id.inputType);
        TextView textMoney=root.findViewById(R.id.inputMoney);
        TextView textYear=root.findViewById(R.id.inputYear);
        TextView textMonth=root.findViewById(R.id.inputMonth);
        TextView textDay=root.findViewById(R.id.inputDay);

        String type= textType.getText().toString();
        String money= textMoney.getText().toString();
        String year= textYear.getText().toString();
        String month= textMonth.getText().toString();
        if(month.length()<2){
            month="0"+month;
        }
        String day= textDay.getText().toString();
        if(day.length()<2){
            day="0"+day;
        }
        String date=year+"-"+month+"-"+day;

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