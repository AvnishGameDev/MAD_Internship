package com.avnishgamedev.academica.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avnishgamedev.academica.R;
import com.avnishgamedev.academica.models.CGPAEntry;

import java.text.DecimalFormat;
import java.util.List;

public class CGPAAdapter extends RecyclerView.Adapter<CGPAAdapter.CGPAViewHolder> {
    private List<CGPAEntry> entries;
    private OnEntryClickListener editListener;
    private OnEntryClickListener deleteListener;

    public interface OnEntryClickListener {
        void onEntryClick(CGPAEntry entry);
    }

    public CGPAAdapter(List<CGPAEntry> entries, OnEntryClickListener editListener, OnEntryClickListener deleteListener) {
        this.entries = entries;
        this.editListener = editListener;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public CGPAViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cgpa, parent, false);
        return new CGPAViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CGPAViewHolder holder, int position) {
        CGPAEntry entry = entries.get(position);
        holder.bind(entry);
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    class CGPAViewHolder extends RecyclerView.ViewHolder {
        private TextView textSemester, textGPA, textCredits, textSubjects;
        private ImageButton buttonEdit, buttonDelete;

        public CGPAViewHolder(@NonNull View itemView) {
            super(itemView);
            textSemester = itemView.findViewById(R.id.textSemester);
            textGPA = itemView.findViewById(R.id.textGPA);
            textCredits = itemView.findViewById(R.id.textCredits);
            textSubjects = itemView.findViewById(R.id.textSubjects);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }

        public void bind(CGPAEntry entry) {
            textSemester.setText(entry.getSemesterName());
            
            DecimalFormat df = new DecimalFormat("#.##");
            textGPA.setText("GPA: " + df.format(entry.getGpa()));
            textCredits.setText("Credits: " + (int) entry.getTotalCredits());
            
            StringBuilder subjectsText = new StringBuilder();
            for (CGPAEntry.SubjectGrade subject : entry.getSubjects()) {
                if (subjectsText.length() > 0) {
                    subjectsText.append(", ");
                }
                subjectsText.append(subject.getSubjectName())
                           .append(" (")
                           .append(subject.getGrade())
                           .append(")");
            }
            textSubjects.setText(subjectsText.toString());

            buttonEdit.setOnClickListener(v -> editListener.onEntryClick(entry));
            buttonDelete.setOnClickListener(v -> deleteListener.onEntryClick(entry));
        }
    }
}
