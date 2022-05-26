package com.s3.movieflex.adapters.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class UserDataBase extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "UserDataBase";


    public UserDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sd) {
        sd.execSQL("CREATE TABLE user(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,EMAIL TEXT,PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sd, int i, int i1) {
        sd.execSQL("DROP TABLE IF EXISTS user");
        onCreate(sd);
    }

    public Boolean insertData(String userName, String email, String password) {
        SQLiteDatabase sd = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME", userName);
        contentValues.put("EMAIL", email);
        contentValues.put("PASSWORD", password);
        long result = sd.insert("user", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean if_user_exists(String email, String password) {
        SQLiteDatabase sd = this.getWritableDatabase();
        Cursor c = sd.rawQuery("SELECT * FROM user WHERE email =? And password =?", new String[]{email, password});
        if (c.getCount() > 0)
            return true;
        else
            return false;
    }

    public boolean if_email_exists(String email) {
        SQLiteDatabase sd = this.getWritableDatabase();
        Cursor cursor = sd.rawQuery("SELECT * FROM user WHERE email =? ", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
}
