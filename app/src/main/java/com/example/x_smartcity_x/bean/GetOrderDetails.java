package com.example.x_smartcity_x.bean;

import java.io.Serializable;

public class GetOrderDetails implements Serializable {

    /**
     * commodityNumber : 1
     * id : 8
     * commodityPrice : 1000
     * commodityName : 手机
     */

    private int commodityNumber;
    private int id;
    private int commodityPrice;
    private String commodityName;

    public int getCommodityNumber() {
        return commodityNumber;
    }

    public void setCommodityNumber(int commodityNumber) {
        this.commodityNumber = commodityNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(int commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
}
