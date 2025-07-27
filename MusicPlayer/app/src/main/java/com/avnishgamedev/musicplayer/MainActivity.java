package com.avnishgamedev.musicplayer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageButton btnHipHop;
    ImageButton btnLofi;
    ImageButton btnClassical;
    ImageButton btnIndie;
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHipHop = findViewById(R.id.btn_hip_hop);
        btnLofi = findViewById(R.id.btn_lofi);
        btnClassical = findViewById(R.id.btn_classical);
        btnIndie = findViewById(R.id.btn_indie);
        btnExit = findViewById(R.id.btn_exit);

        btnExit.setOnClickListener(v -> finish());

        btnHipHop.setOnClickListener(v -> startMusicListActivity(MusicListActivity.GENRE_HIP_HOP));
        btnLofi.setOnClickListener(v -> startMusicListActivity(MusicListActivity.GENRE_LOFI));
        btnClassical.setOnClickListener(v -> startMusicListActivity(MusicListActivity.GENRE_CLASSICAL));
        btnIndie.setOnClickListener(v -> startMusicListActivity(MusicListActivity.GENRE_INDIE));
    }

    private void startMusicListActivity(int genre) {
        Intent i = new Intent(MainActivity.this, MusicListActivity.class);
        i.putExtra("genre", genre);
        startActivity(i);
    }
}