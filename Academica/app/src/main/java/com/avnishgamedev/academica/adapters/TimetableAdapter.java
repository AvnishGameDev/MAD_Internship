package com.avnishgamedev.academica.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avnishgamedev.academica.R;
import com.avnishgamedev.academica.models.TimetableEntry;

import java.util.List;

public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.TimetableViewHolder> {
    private List<TimetableEntry> entries;
    private OnEntryClickListener editListener;
    private OnEntryClickListener deleteListener;

    public interface OnEntryClickListener {
        void onEntryClick(TimetableEntry entry);
    }

    public TimetableAdapter(List<TimetableEntry> entries, OnEntryClickListener editListener, OnEntryClickListener deleteListener) {
        this.entries = entries;
        this.editListener = editListener;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public TimetableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timetable, parent, false);
        return new TimetableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimetableViewHolder holder, int position) {
        TimetableEntry entry = entries.get(position);
        holder.bind(entry);
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    class TimetableViewHolder extends RecyclerView.ViewHolder {
        private TextView textSubject, textProfessor, textClassroom, textTime, textDay;
        private ImageButton buttonEdit, buttonDelete;

        public TimetableViewHolder(@NonNull View itemView) {
            super(itemView);
            textSubject = itemView.findViewById(R.id.textSubject);
            textProfessor = itemView.findViewById(R.id.textProfessor);
            textClassroom = itemView.findViewById(R.id.textClassroom);
            textTime = itemView.findViewById(R.id.textTime);
            textDay = itemView.findViewById(R.id.textDay);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }

        public void bind(TimetableEntry entry) {
            textSubject.setText(entry.getSubjectName());
            textProfessor.setText("Prof. " + entry.getProfessorName());
            
            if (entry.getClassroom() != null && !entry.getClassroom().isEmpty()) {
                textClassroom.setText("Room: " + entry.getClassroom());
                textClassroom.setVisibility(View.VISIBLE);
            } else {
                textClassroom.setVisibility(View.GONE);
            }
            
            textTime.setText(entry.getStartTime() + " - " + entry.getEndTime());
            textDay.setText(entry.getDayOfWeek());

            buttonEdit.setOnClickListener(v -> editListener.onEntryClick(entry));
            buttonDelete.setOnClickListener(v -> deleteListener.onEntryClick(entry));
        }
    }
}
