package com.qxkqyy.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qxkqyy.R;
import com.qxkqyy.activity.ScheduleActivity;
import com.qxkqyy.bean.AppointmentModel;

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
    private AppointmentCallback mAppointmentCallback;

    public AppointmentAdapter(Context context, List<AppointmentModel> appointmentList, AppointmentCallback appointmentCallback) {
        this.mContext = context;
        this.mAppointmentList = appointmentList;
        this.mAppointmentCallback = appointmentCallback;
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
                mAppointmentCallback.startActivityWithResult(mAppointmentList.get(position));
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

    public interface AppointmentCallback{
        void startActivityWithResult(AppointmentModel appointmentModel);
    }
}
