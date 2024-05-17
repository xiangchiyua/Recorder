package com.recorder.ui.viewdatas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.recorder.databinding.FragmentViewdatasBinding;

public class ViewdatasFragment extends Fragment {

    private FragmentViewdatasBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ViewdatasViewModel viewDatasViewModel =
                new ViewModelProvider(this).get(ViewdatasViewModel.class);

        binding = FragmentViewdatasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textViewdatas;
        viewDatasViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}