package com.recorder.ui.addbills;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.recorder.databinding.FragmentAddbillsBinding;

public class AddbillsFragment extends Fragment {

    private FragmentAddbillsBinding binding;
    private TextView textView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AddbillsViewModel addbillsViewModel =
                new ViewModelProvider(this).get(AddbillsViewModel.class);

        binding = FragmentAddbillsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textAddbills;
        addbillsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}