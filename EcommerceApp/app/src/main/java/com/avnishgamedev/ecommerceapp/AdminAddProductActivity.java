package com.avnishgamedev.ecommerceapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminAddProductActivity extends AppCompatActivity {

    EditText etProductName;
    EditText etProductPrice;
    EditText etProductDescription;
    Button btnAddProduct;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_product);

        etProductName = findViewById(R.id.etProductName);
        etProductPrice = findViewById(R.id.etProductPrice);
        etProductDescription = findViewById(R.id.etProductDescription);
        btnAddProduct = findViewById(R.id.btnAddProduct);

        btnAddProduct.setOnClickListener(v -> {
            String productName = etProductName.getText().toString();
            String productPrice = etProductPrice.getText().toString();
            String productDescription = etProductDescription.getText().toString();

            if (productName.isEmpty() || productPrice.isEmpty() || productDescription.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            Product product = new Product(productName, Float.parseFloat(productPrice), productDescription);

            FirebaseDatabase database = FirebaseDatabase.getInstance(Constants.DATABASE_URL);
            DatabaseReference productsRef = database.getReference("products");
            productsRef.push().setValue(product)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Product added successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, "Failed to add product: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        });
    }
}
