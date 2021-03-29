package com.example.x_smartcity_x.bean;

import androidx.work.Data;

import java.io.Serializable;

public class Lists implements Serializable {


    /**
     * imgUrl : http://192.168.155.208:8080/demo1/images/yingdao1.jpg
     * display : N
     * id : 3
     * sort : 1
     * type : 47
     */

    private String imgUrl;
    private String display;
    private int id;
    private int sort;
    private String type;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
