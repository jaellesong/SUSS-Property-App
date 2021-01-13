package com.example.susspropertyapp.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AgentDataManager {
    private SQLiteDatabase db;

    private static final String DB_NAME = "suss_agents";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "agents";

    public static final String TABLE_ROW_ID = "_id";
    public static final String TABLE_ROW_NAME = "name";
    public static final String TABLE_ROW_LICENSE = "license";
    public static final String TABLE_ROW_RATING = "rating";
    public static final String TABLE_ROW_BIO = "bio";

    public AgentDataManager(Context context) {
        AgentDataManager.CustomSQLiteOpenHelper helper = new AgentDataManager.CustomSQLiteOpenHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert(Agent agent){
        String query = "INSERT INTO " +
                TABLE_NAME + " (" +
                TABLE_ROW_NAME + ", " +
                TABLE_ROW_LICENSE + ", " +
                TABLE_ROW_RATING + ", " +
                TABLE_ROW_BIO +
                ") VALUES (" +
                "'" + agent.getName() + "'" + ", " +
                "'" + agent.getId() + "'" + ", " +
                "'" + agent.getRating() + "'" + ", " +
                "'" + agent.getBio() + "'" + "); ";
        db.execSQL(query);
    }

    public Cursor selectAll() {
        Cursor c = db.rawQuery("SELECT *" +" from " + TABLE_NAME, null);
        return c;
    }

    // Find a specific record
    public Cursor searchRecord(String id) {
        String query = "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_NAME + ", " +
                TABLE_ROW_LICENSE + ", " +
                TABLE_ROW_RATING + ", " +
                TABLE_ROW_BIO +
                " from " + TABLE_NAME +
                " WHERE " + TABLE_ROW_LICENSE + " = '" + id +  "';";

        Cursor c = db.rawQuery(query, null);
        return c;
    }


    // Update bio
    public void updateAgentBio(String id, String bio) {
        String query = "UPDATE " + TABLE_NAME +
                " SET " + TABLE_ROW_BIO + " = '" + bio + "' "+
                " WHERE " + TABLE_ROW_LICENSE + " = '" + id +  "';";
        db.execSQL(query);
    }


    // Delete a record
    public void delete(String id){
        // Delete the details from the table if already exists
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + TABLE_ROW_ID + " = '" + id + "';";
        Log.i("delete() = ", query);
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
            String newTableQueryString = "create table IF NOT EXISTS " + TABLE_NAME + " (" +
                    TABLE_ROW_ID + " integer primary key autoincrement not null," +
                    TABLE_ROW_NAME + " text not null," +
                    TABLE_ROW_LICENSE + " text not null," +
                    TABLE_ROW_RATING + " text not null," +
                    TABLE_ROW_BIO + " text not null );" ;
            db.execSQL(newTableQueryString); }

        // This method only runs when we increment DB_VERSION
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
