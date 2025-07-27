package com.avnishgamedev.internshipapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText edtUsername = findViewById(R.id.EDT_Username);
        EditText edtPassword = findViewById(R.id.EDT_Password);
        EditText edtConfirmPassword = findViewById(R.id.EDT_ConfirmPassword);

        Button btnRegister = findViewById(R.id.BTN_Register);
        btnRegister.setOnClickListener(v -> {
            Toast.makeText(this, edtUsername.getText() + " : " + edtPassword.getText(), Toast.LENGTH_SHORT).show();
        });

        Button btnAlreadyAnUser = findViewById(R.id.BTN_AlreadyAnUser);
        btnAlreadyAnUser.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));
    }
}
