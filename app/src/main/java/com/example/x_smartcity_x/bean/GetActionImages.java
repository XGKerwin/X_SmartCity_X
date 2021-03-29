package com.example.x_smartcity_x.bean;

import java.io.Serializable;

public class GetActionImages implements Serializable {

    /**
     * id : 1
     * image : http://192.168.155.208:8080/mobileA/images/a1.jpg
     */

    private int id;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
