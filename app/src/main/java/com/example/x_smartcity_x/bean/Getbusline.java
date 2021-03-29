package com.example.x_smartcity_x.bean;

import java.io.Serializable;

public class Getbusline implements Serializable {

    /**
     * stepsId : 1
     * number : 2
     * name : 光谷金融街
     * id : 25
     */

    private int stepsId;
    private int number;
    private String name;
    private int id;

    public int getStepsId() {
        return stepsId;
    }

    public void setStepsId(int stepsId) {
        this.stepsId = stepsId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
