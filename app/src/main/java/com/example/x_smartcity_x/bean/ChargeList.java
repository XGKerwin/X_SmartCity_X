package com.example.x_smartcity_x.bean;

import java.io.Serializable;
public class ChargeList implements Serializable {


    /**
     * id : 11
     * userid : 2
     * groupId : 2
     * type : 水费
     * accountId : 12399999
     * accountAddress : 3-5-1
     * banlance : 100
     * cost : 20
     * unit : 个人
     */

    private int id;
    private String userid;
    private int groupId;
    private String type;
    private String accountId;
    private String accountAddress;
    private int banlance;
    private int cost;
    private String unit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }

    public int getBanlance() {
        return banlance;
    }

    public void setBanlance(int banlance) {
        this.banlance = banlance;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
