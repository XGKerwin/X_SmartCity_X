package com.example.x_smartcity_x;

import android.app.Application;

public class App extends Application {

    public static String id;

    /**
     * {"patientName":"王五","divisionId":"胸外科","typesId":"1","moeny":"8","startime":"2021-3-11","userId":"3"}
     * @return
     */

    public static String patientName;
    public static String divisionId;
    public static String typesId;
    public static String moeny;

    public static String getPatientName() {
        return patientName;
    }

    public static void setPatientName(String patientName) {
        App.patientName = patientName;
    }

    public static String getDivisionId() {
        return divisionId;
    }

    public static void setDivisionId(String divisionId) {
        App.divisionId = divisionId;
    }

    public static String getTypesId() {
        return typesId;
    }

    public static void setTypesId(String typesId) {
        App.typesId = typesId;
    }

    public static String getMoeny() {
        return moeny;
    }

    public static void setMoeny(String moeny) {
        App.moeny = moeny;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        App.id = id;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

}
