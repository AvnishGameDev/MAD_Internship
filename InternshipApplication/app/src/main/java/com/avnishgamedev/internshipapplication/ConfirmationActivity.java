package com.avnishgamedev.internshipapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        TextView txtShareConfirmation = findViewById(R.id.TXT_ShareConfirmation);
        Button btnConfirmAndShare = findViewById(R.id.BTN_ConfirmAndShare);

        final String name = getIntent().getStringExtra("name");
        final String phone = getIntent().getStringExtra("phone");
        final String email = getIntent().getStringExtra("email");

        StringBuilder str = new StringBuilder();
        str.append("Contact Details:\n");
        str.append("Name: ");
        str.append(name);
        str.append("\n");
        str.append("Phone: ");
        str.append(phone);
        str.append("\n");
        str.append("E-Mail: ");
        str.append(email);

        txtShareConfirmation.setText(str.toString());

        btnConfirmAndShare.setOnClickListener(v -> {
            Intent i = new Intent();
            i.putExtra("string", ((TextView)findViewById(R.id.TXT_ShareConfirmation)).getText().toString());
            setResult(RESULT_OK, i);
            finish();
       });
    }
}
