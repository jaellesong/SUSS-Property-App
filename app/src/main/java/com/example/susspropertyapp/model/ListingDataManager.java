package com.example.susspropertyapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ListingDataManager {
    private SQLiteDatabase db;

    public static final String TABLE_ROW_ID = "_id";
    public static final String TABLE_ROW_TITLE = "title";
    public static final String TABLE_ROW_ADDRESS = "address";
    public static final String TABLE_ROW_LATITUDE = "latitude";
    public static final String TABLE_ROW_LONGITUDE = "longitude";
    public static final String TABLE_ROW_DESCRIPTION = "description";
    public static final String TABLE_ROW_TYPE = "type";
    public static final String TABLE_ROW_BEDROOMS = "bedrooms";
    public static final String TABLE_ROW_BATHROOMS = "bathrooms";
    public static final String TABLE_ROW_PRICE = "price";
    public static final String TABLE_ROW_AREA = "area";
    public static final String TABLE_ROW_YEAR = "year";
    public static final String TABLE_ROW_TENURE = "tenure";
    public static final String TABLE_ROW_STATUS = "status";
    public static final String TABLE_ROW_KEYWORDS = "keywords";
    public static final String TABLE_ROW_AGENT = "agent";
    public static final String TABLE_ROW_SOLD = "sold";

    private static final String DB_NAME = "suss_properties";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "properties";

    public ListingDataManager(Context context) {
        ListingDataManager.CustomSQLiteOpenHelper helper = new ListingDataManager.CustomSQLiteOpenHelper(context);
        db = helper.getWritableDatabase();
    }

//        public void insert(String title, String address, String latitude, String longitude,
//                String description, String type, String bedrooms, String bathrooms,
//                String price, String area, String year, String tenure,
//                String status, String keywords){

    public void insert(Listing listing){
        String query = "INSERT INTO " +
                TABLE_NAME + " (" +
                TABLE_ROW_TITLE + ", " +
                TABLE_ROW_ADDRESS + ", " +
                TABLE_ROW_LATITUDE + ", " +
                TABLE_ROW_LONGITUDE + ", " +
                TABLE_ROW_DESCRIPTION + ", " +
                TABLE_ROW_TYPE + ", " +
                TABLE_ROW_BEDROOMS + ", " +
                TABLE_ROW_BATHROOMS + ", " +
                TABLE_ROW_PRICE + ", " +
                TABLE_ROW_AREA + ", " +
                TABLE_ROW_YEAR + ", " +
                TABLE_ROW_TENURE + ", " +
                TABLE_ROW_STATUS + ", " +
                TABLE_ROW_KEYWORDS + ", " +
                TABLE_ROW_AGENT + ", " +
                TABLE_ROW_SOLD +
                ") VALUES (" +
                "'" + listing.getTitle() + "'" + ", " +
                "'" + listing.getAddress() + "'" + ", " +
                "'" + listing.getLatitude() + "'" + ", " +
                "'" + listing.getLongitude() + "'" + ", " +
                "'" + listing.getDescription() + "'" + ", " +
                "'" + listing.getType() + "'" + ", " +
                "'" + listing.getBedrooms() + "'" + ", " +
                "'" + listing.getBathrooms() + "'" + ", " +
                "'" + listing.getPrice() + "'" + ", " +
                "'" + listing.getArea() + "'" + ", " +
                "'" + listing.getYear() + "'" + ", " +
                "'" + listing.getTenure() + "'" + ", " +
                "'" + listing.getStatus() + "'" + ", " +
                "'" + listing.getKeywords() + "'" + ", " +
                "'" + listing.getAgentId() + "'" + ", " +
                "'" + listing.getIsSold() + "'" + "); ";
        db.execSQL(query);
    }

    public Cursor selectAll() {
        Cursor c = db.rawQuery("SELECT *" +" from " + TABLE_NAME, null);
        return c;
    }

    // Check if record exists
    public Cursor searchRecord(String title) {
        String query = "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_TITLE + ", " +
                TABLE_ROW_ADDRESS + ", " +
                TABLE_ROW_LATITUDE + ", " +
                TABLE_ROW_LONGITUDE + ", " +
                TABLE_ROW_DESCRIPTION + ", " +
                TABLE_ROW_TYPE + ", " +
                TABLE_ROW_BEDROOMS + ", " +
                TABLE_ROW_BATHROOMS + ", " +
                TABLE_ROW_PRICE + ", " +
                TABLE_ROW_AREA + ", " +
                TABLE_ROW_YEAR + ", " +
                TABLE_ROW_TENURE + ", " +
                TABLE_ROW_STATUS + ", " +
                TABLE_ROW_KEYWORDS + ", " +
                TABLE_ROW_AGENT + ", " +
                TABLE_ROW_SOLD +
                " from " + TABLE_NAME +
                " WHERE " + TABLE_ROW_TITLE + " = '" + title +  "';";

//        Log.i("searchRecord() = ", query);
        Cursor c = db.rawQuery(query, null);
        return c;
    }

    // Check if record is Sold
    public Cursor searchSold(String title) {
        String query = "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_TITLE + ", " +
                TABLE_ROW_ADDRESS + ", " +
                TABLE_ROW_LATITUDE + ", " +
                TABLE_ROW_LONGITUDE + ", " +
                TABLE_ROW_DESCRIPTION + ", " +
                TABLE_ROW_TYPE + ", " +
                TABLE_ROW_BEDROOMS + ", " +
                TABLE_ROW_BATHROOMS + ", " +
                TABLE_ROW_PRICE + ", " +
                TABLE_ROW_AREA + ", " +
                TABLE_ROW_YEAR + ", " +
                TABLE_ROW_TENURE + ", " +
                TABLE_ROW_STATUS + ", " +
                TABLE_ROW_KEYWORDS + ", " +
                TABLE_ROW_AGENT + ", " +
                TABLE_ROW_SOLD +
                " from " + TABLE_NAME +
                " WHERE " + TABLE_ROW_SOLD + " = '" + "true'" +
                " AND " + TABLE_ROW_TITLE + " = '" + title +  "';";

//        Log.i("searchRecord() = ", query);
        Cursor c = db.rawQuery(query, null);
        return c;
    }

    // Check if record is under agent
    public Cursor searchAgent(String agent) {
        String query = "SELECT " +
                TABLE_ROW_ID + ", " +
                TABLE_ROW_TITLE + ", " +
                TABLE_ROW_ADDRESS + ", " +
                TABLE_ROW_LATITUDE + ", " +
                TABLE_ROW_LONGITUDE + ", " +
                TABLE_ROW_DESCRIPTION + ", " +
                TABLE_ROW_TYPE + ", " +
                TABLE_ROW_BEDROOMS + ", " +
                TABLE_ROW_BATHROOMS + ", " +
                TABLE_ROW_PRICE + ", " +
                TABLE_ROW_AREA + ", " +
                TABLE_ROW_YEAR + ", " +
                TABLE_ROW_TENURE + ", " +
                TABLE_ROW_STATUS + ", " +
                TABLE_ROW_KEYWORDS + ", " +
                TABLE_ROW_AGENT + ", " +
                TABLE_ROW_SOLD +
                " from " + TABLE_NAME +
                " WHERE " + TABLE_ROW_AGENT + " = '" + agent +  "';";

//        Log.i("searchRecord() = ", query);
        Cursor c = db.rawQuery(query, null);
        return c;
    }

    // Delete a record
    public void delete(String id){
        // Delete the details from the table if already exists
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + TABLE_ROW_ID + " = '" + id + "';";
//        Log.i("delete() = ", query);
        db.execSQL(query);
    }


    // set Sold
    public void setTableRowSold(String title) {
        String query = "UPDATE " + TABLE_NAME +
                " SET " + TABLE_ROW_SOLD + " = '" + "true" + "' "+
                " WHERE " + TABLE_ROW_TITLE + " = '" + title +  "';";
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
                    TABLE_ROW_TITLE + " text not null," +
                    TABLE_ROW_ADDRESS + " text not null," +
                    TABLE_ROW_LATITUDE + " text not null," +
                    TABLE_ROW_LONGITUDE + " text not null," +
                    TABLE_ROW_DESCRIPTION + " text not null," +
                    TABLE_ROW_TYPE + " text not null," +
                    TABLE_ROW_BEDROOMS + " text not null," +
                    TABLE_ROW_BATHROOMS + " text not null," +
                    TABLE_ROW_PRICE + " text not null," +
                    TABLE_ROW_AREA + " text not null," +
                    TABLE_ROW_YEAR + " text not null," +
                    TABLE_ROW_TENURE + " text not null," +
                    TABLE_ROW_STATUS + " text not null," +
                    TABLE_ROW_KEYWORDS + " text not null," +
                    TABLE_ROW_AGENT + " text not null," +
                    TABLE_ROW_SOLD + " text not null );" ;
            db.execSQL(newTableQueryString); }

        // This method only runs when we increment DB_VERSION
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }



}
