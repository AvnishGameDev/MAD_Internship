package com.avnishgamedev.academica;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.avnishgamedev.academica.adapters.RemindersAdapter;
import com.avnishgamedev.academica.models.Reminder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class RemindersActivity extends AppCompatActivity {
    private static final String TAG = "RemindersActivity";
    private RecyclerView recyclerView;
    private RemindersAdapter adapter;
    private List<Reminder> remindersList;
    private FirebaseFirestore db;
    private String userId;
    private ListenerRegistration remindersListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders);

        // Initialize Firebase
        db = FirebaseFirestore.getInstance();
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Initialize views
        recyclerView = findViewById(R.id.recyclerViewReminders);
        FloatingActionButton fabAddReminder = findViewById(R.id.fabAddReminder);

        // Setup RecyclerView
        remindersList = new ArrayList<>();
        adapter = new RemindersAdapter(remindersList, this::editReminder, this::deleteReminder, this::toggleComplete);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Setup FAB
        fabAddReminder.setOnClickListener(v -> {
            Intent intent = new Intent(RemindersActivity.this, AddEditReminderActivity.class);
            startActivity(intent);
        });

        // Setup real-time listener for reminders
        setupRemindersListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove the listener when activity is destroyed
        if (remindersListener != null) {
            remindersListener.remove();
        }
    }

    private void setupRemindersListener() {
        remindersListener = db.collection("reminders")
                .whereEqualTo("userId", userId)
                .orderBy("dateTime", com.google.firebase.firestore.Query.Direction.ASCENDING)
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e);
                        Toast.makeText(this, "Failed to load reminders", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (queryDocumentSnapshots != null) {
                        remindersList.clear();
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            Reminder reminder = document.toObject(Reminder.class);
                            reminder.setId(document.getId());
                            remindersList.add(reminder);
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

    private void editReminder(Reminder reminder) {
        Intent intent = new Intent(RemindersActivity.this, AddEditReminderActivity.class);
        intent.putExtra("reminder_id", reminder.getId());
        intent.putExtra("reminder_title", reminder.getTitle());
        intent.putExtra("reminder_description", reminder.getDescription());
        intent.putExtra("reminder_datetime", reminder.getDateTime().getTime());
        startActivity(intent);
    }

    private void deleteReminder(Reminder reminder) {
        db.collection("reminders").document(reminder.getId())
                .delete()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Reminder deleted", Toast.LENGTH_SHORT).show();
                    // Real-time listener will automatically update the list
                })
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error deleting reminder", e);
                    Toast.makeText(this, "Failed to delete reminder", Toast.LENGTH_SHORT).show();
                });
    }

    private void toggleComplete(Reminder reminder) {
        db.collection("reminders").document(reminder.getId())
                .update("completed", !reminder.isCompleted())
                .addOnSuccessListener(aVoid -> {
                    reminder.setCompleted(!reminder.isCompleted());
                    adapter.notifyDataSetChanged();
                    String message = reminder.isCompleted() ? "Reminder completed" : "Reminder marked as pending";
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Log.w(TAG, "Error updating reminder", e);
                    Toast.makeText(this, "Failed to update reminder", Toast.LENGTH_SHORT).show();
                });
    }
}
