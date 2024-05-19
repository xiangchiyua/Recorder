package com.recorder.dal.bean;

public class Category {
    private int cateID;
    private String name;
    private String description;

    public Category(int cateID, String name, String description) {
        this.cateID = cateID;
        this.name = name;
        this.description = description;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cateID=" + cateID +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
