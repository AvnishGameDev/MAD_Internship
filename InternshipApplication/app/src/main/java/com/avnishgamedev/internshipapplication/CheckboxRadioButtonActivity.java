package com.avnishgamedev.internshipapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class CheckboxRadioButtonActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox_radiobutton);

        RadioGroup rgGender = findViewById(R.id.RG_Gender);

        CheckBox cbCricket = findViewById(R.id.CB_Cricket);
        CheckBox cbFootball = findViewById(R.id.CB_Football);
        CheckBox cbStudying = findViewById(R.id.CB_Studying);

        SwitchCompat swIAgree = findViewById(R.id.SW_IAgree);

        Button btnSubmit = findViewById(R.id.BTN_Submit);

        swIAgree.setOnClickListener(v -> btnSubmit.setEnabled(swIAgree.isChecked()));

        btnSubmit.setOnClickListener(v -> {
            int checkedRBId = rgGender.getCheckedRadioButtonId();
            if (checkedRBId != 1) {
                RadioButton rb = findViewById(checkedRBId);
                StringBuilder hobbies = new StringBuilder();
                hobbies.append("Hobbies: ");
                if (cbCricket.isChecked())
                    hobbies.append("Cricket");
                if (cbFootball.isChecked())
                    hobbies.append(", Football");
                if (cbStudying.isChecked())
                    hobbies.append(", Studying");
                Toast.makeText(this, "Selected Gender: " + rb.getText().toString() + " " + hobbies, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "No RadioButton checked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
