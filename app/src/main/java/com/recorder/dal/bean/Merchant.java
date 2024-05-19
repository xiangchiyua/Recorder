package com.recorder.dal.bean;

public class Merchant {
    private String name;
    private int cateID;

    public Merchant(String name, int cateID) {
        this.name = name;
        this.cateID = cateID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }
}
