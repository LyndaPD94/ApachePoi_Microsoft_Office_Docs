package com.example.apachepoi;

import android.content.Context;


public class Structure_BBDD {
    public Structure_BBDD(Context context){}
    public static final String DATABASE_NAME = "POI.db";
    public static final String TABLE1 = "Sales";
    public static final String COLUMNAID = "Trans_ID";
    public static final String COLUMNA2 = "Date";
    public static final String COLUMNA3 = "Description";
    public static final String COLUMNA4 = "Amount";
    public static final String COLUMNA5 = "Price";
    public static final String COLUMNA6 = "Total";
    public static final String COLUMNA7 = "Notes";

    public static final String TABLE2 = "Materials";
    public static final String COLUMNDID = "ID";
    public static final String COLUMND2 = "Material";
    public static final String COLUMND3 = "Humidity";
    public static final String COLUMND4 = "ST";
    public static final String COLUMND5 = "Carbon";
    public static final String COLUMND6 = "Nitrogen";
    public static final String COLUMND7 = "Phosphorus";




    public static final String SQL_CREATE_ENTRIES1 =

            " CREATE TABLE " + TABLE1 + " ( " +
                    Structure_BBDD.COLUMNAID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Structure_BBDD.COLUMNA2 + " TEXT,"+
                    Structure_BBDD.COLUMNA3 +" TEXT," +
                    Structure_BBDD.COLUMNA4 + " TEXT,"+
                    Structure_BBDD.COLUMNA5 + " TEXT,"+
                    Structure_BBDD.COLUMNA6 + " TEXT,"+
                    Structure_BBDD.COLUMNA7 + " TEXT "+ " )";

    public static final String SQL_DELETE_ENTRIES1 =
     "DROP TABLE IF EXISTS " + Structure_BBDD.TABLE1;

    public static final String SQL_CREATE_ENTRIES4=

            "CREATE TABLE " + Structure_BBDD.TABLE2 + " ( " +
                    Structure_BBDD.COLUMNDID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Structure_BBDD.COLUMND2 + " TEXT,"+
                    Structure_BBDD.COLUMND3 +" TEXT," +
                    Structure_BBDD.COLUMND4 + " TEXT,"+
                    Structure_BBDD.COLUMND5 + " TEXT,"+
                    Structure_BBDD.COLUMND6 + " TEXT,"+
                    Structure_BBDD.COLUMND7 + " TEXT "+ " )";

     public static final String SQL_DELETE_ENTRIES4 =
     "DROP TABLE IF EXISTS " + Structure_BBDD.TABLE2;


}
