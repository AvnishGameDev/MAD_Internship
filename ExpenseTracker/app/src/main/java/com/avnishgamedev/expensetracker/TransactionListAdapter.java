package com.avnishgamedev.expensetracker;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.ViewHolder> {
    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cvTransaction;
        TextView tvAmount;
        TextView tvDescription;
        TextView tvType;
        ViewHolder(View view) {
            super(view);
            cvTransaction = view.findViewById(R.id.cvTransaction);
            tvAmount = view.findViewById(R.id.tvAmount);
            tvDescription = view.findViewById(R.id.tvDescription);
            tvType = view.findViewById(R.id.tvType);
        }
    }

    private List<Transaction> transactionList;
    public TransactionListAdapter(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Transaction transaction = transactionList.get(position);
        holder.cvTransaction.setCardBackgroundColor(transaction.isExpense ? Color.parseColor("#FF2020") : Color.parseColor("#8080FF"));
        holder.tvAmount.setText(String.valueOf(transaction.amount));
        holder.tvDescription.setText(transaction.description);
        holder.tvType.setText(transaction.isExpense ? "Expense" : "Income");
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }
}
