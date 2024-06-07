package com.recorder.model;

import java.util.Date;

public class Statistic {
    private String statisticID;//默认为生成的dateTime
    private float totalIncome;
    private float totalExpense;
    //private float averageExpense;  感觉没有意义
    private Date startDate;
    private Date endDate;

    private String name;
}
