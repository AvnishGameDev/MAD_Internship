package com.avnishgamedev.internshipapplication;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class CoinFlipActivity extends AppCompatActivity {
    Random rand;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin_flip);

        ImageView imgCoinFlip = findViewById(R.id.IMG_CoinFlip);
        TextView txtHeadsOrTails = findViewById(R.id.TXT_HeadsOrTails);
        Button btnFlip = findViewById(R.id.BTN_Flip);

        rand = new Random();

        btnFlip.setOnClickListener(v -> {
            Handler handler = new Handler();
            btnFlip.setEnabled(false);
            Runnable r = new Runnable() {
                boolean bHeads = true;
                int elapsed = 0;
                int interval = 100;
                int end = 2000;
                @Override
                public void run() {
                    imgCoinFlip.setImageResource(bHeads ? R.drawable.coin_tails : R.drawable.coin_heads);
                    txtHeadsOrTails.setText(bHeads ? "Tails" : "Heads");
                    bHeads = !bHeads;
                    elapsed += interval;

                    if (elapsed < end)
                        handler.postDelayed(this, interval);
                    else {
                        btnFlip.setEnabled(true);
                        if (rand.nextInt(2) == 1) {
                            imgCoinFlip.setImageResource(R.drawable.coin_heads);
                            txtHeadsOrTails.setText("Heads");
                        } else {
                            imgCoinFlip.setImageResource(R.drawable.coin_tails);
                            txtHeadsOrTails.setText("Tails");
                        }
                    }
                }
            };
            handler.post(r);
        });
    }
}
