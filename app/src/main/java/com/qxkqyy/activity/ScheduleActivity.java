package com.qxkqyy.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qxkqyy.R;
import com.qxkqyy.bean.AppointmentModel;
import com.qxkqyy.utils.RealmHelper;

import org.feezu.liuli.timeselector.TimeSelector;
import org.jetbrains.annotations.Nullable;

import io.realm.Realm;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/**
 * Created by puyihao on 2018/1/1.
 */

public class ScheduleActivity extends AppCompatActivity{
    Toolbar mToolbar;
    TextView mTvDoctor;
    TextView mTvDate;
    TextView mTvTime;
    Button mBtnModify;
    Button mBtnDelete;
    Button mBtnAddCalender;
    private String mTime="";
    private String mDoctor="";
    private TimeSelector mTimeSelector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_schedule);
        initWidget();
        initToolbar();
        initViewAndData();
        setOnClickListener();
    }

    private void initWidget(){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTvDoctor = (TextView) findViewById(R.id.tv_doctor);
        mTvDate = (TextView) findViewById(R.id.tv_date);
        mTvTime = (TextView) findViewById(R.id.tv_time);
        mBtnModify = (Button) findViewById(R.id.btn_modify);
        mBtnDelete = (Button) findViewById(R.id.btn_delete);
        mBtnAddCalender=(Button)findViewById(R.id.btn_add_calender);
    }

    private void initToolbar(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initViewAndData(){
        Intent intent = getIntent();
        mDoctor = intent.getStringExtra("doctor");
        mTime = intent.getStringExtra("time");
        mTvDoctor.setText(mDoctor+" 医生");
        String[] time = mTime.split(" ");
        mTvDate.setText(time[0]);
        mTvTime.setText(time[1]);
        mTimeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                String[] splitTime = time.split(" ");
                mTvDate.setText(splitTime[0]);
                mTvTime.setText(splitTime[1]);
                RealmHelper.getRealm(getApplicationContext()).setAppointment(new AppointmentModel(mDoctor, splitTime[0]+" "+splitTime[1]));
            }
        }, "2018-1-1 9:00", "2050-12-1 17:00","9:00","17:00");
    }

    private void setOnClickListener() {
        mBtnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimeSelector.show();
            }
        });
        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmHelper.getRealm(getApplicationContext()).removeAppointment(mDoctor, mTime);
                setResult(RESULT_OK);
                finish();

            }
        });
        mBtnAddCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.Events.TITLE, "把预约添加到日程表")
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, "预约的地点")
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, 1515114000000L)//unix时间戳毫秒
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, 1515115800000L);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });
    }
}
