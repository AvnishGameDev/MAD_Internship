package com.avnishgamedev.internshipapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity {

    private ArrayList<ShoppingListItem> items;
    private ShoppingListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoppinglist);

        EditText edtItemName = findViewById(R.id.EDT_ItemName);
        Button btnAddItem = findViewById(R.id.BTN_AddItem);
        ListView lvShoppingList = findViewById(R.id.LV_ShoppingList);

        items = new ArrayList<ShoppingListItem>();
        adapter = new ShoppingListAdapter(this, items);

        btnAddItem.setOnClickListener(v -> {
            String itemName = edtItemName.getText().toString();
            if (!itemName.isEmpty()) {
                ShoppingListItem item = new ShoppingListItem(itemName);
                items.add(item);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Enter Item Name before adding!", Toast.LENGTH_SHORT).show();
            }
        });

        lvShoppingList.setAdapter(adapter);

        lvShoppingList.setOnItemLongClickListener((parent, view, position, id) -> {
            Toast.makeText(this, "Pressed", Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}
