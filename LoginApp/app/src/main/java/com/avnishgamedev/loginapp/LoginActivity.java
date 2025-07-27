package com.avnishgamedev.loginapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin, btnRegister;
    UserDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            dbHelper = new UserDBHelper(this);
            String dbPassword = dbHelper.getPassword(username);
            if (dbPassword == null) {
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
            } else if (!dbPassword.equals(String.valueOf(password.hashCode()))) {
                Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();

                SharedPreferences s = getSharedPreferences("login", MODE_PRIVATE);
                s.edit().putString("username", username).putString("fullname", dbHelper.getFullname(username)).apply();

                Intent i = new Intent(LoginActivity.this, UserActivity.class);
                startActivity(i);
            }
        });
        btnRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }
}