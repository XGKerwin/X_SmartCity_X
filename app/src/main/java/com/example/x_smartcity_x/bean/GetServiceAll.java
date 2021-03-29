package com.example.x_smartcity_x.bean;

public class GetServiceAll {


    /**
     * serviceType : 2
     * imgUrl : http://192.168.155.208:8080/demo1/images/tubiao1.png
     * isRecommend : 0
     * createTime : 2021-03-16 17:33:47.0
     * link : https://www.bilibili.com/
     * updateTime : 2021-03-16 17:33:49.0
     * id : 1
     * serviceName : 便民服务
     */

    private int serviceType;
    private String imgUrl;
    private int isRecommend;
    private String createTime;
    private String link;
    private String updateTime;
    private int id;
    private String serviceName;

    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) {
        this.serviceType = serviceType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(int isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
