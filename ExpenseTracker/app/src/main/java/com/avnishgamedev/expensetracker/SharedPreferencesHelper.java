package com.avnishgamedev.expensetracker;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken; // Crucial for lists
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesHelper {

    private static final String PREFS_NAME = "MyExpensePrefs"; // Your SharedPreferences file name
    private static final String TRANSACTIONS_KEY = "userTransactionsList"; // Key for storing the list
    private Gson gson = new Gson();

    public void saveTransactions(Context context, List<Transaction> transactions) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String jsonTransactions = gson.toJson(transactions); // Convert List<Transaction> to JSON string
        editor.putString(TRANSACTIONS_KEY, jsonTransactions);
        editor.apply(); // Asynchronous save
    }

    public List<Transaction> loadTransactions(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String jsonTransactions = sharedPreferences.getString(TRANSACTIONS_KEY, null); // Get JSON string

        if (jsonTransactions == null) {
            return new ArrayList<>(); // Return empty list if nothing saved
        }

        // IMPORTANT: Define the type of the list for Gson
        Type type = new TypeToken<ArrayList<Transaction>>() {}.getType();
        return gson.fromJson(jsonTransactions, type); // Convert JSON string back to List<Transaction>
    }
}