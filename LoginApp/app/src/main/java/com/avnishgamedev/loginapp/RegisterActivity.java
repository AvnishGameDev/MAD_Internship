package com.avnishgamedev.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText etFullname, etUsername, etPassword;
    Button btnRegister, btnLogin;
    UserDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFullname = findViewById(R.id.etFullname);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);

        btnRegister.setOnClickListener(v -> {
            String fullname = etFullname.getText().toString();
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (fullname.length() < 2) {
                etFullname.setError("Fullname must be at least 2 characters long");
                return;
            }
            if (username.length() < 2) {
                etUsername.setError("Username must be at least 2 characters long");
                return;
            }
            if (password.length() < 6) {
                etPassword.setError("Password must be at least 6 characters long");
                return;
            }

            password = String.valueOf(password.hashCode());

            dbHelper = new UserDBHelper(this);
            dbHelper.registerUser(username, password, fullname);

            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
        btnLogin.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        });
    }
}