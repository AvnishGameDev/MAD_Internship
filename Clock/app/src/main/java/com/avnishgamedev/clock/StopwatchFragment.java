package com.avnishgamedev.clock;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

public class StopwatchFragment extends Fragment {

    TextView tvTime;
    Button btnReset, btnStartPause;

    private boolean isRunning = false;
    private long startTime = 0L;
    private long timeWhenPaused = 0L;

    private Handler handler = new Handler(Looper.getMainLooper());

    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime + timeWhenPaused;
            tvTime.setText(formatTime(millis));
            handler.postDelayed(this, 10); // Update every 10 milliseconds
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_stopwatch, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTime = view.findViewById(R.id.tvTime);
        btnReset = view.findViewById(R.id.btnReset);
        btnStartPause = view.findViewById(R.id.btnStartPause);

        btnStartPause.setOnClickListener(v -> {
            if (isRunning) {
                pauseTimer();
            } else {
                startTimer();
            }
        });

        btnReset.setOnClickListener(v -> resetTimer());
    }

    private void startTimer() {
        isRunning = true;
        startTime = System.currentTimeMillis();
        handler.post(timerRunnable);
        btnStartPause.setText("Pause");
        btnReset.setEnabled(false);
    }

    private void pauseTimer() {
        isRunning = false;
        timeWhenPaused += System.currentTimeMillis() - startTime;
        handler.removeCallbacks(timerRunnable);
        btnStartPause.setText("Resume");
        btnReset.setEnabled(true);
    }

    private void resetTimer() {
        timeWhenPaused = 0L;
        tvTime.setText("00:00:00.00");
        btnStartPause.setText("Start");
        btnReset.setEnabled(false);
    }

    private String formatTime(long millis) {
        // Calculate the different time units
        int hours = (int) (millis / (1000 * 60 * 60));
        int minutes = (int) ((millis / (1000 * 60)) % 60);
        int seconds = (int) ((millis / 1000) % 60);
        int centiseconds = (int) ((millis / 10) % 100);

        // Use String.format() to ensure two digits for each unit
        return String.format(Locale.getDefault(), "%02d:%02d:%02d.%02d", hours, minutes, seconds, centiseconds);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop the handler to prevent leaks if the timer is running
        handler.removeCallbacks(timerRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        // Resume the timer if it was running
        if (isRunning) {
            startTime = System.currentTimeMillis(); // Recalibrate start time
            handler.post(timerRunnable);
        }
    }
}
