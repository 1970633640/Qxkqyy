package com.qxkqyy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.qxkqyy.adapter.MessageAdapter;
import com.qxkqyy.bean.MessageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by puyihao on 2017/12/26.
 */

public class BoardActivity extends AppCompatActivity {
    private MessageAdapter mAdapter;
    private RecyclerView mRvMessage;
    private EditText mEtContent;
    private ImageButton mBtnSend;
    private List<MessageBean> mMessageBeanList;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        initWidget();
        init();
    }

    public void initWidget() {
        mEtContent = (EditText) findViewById(R.id.et_content);
        mBtnSend = (ImageButton) findViewById(R.id.btn_send);
        mRvMessage = (RecyclerView) findViewById(R.id.rv_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sendMessage = mEtContent.getText().toString().trim();
                if (sendMessage != null && !sendMessage.equals("")) {
                    mMessageBeanList.add(new MessageBean(sendMessage, 100));//100:send, 200:receive
                    mEtContent.setText("");
                    mAdapter.notifyDataSetChanged();
                    mRvMessage.scrollToPosition(mAdapter.getItemCount() - 1);
                    //start chat robot:根据不同的文字来给出反馈效果
                    if(sendMessage.equals("你好")){
                        mMessageBeanList.add(new MessageBean("你好", 200));
                        mAdapter.notifyDataSetChanged();
                        mRvMessage.scrollToPosition(mAdapter.getItemCount() - 1);
                    }
                }
            }
        });
    }


    public void init() {
        mMessageBeanList = new ArrayList<>();
        mMessageBeanList.add(new MessageBean("你好", 100));//100:send, 200:receive
        mMessageBeanList.add(new MessageBean("你好", 200));
        mAdapter = new MessageAdapter(this, mMessageBeanList);
        mRvMessage.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRvMessage.setItemAnimator(new DefaultItemAnimator());
        mRvMessage.setAdapter(mAdapter);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.upin, R.anim.upout2);
    }
}
