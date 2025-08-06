package com.avnishgamedev.academica;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.avnishgamedev.academica.models.CGPAEntry;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddEditSemesterActivity extends AppCompatActivity {
    private static final String TAG = "AddEditSemesterActivity";
    
    private EditText editTextSemesterName;
    private LinearLayout linearLayoutSubjects;
    private Button buttonAddSubject, buttonSave;
    private FirebaseFirestore db;
    private String userId;
    private String entryId = null;
    private List<SubjectView> subjectViews;

    // Grade point mapping
    private static final Map<String, Double> GRADE_POINTS = new HashMap<>();
    static {
        GRADE_POINTS.put("A+", 10.0);
        GRADE_POINTS.put("A", 9.0);
        GRADE_POINTS.put("B+", 8.0);
        GRADE_POINTS.put("B", 7.0);
        GRADE_POINTS.put("C+", 6.0);
        GRADE_POINTS.put("C", 5.0);
        GRADE_POINTS.put("D", 4.0);
        GRADE_POINTS.put("F", 0.0);
    }

    private static class SubjectView {
        EditText editSubjectName;
        EditText editCredits;
        Spinner spinnerGrade;
        Button buttonRemove;
        View containerView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_semester);

        // Initialize Firebase
        db = FirebaseFirestore.getInstance();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Initialize views
        editTextSemesterName = findViewById(R.id.editTextSemesterName);
        linearLayoutSubjects = findViewById(R.id.linearLayoutSubjects);
        buttonAddSubject = findViewById(R.id.buttonAddSubject);
        buttonSave = findViewById(R.id.buttonSave);

        subjectViews = new ArrayList<>();

        // Check if editing existing semester
        if (getIntent().hasExtra("entry_id")) {
            entryId = getIntent().getStringExtra("entry_id");
            editTextSemesterName.setText(getIntent().getStringExtra("semester_name"));
            buttonSave.setText("Update Semester");
        }

        buttonAddSubject.setOnClickListener(v -> addSubjectView());
        buttonSave.setOnClickListener(v -> saveSemester());

        // Add initial subject view
        addSubjectView();
    }

    private void addSubjectView() {
        View subjectView = LayoutInflater.from(this).inflate(R.layout.item_subject_grade, linearLayoutSubjects, false);
        
        SubjectView sv = new SubjectView();
        sv.containerView = subjectView;
        sv.editSubjectName = subjectView.findViewById(R.id.editSubjectName);
        sv.editCredits = subjectView.findViewById(R.id.editCredits);
        sv.spinnerGrade = subjectView.findViewById(R.id.spinnerGrade);
        sv.buttonRemove = subjectView.findViewById(R.id.buttonRemove);

        // Setup grade spinner
        String[] grades = {"A+", "A", "B+", "B", "C+", "C", "D", "F"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, grades);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sv.spinnerGrade.setAdapter(adapter);

        sv.buttonRemove.setOnClickListener(v -> {
            linearLayoutSubjects.removeView(subjectView);
            subjectViews.remove(sv);
        });

        subjectViews.add(sv);
        linearLayoutSubjects.addView(subjectView);
    }

    private void saveSemester() {
        String semesterName = editTextSemesterName.getText().toString().trim();

        if (TextUtils.isEmpty(semesterName)) {
            editTextSemesterName.setError("Semester name is required");
            editTextSemesterName.requestFocus();
            return;
        }

        if (subjectViews.isEmpty()) {
            Toast.makeText(this, "Please add at least one subject", Toast.LENGTH_SHORT).show();
            return;
        }

        // Disable save button and show saving feedback
        buttonSave.setEnabled(false);
        buttonSave.setText("Saving...");

        List<Map<String, Object>> subjects = new ArrayList<>();
        double totalGradePoints = 0;
        double totalCredits = 0;

        for (SubjectView sv : subjectViews) {
            String subjectName = sv.editSubjectName.getText().toString().trim();
            String creditsStr = sv.editCredits.getText().toString().trim();
            String grade = sv.spinnerGrade.getSelectedItem().toString();

            if (TextUtils.isEmpty(subjectName)) {
                sv.editSubjectName.setError("Subject name is required");
                sv.editSubjectName.requestFocus();
                buttonSave.setEnabled(true);
                buttonSave.setText("Save");
                return;
            }

            if (TextUtils.isEmpty(creditsStr)) {
                sv.editCredits.setError("Credits are required");
                sv.editCredits.requestFocus();
                buttonSave.setEnabled(true);
                buttonSave.setText("Save");
                return;
            }

            double credits;
            try {
                credits = Double.parseDouble(creditsStr);
                if (credits <= 0) {
                    sv.editCredits.setError("Credits must be positive");
                    sv.editCredits.requestFocus();
                    buttonSave.setEnabled(true);
                    buttonSave.setText("Save");
                    return;
                }
            } catch (NumberFormatException e) {
                sv.editCredits.setError("Invalid credits");
                sv.editCredits.requestFocus();
                buttonSave.setEnabled(true);
                buttonSave.setText("Save");
                return;
            }

            double gradePoint = GRADE_POINTS.get(grade);
            
            Map<String, Object> subject = new HashMap<>();
            subject.put("subjectName", subjectName);
            subject.put("credits", credits);
            subject.put("grade", grade);
            subject.put("gradePoint", gradePoint);
            subjects.add(subject);

            totalGradePoints += gradePoint * credits;
            totalCredits += credits;
        }

        double gpa = totalCredits > 0 ? totalGradePoints / totalCredits : 0;

        Map<String, Object> semesterData = new HashMap<>();
        semesterData.put("userId", userId);
        semesterData.put("semesterName", semesterName);
        semesterData.put("subjects", subjects);
        semesterData.put("gpa", gpa);
        semesterData.put("totalCredits", totalCredits);

        if (entryId == null) {
            // Adding new semester
            db.collection("cgpa")
                    .add(semesterData)
                    .addOnSuccessListener(documentReference -> {
                        runOnUiThread(() -> {
                            Toast.makeText(AddEditSemesterActivity.this, "Semester saved successfully!", Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK);
                            finish();
                        });
                    })
                    .addOnFailureListener(e -> {
                        runOnUiThread(() -> {
                            buttonSave.setEnabled(true);
                            buttonSave.setText("Save");
                            Log.w(TAG, "Error adding semester", e);
                            Toast.makeText(AddEditSemesterActivity.this, "Failed to save semester: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        });
                    });
        } else {
            // Updating existing semester
            db.collection("cgpa").document(entryId)
                    .update(semesterData)
                    .addOnSuccessListener(aVoid -> {
                        runOnUiThread(() -> {
                            Toast.makeText(AddEditSemesterActivity.this, "Semester updated successfully!", Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK);
                            finish();
                        });
                    })
                    .addOnFailureListener(e -> {
                        runOnUiThread(() -> {
                            buttonSave.setEnabled(true);
                            buttonSave.setText("Save");
                            Log.w(TAG, "Error updating semester", e);
                            Toast.makeText(AddEditSemesterActivity.this, "Failed to update semester: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        });
                    });
        }
    }
}
