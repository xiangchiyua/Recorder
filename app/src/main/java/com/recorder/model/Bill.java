package com.recorder.model;

import com.recorder.dal.bean.Category;

import java.util.Date;

public class Bill {
    public int billID;
    public String type;//采用Income与Expense！！！后面逻辑如此使用。
    public Date dateTime;
    public Category category;
    public float money;
    public int cateID;
    public String remarks;
    public String toString() {
        return "Bill{" +
                "billID=" + billID +
                ", type='" + type + '\'' +
                ", dateTime=" + dateTime +
                ", category=" + category +
                ", money=" + money +
                ", cateID=" + cateID +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
