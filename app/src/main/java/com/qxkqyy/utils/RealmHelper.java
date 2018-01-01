package com.qxkqyy.utils;

import android.content.Context;

import com.qxkqyy.bean.AppointmentModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by puyihao on 2018/1/1.
 */

public class RealmHelper {

    private static final String DB_NAME = "mAppointment.db";

    public static RealmHelper realmHelper=null;

    private Realm mRealm;

    private RealmHelper(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .schemaVersion(0) //版本号
                .deleteRealmIfMigrationNeeded()
                .name(DB_NAME)
                .build();
        mRealm = Realm.getInstance(config);
    }

    public static RealmHelper getRealm(Context context) {
        if (realmHelper == null) {
            realmHelper = new RealmHelper(context);
        }
            return realmHelper;

    }

    public void insertAppointment(AppointmentModel appointmentModel) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(appointmentModel);
        mRealm.commitTransaction();
    }

    public void setAppointment(AppointmentModel appointmentModel) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(appointmentModel);
        mRealm.commitTransaction();
    }


    public List<AppointmentModel> getAppointment() {
        RealmResults<AppointmentModel> results = mRealm.where(AppointmentModel.class).findAll().sort("time");
        if (results != null) {
            List<AppointmentModel> list = mRealm.copyFromRealm(results);
            return list;
        } else {
            return null;
        }
    }

    public void clear() {
        mRealm.beginTransaction();
        mRealm.deleteAll();
        mRealm.commitTransaction();
    }

    public void removeAppointment(String doctor, String time) {
        AppointmentModel matchModel = mRealm.where(AppointmentModel.class).equalTo("doctor", doctor).equalTo("time", time).findFirst();
        if (matchModel != null) {
            mRealm.beginTransaction();
            matchModel.deleteFromRealm();
            mRealm.commitTransaction();
        }
    }
}

