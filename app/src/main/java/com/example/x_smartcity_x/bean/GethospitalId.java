package com.example.x_smartcity_x.bean;

import java.io.Serializable;

public class GethospitalId implements Serializable {


    /**
     * msg : 操作成功
     * brief : 中国人民解放军总医院（301医院）创建于1953年，是集医疗、保健、教学、科研于一体的大型现代化综合性医院，直属于中国人民解放军联勤保障部队。医院是中央重要保健基地，承担军委、总部等多个体系单位、官兵的医疗保健和各军区、军兵种转诊、后送的疑难病诊治任务。医院同时又是解放军医学院，以研究生教育为主，是全军唯所医院办学单位。
     * imgUrl : http://192.168.155.208:8080/demo1/images/hospital_301.png
     * code : 200
     * hospitalId : 1
     * level : 5
     * id : 1
     * hospitalName : 中国人民解放军总医院
     */

    private String msg;
    private String brief;
    private String imgUrl;
    private String code;
    private int hospitalId;
    private int level;
    private int id;
    private String hospitalName;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
}
