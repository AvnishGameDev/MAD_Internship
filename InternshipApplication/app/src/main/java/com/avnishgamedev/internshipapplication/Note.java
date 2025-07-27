package com.avnishgamedev.internshipapplication;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id = 0;

    @ColumnInfo(name = "title")
    @NonNull
    public String title;

    @ColumnInfo(name = "description")
    public String description;
}
