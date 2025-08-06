package com.avnishgamedev.academica.adapters;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.avnishgamedev.academica.R;
import com.avnishgamedev.academica.models.Reminder;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class RemindersAdapter extends RecyclerView.Adapter<RemindersAdapter.ReminderViewHolder> {
    private List<Reminder> reminders;
    private OnReminderClickListener editListener;
    private OnReminderClickListener deleteListener;
    private OnReminderClickListener toggleListener;

    public interface OnReminderClickListener {
        void onReminderClick(Reminder reminder);
    }

    public RemindersAdapter(List<Reminder> reminders, OnReminderClickListener editListener, 
                          OnReminderClickListener deleteListener, OnReminderClickListener toggleListener) {
        this.reminders = reminders;
        this.editListener = editListener;
        this.deleteListener = deleteListener;
        this.toggleListener = toggleListener;
    }

    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reminder, parent, false);
        return new ReminderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
        Reminder reminder = reminders.get(position);
        holder.bind(reminder);
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }

    class ReminderViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle, textDescription, textDateTime;
        private CheckBox checkBoxCompleted;
        private ImageButton buttonEdit, buttonDelete;

        public ReminderViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDescription = itemView.findViewById(R.id.textDescription);
            textDateTime = itemView.findViewById(R.id.textDateTime);
            checkBoxCompleted = itemView.findViewById(R.id.checkBoxCompleted);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }

        public void bind(Reminder reminder) {
            textTitle.setText(reminder.getTitle());
            textDescription.setText(reminder.getDescription());
            
            // Format date and time
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy 'at' HH:mm", Locale.getDefault());
            textDateTime.setText(sdf.format(reminder.getDateTime()));

            checkBoxCompleted.setChecked(reminder.isCompleted());
            
            // Strike through text if completed
            if (reminder.isCompleted()) {
                textTitle.setPaintFlags(textTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                textDescription.setPaintFlags(textDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                textTitle.setPaintFlags(textTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                textDescription.setPaintFlags(textDescription.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }

            checkBoxCompleted.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked != reminder.isCompleted()) {
                    toggleListener.onReminderClick(reminder);
                }
            });

            buttonEdit.setOnClickListener(v -> editListener.onReminderClick(reminder));
            buttonDelete.setOnClickListener(v -> deleteListener.onReminderClick(reminder));
        }
    }
}
