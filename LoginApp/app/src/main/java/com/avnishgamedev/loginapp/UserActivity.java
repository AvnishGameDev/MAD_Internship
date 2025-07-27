package com.avnishgamedev.loginapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {

    TextView tvHello;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        tvHello = findViewById(R.id.tvHello);

        String fullname = getSharedPreferences("login", MODE_PRIVATE).getString("fullname", null);

        tvHello.setText("Hello " + fullname + "!");
    }
}
