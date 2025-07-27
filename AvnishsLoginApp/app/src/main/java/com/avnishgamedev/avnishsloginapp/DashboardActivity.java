package com.avnishgamedev.avnishsloginapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class DashboardActivity extends AppCompatActivity {

    TextView tvGreeting;

    Button btnSignOut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tvGreeting = findViewById(R.id.tvGreeting);
        btnSignOut = findViewById(R.id.btnSignOut);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").document(user.getUid()).get()
                .addOnSuccessListener(doc -> {
                    String fullName = doc.getString("fullName");
                    tvGreeting.setText("Hi " + fullName + "!");
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to get User data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });

        btnSignOut.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            finish();
        });
    }
}
