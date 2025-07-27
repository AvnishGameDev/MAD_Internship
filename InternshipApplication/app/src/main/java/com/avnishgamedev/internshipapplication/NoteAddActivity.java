package com.avnishgamedev.internshipapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.Executors;

public class NoteAddActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_add);

        EditText edtNoteTitle = findViewById(R.id.EDT_NoteTitle);
        EditText edtNoteDescription = findViewById(R.id.EDT_NoteDescription);
        Button btnAddNote = findViewById(R.id.BTN_AddNoteFinal);
        Button btnNoteBack = findViewById(R.id.BTN_NoteBack);

        btnNoteBack.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        btnAddNote.setOnClickListener(v -> {
            Note n = new Note();
            n.title = edtNoteTitle.getText().toString();
            n.description = edtNoteDescription.getText().toString();

            if (n.title.isEmpty()) {
                Snackbar.make(v, "You must provide a title!", Snackbar.LENGTH_SHORT).show();
                return;
            }

            addNoteInBackground(n);

            setResult(RESULT_OK);
            finish();
        });
    }

    public void addNoteInBackground(Note note) {
        Executors.newSingleThreadExecutor().execute(() -> Notes_DB.getInstance(this).notesDAO().insert(note));
    }
}
