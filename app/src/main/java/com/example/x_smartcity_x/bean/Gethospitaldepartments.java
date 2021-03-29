package com.example.x_smartcity_x.bean;

import java.io.Serializable;

public class Gethospitaldepartments implements Serializable {

    /**
     * money : 6
     * id : 1
     * tag : MRI，CT，ECT，PETCT，脑电图
     * categoryName : 神经内科
     * did : 1
     * desc : 神经内科是关于神经方面的二级学科。不属于内科概念。主要收治脑血管疾病（脑梗塞、脑出血）、偏头痛、脑部炎症性疾病（脑炎、脑膜炎）、脊髓炎、癫痫、痴呆、代谢病和遗传倾向疾病、三叉神经痛、坐骨神经病、周围神经病及重症肌无力等。
     */

    private String money;
    private int id;
    private String tag;
    private String categoryName;
    private String did;
    private String desc;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
