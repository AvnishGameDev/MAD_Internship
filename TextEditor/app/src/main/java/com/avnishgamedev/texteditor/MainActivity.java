package com.avnishgamedev.texteditor;

import android.app.ComponentCaller;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int CREATE_FILE = 2;
    private static final int PICK_TEXT_FILE = 1;
    private Uri currentUri = null;

    EditText etMain;
    Button btnOpen;
    Button btnCreate;
    Button btnSave;
    Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMain = findViewById(R.id.etMain);

        btnOpen = findViewById(R.id.btnOpen);
        btnOpen.setOnClickListener(v -> openFilePicker());

        btnCreate = findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(v -> create());

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(v -> saveFile());

        btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(v -> share());
    }

    private void saveFile() {
        if (currentUri != null) {
            try {
                FileUtils.writeTextToUri(this, currentUri, etMain.getText().toString());
                Toast.makeText(this, "File saved", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Could not save file", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void create() {
        Intent i = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TITLE, "newFile.txt");
        startActivityForResult(i, CREATE_FILE);
    }

    private void share() {
        String text = etMain.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(intent);
    }

    private void openFilePicker() {
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("text/plain");
        startActivityForResult(i, PICK_TEXT_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data, @NonNull ComponentCaller caller) {
        super.onActivityResult(requestCode, resultCode, data, caller);
        if (data != null && resultCode == RESULT_OK) {
            switch (requestCode) {
                case CREATE_FILE:
                    currentUri = data.getData();
                    try {
                        FileUtils.writeTextToUri(this, currentUri, etMain.getText().toString());
                        etMain.setText("");
                        RecentFilesManager.addRecentFile(this, currentUri.toString());
                        Toast.makeText(this, "File created", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Could not create file", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case PICK_TEXT_FILE:
                    currentUri = data.getData();
                    try {
                        etMain.setText(FileUtils.readTextFromUri(this, currentUri));
                        RecentFilesManager.addRecentFile(this, currentUri.toString());
                        Toast.makeText(this, "File opened", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Could not open file", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }
}