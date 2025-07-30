package com.avnishgamedev.clock;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class WorldClockFragment extends Fragment {

    RecyclerView rvWorldClocks;
    private WorldClockAdapter adapter;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private Runnable updateTimeRunnable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_world_clock, container, false);

        rvWorldClocks = view.findViewById(R.id.rvWorldClocks);

        List<String> timeZoneIds = Arrays.asList(
                "Asia/Kolkata",     // India
                "Europe/London",    // UK
                "America/New_York", // USA
                "Asia/Tokyo",       // Japan
                "Australia/Sydney"  // Australia
        );

        adapter = new WorldClockAdapter(timeZoneIds);
        rvWorldClocks.setAdapter(adapter);

        // Create the runnable to update the clocks
        updateTimeRunnable = new Runnable() {
            @Override
            public void run() {
                if (adapter != null) {
                    adapter.notifyDataSetChanged(); // Refresh the displayed times
                }
                // Schedule the next update in 1 minute
                handler.postDelayed(this, 60000); // 60 seconds
            }
        };

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Start the periodic updates when the fragment is visible
        handler.post(updateTimeRunnable);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop the updates when the fragment is not visible to save battery
        handler.removeCallbacks(updateTimeRunnable);
    }
}
