package com.example.x_smartcity_x.bean;

import java.io.Serializable;

public class Parklotlist implements Serializable {


    /**
     * imgUrl : http://192.168.155.208:8080/demo1/images/parkin_4.png
     * address : 大连市国际大厦 F1 楼
     * parkName : 国际大厦停车场
     * distance : 800
     * allPark : 200
     * rates : 6
     * priceCaps : 30
     * id : 1
     * vacancy : 30
     */

    private String imgUrl;
    private String address;
    private String parkName;
    private String distance;
    private String allPark;
    private String rates;
    private String priceCaps;
    private int id;
    private String vacancy;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getAllPark() {
        return allPark;
    }

    public void setAllPark(String allPark) {
        this.allPark = allPark;
    }

    public String getRates() {
        return rates;
    }

    public void setRates(String rates) {
        this.rates = rates;
    }

    public String getPriceCaps() {
        return priceCaps;
    }

    public void setPriceCaps(String priceCaps) {
        this.priceCaps = priceCaps;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }
}
