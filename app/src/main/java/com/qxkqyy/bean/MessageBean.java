package com.qxkqyy.bean;

/**
 * Created by puyihao on 2017/12/26.
 */

public class MessageBean {
    private int type;
    private String content;

    public MessageBean(){

    }

    public MessageBean(String content, int type){
        this.type = type;
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
