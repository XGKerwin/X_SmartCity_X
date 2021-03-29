package com.example.x_smartcity_x.bean;

import java.io.Serializable;

public class GetbusOrder implements Serializable {


    /**
     * path : 一号线
     * price : 8
     * start : 泰德大厦
     * end : 大连北站
     * userTel : 12345611
     * id : 1
     * userid : 1
     * status : 1
     * username : 张三
     */

    private String path;
    private String price;
    private String start;
    private String end;
    private String userTel;
    private int id;
    private String userid;
    private String status;
    private String username;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
