package com.avnishgamedev.diaryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DiaryDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "diary_db";
    private static final int DATABASE_VERSION = 1;

    public static class DiaryEntry {
        public String title;
        public String content;
        public String date;
        public DiaryEntry(String title, String content, String date) {
            this.title = title;
            this.content = content;
            this.date = date;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE diary (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, content TEXT, date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS diary");
        onCreate(sqLiteDatabase);
    }

    public DiaryDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void insertEntry(String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("content", content);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy hh:mm a", Locale.getDefault());
        values.put("date", sdf.format(new java.util.Date()));
        db.insert("diary", null, values);
        db.close();
    }

    public List<DiaryEntry> getAllEntries() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM diary";
        Cursor cursor = db.rawQuery(query, null);
        List<DiaryEntry> entries = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(1);
                String content = cursor.getString(2);
                String date = cursor.getString(3);
                entries.add(new DiaryEntry(title, content, date));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return entries;
    }
}
