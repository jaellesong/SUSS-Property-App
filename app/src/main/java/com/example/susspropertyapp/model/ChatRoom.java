package com.example.susspropertyapp.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ChatRoom implements Serializable {

    public String agentId;
    public String userId;
    public String listing;
    public ArrayList<MyMsg> msgList = null;
    public String isSeller = "false";
    public String meeting = null;
    public String roomId;

    public void setChatRoom(String agentId, String userId, String listing) {
        this.agentId = agentId;
        this.userId = userId;
        this.listing = listing;
    }
    public String getAgentId(){
        return  agentId;
    }
    public String getUserId(){
        return  userId;
    }
    public String getListing(){
        return listing;
    }

    public void setMsgList(ArrayList<MyMsg> msgList){
        this.msgList = msgList;
    }
    public ArrayList<MyMsg> getMsgList(){
        return  msgList;
    }
    public void setIsSeller(String isSeller){
        this.isSeller = isSeller;
    }
    public String getIsSeller(){
       return  isSeller;
    }
    public void setMeeting(String meeting){
        this.meeting = meeting;
    }
    public String getMeeting(){
        return meeting;
    }

    public String getRoomId(){
        this.roomId = agentId+"_"+userId+"_"+listing;
        return roomId;
    }

}
