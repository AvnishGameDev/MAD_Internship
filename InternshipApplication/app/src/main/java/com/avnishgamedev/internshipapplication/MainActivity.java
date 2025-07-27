package com.avnishgamedev.internshipapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTemp = findViewById(R.id.BTN_TempApp);
        Button btnAuthApp = findViewById(R.id.BTN_AuthApp);
        Button btnCheckboxRadioApp = findViewById(R.id.BTN_CheckboxRadioApp);
        Button btnPizzaOrderCustomizerApp = findViewById(R.id.BTN_PizzaOrderCustomizerApp);
        Button btnUTSApp = findViewById(R.id.BTN_UTSApp);
        Button btnCalculatorApp = findViewById(R.id.BTN_CalculatorApp);
        Button btnTempConversionApp = findViewById(R.id.BTN_TempConversionApp);
        Button btnCoinFlipApp = findViewById(R.id.BTN_CoinFlipApp);
        Button btnDiceRollApp = findViewById(R.id.BTN_DiceRollApp);
        Button btnFlipkartClone = findViewById(R.id.BTN_FlipkartClone);
        Button btnShoppingList = findViewById(R.id.BTN_ShoppingList);
        Button btnCollegeList = findViewById(R.id.BTN_CollegeList);
        Button btnNotesApp = findViewById(R.id.BTN_NotesApp);
        Button btnAdvancedShoppingList = findViewById(R.id.BTN_AdvancedShoppingList);

        btnTemp.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TempActivity.class));
        });
        btnAuthApp.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        });
        btnCheckboxRadioApp.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CheckboxRadioButtonActivity.class));
        });
        btnPizzaOrderCustomizerApp.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, PizzaOrderCustomizerActivity.class));
        });
        btnUTSApp.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, UTSActivity.class));
        });
        btnCalculatorApp.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CalculatorActivity.class));
        });
        btnTempConversionApp.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TemperatureConvertorActivity.class));
        });
        btnCoinFlipApp.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CoinFlipActivity.class));
        });
        btnDiceRollApp.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, DiceRollActivity.class));
        });
        btnFlipkartClone.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, FlipkartActivity.class));
        });
        btnShoppingList.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, ShoppingListActivity.class));
        });
        btnCollegeList.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CollegeListActivity.class));
        });
        btnNotesApp.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, NotesMainActivity.class));
        });
        btnAdvancedShoppingList.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AdvancedShoppingListActivity.class));
        });
    }
}