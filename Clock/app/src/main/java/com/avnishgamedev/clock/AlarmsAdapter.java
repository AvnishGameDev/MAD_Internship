package com.avnishgamedev.clock;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.materialswitch.MaterialSwitch;

import java.util.List;

public class AlarmsAdapter extends RecyclerView.Adapter<AlarmsAdapter.ViewHolder> {
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView tvAlarmTime;
        TextView tvAlarmLabel;
        MaterialSwitch swAlarm;
        public ViewHolder(View view) {
            super(view);
            tvAlarmTime = view.findViewById(R.id.tvAlarmTime);
            tvAlarmLabel = view.findViewById(R.id.tvAlarmLabel);
            swAlarm = view.findViewById(R.id.swAlarm);

            view.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            MenuInflater inflater = new MenuInflater(view.getContext());
            inflater.inflate(R.menu.alarm_context_menu, contextMenu);
        }
    }
    public interface OnAlarmEnabledChangeListener {
        void onAlarmEnabledChange(int position, boolean isEnabled);
    }

    private List<Alarm> alarms;
    public int longClickedItemPosition;
    public AlarmsAdapter(List<Alarm> alarms) {
        this.alarms = alarms;
    }

    public void updateData(List<Alarm> newAlarms) {
        // Clear the old list and add the new data
        this.alarms.clear();
        this.alarms.addAll(newAlarms);

        // Notify the adapter that the data has changed
        notifyDataSetChanged();
    }

    OnAlarmEnabledChangeListener listener;
    public void setOnAlarmEnabledChangeListener(OnAlarmEnabledChangeListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Alarm alarm = alarms.get(position);
        holder.tvAlarmTime.setText(String.format("%02d:%02d", alarm.getHour(), alarm.getMinute()));
        holder.tvAlarmLabel.setText(alarm.getLabel());
        holder.swAlarm.setChecked(alarm.isEnabled());
        holder.swAlarm.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (listener != null) {
                listener.onAlarmEnabledChange(position, isChecked);
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            longClickedItemPosition = holder.getAdapterPosition();
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return alarms.size();
    }

}
