package com.prototype.protoapp.stock.entity;


import java.sql.Timestamp;

public class Stock {
    private int id;
    private String category;
    private String title;
    private int quantity;
    private String ymd;
    private Timestamp update_datetime;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getYmd() {
        return ymd;
    }

    public void setYmd(String ymd) {
        this.ymd = ymd;
    }

    public Timestamp getUpdate_datetime() {
        return update_datetime;
    }

    public void setUpdate_datetime(Timestamp update_datetime) {
        this.update_datetime = update_datetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}