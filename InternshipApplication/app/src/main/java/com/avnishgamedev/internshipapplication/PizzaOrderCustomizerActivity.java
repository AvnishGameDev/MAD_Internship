package com.avnishgamedev.internshipapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PizzaOrderCustomizerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizzaordercustomizer);

        EditText edtName = findViewById(R.id.EDT_Name);

        RadioGroup rgSize = findViewById(R.id.RG_PizzaSize);

        // Toppings
        CheckBox cbExtraCheese = findViewById(R.id.CB_ExtraCheese);
        CheckBox cbPaneer = findViewById(R.id.CB_Paneer);
        CheckBox cbOlives = findViewById(R.id.CB_Olives);
        CheckBox cbJalepenos = findViewById(R.id.CB_Jalepenos);

        EditText edtQuantity = findViewById(R.id.EDT_Quantity);

        Button btnPlaceOrder = findViewById(R.id.BTN_PlaceOrder);

        TextView txtOrderDetails = findViewById(R.id.TXT_OrderDetails);

        btnPlaceOrder.setOnClickListener(v -> {
           StringBuilder str = new StringBuilder();
           str.append("Name: ");
           str.append(edtName.getText().toString());
           str.append("\n");
           str.append("Pizza Size: ");
           str.append(((RadioButton)findViewById(rgSize.getCheckedRadioButtonId())).getText().toString());
           str.append("\n");
           str.append("Toppings: ");
           boolean bFirst = true;
           if (cbExtraCheese.isChecked()) {
               str.append("Extra Cheese");
               bFirst = false;
           }
           if (cbPaneer.isChecked()) {
               if (!bFirst)
                   str.append(", ");
               str.append("Paneer");
               bFirst = false;
           }
           if (cbOlives.isChecked()) {
               if (!bFirst)
                   str.append(", ");
               str.append("Olives");
               bFirst = false;
           }
           if (cbJalepenos.isChecked()) {
               if (!bFirst)
                   str.append(", ");
               str.append("Jalepenos");
               bFirst = false;
           }
           str.append("\n");
           str.append("Quantity: ");
           str.append(edtQuantity.getText().toString());
           txtOrderDetails.setText(str);
        });
    }
}
