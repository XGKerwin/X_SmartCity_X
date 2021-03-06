package com.example.x_smartcity_x.bean;

import java.io.Serializable;

public class GetActionCommitById implements Serializable {


    /**
     * num : 1
     * id : 1
     * username : tom
     * commitTime : 2020-10-01 10:00:00
     * commitContent : 12334567
     */

    private int num;
    private int id;
    private String username;
    private String commitTime;
    private String commitContent;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime = commitTime;
    }

    public String getCommitContent() {
        return commitContent;
    }

    public void setCommitContent(String commitContent) {
        this.commitContent = commitContent;
    }
}
