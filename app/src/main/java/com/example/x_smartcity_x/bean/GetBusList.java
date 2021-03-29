package com.example.x_smartcity_x.bean;

import java.io.Serializable;
public class GetBusList implements Serializable {


    /**
     * number : 1
     * price : 8
     * name : 一号线
     * end : 南湖大厦
     * startTime : 6：30
     * id : 1
     * endTime : 19：45
     * first : 光谷金融街
     * mileage : 20
     */

    private String number;
    private String price;
    private String name;
    private String end;
    private String startTime;
    private int id;
    private String endTime;
    private String first;
    private String mileage;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
}
