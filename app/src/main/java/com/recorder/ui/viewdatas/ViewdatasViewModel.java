package com.recorder.ui.viewdatas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel;
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType;
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement;

import java.lang.reflect.Array;

public class ViewdatasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    AAChartModel aaChartModel1 ;/*= new AAChartModel()
            .chartType(AAChartType.Area)
            .title("Monthly Average Temperature")
            .categories(new String[]{"Jan", "Feb", "Mar", "Apr", "May"})
            .series(new AASeriesElement[]{
                    new AASeriesElement().name("Tokyo").data(new Object[]{7.0, 6.9, 9.5, 14.5, 18.2}),
                    new AASeriesElement().name("New York").data(new Object[]{-0.2, 0.8, 5.7, 11.3, 17.0})
            });*/
    AAChartModel aaChartModel2;
    public ViewdatasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("View datas here");
    }
    public void setAAData1(Object[] object){
        aaChartModel1 = new AAChartModel()
                .chartType(AAChartType.Pie)
                .title("pie chart")
                .dataLabelsEnabled(false)
                //.yAxisGridLineWidth(0f)
                .legendEnabled(false)
                .xAxisGridLineWidth(10f)
                .series(new AASeriesElement[]{
                        new AASeriesElement()
                                .innerSize(90f)
                                .borderWidth(2f)
                                .allowPointSelect(false)
                                .data(new Object[][]{
                                    {"a",object[0]},
                                    {"b",object[1]},
                                    {"c",object[2]},
                                    {"d",object[3]},
                                    {"e",object[4]},
                                    {"f",object[5]},
                                    {"g",object[6]}
                                })
                });
    }
    public void setAAData2(Object[] object){
        aaChartModel2 = new AAChartModel()
                .chartType(AAChartType.Area)
                .title("area chart")
                .categories(new String[]{"Jan", "Feb", "Mar", "Apr", "May"})
                .series(new AASeriesElement[]{
                        new AASeriesElement()
                                .name("Tokyo")
                                .data(object)
                });
    }
    public LiveData<String> getText() {
        return mText;
    }
}