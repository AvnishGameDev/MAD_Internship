package com.avnishgamedev.avnishsloginapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText etFullName;
    EditText etCourse;
    Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullName = findViewById(R.id.etFullName);
        etCourse = findViewById(R.id.etCourse);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(v -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("fullName", etFullName.getText().toString());
            map.put("course", etCourse.getText().toString());

            FirebaseFirestore.getInstance().collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(map, SetOptions.merge())
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, DashboardActivity.class));
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Failed to register: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
    }
}
