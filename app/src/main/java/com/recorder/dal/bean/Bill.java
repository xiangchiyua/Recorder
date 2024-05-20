package com.recorder.dal.bean;


import java.util.Date;

public class Bill {
    private int billID;
    private String type;//采用Income与Expense！！！后面逻辑如此使用。
    private Date dateTime;
    private Category category;
    private float money;
    private int cateID;
    private String remarks;

    public Bill() {
    }

    public Bill(/*int billID,*/ String type, Date dateTime, /*Category category,*/ float money, int cateID, String remarks) {
        //this.billID = billID;
        this.type = type;
        this.dateTime = dateTime;
//        this.category = category;
        this.money = money;
        this.cateID = cateID;
        this.remarks = remarks;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateTime() {
        return dateTime.toString();
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /*
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    */

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billID=" + billID +
                ", type='" + type + '\'' +
                ", dateTime=" + dateTime +
                ", money=" + money +
                ", cateID=" + cateID +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
