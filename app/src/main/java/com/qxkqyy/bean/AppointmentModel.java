package com.qxkqyy.bean;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by puyihao on 2018/1/1.
 */

public class AppointmentModel extends RealmObject {
    @PrimaryKey
    private String time;
    private String doctor;

    public AppointmentModel() {

    }

    public AppointmentModel(String doctor, String time) {
        this.time = time;
        this.doctor = doctor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
