package com.qxkqyy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qxkqyy.R;
import com.qxkqyy.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by puyihao on 2017/12/26.
 */

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_SEND = 100;
    private static final int TYPE_RECEIVE = 200;
    private int mLayoutSend = R.layout.item_send;
    private int mLayoutReceive = R.layout.item_receive;
    private Context mContext;
    private List<MessageBean> mMessageList;

    public MessageAdapter(Context context, List<MessageBean> messageBeans) {
        this.mContext = context;
        this.mMessageList = messageBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_SEND) {
            View view = LayoutInflater.from(mContext).inflate(mLayoutSend, parent, false);
            return new MessageSendViewHolder(view);
        } else if (viewType == TYPE_RECEIVE) {
            View view = LayoutInflater.from(mContext).inflate(mLayoutReceive, parent, false);
            return new MessageReceiveViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case TYPE_SEND:
                MessageSendViewHolder messageSendViewHolder = (MessageSendViewHolder) holder;
                ((MessageSendViewHolder) holder).mTvContent.setText(mMessageList.get(position).getContent());
                break;
            case TYPE_RECEIVE:
                MessageReceiveViewHolder messageReceiveViewHolder = (MessageReceiveViewHolder) holder;
                ((MessageReceiveViewHolder) holder).mTvContent.setText(mMessageList.get(position).getContent());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mMessageList.get(position).getType();
    }

    class MessageSendViewHolder extends RecyclerView.ViewHolder {
        TextView mTvContent;

        public MessageSendViewHolder(View itemView) {
            super(itemView);
            mTvContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }

    class MessageReceiveViewHolder extends RecyclerView.ViewHolder {
        TextView mTvContent;

        public MessageReceiveViewHolder(View itemView) {
            super(itemView);
            mTvContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
