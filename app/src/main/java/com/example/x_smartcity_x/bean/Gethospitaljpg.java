package com.example.x_smartcity_x.bean;

import java.io.Serializable;

public class Gethospitaljpg implements Serializable {

    /**
     * imgUrl : http://192.168.155.208:8080/demo1/images/hospital_301.png
     * hospitalId : 1
     */

    private String imgUrl;
    private int hospitalId;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }
}
