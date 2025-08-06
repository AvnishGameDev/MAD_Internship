package com.avnishgamedev.academica;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avnishgamedev.academica.adapters.NotesAdapter;
import com.avnishgamedev.academica.models.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends AppCompatActivity {
    private static final String TAG = "NotesActivity";
    private RecyclerView recyclerView;
    private NotesAdapter adapter;
    private List<Note> notesList;
    private FirebaseFirestore db;
    private String userId;
    private ListenerRegistration notesListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        // Initialize Firebase
        db = FirebaseFirestore.getInstance();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Initialize views
        recyclerView = findViewById(R.id.recyclerViewNotes);
        FloatingActionButton fabAddNote = findViewById(R.id.fabAddNote);

        // Setup RecyclerView
        notesList = new ArrayList<>();
        adapter = new NotesAdapter(notesList, this::editNote, this::deleteNote);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Setup FAB
        fabAddNote.setOnClickListener(v -> {
            Intent intent = new Intent(NotesActivity.this, AddEditNoteActivity.class);
            startActivity(intent);
        });

        // Load notes with real-time listener
        setupNotesListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove the listener when activity is destroyed
        if (notesListener != null) {
            notesListener.remove();
        }
    }

    private void setupNotesListener() {
        notesListener = db.collection("notes")
                .whereEqualTo("userId", userId)
                .orderBy("updatedAt", com.google.firebase.firestore.Query.Direction.DESCENDING)
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e);
                        Toast.makeText(this, "Failed to load notes", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (queryDocumentSnapshots != null) {
                        notesList.clear();
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Note note = document.toObject(Note.class);
                            note.setId(document.getId());
                            notesList.add(note);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Real-time listener handles data refresh automatically
    }

    private void editNote(Note note) {
        Intent intent = new Intent(NotesActivity.this, AddEditNoteActivity.class);
        intent.putExtra("note_id", note.getId());
        intent.putExtra("note_title", note.getTitle());
        intent.putExtra("note_content", note.getContent());
        startActivity(intent);
    }

    private void deleteNote(Note note) {
        db.collection("notes").document(note.getId())
                .delete()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show();
                    // Real-time listener will automatically update the list
                })
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error deleting note", e);
                    Toast.makeText(this, "Failed to delete note", Toast.LENGTH_SHORT).show();
                });
    }
}
