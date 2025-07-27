package com.avnishgamedev.diaryapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DiaryEntryAdapter extends RecyclerView.Adapter<DiaryEntryAdapter.ViewHolder> {
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvContent;
        TextView tvDate;
        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }

    private List<DiaryDBHelper.DiaryEntry> entries;
    public DiaryEntryAdapter(List<DiaryDBHelper.DiaryEntry> entries) {
        this.entries = entries;
    }

    public void setEntries(List<DiaryDBHelper.DiaryEntry> entries) {
        this.entries = entries;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diary_entry, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DiaryDBHelper.DiaryEntry entry = entries.get(position);
        holder.tvTitle.setText(entry.title);
        holder.tvContent.setText(entry.content);
        holder.tvDate.setText(entry.date);

        // Set click listener for the item view
        holder.itemView.setOnClickListener(v -> {
            Intent i = new Intent(v.getContext(), ViewEntryActivity.class);
            i.putExtra("ENTRY_TITLE", entry.title);
            i.putExtra("ENTRY_CONTENT", entry.content);
            v.getContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }
}
