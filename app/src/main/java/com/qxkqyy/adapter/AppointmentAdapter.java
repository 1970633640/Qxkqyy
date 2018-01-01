package com.qxkqyy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qxkqyy.R;
import com.qxkqyy.ScheduleActivity;
import com.qxkqyy.bean.AppointmentModel;
import com.qxkqyy.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by puyihao on 2017/12/26.
 */

public class AppointmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_SEND = 100;
    private static final int TYPE_RECEIVE = 200;
    private int mLayoutAppointment = R.layout.item_appointment;
    private Context mContext;
    private List<AppointmentModel> mAppointmentList;

    public AppointmentAdapter(Context context, List<AppointmentModel> appointmentList) {
        this.mContext = context;
        this.mAppointmentList = appointmentList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mLayoutAppointment, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ((AppointmentViewHolder) holder).mTvContent.setText(mAppointmentList.get(position).getTime()+" "+mAppointmentList.get(position).getDoctor()+"医生");
        ((AppointmentViewHolder) holder).mTvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ScheduleActivity.class);
                intent.putExtra("doctor",mAppointmentList.get(position).getDoctor());
                intent.putExtra("time",mAppointmentList.get(position).getTime());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAppointmentList.size();
    }

    class AppointmentViewHolder extends RecyclerView.ViewHolder {
        TextView mTvContent;

        public AppointmentViewHolder(View itemView) {
            super(itemView);
            mTvContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
