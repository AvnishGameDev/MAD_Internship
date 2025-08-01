package com.avnishgamedev.internshipapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDAO {
    @Query("SELECT * FROM notes")
    List<Note> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Note... notes);

    @Delete
    void deleteNote(Note note);
}
