package com.avnishgamedev.internshipapplication;

import android.app.ComponentCaller;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShareContactActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;

    String name;
    String phone;
    String email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_contact);

        EditText edtName = findViewById(R.id.EDT_Name);
        EditText edtPhoneNumber = findViewById(R.id.EDT_PhoneNumber);
        EditText edtEmail = findViewById(R.id.EDT_email);

        Button btnShare = findViewById(R.id.BTN_Share);
        btnShare.setOnClickListener(v -> {
            Intent i = new Intent(ShareContactActivity.this, ConfirmationActivity.class);
            name = edtName.getText().toString();
            phone = edtPhoneNumber.getText().toString();
            email = edtEmail.getText().toString();
            i.putExtra("name", name);
            i.putExtra("phone", phone);
            i.putExtra("email", email);
            startActivityForResult(i, REQUEST_CODE);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data, @NonNull ComponentCaller caller) {
        super.onActivityResult(requestCode, resultCode, data, caller);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            ((TextView)findViewById(R.id.TXT_ConfirmedOrNot)).setText("Confirmed");

            Intent i = new Intent(Intent.ACTION_SEND);
            i.putExtra(Intent.EXTRA_TEXT, data.getStringExtra("string"));
            i.setType("text/plain");
            startActivity(Intent.createChooser(i, "Share contact via"));
        } else {
            ((TextView)findViewById(R.id.TXT_ConfirmedOrNot)).setText("Not Confirmed");
        }
    }
}
