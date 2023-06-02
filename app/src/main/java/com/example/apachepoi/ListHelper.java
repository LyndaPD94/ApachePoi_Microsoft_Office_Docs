package com.example.apachepoi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class ListHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "POI.db";
    public static final String TABLE1 = "Sales";

    public ListHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Structure_BBDD.SQL_CREATE_ENTRIES1);
        db.execSQL(Structure_BBDD.SQL_CREATE_ENTRIES4);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Structure_BBDD.SQL_DELETE_ENTRIES4);
        db.execSQL(Structure_BBDD.SQL_CREATE_ENTRIES4);
        ContentValues values1 = new ContentValues();
        values1.put(Structure_BBDD.COLUMND2, "Horse Manure");
        values1.put(Structure_BBDD.COLUMND3, "73.6");
        values1.put(Structure_BBDD.COLUMND4, "26.4");
        values1.put(Structure_BBDD.COLUMND5, "42.52");
        values1.put(Structure_BBDD.COLUMND6, "1.69");
        values1.put(Structure_BBDD.COLUMND7, "0.83");
        db.insert(Structure_BBDD.TABLE2, null, values1);
        ContentValues values2 = new ContentValues();
        values2.put(Structure_BBDD.COLUMND2, "Cow Manure");
        values2.put(Structure_BBDD.COLUMND3, "86.2");
        values2.put(Structure_BBDD.COLUMND4, "13.8");
        values2.put(Structure_BBDD.COLUMND5, "39.99");
        values2.put(Structure_BBDD.COLUMND6, "1.99");
        values2.put(Structure_BBDD.COLUMND7, "0.65");
        db.insert(Structure_BBDD.TABLE2, null, values2);
        ContentValues values3 = new ContentValues();
        values3.put(Structure_BBDD.COLUMND2, "Sheep Manure");
        values3.put(Structure_BBDD.COLUMND3, "66.2");
        values3.put(Structure_BBDD.COLUMND4, "33.8");
        values3.put(Structure_BBDD.COLUMND5, "36.07");
        values3.put(Structure_BBDD.COLUMND6, "2.65");
        values3.put(Structure_BBDD.COLUMND7, "0.96");
        db.insert(Structure_BBDD.TABLE2, null, values3);
        ContentValues values4 = new ContentValues();
        values4.put(Structure_BBDD.COLUMND2, "Swine Manure");
        values4.put(Structure_BBDD.COLUMND3, "65.5");
        values4.put(Structure_BBDD.COLUMND4, "34.5");
        values4.put(Structure_BBDD.COLUMND5, "45.89");
        values4.put(Structure_BBDD.COLUMND6, "4.10");
        values4.put(Structure_BBDD.COLUMND7, "2.02");
        db.insert(Structure_BBDD.TABLE2, null, values4);
        ContentValues values5 = new ContentValues();
        values5.put(Structure_BBDD.COLUMND2, "Fowl Manure");
        values5.put(Structure_BBDD.COLUMND3, "72.3");
        values5.put(Structure_BBDD.COLUMND4, "27.7");
        values5.put(Structure_BBDD.COLUMND5, "31.03");
        values5.put(Structure_BBDD.COLUMND6, "4.83");
        values5.put(Structure_BBDD.COLUMND7, "1.30");
        db.insert(Structure_BBDD.TABLE2, null, values5);
        ContentValues values6 = new ContentValues();
        values6.put(Structure_BBDD.COLUMND2, "Hay");
        values6.put(Structure_BBDD.COLUMND3, "10.9");
        values6.put(Structure_BBDD.COLUMND4, "89.1");
        values6.put(Structure_BBDD.COLUMND5, "40.49");
        values6.put(Structure_BBDD.COLUMND6, "0.90");
        values6.put(Structure_BBDD.COLUMND7, "0.11");
        db.insert(Structure_BBDD.TABLE2, null, values6);
        ContentValues values7 = new ContentValues();
        values7.put(Structure_BBDD.COLUMND2, "Coconut Fibre");
        values7.put(Structure_BBDD.COLUMND3, "30.50");
        values7.put(Structure_BBDD.COLUMND4, "69.50");
        values7.put(Structure_BBDD.COLUMND5, "47.79");
        values7.put(Structure_BBDD.COLUMND6, "0.67");
        values7.put(Structure_BBDD.COLUMND7, "0.09");
        db.insert(Structure_BBDD.TABLE2, null, values7);
        ContentValues values8 = new ContentValues();
        values8.put(Structure_BBDD.COLUMND2, "Sawdust");
        values8.put(Structure_BBDD.COLUMND3, "57.2");
        values8.put(Structure_BBDD.COLUMND4, "42.8");
        values8.put(Structure_BBDD.COLUMND5, "47.41");
        values8.put(Structure_BBDD.COLUMND6, "0.49");
        values8.put(Structure_BBDD.COLUMND7, "0.04");
        db.insert(Structure_BBDD.TABLE2, null, values8);
        ContentValues values9 = new ContentValues();
        values9.put(Structure_BBDD.COLUMND2, "Banana Field Remains");
        values9.put(Structure_BBDD.COLUMND3, "92.8");
        values9.put(Structure_BBDD.COLUMND4, "17.2");
        values9.put(Structure_BBDD.COLUMND5, "37.17");
        values9.put(Structure_BBDD.COLUMND6, "1.27");
        values9.put(Structure_BBDD.COLUMND7, "0.15");
        db.insert(Structure_BBDD.TABLE2, null, values9);
        ContentValues values10 = new ContentValues();
        values10.put(Structure_BBDD.COLUMND2, "Dry leaves");
        values10.put(Structure_BBDD.COLUMND3, "10.8");
        values10.put(Structure_BBDD.COLUMND4, "89.20");
        values10.put(Structure_BBDD.COLUMND5, "43.41");
        values10.put(Structure_BBDD.COLUMND6, "1.11");
        values10.put(Structure_BBDD.COLUMND7, "0.09");
        db.insert(Structure_BBDD.TABLE2, null, values10);
        ContentValues values11 = new ContentValues();
        values11.put(Structure_BBDD.COLUMND2, "Freshly Cut Grass");
        values11.put(Structure_BBDD.COLUMND3, "87.60");
        values11.put(Structure_BBDD.COLUMND4, "12.40");
        values11.put(Structure_BBDD.COLUMND5, "39.16");
        values11.put(Structure_BBDD.COLUMND6, "2.87");
        values11.put(Structure_BBDD.COLUMND7, "0.36");
        db.insert(Structure_BBDD.TABLE2, null, values11);


    }
    public Cursor exportAll() {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur=db.rawQuery("SELECT * FROM "+ TABLE1,null);
        return cur;
    }
}

