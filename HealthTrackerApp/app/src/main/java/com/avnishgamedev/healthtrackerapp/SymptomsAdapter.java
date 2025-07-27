package com.avnishgamedev.healthtrackerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SymptomsAdapter extends RecyclerView.Adapter<SymptomsAdapter.ViewHolder> {

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSymptom;
        public ViewHolder(View itemView) {
            super(itemView);
            tvSymptom = itemView.findViewById(R.id.tvSymptom);
        }
    }

    private List<String> symptoms;
    public SymptomsAdapter(List<String> symptoms) {
        this.symptoms = symptoms;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_symptom, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvSymptom.setText(symptoms.get(position));
    }

    @Override
    public int getItemCount() {
        return symptoms.size();
    }

}
