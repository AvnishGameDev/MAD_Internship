package com.avnishgamedev.healthtrackerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "SymptomsDB";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE symptoms (id INTEGER PRIMARY KEY AUTOINCREMENT, symptom TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS symptoms");
        onCreate(sqLiteDatabase);
    }

    public boolean addSymptom(String symptom) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("symptom", symptom);
        long result = db.insert("symptoms", null, contentValues);
        return result != -1;
    }

    public List<String> getSymptoms() {
        List<String> symptoms = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM symptoms", null);
        if (cursor.moveToFirst()) {
            do {
                symptoms.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return symptoms;
    }
}
