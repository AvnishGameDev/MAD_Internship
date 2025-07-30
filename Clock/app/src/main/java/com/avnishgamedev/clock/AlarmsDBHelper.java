package com.avnishgamedev.clock;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class AlarmsDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "alarms.db";
    private static final int DATABASE_VERSION = 1;

    public AlarmsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE alarms (id INTEGER PRIMARY KEY AUTOINCREMENT, hour INTEGER, minute INTEGER, label TEXT, isEnabled INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS alarms");
        onCreate(db);
    }

    public void insertAlarm(Alarm alarm) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO alarms (hour, minute, label, isEnabled) VALUES (?, ?, ?, ?)",
                new Object[] { alarm.getHour(), alarm.getMinute(), alarm.getLabel(), alarm.isEnabled() ? 1 : 0 });
        db.close();
    }

    public void updateAlarm(Alarm alarm) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE alarms SET hour=?, minute=?, label=?, isEnabled=? WHERE id=?",
                new Object[] { alarm.getHour(), alarm.getMinute(), alarm.getLabel(), alarm.isEnabled() ? 1 : 0, alarm.getId() });
        db.close();
    }

    public void deleteAlarm(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM alarms WHERE id=?", new Object[] { id });
        db.close();
    }

    public List<Alarm> getAllAlarms() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM alarms", null);
        List<Alarm> alarms = new ArrayList<>();
        if (c.moveToFirst()) {
            do {
                // Get column indices once to be efficient and safe
                int idColumnIndex = c.getColumnIndexOrThrow("id");
                int labelColumnIndex = c.getColumnIndexOrThrow("label");
                int hourColumnIndex = c.getColumnIndexOrThrow("hour");
                int minuteColumnIndex = c.getColumnIndexOrThrow("minute");
                int isEnabledColumnIndex = c.getColumnIndexOrThrow("isEnabled");
                Alarm alarm = new Alarm(
                        c.getInt(idColumnIndex),
                        c.getString(labelColumnIndex),
                        c.getInt(hourColumnIndex),
                        c.getInt(minuteColumnIndex),
                        c.getInt(isEnabledColumnIndex) == 1
                );
                alarms.add(alarm);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return alarms;
    }
}
