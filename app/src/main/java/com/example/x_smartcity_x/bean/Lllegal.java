package com.example.x_smartcity_x.bean;

import java.io.Serializable;

public class Lllegal implements Serializable {


    /**
     * illegalSites : 大连市万达广场
     * disposeState : 1
     * badTime : 2020-10-23 08:12:00.0
     * money : 200
     * noticeNo : 123456
     * licencePlate : 鲁123
     * engineNumber : 1234
     * deductMarks : 6
     * id : 1
     */

    private String illegalSites;
    private String disposeState;
    private String badTime;
    private String money;
    private String noticeNo;
    private String licencePlate;
    private String engineNumber;
    private String deductMarks;
    private int id;

    public String getIllegalSites() {
        return illegalSites;
    }

    public void setIllegalSites(String illegalSites) {
        this.illegalSites = illegalSites;
    }

    public String getDisposeState() {
        return disposeState;
    }

    public void setDisposeState(String disposeState) {
        this.disposeState = disposeState;
    }

    public String getBadTime() {
        return badTime;
    }

    public void setBadTime(String badTime) {
        this.badTime = badTime;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNoticeNo() {
        return noticeNo;
    }

    public void setNoticeNo(String noticeNo) {
        this.noticeNo = noticeNo;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getDeductMarks() {
        return deductMarks;
    }

    public void setDeductMarks(String deductMarks) {
        this.deductMarks = deductMarks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
