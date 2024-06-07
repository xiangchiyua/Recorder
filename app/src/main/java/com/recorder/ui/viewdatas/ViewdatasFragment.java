package com.recorder.ui.viewdatas;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.github.aachartmodel.aainfographics.aachartcreator.AAChartView;

import com.recorder.model.*;

import com.recorder.databinding.FragmentViewdatasBinding;
import com.recorder.R;
public class ViewdatasFragment extends Fragment {

    private FragmentViewdatasBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewdatasViewModel viewDatasViewModel =
                new ViewModelProvider(this).get(ViewdatasViewModel.class);
        binding = FragmentViewdatasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //final TextView textView = binding.textViewdatas;
        //viewDatasViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        //aaChartView

        viewDatasViewModel.setAAData1(new Object[]{7.0, 6.9, 9.5, 14.5});
        AAChartView aaChartView1=root.findViewById(R.id.aaChartView1);
        aaChartView1.aa_drawChartWithChartModel(viewDatasViewModel.aaChartModel1);
        //Log.d("My","cnm");

        viewDatasViewModel.setAAData2(new Object[]{17.0, 16.9, 9.5, 14.5, 18.2, 16.0, 13.8});
        AAChartView aaChartView2=root.findViewById(R.id.aaChartView2);
        aaChartView2.aa_drawChartWithChartModel(viewDatasViewModel.aaChartModel2);
        //Log.d("My","cnm");
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}