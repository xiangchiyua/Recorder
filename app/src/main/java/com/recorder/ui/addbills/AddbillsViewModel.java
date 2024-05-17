package com.recorder.ui.addbills;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddbillsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AddbillsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Add bills here");
    }

    public LiveData<String> getText() {
        return mText;
    }
}