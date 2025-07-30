package com.avnishgamedev.clock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class WorldClockAdapter extends RecyclerView.Adapter<WorldClockAdapter.ViewHolder> {
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCityName;
        TextView tvTime;
        public ViewHolder(View view) {
            super(view);

            tvCityName = view.findViewById(R.id.tvCityName);
            tvTime = view.findViewById(R.id.tvTime);
        }
    }

    private final List<String> timeZoneIds;
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
    public WorldClockAdapter(List<String> timeZoneIds) {
        this.timeZoneIds = timeZoneIds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_world_clock, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String zoneIdString = timeZoneIds.get(position);

        try {
            // Get the current time in the specified zone
            ZoneId zoneId = ZoneId.of(zoneIdString);
            ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);

            // Extract a clean city name (e.g., "America/New_York" -> "New York")
            String cityName = zoneIdString.substring(zoneIdString.lastIndexOf('/') + 1).replace('_', ' ');

            // Set the text on the views
            holder.tvCityName.setText(cityName);
            holder.tvTime.setText(zonedDateTime.format(timeFormatter));

        } catch (Exception e) {
            // Handle cases where the time zone ID might be invalid
            holder.tvCityName.setText("Invalid Zone");
            holder.tvTime.setText("--:--");
        }
    }

    @Override
    public int getItemCount() {
        return timeZoneIds.size();
    }
}
