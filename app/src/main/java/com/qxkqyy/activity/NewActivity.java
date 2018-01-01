package com.qxkqyy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.qxkqyy.R;
import com.qxkqyy.adapter.AppointmentAdapter;
import com.qxkqyy.bean.AppointmentModel;
import com.qxkqyy.utils.RealmHelper;

import org.feezu.liuli.timeselector.TimeSelector;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

public class NewActivity extends AppCompatActivity {
    private SingleDateAndTimePickerDialog.Builder mSingleDateAndTimePickerDialog;
    private Toolbar mToolbar;
    private EditText mEtDoctor;
    private TextView mTvDate;
    private Button mBtnAdd;
    private TimeSelector mTimeSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        setResult(RESULT_OK);
        initWidget();
        initDialog();
        initListener();
    }

    private void initWidget(){
        mEtDoctor = (EditText) findViewById(R.id.et_doctor);
        mTvDate = (TextView) findViewById(R.id.tv_date);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mBtnAdd = (Button) findViewById(R.id.btn_add);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initDialog(){
        mTimeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                mTvDate.setText(time);
            }
        }, "2018-1-1 9:00", "2050-12-1 17:00","9:00","17:00");

    }

    private void initListener(){
        mTvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimeSelector.show();
            }
        });
        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String doctor = mEtDoctor.getText().toString().trim();
                String date = mTvDate.getText().toString();
                if (doctor.equals("")){
                    Toast.makeText(getApplicationContext(), "请输入预约的医生", Toast.LENGTH_LONG).show();
                } else if (date.equals("点击选择时间")){
                    Toast.makeText(getApplicationContext(), "请选择预约的时间", Toast.LENGTH_LONG).show();
                } else {
                    RealmHelper.getRealm(getApplicationContext()).insertAppointment(new AppointmentModel(doctor, date));
                    Toast.makeText(getApplicationContext(),"新建成功",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}
