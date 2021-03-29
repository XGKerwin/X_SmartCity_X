package com.example.x_smartcity_x.bean;

import java.io.Serializable;

public class GetNewsAll {


    /**
     * pressCategory : 时政
     * isRecommend : 1
     * viewsNumber : 500
     * updateTime : 2021-03-17 08:15:43.0
     * title : 外交部：将妥善安排驻休斯敦总领馆人员
     * userId : 1
     * content : 汪文斌：关于中国驻休斯敦总领馆馆员的有关情况，相信你已经从媒体上看到了。 我这里要说的是，对于中国驻休斯敦总领馆的人员，中方将会作出妥善的安排。
     * imgUrl : http://192.168.155.208:8080/demo1/images/newss1.jpg
     * createTime : 2021-03-17 08:15:40.0
     * theme : 国庆专题
     * id : 1
     * pressStatus : 0
     * likeNumber : 300
     */

    private String pressCategory;
    private int isRecommend;
    private int viewsNumber;
    private String updateTime;
    private String title;
    private int userId;
    private String content;
    private String imgUrl;
    private String createTime;
    private String theme;
    private int id;
    private String pressStatus;
    private int likeNumber;

    public String getPressCategory() {
        return pressCategory;
    }

    public void setPressCategory(String pressCategory) {
        this.pressCategory = pressCategory;
    }

    public int getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(int isRecommend) {
        this.isRecommend = isRecommend;
    }

    public int getViewsNumber() {
        return viewsNumber;
    }

    public void setViewsNumber(int viewsNumber) {
        this.viewsNumber = viewsNumber;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPressStatus() {
        return pressStatus;
    }

    public void setPressStatus(String pressStatus) {
        this.pressStatus = pressStatus;
    }

    public int getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(int likeNumber) {
        this.likeNumber = likeNumber;
    }
}
