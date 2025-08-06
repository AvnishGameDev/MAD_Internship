package com.avnishgamedev.academica;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddEditReminderActivity extends AppCompatActivity {
    private static final String TAG = "AddEditReminderActivity";
    
    private EditText editTextTitle, editTextDescription;
    private TextView textSelectedDateTime;
    private Button buttonSelectDateTime, buttonSave;
    private FirebaseFirestore db;
    private String userId;
    private String reminderId = null;
    private Calendar selectedDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_reminder);

        // Initialize Firebase
        db = FirebaseFirestore.getInstance();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Initialize views
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
        textSelectedDateTime = findViewById(R.id.textSelectedDateTime);
        buttonSelectDateTime = findViewById(R.id.buttonSelectDateTime);
        buttonSave = findViewById(R.id.buttonSave);

        selectedDateTime = Calendar.getInstance();

        // Check if editing existing reminder
        if (getIntent().hasExtra("reminder_id")) {
            reminderId = getIntent().getStringExtra("reminder_id");
            editTextTitle.setText(getIntent().getStringExtra("reminder_title"));
            editTextDescription.setText(getIntent().getStringExtra("reminder_description"));
            
            long dateTimeMillis = getIntent().getLongExtra("reminder_datetime", System.currentTimeMillis());
            selectedDateTime.setTimeInMillis(dateTimeMillis);
            updateDateTimeDisplay();
            
            buttonSave.setText("Update Reminder");
        }

        buttonSelectDateTime.setOnClickListener(v -> showDateTimePicker());
        buttonSave.setOnClickListener(v -> saveReminder());
    }

    private void showDateTimePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    selectedDateTime.set(Calendar.YEAR, year);
                    selectedDateTime.set(Calendar.MONTH, month);
                    selectedDateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    
                    TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                            (timeView, hourOfDay, minute) -> {
                                selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                selectedDateTime.set(Calendar.MINUTE, minute);
                                updateDateTimeDisplay();
                            },
                            selectedDateTime.get(Calendar.HOUR_OF_DAY),
                            selectedDateTime.get(Calendar.MINUTE),
                            false);
                    timePickerDialog.show();
                },
                selectedDateTime.get(Calendar.YEAR),
                selectedDateTime.get(Calendar.MONTH),
                selectedDateTime.get(Calendar.DAY_OF_MONTH));
        
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private void updateDateTimeDisplay() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy 'at' HH:mm", Locale.getDefault());
        textSelectedDateTime.setText("Selected: " + sdf.format(selectedDateTime.getTime()));
    }

    private void saveReminder() {
        String title = editTextTitle.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();

        if (TextUtils.isEmpty(title)) {
            editTextTitle.setError("Title is required");
            editTextTitle.requestFocus();
            return;
        }

        // Disable save button and show saving feedback
        buttonSave.setEnabled(false);
        buttonSave.setText("Saving...");

        Map<String, Object> reminderData = new HashMap<>();
        reminderData.put("userId", userId);
        reminderData.put("title", title);
        reminderData.put("description", description);
        reminderData.put("dateTime", selectedDateTime.getTime());
        reminderData.put("completed", false);

        if (reminderId == null) {
            // Adding new reminder
            reminderData.put("createdAt", new Date());
            db.collection("reminders")
                    .add(reminderData)
                    .addOnSuccessListener(documentReference -> {
                        runOnUiThread(() -> {
                            Toast.makeText(AddEditReminderActivity.this, "Reminder saved successfully!", Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK);
                            finish();
                        });
                    })
                    .addOnFailureListener(e -> {
                        runOnUiThread(() -> {
                            buttonSave.setEnabled(true);
                            buttonSave.setText("Save");
                            Log.w(TAG, "Error adding reminder", e);
                            Toast.makeText(AddEditReminderActivity.this, "Failed to save reminder: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        });
                    });
        } else {
            // Updating existing reminder
            db.collection("reminders").document(reminderId)
                    .update(reminderData)
                    .addOnSuccessListener(aVoid -> {
                        runOnUiThread(() -> {
                            Toast.makeText(AddEditReminderActivity.this, "Reminder updated successfully!", Toast.LENGTH_SHORT).show();
                            setResult(RESULT_OK);
                            finish();
                        });
                    })
                    .addOnFailureListener(e -> {
                        runOnUiThread(() -> {
                            buttonSave.setEnabled(true);
                            buttonSave.setText("Save");
                            Log.w(TAG, "Error updating reminder", e);
                            Toast.makeText(AddEditReminderActivity.this, "Failed to update reminder: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        });
                    });
        }
    }
}
