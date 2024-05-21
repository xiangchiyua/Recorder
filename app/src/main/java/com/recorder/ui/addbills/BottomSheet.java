package com.recorder.ui.addbills;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.recorder.databinding.FragmentAddbillsBinding;
import com.recorder.R;

public class BottomSheet extends BottomSheetDialogFragment {
    public View onCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bottomsheet, container, false);
    }
}
