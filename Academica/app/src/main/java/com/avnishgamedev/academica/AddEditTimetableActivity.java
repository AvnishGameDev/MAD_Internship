package com.avnishgamedev.academica;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddEditTimetableActivity extends AppCompatActivity {
    private static final String TAG = "AddEditTimetableActivity";
    
    private EditText editTextSubject, editTextProfessor, editTextClassroom;
    private Spinner spinnerDay;
    private TextView textStartTime, textEndTime;
    private Button buttonStartTime, buttonEndTime, buttonSave;
    private FirebaseFirestore db;
    private String userId;
    private String entryId = null;
    private String startTime = "", endTime = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_timetable);

        // Initialize Firebase
        db = FirebaseFirestore.getInstance();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Initialize views
        editTextSubject = findViewById(R.id.editTextSubject);
        editTextProfessor = findViewById(R.id.editTextProfessor);
        editTextClassroom = findViewById(R.id.editTextClassroom);
        spinnerDay = findViewById(R.id.spinnerDay);
        textStartTime = findViewById(R.id.textStartTime);
        textEndTime = findViewById(R.id.textEndTime);
        buttonStartTime = findViewById(R.id.buttonStartTime);
        buttonEndTime = findViewById(R.id.buttonEndTime);
        buttonSave = findViewById(R.id.buttonSave);

        // Setup spinner
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, days);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDay.setAdapter(adapter);

        // Check if editing existing entry
        if (getIntent().hasExtra("entry_id")) {
            entryId = getIntent().getStringExtra("entry_id");
            editTextSubject.setText(getIntent().getStringExtra("subject_name"));
            editTextProfessor.setText(getIntent().getStringExtra("professor_name"));
            editTextClassroom.setText(getIntent().getStringExtra("classroom"));
            
            String dayOfWeek = getIntent().getStringExtra("day_of_week");
            for (int i = 0; i < days.length; i++) {
                if (days[i].equalsIgnoreCase(dayOfWeek)) {
                    spinnerDay.setSelection(i);
                    break;
                }
            }
            
            startTime = getIntent().getStringExtra("start_time");
            endTime = getIntent().getStringExtra("end_time");
            textStartTime.setText("Start: " + startTime);
            textEndTime.setText("End: " + endTime);
            
            buttonSave.setText("Update Entry");
        }

        buttonStartTime.setOnClickListener(v -> showTimePicker(true));
        buttonEndTime.setOnClickListener(v -> showTimePicker(false));
        buttonSave.setOnClickListener(v -> saveEntry());
    }

    private void showTimePicker(boolean isStartTime) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minute) -> {
                    String time = String.format("%02d:%02d", hourOfDay, minute);
                    if (isStartTime) {
                        startTime = time;
                        textStartTime.setText("Start: " + time);
                    } else {
                        endTime = time;
                        textEndTime.setText("End: " + time);
                    }
                }, 9, 0, false);
        timePickerDialog.show();
    }

    private void saveEntry() {
        String subject = editTextSubject.getText().toString().trim();
        String professor = editTextProfessor.getText().toString().trim();
        String classroom = editTextClassroom.getText().toString().trim();
        String dayOfWeek = spinnerDay.getSelectedItem().toString();

        if (TextUtils.isEmpty(subject)) {
            editTextSubject.setError("Subject is required");
            editTextSubject.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(professor)) {
            editTextProfessor.setError("Professor name is required");
            editTextProfessor.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(startTime) || TextUtils.isEmpty(endTime)) {
            Toast.makeText(this, "Please select start and end times", Toast.LENGTH_SHORT).show();
            return;
        }

        // Disable save button and show saving feedback
        buttonSave.setEnabled(false);
        buttonSave.setText("Saving...");

        Map<String, Object> entryData = new HashMap<>();
        entryData.put("userId", userId);
        entryData.put("subjectName", subject);
        entryData.put("professorName", professor);
        entryData.put("classroom", classroom);
        entryData.put("dayOfWeek", dayOfWeek.toUpperCase());
        entryData.put("startTime", startTime);
        entryData.put("endTime", endTime);

        if (entryId == null) {
            // Adding new entry
            db.collection("timetable")
                    .add(entryData)
                    .addOnSuccessListener(documentReference -> {
                        runOnUiThread(() -> {
                            Toast.makeText(AddEditTimetableActivity.this, "Timetable entry saved successfully!", Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK);
                            finish();
                        });
                    })
                    .addOnFailureListener(e -> {
                        runOnUiThread(() -> {
                            buttonSave.setEnabled(true);
                            buttonSave.setText("Save");
                            Log.w(TAG, "Error adding entry", e);
                            Toast.makeText(AddEditTimetableActivity.this, "Failed to save entry: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        });
                    });
        } else {
            // Updating existing entry
            db.collection("timetable").document(entryId)
                    .update(entryData)
                    .addOnSuccessListener(aVoid -> {
                        runOnUiThread(() -> {
                            Toast.makeText(AddEditTimetableActivity.this, "Timetable entry updated successfully!", Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK);
                            finish();
                        });
                    })
                    .addOnFailureListener(e -> {
                        runOnUiThread(() -> {
                            buttonSave.setEnabled(true);
                            buttonSave.setText("Save");
                            Log.w(TAG, "Error updating entry", e);
                            Toast.makeText(AddEditTimetableActivity.this, "Failed to update entry: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        });
                    });
        }
    }
}
