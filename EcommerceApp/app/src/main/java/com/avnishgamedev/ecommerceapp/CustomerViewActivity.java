package com.avnishgamedev.ecommerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CustomerViewActivity extends AppCompatActivity {

    Button btnShowCart;
    RecyclerView rvAvailableProducts;
    ProductListAdapter adapter;

    List<Product> productList;

    DatabaseReference productsRef;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);

        btnShowCart = findViewById(R.id.btnShowCart);
        btnShowCart.setOnClickListener(v -> startActivity(new Intent(CustomerViewActivity.this, CartActivity.class)));
        rvAvailableProducts = findViewById(R.id.rvAvailableProducts);

        productList = new ArrayList<>();

        adapter = new ProductListAdapter(productList);
        adapter.setOnBuyClickListener(this::onProductBuyClicked);

        rvAvailableProducts.setAdapter(adapter);

        productsRef = FirebaseDatabase.getInstance(Constants.DATABASE_URL).getReference("products");

        attachDatabaseReadListener();
    }

    private void attachDatabaseReadListener() {
        productsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                productList.clear();
                for (DataSnapshot productSnapshot : snapshot.getChildren()) {
                    Product product = productSnapshot.getValue(Product.class);
                    productList.add(product);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CustomerViewActivity.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onProductBuyClicked(Product product) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user == null) {
            Toast.makeText(this, "User not logged in!", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = user.getUid();

        DatabaseReference cartsRef = FirebaseDatabase.getInstance(Constants.DATABASE_URL).getReference("carts");
        cartsRef.child(userId).push().setValue(product)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Product added to cart", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error adding to cart: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
