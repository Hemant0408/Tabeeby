package com.tabeeby.doctor.database;

/**
 * Created by Z510 on 4/26/2016.
 */

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tabeeby.doctor.Models.Country;

import java.util.ArrayList;

public class DbClass extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Tabbeby";
    public static final int DATABASE_VERSION = 1;

    private final String DEBUG_TAG = "App";

    public SQLiteDatabase ourDatabase;

    // Table Name
    private final String TABLE_COUNTRY = "country";

    // Table Columns names
    private final String KEY_COUNTRY_ID = "country_id";
    private final String KEY_COUNTRY_NAME_ENGLISH = "country_name_english";
    private final String KEY_COUNTRY_NAME_ARABIC = "country_name_arabic";

    // DatabaseHandler
    private static DbClass sInstance;

    public DbClass(Context ourContext) {
        super(ourContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DbClass(Application context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DbClass getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new DbClass(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        Log.e("onCreate", "onCreate");
        try {

            String country = "CREATE TABLE " + TABLE_COUNTRY + "("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL,"
                    + KEY_COUNTRY_ID + " INTEGER,"
                    + KEY_COUNTRY_NAME_ENGLISH + " TEXT,"
                    + KEY_COUNTRY_NAME_ARABIC + " TEXT)";

            db.execSQL(country);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        Log.e("onUpgrade", "onUpgrade");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTRY);

        onCreate(db);
    }

    public DbClass open() {
        try {
            ourDatabase = this.getWritableDatabase();
            return this;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this;
    }

    public void insertCountryData(String country_id, String country_name, String country_name_arabic) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_COUNTRY_ID, country_id);
            contentValues.put(KEY_COUNTRY_NAME_ENGLISH, country_name);
            contentValues.put(KEY_COUNTRY_NAME_ARABIC, country_name_arabic);
            ourDatabase.insert(TABLE_COUNTRY, "id", contentValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Country> getCountryListEnglish() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_COUNTRY, null);
        res.moveToFirst();

        ArrayList<Country> onlineUsersArrayList = new ArrayList<>();
        Country obj_cart = null;

        while (res.isAfterLast() == false) {

            obj_cart = new Country();
            obj_cart.setId(res.getString(res.getColumnIndex(KEY_COUNTRY_ID)));
            obj_cart.setName(res.getString(res.getColumnIndex(KEY_COUNTRY_NAME_ENGLISH)));
            onlineUsersArrayList.add(obj_cart);
            res.moveToNext();
        }
        db.close();
        res.close();
        return onlineUsersArrayList;
    }

    public ArrayList<Country> getCountryListArabic() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_COUNTRY, null);
        res.moveToFirst();

        ArrayList<Country> onlineUsersArrayList = new ArrayList<>();
        Country obj_cart = null;

        while (res.isAfterLast() == false) {

            obj_cart = new Country();
            obj_cart.setId(res.getString(res.getColumnIndex(KEY_COUNTRY_ID)));
            obj_cart.setName(res.getString(res.getColumnIndex(KEY_COUNTRY_NAME_ARABIC)));
            onlineUsersArrayList.add(obj_cart);
            res.moveToNext();
        }
        db.close();
        res.close();
        return onlineUsersArrayList;
    }

    // Delete from table
    public void deleteData() {
        String sql = "delete from " + TABLE_COUNTRY;
        ourDatabase.execSQL(sql);
    }
}
