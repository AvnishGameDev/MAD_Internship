package com.avnishgamedev.newsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "NewsDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table news(id integer primary key autoincrement, title text, content text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists news");
        onCreate(sqLiteDatabase);
    }

    public boolean addNews(String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("content", content);
        long result = db.insert("news", null, contentValues);
        return result != -1;
    }

    public List<Pair<String, String>> getAllNews() {
        List<Pair<String, String>> newsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from news", null);
        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(1);
                String content = cursor.getString(2);
                newsList.add(new Pair<>(title, content));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return newsList;
    }
}
