package com.example.apachepoi;

import android.content.Context;


public class Structure_BBDD {
    public Structure_BBDD(Context context){}
    public static final String DATABASE_NAME = "IER.db";
    public static final String TABLE1 = "Sales";
    public static final String COLUMNAID = "Trans_ID";
    public static final String COLUMNA2 = "Date";
    public static final String COLUMNA3 = "Description";
    public static final String COLUMNA4 = "Amount";
    public static final String COLUMNA5 = "Price";
    public static final String COLUMNA6 = "Total";
    public static final String COLUMNA7 = "Notes";

    public static final String TABLE2 = "Expenditure";
    public static final String COLUMNBID = "Trans_ID";
    public static final String COLUMNB2 = "Date";
    public static final String COLUMNB3 = "Description";
    public static final String COLUMNB4 = "Amount";
    public static final String COLUMNB5 = "Price";
    public static final String COLUMNB6 = "Total";
    public static final String COLUMNB7 = "Notes";

    public static final String TABLE3 = "Records";
    public static final String COLUMNCID = "ID";
    public static final String COLUMNC2 = "Date";
    public static final String COLUMNC3 = "Description";
    public static final String COLUMNC4 = "Notes";

    public static final String TABLE4 = "Materials";
    public static final String COLUMNDID = "ID";
    public static final String COLUMND2 = "Material";
    public static final String COLUMND3 = "Humidity";
    public static final String COLUMND4 = "ST";
    public static final String COLUMND5 = "Carbon";
    public static final String COLUMND6 = "Nitrogen";
    public static final String COLUMND7 = "Phosphorus";

    public static final String TABLE5 = "OMaterials";
    public static final String COLUMNEID = "ID";
    public static final String COLUMNE2 = "Material";
    public static final String COLUMNE3 = "N";
    public static final String COLUMNE4 = "P";
    public static final String COLUMNE5 = "K";


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

    public static final String SQL_CREATE_ENTRIES2=

            "CREATE TABLE " + Structure_BBDD.TABLE2+ " ( " +
                    Structure_BBDD.COLUMNBID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    Structure_BBDD.COLUMNB2 + " TEXT,"+
                    Structure_BBDD.COLUMNB3 +" TEXT," +
                    Structure_BBDD.COLUMNB4 + " TEXT,"+
                    Structure_BBDD.COLUMNB5 + " TEXT,"+
                    Structure_BBDD.COLUMNB6 + " TEXT,"+
                    Structure_BBDD.COLUMNB7 + " TEXT "+ " )";

     public static final String SQL_DELETE_ENTRIES2 =
     "DROP TABLE IF EXISTS " + Structure_BBDD.TABLE2;

    public static final String SQL_CREATE_ENTRIES3 =

            " CREATE TABLE " + Structure_BBDD.TABLE3 + " ( " +
                    Structure_BBDD.COLUMNCID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    Structure_BBDD.COLUMNC2 + " TEXT,"+
                    Structure_BBDD.COLUMNC3 +" TEXT," +
                    Structure_BBDD.COLUMNC4 + " TEXT "+ " )";

        public static final String SQL_DELETE_ENTRIES3 =
         "DROP TABLE IF EXISTS " + Structure_BBDD.TABLE3;

    public static final String SQL_CREATE_ENTRIES4=

            "CREATE TABLE " + Structure_BBDD.TABLE4 + " ( " +
                    Structure_BBDD.COLUMNDID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Structure_BBDD.COLUMND2 + " TEXT,"+
                    Structure_BBDD.COLUMND3 +" TEXT," +
                    Structure_BBDD.COLUMND4 + " TEXT,"+
                    Structure_BBDD.COLUMND5 + " TEXT,"+
                    Structure_BBDD.COLUMND6 + " TEXT,"+
                    Structure_BBDD.COLUMND7 + " TEXT "+ " )";

     public static final String SQL_DELETE_ENTRIES4 =
     "DROP TABLE IF EXISTS " + Structure_BBDD.TABLE4;

    public static final String SQL_CREATE_ENTRIES5=

            "CREATE TABLE " + Structure_BBDD.TABLE5 + " ( " +
                    Structure_BBDD.COLUMNEID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Structure_BBDD.COLUMNE2 + " TEXT,"+
                    Structure_BBDD.COLUMNE3 +" TEXT," +
                    Structure_BBDD.COLUMNE4 + " TEXT,"+
                    Structure_BBDD.COLUMNE5 + " TEXT "+ " )";

    public static final String SQL_DELETE_ENTRIES5 =
            "DROP TABLE IF EXISTS " + Structure_BBDD.TABLE5;

}
