package com.avnishgamedev.academica;

import android.content.Intent;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.credentials.ClearCredentialStateRequest;
import androidx.credentials.CredentialManager;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.exceptions.ClearCredentialException;

import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executors;

public class DashboardActivity extends AppCompatActivity {
    private static final String TAG = "DashboardActivity";

    Button btnSignOut;
    CardView cardNotes, cardReminders, cardTimetable, cardCGPA;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnSignOut = findViewById(R.id.btnSignOut);
        cardNotes = findViewById(R.id.card_notes);
        cardReminders = findViewById(R.id.card_reminders);
        cardTimetable = findViewById(R.id.card_timetable);
        cardCGPA = findViewById(R.id.card_cgpa);

        btnSignOut.setOnClickListener(v -> signOut());
        
        // Set click listeners for cards
        cardNotes.setOnClickListener(v -> startActivity(new Intent(this, NotesActivity.class)));
        cardReminders.setOnClickListener(v -> startActivity(new Intent(this, RemindersActivity.class)));
        cardTimetable.setOnClickListener(v -> startActivity(new Intent(this, TimetableActivity.class)));
        cardCGPA.setOnClickListener(v -> startActivity(new Intent(this, CGPAActivity.class)));
    }

    private void signOut() {
        // Firebase sign out
        FirebaseAuth.getInstance().signOut();

        CredentialManager credentialManager = CredentialManager.create(getBaseContext());

        // When a user signs out, clear the current user credential state from all credential providers.
        ClearCredentialStateRequest clearRequest = new ClearCredentialStateRequest();
        credentialManager.clearCredentialStateAsync(
                clearRequest,
                new CancellationSignal(),
                Executors.newSingleThreadExecutor(),
                new CredentialManagerCallback<>() {
                    @Override
                    public void onResult(Void result) {
                        startActivity(new Intent(DashboardActivity.this, MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onError(ClearCredentialException e) {
                        Toast.makeText(DashboardActivity.this, "SignOut Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Couldn't clear user credentials: " + e.getLocalizedMessage());
                    }
                });
    }
}
