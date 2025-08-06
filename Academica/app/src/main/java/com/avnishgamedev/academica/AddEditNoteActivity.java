package com.avnishgamedev.academica;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.avnishgamedev.academica.models.Note;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddEditNoteActivity extends AppCompatActivity {
    private static final String TAG = "AddEditNoteActivity";
    
    private EditText editTextTitle, editTextContent;
    private Button buttonSave;
    private FirebaseFirestore db;
    private String userId;
    private String noteId = null; // null means adding new note

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_note);

        // Initialize Firebase
        db = FirebaseFirestore.getInstance();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Initialize views
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextContent = findViewById(R.id.editTextContent);
        buttonSave = findViewById(R.id.buttonSave);

        // Check if editing existing note
        if (getIntent().hasExtra("note_id")) {
            noteId = getIntent().getStringExtra("note_id");
            editTextTitle.setText(getIntent().getStringExtra("note_title"));
            editTextContent.setText(getIntent().getStringExtra("note_content"));
            buttonSave.setText("Update Note");
        }

        buttonSave.setOnClickListener(v -> saveNote());
    }

    private void saveNote() {
        String title = editTextTitle.getText().toString().trim();
        String content = editTextContent.getText().toString().trim();

        if (TextUtils.isEmpty(title)) {
            editTextTitle.setError("Title is required");
            editTextTitle.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(content)) {
            editTextContent.setError("Content is required");
            editTextContent.requestFocus();
            return;
        }

        // Disable save button and show saving feedback
        buttonSave.setEnabled(false);
        buttonSave.setText("Saving...");

        Map<String, Object> noteData = new HashMap<>();
        noteData.put("userId", userId);
        noteData.put("title", title);
        noteData.put("content", content);
        noteData.put("updatedAt", new Date());

        if (noteId == null) {
            // Adding new note
            noteData.put("createdAt", new Date());
            db.collection("notes")
                    .add(noteData)
                    .addOnSuccessListener(documentReference -> {
                        runOnUiThread(() -> {
                            Toast.makeText(AddEditNoteActivity.this, "Note saved successfully!", Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK);
                            finish();
                        });
                    })
                    .addOnFailureListener(e -> {
                        runOnUiThread(() -> {
                            buttonSave.setEnabled(true);
                            buttonSave.setText("Save");
                            Log.w(TAG, "Error adding note", e);
                            Toast.makeText(AddEditNoteActivity.this, "Failed to save note: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        });
                    });
        } else {
            // Updating existing note
            db.collection("notes").document(noteId)
                    .update(noteData)
                    .addOnSuccessListener(aVoid -> {
                        runOnUiThread(() -> {
                            Toast.makeText(AddEditNoteActivity.this, "Note updated successfully!", Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK);
                            finish();
                        });
                    })
                    .addOnFailureListener(e -> {
                        runOnUiThread(() -> {
                            buttonSave.setEnabled(true);
                            buttonSave.setText("Save");
                            Log.w(TAG, "Error updating note", e);
                            Toast.makeText(AddEditNoteActivity.this, "Failed to update note: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        });
                    });
        }
    }
}
