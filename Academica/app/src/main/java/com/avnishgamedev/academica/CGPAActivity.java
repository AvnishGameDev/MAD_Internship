package com.avnishgamedev.academica;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avnishgamedev.academica.adapters.CGPAAdapter;
import com.avnishgamedev.academica.models.CGPAEntry;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CGPAActivity extends AppCompatActivity {
    private static final String TAG = "CGPAActivity";
    private RecyclerView recyclerView;
    private CGPAAdapter adapter;
    private List<CGPAEntry> cgpaList;
    private FirebaseFirestore db;
    private String userId;
    private TextView textCGPA;
    private ListenerRegistration cgpaListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa);

        // Initialize Firebase
        db = FirebaseFirestore.getInstance();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Initialize views
        recyclerView = findViewById(R.id.recyclerViewCGPA);
        textCGPA = findViewById(R.id.textCGPA);
        FloatingActionButton fabAddSemester = findViewById(R.id.fabAddSemester);

        // Setup RecyclerView
        cgpaList = new ArrayList<>();
        adapter = new CGPAAdapter(cgpaList, this::editSemester, this::deleteSemester);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Setup FAB
        fabAddSemester.setOnClickListener(v -> {
            Intent intent = new Intent(CGPAActivity.this, AddEditSemesterActivity.class);
            startActivity(intent);
        });

        // Setup real-time listener for CGPA data
        setupCGPAListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove the listener when activity is destroyed
        if (cgpaListener != null) {
            cgpaListener.remove();
        }
    }

    private void setupCGPAListener() {
        cgpaListener = db.collection("cgpa")
                .whereEqualTo("userId", userId)
                .orderBy("semesterName")
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e);
                        Toast.makeText(this, "Failed to load CGPA data", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (queryDocumentSnapshots != null) {
                        cgpaList.clear();
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            CGPAEntry entry = document.toObject(CGPAEntry.class);
                            entry.setId(document.getId());
                            cgpaList.add(entry);
                        }
                        adapter.notifyDataSetChanged();
                        calculateOverallCGPA();
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Real-time listener handles data refresh automatically
    }

    private void calculateOverallCGPA() {
        if (cgpaList.isEmpty()) {
            textCGPA.setText("Overall CGPA: --");
            return;
        }

        double totalGradePoints = 0;
        double totalCredits = 0;

        for (CGPAEntry entry : cgpaList) {
            for (CGPAEntry.SubjectGrade subject : entry.getSubjects()) {
                totalGradePoints += subject.getGradePoint() * subject.getCredits();
                totalCredits += subject.getCredits();
            }
        }

        double cgpa = totalCredits > 0 ? totalGradePoints / totalCredits : 0;
        DecimalFormat df = new DecimalFormat("#.##");
        textCGPA.setText("Overall CGPA: " + df.format(cgpa));
    }

    private void editSemester(CGPAEntry entry) {
        Intent intent = new Intent(CGPAActivity.this, AddEditSemesterActivity.class);
        intent.putExtra("entry_id", entry.getId());
        intent.putExtra("semester_name", entry.getSemesterName());
        // TODO: Pass subjects data
        startActivity(intent);
    }

    private void deleteSemester(CGPAEntry entry) {
        db.collection("cgpa").document(entry.getId())
                .delete()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Semester deleted", Toast.LENGTH_SHORT).show();
                    // Real-time listener will automatically update the list and recalculate CGPA
                })
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error deleting semester", e);
                    Toast.makeText(this, "Failed to delete semester", Toast.LENGTH_SHORT).show();
                });
    }
}
