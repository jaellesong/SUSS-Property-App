package com.example.susspropertyapp.model;

import java.io.Serializable;

public class MyMsg implements Serializable {
    private String user;
    private String msg;
    private String msgId;
    private String roomId;
    private String timestamp;

    public void setMsg(String roomId, String msgId, String user, String timestamp, String msg){
        this.roomId = roomId;
        this.msgId = msgId;
        this.user = user;
        this.timestamp = timestamp;
        this.msg = msg;
    }
    public String getRoomId(){
        return roomId;
    }
    public String getMsgId(){
        return msgId;
    }
    public String getUser(){
        return user;
    }
    public String getMsg(){
        return msg;
    }
    public String getTimestamp(){
        return timestamp;
    }
}

