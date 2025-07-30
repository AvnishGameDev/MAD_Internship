package com.avnishgamedev.clock;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TimerFragment extends Fragment {

    EditText etHour, etMinute, etSecond;
    Button btnStartPause, btnCancel;

    CountDownTimer countDownTimer;
    boolean isTimerRunning;
    long startTimeInMillis;
    long timeLeftInMillis;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etHour = view.findViewById(R.id.etHour);
        etMinute = view.findViewById(R.id.etMinute);
        etSecond = view.findViewById(R.id.etSecond);
        btnStartPause = view.findViewById(R.id.btnStartPause);
        btnCancel = view.findViewById(R.id.btnCancel);

        btnStartPause.setOnClickListener(v -> {
            if (isTimerRunning) {
                pauseTimer();
            } else {
                startTimer();
            }
        });

        btnCancel.setOnClickListener(v -> resetTimer());

        updateCountdownText();
    }

    private void startTimer() {
        isTimerRunning = true;
        updateInputEnabled();
        long hour = Long.parseLong(etHour.getText().toString());
        long minute = Long.parseLong(etMinute.getText().toString());
        long second = Long.parseLong(etSecond.getText().toString());
        timeLeftInMillis = (hour * 60 * 60 + minute * 60 + second) * 1000;
        startTimeInMillis = timeLeftInMillis;
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountdownText();
            }
            @Override
            public void onFinish() {
                isTimerRunning = false;
                updateInputEnabled();
                Toast.makeText(requireContext(), "Timer Finished!", Toast.LENGTH_SHORT).show();
                playSound();
                resetTimer();
            }
        }.start();
        btnStartPause.setText("Pause");
    }

    private void updateInputEnabled() {
        etHour.setEnabled(!isTimerRunning);
        etMinute.setEnabled(!isTimerRunning);
        etSecond.setEnabled(!isTimerRunning);
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        isTimerRunning = false;
        updateInputEnabled();
        updateButtons();
    }

    private void resetTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        timeLeftInMillis = 0;
        isTimerRunning = false;
        updateInputEnabled();
        updateCountdownText();
        btnStartPause.setText("Start");
        btnCancel.setEnabled(false);
    }

    private void updateCountdownText() {
        long hours = timeLeftInMillis / (1000 * 60 * 60);
        long minutes = (timeLeftInMillis / (1000 * 60)) % 60;
        long seconds = (timeLeftInMillis / 1000) % 60;
        etHour.setText(String.format("%02d", hours));
        etMinute.setText(String.format("%02d", minutes));
        etSecond.setText(String.format("%02d", seconds));
    }

    private void updateButtons() {
        if (isTimerRunning) {
            btnStartPause.setText("Pause");
        } else {
            btnStartPause.setText("Start");

            if (timeLeftInMillis < startTimeInMillis) // Paused
                btnStartPause.setText("Resume");
        }
    }

    private void playSound() {
        // You can use the same alarm sound from your previous features
        try {
            MediaPlayer mediaPlayer = MediaPlayer.create(requireContext(), R.raw.alarm);
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        // Ensure the timer is cancelled if the user leaves the fragment
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
