package com.example.x_smartcity_x.bean;

import java.io.Serializable;

public class GetCommentByPressId {


    /**
     * createTime : 2021-03-17 20:50:19.0
     * nickName : 张三
     * pressId : 1
     * id : 1
     * userName : zhangsan
     * userId : 1
     * content : 祖国美好
     */

    private String createTime;
    private String nickName;
    private int pressId;
    private int id;
    private String userName;
    private int userId;
    private String content;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getPressId() {
        return pressId;
    }

    public void setPressId(int pressId) {
        this.pressId = pressId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
