package com.avnishgamedev.ecommerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    CardView cvAdminPortal;
    CardView cvCustomerPortal;
    Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cvAdminPortal = findViewById(R.id.cvAdminPortal);
        cvCustomerPortal = findViewById(R.id.cvCustomerPortal);
        btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });

        cvAdminPortal.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AdminAddProductActivity.class));
        });

        cvCustomerPortal.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CustomerViewActivity.class));
        });
    }

    @Override
    protected void onStart() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
        super.onStart();
    }
}