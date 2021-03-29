package com.example.x_smartcity_x.bean;

import java.io.Serializable;

public class Gethospitalreservationuser implements Serializable {


    /**
     * patientName : 张三
     * typesId : 1
     * startime : 2021-03-18
     * moeny : 5
     * id : 1
     * divisionId : 耳鼻喉科
     * userId : 1
     */

    private String patientName;
    private String typesId;
    private String startime;
    private String moeny;
    private int id;
    private String divisionId;
    private String userId;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getTypesId() {
        return typesId;
    }

    public void setTypesId(String typesId) {
        this.typesId = typesId;
    }

    public String getStartime() {
        return startime;
    }

    public void setStartime(String startime) {
        this.startime = startime;
    }

    public String getMoeny() {
        return moeny;
    }

    public void setMoeny(String moeny) {
        this.moeny = moeny;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(String divisionId) {
        this.divisionId = divisionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
