package com.example.susspropertyapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDataManager {

    private SQLiteDatabase db;

    public static final String TABLE_ROW_ID = "_id";
    public static final String TABLE_ROW_USERID = "userId";
    public static final String TABLE_ROW_EMAIL = "email";
    public static final String TABLE_ROW_PASSWORD = "password";
    public static final String TABLE_ROW_NAME = "name";
    public static final String  TABLE_ROW_ISAGENT= "isAgent";

    private static final String DB_NAME = "suss_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "users";


    public UserDataManager(Context context) {
        // Create an instance of our internal CustomSQLiteOpenHelper class
        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
        // Get a writable database
        db = helper.getWritableDatabase();
    }

    // Here are all our helper methods
    public void insert(String userId, String email, String password, String name, String isAgent){
        // Add all the details to the table
        String query = "INSERT INTO " +
                TABLE_NAME + " (" +
                TABLE_ROW_USERID + ", " +
                TABLE_ROW_EMAIL + ", " +
                TABLE_ROW_PASSWORD + ", " +
                TABLE_ROW_NAME + ", " +
                TABLE_ROW_ISAGENT + ") " +
                "VALUES (" +
                "'" + userId + "'" + ", " +
                "'" + email + "'" + ", " +
                "'" + password + "'" + ", " +
                "'" + name + "'" + ", " +
                "'" + isAgent + "'" + "); ";
        Log.i("insert() = ", query);
        db.execSQL(query);
    }

    // Find a specific record
    public Cursor searchUser(String email, String pw) {
        String query = "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_USERID + ", " +
                TABLE_ROW_EMAIL + ", " +
                TABLE_ROW_PASSWORD + ", " +
                TABLE_ROW_NAME + ", " +
                TABLE_ROW_ISAGENT +
                " from " + TABLE_NAME +
                " WHERE " + TABLE_ROW_EMAIL + " = '" + email + "'" +
                " AND " + TABLE_ROW_PASSWORD + " = '" + pw + "';";

        Log.i("searchRecord() = ", query);
        Cursor c = db.rawQuery(query, null);
        return c;
    }
    public Cursor getUserById(String id) {
        String query = "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_USERID + ", " +
                TABLE_ROW_EMAIL + ", " +
                TABLE_ROW_PASSWORD + ", " +
                TABLE_ROW_NAME + ", " +
                TABLE_ROW_ISAGENT +
                " from " + TABLE_NAME +
                " WHERE " + TABLE_ROW_USERID + " = '" + id + "';";

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
                    TABLE_ROW_USERID + " text not null," +
                    TABLE_ROW_EMAIL + " text not null," +
                    TABLE_ROW_PASSWORD + " text not null," +
                    TABLE_ROW_NAME + " text not null," +
                    TABLE_ROW_ISAGENT + " text not null);";
            db.execSQL(newTableQueryString); }

        // This method only runs when we increment DB_VERSION
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    }

}
