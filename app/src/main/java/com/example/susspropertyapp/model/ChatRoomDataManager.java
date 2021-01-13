package com.example.susspropertyapp.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class ChatRoomDataManager {

    private SQLiteDatabase db;

    public static final String TABLE_ROW_ID = "_id";
    public static final String TABLE_ROW_AGENTID = "agentId";
    public static final String TABLE_ROW_USERID = "userId";
    public static final String TABLE_ROW_LISTINGNAME = "listing";
    public static final String TABLE_ROW_ISSELLER = "isSeller";
    public static final String  TABLE_ROW_MEETING= "meeting";
    public static final String TABLE_ROW_ROOMID = "roomId";

    private static final String DB_NAME = "chats_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "chats";


    public ChatRoomDataManager(Context context) {
        // Create an instance of our internal CustomSQLiteOpenHelper class
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
        // Get a writable database
        db = helper.getWritableDatabase();
    }

    // Here are all our helper methods
    public void insert(ChatRoom cr){
        // Add all the details to the table
        String query = "INSERT INTO " +
                TABLE_NAME + " (" +
                TABLE_ROW_AGENTID + ", " +
                TABLE_ROW_USERID + ", " +
                TABLE_ROW_LISTINGNAME + ", " +
                TABLE_ROW_ISSELLER + ", " +
                TABLE_ROW_MEETING + ", " +
                TABLE_ROW_ROOMID +
                ")VALUES (" +
                "'" + cr.getAgentId() + "'" + ", " +
                "'" + cr.getUserId() + "'" + ", " +
                "'" + cr.getListing() + "'" + ", " +
                "'" + cr.getIsSeller() + "'" + ", " +
                "'" + cr.getMeeting() + "'" + ", " +
                "'" + cr.getRoomId() + "'" + "); ";
        Log.i("insert() = ", query);
        db.execSQL(query);
    }

    public Cursor selectAll() {
        Cursor c = db.rawQuery("SELECT *" +" from " + TABLE_NAME, null);
        return c;
    }
    // Find a specific record
    public Cursor getAgent(String agentId) {
        String query = "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_AGENTID + ", " +
                TABLE_ROW_USERID + ", " +
                TABLE_ROW_LISTINGNAME + ", " +
                TABLE_ROW_ISSELLER + ", " +
                TABLE_ROW_MEETING + ", " +
                TABLE_ROW_ROOMID +
                " from " + TABLE_NAME +
                " WHERE " + TABLE_ROW_AGENTID + " = '" + agentId + "';";
        Log.i("searchRecord() =", query);
        Cursor c = db.rawQuery(query, null);
        return c;
    }


    // Find a specific record
    public Cursor getUser(String userId) {
        String query = "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_AGENTID + ", " +
                TABLE_ROW_USERID + ", " +
                TABLE_ROW_LISTINGNAME + ", " +
                TABLE_ROW_ISSELLER + ", " +
                TABLE_ROW_MEETING + ", " +
                TABLE_ROW_ROOMID +
                " from " + TABLE_NAME +
                " WHERE " + TABLE_ROW_USERID + " = '" + userId + "';";
        Log.i("searchRecord() =", query);
        Cursor c = db.rawQuery(query, null);
        return c;
    }
    // Find a specific record
    public Cursor getIsSeller(String agentId) {
        String query = "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_AGENTID + ", " +
                TABLE_ROW_USERID + ", " +
                TABLE_ROW_LISTINGNAME + ", " +
                TABLE_ROW_ISSELLER + ", " +
                TABLE_ROW_MEETING + ", " +
                TABLE_ROW_ROOMID +
                " from " + TABLE_NAME +
                " WHERE " + TABLE_ROW_AGENTID + " = '" + agentId + "'" +
                " AND " + TABLE_ROW_ISSELLER + " = '" + "true" + "';";
        Cursor c = db.rawQuery(query, null);
        return c;
    }
    // Find a specific record
    public Cursor getIsBuyer(String agentId) {
        String query = "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_AGENTID + ", " +
                TABLE_ROW_USERID + ", " +
                TABLE_ROW_LISTINGNAME + ", " +
                TABLE_ROW_ISSELLER + ", " +
                TABLE_ROW_MEETING + ", " +
                TABLE_ROW_ROOMID +
                " from " + TABLE_NAME +
                " WHERE " + TABLE_ROW_AGENTID + " = '" + agentId + "'" +
                " AND " + TABLE_ROW_ISSELLER + " = '" + "false" + "';";
        Cursor c = db.rawQuery(query, null);
        return c;
    }

    // Find a specific record
    public Cursor getChat(String roomId) {
        String query = "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_AGENTID + ", " +
                TABLE_ROW_USERID + ", " +
                TABLE_ROW_LISTINGNAME + ", " +
                TABLE_ROW_ISSELLER + ", " +
                TABLE_ROW_MEETING + ", " +
                TABLE_ROW_ROOMID +
                " from " + TABLE_NAME +
                " WHERE " + TABLE_ROW_ROOMID + " = '" + roomId + "';";
        Cursor c = db.rawQuery(query, null);
        return c;
    }

    public void setTableRowMeeting(String roomId, String meeting) {
        String query = "UPDATE " + TABLE_NAME +
                " SET " + TABLE_ROW_MEETING + " = '" + meeting + "' "+
                " WHERE " + TABLE_ROW_ROOMID + " = '" + roomId +  "';";
        db.execSQL(query);
    }

    public void setTableRowIsSeller(String roomId) {
        String query = "UPDATE " + TABLE_NAME +
                " SET " + TABLE_ROW_ISSELLER + " = '" + "true" + "' "+
                " WHERE " + TABLE_ROW_ROOMID + " = '" + roomId +  "';";
        db.execSQL(query);
    }

    // This class is created when our DataManager is initialized
    private class CustomSQLiteOpenHelper extends SQLiteOpenHelper {
        public CustomSQLiteOpenHelper(Context context) { super(context, DB_NAME, null, DB_VERSION);
        }
        // This method only runs the first time the database is created
        @Override
        public void onCreate(SQLiteDatabase db) {
            // Create a table for photos and all their details
            String newTableQueryString = "create table " + TABLE_NAME + " ("
                    + TABLE_ROW_ID + " integer primary key autoincrement not null," +
                    TABLE_ROW_AGENTID + " text not null," +
                    TABLE_ROW_USERID + " text not null," +
                    TABLE_ROW_LISTINGNAME + " text not null," +
                    TABLE_ROW_ISSELLER + " text not null," +
                    TABLE_ROW_MEETING + " text not null," +
                    TABLE_ROW_ROOMID + " text not null);";
            db.execSQL(newTableQueryString); }

        // This method only runs when we increment DB_VERSION
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    }

}

