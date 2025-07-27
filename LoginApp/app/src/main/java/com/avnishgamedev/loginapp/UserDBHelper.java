package com.avnishgamedev.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Users";
    private static final int VERSION = 2;
    public UserDBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Users (username TEXT, password TEXT, fullname TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Users");
        onCreate(sqLiteDatabase);
    }

    public void registerUser(String username, String password, String fullname) {
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        values.put("fullname", fullname);
        getWritableDatabase().insert("Users", null, values);
    }

    public boolean userExists(String username) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT COUNT(*) FROM Users WHERE username=?", new String[]{username});
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
    }

    public String getPassword(String username) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT password FROM Users WHERE username=?", new String[]{username});
        cursor.moveToFirst();
        String password = cursor.getCount() == 0 ? null : cursor.getString(0);
        cursor.close();
        return password;
    }

    public String getFullname(String username) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT fullname FROM Users WHERE username=?", new String[]{username});
        cursor.moveToFirst();
        String fullname = cursor.getCount() == 0 ? null : cursor.getString(0);
        cursor.close();
        return fullname;
    }
}
