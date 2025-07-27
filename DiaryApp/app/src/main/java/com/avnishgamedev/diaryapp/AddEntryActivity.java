package com.avnishgamedev.diaryapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddEntryActivity extends AppCompatActivity {

    private DiaryDBHelper dbHelper;
    private EditText etTitle, etContent;
    private Button btnSaveEntry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        // If you added a Toolbar to your XML:
        Toolbar toolbar = findViewById(R.id.toolbarViewEntry);
        setSupportActionBar(toolbar);

        // Enable the Up button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true); // Optional: shows the icon
            getSupportActionBar().setTitle("Add Blog");
        }

        dbHelper = new DiaryDBHelper(this);
        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        btnSaveEntry = findViewById(R.id.btnSaveEntry);

        btnSaveEntry.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();
            dbHelper.insertEntry(title, content);
            finish();
        });
    }
}
