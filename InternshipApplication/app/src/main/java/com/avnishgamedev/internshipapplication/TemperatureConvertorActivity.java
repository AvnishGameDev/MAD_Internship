package com.avnishgamedev.internshipapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TemperatureConvertorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_convertor);

        EditText edtTemp = findViewById(R.id.EDT_Temperature);
        TextView txtTempInF = findViewById(R.id.TXT_TempInF);

        Button btnConvert = findViewById(R.id.BTN_Convert);
        btnConvert.setOnClickListener(v -> {
            double temp = Double.parseDouble(edtTemp.getText().toString());
            txtTempInF.setText("Temperature in Fahrenheit: " + (temp * 9 / 5 + 32));
        });
    }
}
