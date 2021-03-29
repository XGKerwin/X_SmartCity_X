package com.example.x_smartcity_x.bean;

import java.io.Serializable;

public class GetOrderForm implements Serializable {

    /**
     * orderType : 食品
     * orderTime : 2021-03-19 08:02:48.0
     * orderNum : 20210001
     * orderStatus : 已支付
     * shopName : 德职地超
     * id : 1
     */

    private String orderType;
    private String orderTime;
    private String orderNum;
    private String orderStatus;
    private String shopName;
    private int id;

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
