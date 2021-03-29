package com.example.x_smartcity_x.bean;

import java.io.Serializable;

public class GetUserByUserId implements Serializable {


    /**
     * msg : 操作成功
     * code : 200
     * nickName : 张三
     * idCard : 37385938274921859
     * sex : 男
     * phonenumber : 19811816738
     * id : 1
     * avatar : http://192.168.155.208:8080/demo1/images/touxiang.jpg
     * userName : zhangsan
     * email : 2742837998@qq.com
     */

    private String msg;
    private String code;
    private String nickName;
    private String idCard;
    private String sex;
    private String phonenumber;
    private int id;
    private String avatar;
    private String userName;
    private String email;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
