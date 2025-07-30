package com.avnishgamedev.clock;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AlarmRingActivity extends AppCompatActivity {

    Button btnCancel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_ring);

        btnCancel = findViewById(R.id.btnCancel);

        btnCancel.setOnClickListener(v -> {
            stopAlarm();
            finish();
        });
    }

    private void stopAlarm() {
        Intent intent = new Intent(this, AlarmService.class);
        stopService(intent);
    }
}
