package com.recorder.ui.viewdatas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel;
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType;
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement;

import java.lang.reflect.Array;
import java.util.Date;

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
                .title("花费种类统计")
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
                                    {"饮食",object[0]},
                                    {"住宿",object[1]},
                                    {"出行",object[2]},
                                    {"购物",object[3]}
                                })
                });
    }
    public void setAAData2(Object[] object){
        aaChartModel2 = new AAChartModel()
                .chartType(AAChartType.Area)
                .title("每天花费统计")
                .categories(new String[]{"1","2","3","4","5","6","7"})
                .series(new AASeriesElement[]{
                        new AASeriesElement()
                                .name("Date")
                                .data(object)
                });
    }
    public LiveData<String> getText() {
        return mText;
    }
}