package com.avnishgamedev.internshipapplication;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class Notes_DB extends RoomDatabase {
    public abstract NotesDAO notesDAO();

    private static volatile Notes_DB INSTANCE;
    public static Notes_DB getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (Notes_DB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Notes_DB.class, "notes_db").build();
                }
            }
        }
        return INSTANCE;
    }
}
