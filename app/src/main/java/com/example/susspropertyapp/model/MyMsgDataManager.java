package com.example.susspropertyapp.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyMsgDataManager {

    private SQLiteDatabase db;

    public static final String TABLE_ROW_ID = "_id";
    public static final String TABLE_ROW_ROOMID = "roomId";
    public static final String TABLE_ROW_MSGID = "msgId";
    public static final String TABLE_ROW_USER = "user";
    public static final String TABLE_ROW_MSG = "msg";
    public static final String TABLE_ROW_TIMESTAMP = "timestamp";


    private static final String DB_NAME = "msg_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "msg";


    public MyMsgDataManager(Context context) {
        // Create an instance of our internal CustomSQLiteOpenHelper class
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
        // Get a writable database
        db = helper.getWritableDatabase();
    }

    // Here are all our helper methods
    public void insert(MyMsg m){
        // Add all the details to the table
        String query = "INSERT INTO " +
                TABLE_NAME + " (" +
                TABLE_ROW_ROOMID + ", " +
                TABLE_ROW_MSGID + ", " +
                TABLE_ROW_USER + ", " +
                TABLE_ROW_MSG + ", " +
                TABLE_ROW_TIMESTAMP +
                ") VALUES (" +
                "'" + m.getRoomId() + "'" + ", " +
                "'" + m.getMsgId() + "'" + ", " +
                "'" + m.getUser() + "'" + ", " +
                "'" + m.getMsg() + "'" + ", " +
                "'" + m.getTimestamp() + "'" + "); ";
        Log.i("insert() = ", query);
        db.execSQL(query);
    }

    // Find a specific record
    public Cursor getMsg(String msgId) {
        String query = "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_ROOMID + ", " +
                TABLE_ROW_MSGID + ", " +
                TABLE_ROW_USER + ", " +
                TABLE_ROW_MSG + ", " +
                TABLE_ROW_TIMESTAMP +
                " from " + TABLE_NAME +
                " WHERE " + TABLE_ROW_MSGID + " = '" + msgId + "';";

        Log.i("searchRecord() = ", query);
        Cursor c = db.rawQuery(query, null);
        return c;
    }
    // Find a specific record
    public Cursor getMsgByRoomId(String roomId) {
        String query = "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_ROOMID + ", " +
                TABLE_ROW_MSGID + ", " +
                TABLE_ROW_USER + ", " +
                TABLE_ROW_MSG + ", " +
                TABLE_ROW_TIMESTAMP +
                " from " + TABLE_NAME +
                " WHERE " + TABLE_ROW_ROOMID + " = '" + roomId + "';";

        Log.i("searchRecord() = ", query);
        Cursor c = db.rawQuery(query, null);
        return c;
    }

    public Cursor selectAll() {
        Cursor c = db.rawQuery("SELECT *" +" from " + TABLE_NAME, null);
        return c;
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
                    TABLE_ROW_ROOMID + " text not null," +
                    TABLE_ROW_MSGID + " text not null," +
                    TABLE_ROW_USER + " text not null," +
                    TABLE_ROW_MSG + " text not null," +
                    TABLE_ROW_TIMESTAMP + " text not null);";
            db.execSQL(newTableQueryString); }

        // This method only runs when we increment DB_VERSION
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    }

}
