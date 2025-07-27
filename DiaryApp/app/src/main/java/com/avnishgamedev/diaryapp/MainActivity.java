package com.avnishgamedev.diaryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tvNoEntries;
    RecyclerView rvDiaryEntries;
    DiaryEntryAdapter adapter;
    FloatingActionButton fabAddEntry;

    DiaryDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNoEntries = findViewById(R.id.tvNoEntries);

        dbHelper = new DiaryDBHelper(this);

        List<DiaryDBHelper.DiaryEntry> entries = dbHelper.getAllEntries();
        tvNoEntries.setVisibility(entries.isEmpty() ? View.VISIBLE : View.GONE);

        rvDiaryEntries = findViewById(R.id.rvDiaryEntries);
        adapter = new DiaryEntryAdapter(entries);
        rvDiaryEntries.setAdapter(adapter);

        fabAddEntry = findViewById(R.id.fabAddEntry);
        fabAddEntry.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddEntryActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<DiaryDBHelper.DiaryEntry> entries = dbHelper.getAllEntries();
        tvNoEntries.setVisibility(entries.isEmpty() ? View.VISIBLE : View.GONE);
        adapter.setEntries(entries);
    }
}