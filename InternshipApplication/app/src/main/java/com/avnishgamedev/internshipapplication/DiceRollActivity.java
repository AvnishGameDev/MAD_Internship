package com.avnishgamedev.internshipapplication;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DiceRollActivity extends AppCompatActivity {
    int diceNumber = 1;
    Random rand;
    ImageView imgDice;
    TextView txtDiceNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_roll);

        imgDice = findViewById(R.id.IMG_Dice);
        txtDiceNumber = findViewById(R.id.TXT_DiceNumber);
        Button btnDiceRoll = findViewById(R.id.BTN_DiceRoll);

        rand = new Random();

        btnDiceRoll.setOnClickListener(v -> {
            Handler handler = new Handler();
            btnDiceRoll.setEnabled(false);
            Runnable r = new Runnable() {
                int elapsed = 0;
                int interval = 100;
                int end = 2000;
                @Override
                public void run() {
                    diceNumber = rand.nextInt(6) + 1;
                    setDiceNumberImageAndText(diceNumber);
                    elapsed += interval;

                    if (elapsed < end)
                        handler.postDelayed(this, interval);
                    else {
                        diceNumber = rand.nextInt(6) + 1;
                        setDiceNumberImageAndText(diceNumber);
                        btnDiceRoll.setEnabled(true);
                    }
                }
            };
            handler.post(r);
        });
    }

    private void setDiceNumberImageAndText(int n) {
        diceNumber = n;
        switch (diceNumber) {
            case 1:
                imgDice.setImageResource(R.drawable.dice_one);
                txtDiceNumber.setText("One");
                break;
            case 2:
                imgDice.setImageResource(R.drawable.dice_two);
                txtDiceNumber.setText("Two");
                break;
            case 3:
                imgDice.setImageResource(R.drawable.dice_three);
                txtDiceNumber.setText("Three");
                break;
            case 4:
                imgDice.setImageResource(R.drawable.dice_four);
                txtDiceNumber.setText("Four");
                break;
            case 5:
                imgDice.setImageResource(R.drawable.dice_five);
                txtDiceNumber.setText("Five");
                break;
            case 6:
                imgDice.setImageResource(R.drawable.dice_six);
                txtDiceNumber.setText("Six");
                break;
            default:
                break;
        }
    }
}
