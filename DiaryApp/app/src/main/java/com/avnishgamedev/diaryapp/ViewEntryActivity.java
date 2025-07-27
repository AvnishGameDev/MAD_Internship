package com.avnishgamedev.diaryapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ViewEntryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entry);

        // If you added a Toolbar to your XML:
        Toolbar toolbar = findViewById(R.id.toolbarViewEntry);
        setSupportActionBar(toolbar);

        // Enable the Up button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true); // Optional: shows the icon
        }

        // --- Your code to find TextViews and set data ---
        TextView tvTitle = findViewById(R.id.tvEntryTitle);
        TextView tvContent = findViewById(R.id.tvEntryContent);

        // Example: Get data from Intent (replace with your actual data source)
        String title = getIntent().getStringExtra("ENTRY_TITLE");
        if (title == null) {
            title = "No Title";
        }
        String content = getIntent().getStringExtra("ENTRY_CONTENT");
        if (content == null) {
            content = "No Content";
        }

        tvTitle.setText(title);
        tvContent.setText(content);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title); // Optionally set the toolbar title
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Check if the correct item ID was clicked
        if (item.getItemId() == android.R.id.home) {
            // This will simulate the system Back button press
            // and navigate according to the back stack.
            getOnBackPressedDispatcher().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
