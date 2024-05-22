package com.recorder.ui.viewdatas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel;
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType;
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement;

public class ViewdatasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    AAChartModel aaChartModel = new AAChartModel()
            .chartType(AAChartType.Area)
            .title("Monthly Average Temperature")
            .categories(new String[]{"Jan", "Feb", "Mar", "Apr", "May"})
            .series(new AASeriesElement[]{
                    new AASeriesElement().name("Tokyo").data(new Object[]{7.0, 6.9, 9.5, 14.5, 18.2}),
                    new AASeriesElement().name("New York").data(new Object[]{-0.2, 0.8, 5.7, 11.3, 17.0})
            });
    public ViewdatasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("View datas here");
    }

    public LiveData<String> getText() {
        return mText;
    }
}