package com.avnishgamedev.internshipapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.Executors;

public class NotesMainActivity extends AppCompatActivity {

    ActivityResultLauncher<Intent> addNoteActivityResultLauncher;
    NotesListAdapter notesListAdapter;
    RecyclerView rvNotes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_main);

        FloatingActionButton btnAddNote = findViewById(R.id.BTN_AddNote);
        rvNotes = findViewById(R.id.RV_Notes);

        refreshNotes();

        notesListAdapter = new NotesListAdapter();
        rvNotes.setAdapter(notesListAdapter);

        addNoteActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            refreshNotes();
                        }
                    }
                });

        btnAddNote.setOnClickListener(v -> {
            Intent i = new Intent(NotesMainActivity.this, NoteAddActivity.class);
            addNoteActivityResultLauncher.launch(i);
        });
    }

    public void refreshNotes() {
        Executors.newSingleThreadExecutor().execute(() -> {
            List<Note> n = Notes_DB.getInstance(this).notesDAO().getAll();

            (new Handler(Looper.getMainLooper())).post(() -> {
                notesListAdapter.notes = n;
                if (notesListAdapter != null)
                    notesListAdapter.notifyDataSetChanged();
                }
            );
        });
    }
}
