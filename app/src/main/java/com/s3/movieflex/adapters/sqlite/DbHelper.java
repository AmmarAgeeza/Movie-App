package com.s3.movieflex.adapters.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public final static String DbName = "favorite";
    // Table 1
    public final static String TABLE1 = "MOVIES";
    public final static String TAB1_COLO1 = "_ID";
    public final static String TAB1_COLO2 = "TITLE";
    public final static String TAB1_COLO3 = "OVERVIEW";
    public final static String TAB1_COLO4 = "POSTER";
    public final static String TAB1_COLO5 = "COVER";
    public final static String TAB1_COLO6 = "RELEASE_DATE";
    public final static String TAB1_COLO7 = "RATING";

    // Table 2
    public final static String TABLE2 = "CREW";
    public final static String TAB2_COLO1 = "_ID";
    public final static String TAB2_COLO2 = "NAME";
    public final static String TAB2_COLO3 = "PHOTO";
    private final static int DbVersion = 2;
    private final static String CREATE_TABLE1 = "CREATE TABLE " + TABLE1 + " ( " +
            TAB1_COLO1 + " INTEGER PRIMARY KEY ," +
            TAB1_COLO2 + " TEXT NOT NULL , " +
            TAB1_COLO3 + " TEXT NOT NULL , " +
            TAB1_COLO4 + " TEXT , " +
            TAB1_COLO5 + " TEXT , " +
            TAB1_COLO6 + " TEXT , " +
            TAB1_COLO7 + " REAL ) ";
    private final static String CREATE_TABLE2 = "CREATE TABLE " + TABLE2 + " (" +
            TAB2_COLO1 + " INTEGER NOT NULL ," +
            TAB2_COLO2 + " TEXT NOT NULL ," +
            TAB2_COLO3 + " TEXT )";

    public DbHelper(Context context) {
        super(context, DbName, null, DbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL(CREATE_TABLE1);
        DB.execSQL(CREATE_TABLE2);


    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE1);
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE2);
        onCreate(DB);

    }
}
