package com.avnishgamedev.themeswitcher;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS = "theme_prefs";
    private static final String DARK_MODE = "dark_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        boolean dark = prefs.getBoolean(DARK_MODE, false);
        setTheme(dark ? R.style.AppTheme_Dark : R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SwitchCompat swDarkMode = findViewById(R.id.sw_dark_mode);
        swDarkMode.setChecked(dark);

        swDarkMode.setOnCheckedChangeListener((view, state) -> {
            prefs.edit().putBoolean(DARK_MODE, state).apply();
            recreate();
        });
    }
}