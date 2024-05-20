package com.recorder.dal.bean;

import java.util.Date;

public class Statistic {
    private String statisticID;//默认为生成的dateTime
    private float totalIncome;
    private float totalExpense;
    //private float averageExpense;  感觉没有意义
    private Date startDate;
    private Date endDate;

    private String name;
    public Statistic() {
    }

    public Statistic(String statisticID, float totalIncome, float totalExpense, Date startDate, Date endDate) {
        this.statisticID = statisticID;
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getStatisticID() {
        return statisticID;
    }

    public void setStatisticID(String statisticID) {
        this.statisticID = statisticID;
    }

    public float getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(float totalIncome) {
        this.totalIncome = totalIncome;
    }

    public float getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(float totalExpense) {
        this.totalExpense = totalExpense;
    }

    /*public float getAverageExpense() {
        return averageExpense;
    }

    public void setAverageExpense(float averageExpense) {
        this.averageExpense = averageExpense;
    }*/

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "statisticID='" + statisticID + '\'' +
                ", totalIncome=" + totalIncome +
                ", totalExpense=" + totalExpense +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", name='" + name + '\'' +
                '}';
    }
}
