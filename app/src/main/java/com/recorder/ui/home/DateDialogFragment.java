package com.recorder.ui.home;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.recorder.MainActivity;
import com.recorder.R;
import com.recorder.databinding.FragmentHomeBinding;

import java.util.Calendar;

public class DateDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    Button btn;
    final Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);
    public DateDialogFragment(Button btn){
        super();
        this.btn=btn;
    }
    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.datepickerdialog, container, false);
        DatePicker datePicker=view.findViewById(R.id.myDatePicker);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int Year, int Month, int Day) {
                year=Year;
                month=Month;
                day=Day;
            }
        });
        Button btnEnsure=view.findViewById(R.id.myDatePickerEnsureButton);
        btnEnsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDateSet(datePicker, year,month, day);
            }
        });
        return view;
    }
    public void onDateSet(DatePicker view, int year, int month, int day) {
        month = month + 1;
        String selectedDate = year + "/" + month + "/" + day;
        btn.setText(selectedDate);
        dismiss();
    }
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Fragment parentFragment = getParentFragment();
        ((HomeFragment) parentFragment).onDialogDismiss();
    }
}