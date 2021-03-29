package com.example.x_smartcity_x.bean;

import java.io.Serializable;
public class Parkrecord implements Serializable {


    /**
     * entryTime : 2021-03-16 17:30:53.0
     * monetary : 80
     * parkName : 奥德乐停车场
     * id : 1
     * plateNumber : 鲁A10001
     * outTime : 2021-03-18 14:30:57.0
     */

    private String entryTime;
    private String monetary;
    private String parkName;
    private int id;
    private String plateNumber;
    private String outTime;

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getMonetary() {
        return monetary;
    }

    public void setMonetary(String monetary) {
        this.monetary = monetary;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }
}
