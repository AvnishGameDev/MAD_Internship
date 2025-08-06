package com.avnishgamedev.academica;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avnishgamedev.academica.adapters.TimetableAdapter;
import com.avnishgamedev.academica.models.TimetableEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class TimetableActivity extends AppCompatActivity {
    private static final String TAG = "TimetableActivity";
    private RecyclerView recyclerView;
    private TimetableAdapter adapter;
    private List<TimetableEntry> timetableList;
    private FirebaseFirestore db;
    private String userId;
    private ListenerRegistration timetableListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        // Initialize Firebase
        db = FirebaseFirestore.getInstance();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Initialize views
        recyclerView = findViewById(R.id.recyclerViewTimetable);
        FloatingActionButton fabAddEntry = findViewById(R.id.fabAddEntry);

        // Setup RecyclerView
        timetableList = new ArrayList<>();
        adapter = new TimetableAdapter(timetableList, this::editEntry, this::deleteEntry);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Setup FAB
        fabAddEntry.setOnClickListener(v -> {
            Intent intent = new Intent(TimetableActivity.this, AddEditTimetableActivity.class);
            startActivity(intent);
        });

        // Setup real-time listener for timetable
        setupTimetableListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove the listener when activity is destroyed
        if (timetableListener != null) {
            timetableListener.remove();
        }
    }

    private void setupTimetableListener() {
        timetableListener = db.collection("timetable")
                .whereEqualTo("userId", userId)
                .orderBy("dayOfWeek")
                .orderBy("startTime")
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e);
                        Toast.makeText(this, "Failed to load timetable", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (queryDocumentSnapshots != null) {
                        timetableList.clear();
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            TimetableEntry entry = document.toObject(TimetableEntry.class);
                            entry.setId(document.getId());
                            timetableList.add(entry);
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

    private void editEntry(TimetableEntry entry) {
        Intent intent = new Intent(TimetableActivity.this, AddEditTimetableActivity.class);
        intent.putExtra("entry_id", entry.getId());
        intent.putExtra("subject_name", entry.getSubjectName());
        intent.putExtra("professor_name", entry.getProfessorName());
        intent.putExtra("classroom", entry.getClassroom());
        intent.putExtra("day_of_week", entry.getDayOfWeek());
        intent.putExtra("start_time", entry.getStartTime());
        intent.putExtra("end_time", entry.getEndTime());
        startActivity(intent);
    }

    private void deleteEntry(TimetableEntry entry) {
        db.collection("timetable").document(entry.getId())
                .delete()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Entry deleted", Toast.LENGTH_SHORT).show();
                    // Real-time listener will automatically update the list
                })
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error deleting entry", e);
                    Toast.makeText(this, "Failed to delete entry", Toast.LENGTH_SHORT).show();
                });
    }
}
