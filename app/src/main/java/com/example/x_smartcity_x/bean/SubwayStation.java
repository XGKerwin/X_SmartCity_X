package com.example.x_smartcity_x.bean;

import java.io.Serializable;

public class SubwayStation implements Serializable {


    /**
     * name : 积水潭
     * id : 1
     */

    private String name;
    private int id;

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
