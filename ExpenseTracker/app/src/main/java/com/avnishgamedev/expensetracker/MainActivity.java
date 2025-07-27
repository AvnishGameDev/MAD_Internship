package com.avnishgamedev.expensetracker;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etAmount;
    EditText etDescription;
    RadioGroup rgType;
    Button btnSubmit;
    TextView tvBalance;
    RecyclerView rvTransactions;
    TransactionListAdapter adapter;

    List<Transaction> transactionList;
    SharedPreferencesHelper prefsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAmount = findViewById(R.id.etAmount);
        etDescription = findViewById(R.id.etDescription);
        rgType = findViewById(R.id.rgType);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvBalance = findViewById(R.id.tvBalance);
        rvTransactions = findViewById(R.id.rvTransactions);

        prefsHelper = new SharedPreferencesHelper();

        // Load transactions when the app starts
        transactionList = prefsHelper.loadTransactions(this);
        if (transactionList == null) { // Double check for safety, though loadTransactions handles it
            transactionList = new ArrayList<>();
        }

        transactionList.forEach(transaction -> {
            float balanceChange = transaction.isExpense ? -transaction.amount : transaction.amount;
            float currentBalance = Float.parseFloat(tvBalance.getText().toString().split(": ")[1]);
            tvBalance.setText("Balance: " + (currentBalance + balanceChange));
        });

        adapter = new TransactionListAdapter(transactionList);
        rvTransactions.setAdapter(adapter);

        btnSubmit.setOnClickListener(v -> {
            String amount = etAmount.getText().toString();
            String description = etDescription.getText().toString();
            boolean isExpense = rgType.getCheckedRadioButtonId() == R.id.rbExpense;
            transactionList.add(new Transaction(amount, description, isExpense));
            adapter.notifyItemInserted(transactionList.size() - 1);
            float balanceChange = isExpense ? -Float.parseFloat(amount) : Float.parseFloat(amount);
            float currentBalance = Float.parseFloat(tvBalance.getText().toString().split(": ")[1]);
            tvBalance.setText("Balance: " + (currentBalance + balanceChange));
            prefsHelper.saveTransactions(this, transactionList);
            etAmount.setText("");
            etDescription.setText("");
            rgType.check(R.id.rbExpense);
        });
    }
}