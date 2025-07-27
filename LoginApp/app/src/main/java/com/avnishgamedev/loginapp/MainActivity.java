package com.avnishgamedev.loginapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username = getSharedPreferences("login", MODE_PRIVATE).getString("username", null);
        startActivity(new Intent(MainActivity.this, (username == null || !(new UserDBHelper(this).userExists(username))) ? LoginActivity.class : UserActivity.class));
    }
}