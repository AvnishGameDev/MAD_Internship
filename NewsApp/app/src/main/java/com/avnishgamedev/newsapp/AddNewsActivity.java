package com.avnishgamedev.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddNewsActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etContent;
    Button btnSubmit;
    DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        btnSubmit = findViewById(R.id.btnSubmit);
        dbHelper = new DBHelper(this);

        btnSubmit.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();
            if (dbHelper.addNews(title, content)) {
                Toast.makeText(this, "News added", Toast.LENGTH_SHORT).show();
                NotificationHelper.showNotification(this, "New News", "New news added!");
                startActivity(new Intent(AddNewsActivity.this, ViewNewsActivity.class));
                finish();
            } else {
                etTitle.setError("Something went wrong");
            }
        });
    }
}
