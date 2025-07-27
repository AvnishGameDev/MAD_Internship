package com.avnishgamedev.internshipapplication;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;

public class AdvancedShoppingListActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    ArrayList<String> items;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_shoppinglist);

        Toolbar tbAdvancedShoppingList = findViewById(R.id.tb_advanced_shoppinglist);
        setSupportActionBar(tbAdvancedShoppingList);

        items = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, R.layout.support_advanced_shoppinglist_item, items);

        ListView lvAdvancedShoppingList = findViewById(R.id.lv_advanced_shoppinglist);
        lvAdvancedShoppingList.setAdapter(adapter);

        registerForContextMenu(lvAdvancedShoppingList);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.advanced_shoppinglist_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.i_edit_item) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            if (info == null) return false;
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_advanced_shoppinglist_add_item, null);
            EditText etItemName = dialogView.findViewById(R.id.et_item_name);
            etItemName.setText(items.get(info.position));
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Edit Item")
                    .setView(dialogView)
                    .setPositiveButton("Confirm", (dialog, which) -> {
                        if (etItemName.getText().toString().isEmpty()) {
                            Toast.makeText(this, "Item name cannot be empty!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        items.set(info.position, etItemName.getText().toString());
                        adapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
            return true;
        } else if (item.getItemId() == R.id.i_delete_item) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            if (info == null) return false;
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Delete Item")
                    .setMessage("Are you sure you want to remove " + items.get(info.position) + "?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        items.remove(info.position);
                        adapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("No", (dialog, which) -> {
                    })
                    .show();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.i_add_item) {
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_advanced_shoppinglist_add_item, null);
            EditText etItemName = dialogView.findViewById(R.id.et_item_name);
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Add Item")
                    .setView(dialogView)
                    .setPositiveButton("Add", (dialog, which) -> {
                        if (etItemName.getText().toString().isEmpty()) {
                            Toast.makeText(this, "Item name cannot be empty!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        items.add(etItemName.getText().toString());
                        adapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
            return true;
        } else if (item.getItemId() == R.id.i_clear_items) {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Clear Items")
                    .setMessage("Are you sure you want to clear all items?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        items.clear();
                        adapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        } else if (item.getItemId() == R.id.i_advanced_shoppinglist_about) {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("About")
                    .setMessage("Shopping List v1.0\nDeveloped by Avnish Kirnalli.")
                    .setPositiveButton("OK", null)
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.advanced_shoppinglist_menu, menu);
        return true;
    }
}
