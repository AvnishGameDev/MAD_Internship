package com.avnishgamedev.internshipapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText edtUsername = findViewById(R.id.EDT_Username);
        EditText edtPassword = findViewById(R.id.EDT_Password);

        Button btnLogin = findViewById(R.id.BTN_Login);
        btnLogin.setOnClickListener(v -> {
            Toast.makeText(this, edtUsername.getText() + " : " + edtPassword.getText(), Toast.LENGTH_SHORT).show();
        });

        Button btnNotAnUser = findViewById(R.id.BTN_NotAnUser);
        btnNotAnUser.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }
}
