package com.recorder.ui.viewdatas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewdatasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ViewdatasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("View datas here");
    }

    public LiveData<String> getText() {
        return mText;
    }
}