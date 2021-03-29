package com.example.x_smartcity_x.bean;

import java.io.Serializable;

public class SubwayStationRoute implements Serializable {


    /**
     * currentName : 建国门
     * lineId : 1
     * lineName : 地铁 2 号线(内环(积水潭-积水潭))
     * nextName : 北京站
     * reachTime : 2
     */

    private String currentName;
    private int lineId;
    private String lineName;
    private String nextName;
    private int reachTime;

    public String getCurrentName() {
        return currentName;
    }

    public void setCurrentName(String currentName) {
        this.currentName = currentName;
    }

    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getNextName() {
        return nextName;
    }

    public void setNextName(String nextName) {
        this.nextName = nextName;
    }

    public int getReachTime() {
        return reachTime;
    }

    public void setReachTime(int reachTime) {
        this.reachTime = reachTime;
    }
}
