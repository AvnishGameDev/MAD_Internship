package com.avnishgamedev.expensetracker;

public class Transaction {
    float amount;
    String description;
    boolean isExpense;

    public Transaction(String amount, String description, boolean isExpense) {
        this.amount = Float.parseFloat(amount);
        this.description = description;
        this.isExpense = isExpense;
    }
}
