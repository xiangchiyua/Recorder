package com.recorder.model;

import com.recorder.dal.bean.Category;

import java.util.Date;

public class Bill {
    private int billID;
    private String type;//采用Income与Expense！！！后面逻辑如此使用。
    private Date dateTime;
    private Category category;
    private float money;
    private int cateID;
    private String remarks;

    public int getBillID() {
        return billID;
    }

    public String getType() {
        return type;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public Category getCategory() {
        return category;
    }

    public float getMoney() {
        return money;
    }

    public int getCateID() {
        return cateID;
    }

    public String getRemarks() {
        return remarks;
    }
}
