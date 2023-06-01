package com.example.apachepoi;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class DB_Helper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "POI.db";
    public static final String TABLE1 = "Sales";
    public static final String TABLE2 = "Materials";

    public DB_Helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DB_Helper(Context context, String s, Object o, int i) {
        super(context.getApplicationContext(), DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        DB_Helper helper = null;
        db = helper.getWritableDatabase();
        db.execSQL(Structure_BBDD.SQL_CREATE_ENTRIES1);


    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        //db.execSQL(Structure_BBDD.SQL_DELETE_ENTRIES1);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public SQLiteDatabase getReadableDatabase(SQLiteDatabase db, String s) {
        db.execSQL("select * from " + TABLE1);
        db.execSQL("select * from " + TABLE2);
        s.getBytes(StandardCharsets.UTF_8).toString();
        return null;
    }

    public SQLiteDatabase getWriteableDatabase(SQLiteDatabase db, String s) {
        db.execSQL("select * from " + TABLE1);
        db.execSQL("select * from " + TABLE2);
        return null;

    }

    public Cursor getSTotal() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur5 = db.rawQuery("SELECT Description, Total FROM " + TABLE1, null);
        return cur5;
    }

    public Cursor exportAll() {
        ArrayList<Sales> data = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE1, null);

        return cur;

    }

    public SQLiteDatabase getWriteableDatabase() {
        return null;
    }

}

