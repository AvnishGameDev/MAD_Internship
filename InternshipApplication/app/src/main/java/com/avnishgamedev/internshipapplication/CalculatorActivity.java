package com.avnishgamedev.internshipapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CalculatorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        EditText edtFirstNumber = findViewById(R.id.EDT_FirstNumber);
        EditText edtSecondNumber = findViewById(R.id.EDT_SecondNumber);

        Button btnMultiply = findViewById(R.id.BTN_Multiply);
        Button btnAdd = findViewById(R.id.BTN_Add);
        Button btnDivide = findViewById(R.id.BTN_Divide);
        Button btnSubtract = findViewById(R.id.BTN_Subtract);

        TextView txtCalculatorAnswer = findViewById(R.id.TXT_CalculatorAnswer);

        btnMultiply.setOnClickListener(v -> {
            double firstNumber = Double.parseDouble(edtFirstNumber.getText().toString());
            double secondNumber = Double.parseDouble(edtSecondNumber.getText().toString());
            txtCalculatorAnswer.setText("Answer: " + (firstNumber * secondNumber));
        });
        btnAdd.setOnClickListener(v -> {
            double firstNumber = Double.parseDouble(edtFirstNumber.getText().toString());
            double secondNumber = Double.parseDouble(edtSecondNumber.getText().toString());
            txtCalculatorAnswer.setText("Answer: " + (firstNumber + secondNumber));
        });
        btnDivide.setOnClickListener(v -> {
            double firstNumber = Double.parseDouble(edtFirstNumber.getText().toString());
            double secondNumber = Double.parseDouble(edtSecondNumber.getText().toString());
            txtCalculatorAnswer.setText("Answer: " + (firstNumber / secondNumber));
        });
        btnSubtract.setOnClickListener(v -> {
            double firstNumber = Double.parseDouble(edtFirstNumber.getText().toString());
            double secondNumber = Double.parseDouble(edtSecondNumber.getText().toString());
            txtCalculatorAnswer.setText("Answer: " + (firstNumber - secondNumber));
        });
    }
}
