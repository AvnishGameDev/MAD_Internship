package com.avnishgamedev.clock;

import static android.content.Context.ALARM_SERVICE;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AlarmFragment extends Fragment {

    TextView tvAlarmStatus;
    RecyclerView rvAlarms;
    Button btnNewAlarm;

    AlarmsDBHelper db;
    List<Alarm> alarms;
    AlarmsAdapter adapter;

    AlarmManager alarmManager;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);

        tvAlarmStatus = view.findViewById(R.id.tvAlarmStatus);
        rvAlarms = view.findViewById(R.id.rvAlarms);
        btnNewAlarm = view.findViewById(R.id.btnNewAlarm);
        btnNewAlarm.setOnClickListener(v -> {
            AlarmBottomSheetFragment bottomSheetFragment = new AlarmBottomSheetFragment();
            bottomSheetFragment.show(getParentFragmentManager(), bottomSheetFragment.getTag());
        });

        // Load stored alarms
        db = new AlarmsDBHelper(getContext());
        alarms = db.getAllAlarms();

        // Set up RecyclerView
        adapter = new AlarmsAdapter(alarms);
        rvAlarms.setAdapter(adapter);

        registerForContextMenu(rvAlarms);

        // Listen for Bottom Sheet dismissal
        getParentFragmentManager().setFragmentResultListener("bottomSheetDismissed", this, (requestKey, bundle) -> {
            executor.execute(() -> {
                List<Alarm> updatedAlarms = db.getAllAlarms();
                handler.post(() -> adapter.updateData(updatedAlarms));
                handler.post(this::setAlarmStatus);
            });
        });

        adapter.setOnAlarmEnabledChangeListener((position, isEnabled) -> {
            Alarm alarm = alarms.get(position);
            if (alarm.isEnabled() != isEnabled) {
                // State change
                Intent intent = new Intent(getContext(), AlarmReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        getContext(),
                        alarm.getId(),
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
                );
                if (isEnabled) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(Calendar.HOUR_OF_DAY, alarm.getHour());
                    calendar.set(Calendar.MINUTE, alarm.getMinute());
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);

                    // If the trigger time you set is in the past, add one day to schedule it for tomorrow
                    if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
                        calendar.add(Calendar.DAY_OF_YEAR, 1);
                    }
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        if (alarmManager.canScheduleExactAlarms()) {
                            alarmManager.setExactAndAllowWhileIdle(
                                    AlarmManager.RTC_WAKEUP,
                                    calendar.getTimeInMillis(),
                                    pendingIntent
                            );
                        }
                    } else {
                        alarmManager.setExact(
                                AlarmManager.RTC_WAKEUP,
                                calendar.getTimeInMillis(),
                                pendingIntent
                        );
                    }
                    alarm.setEnabled(true);
                    db.updateAlarm(alarm);

                    Toast.makeText(getContext(), "Alarm enabled", Toast.LENGTH_SHORT).show();
                    setAlarmStatus();
                } else {
                    alarmManager.cancel(pendingIntent);
                    alarm.setEnabled(false);
                    db.updateAlarm(alarm);
                    Toast.makeText(getContext(), "Alarm disabled", Toast.LENGTH_SHORT).show();
                    setAlarmStatus();
                }
            }
        });

        alarmManager = (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);

        setAlarmStatus();

        return view;
    }

    private void setAlarmStatus() {
        int i = 0;
        for (Alarm alarm : alarms) {
            if (alarm.isEnabled()) {
                i++;
            }
        }
        if (i == 0) {
            tvAlarmStatus.setText("All alarms turned off");
        } else {
            tvAlarmStatus.setText(i + " alarms turned on");
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Alarm selectedAlarm = alarms.get(adapter.longClickedItemPosition);
        if (item.getItemId() == R.id.menu_delete) {
            Intent intent = new Intent(getContext(), AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    getContext(),
                    selectedAlarm.getId(),
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );
            db.deleteAlarm(selectedAlarm.getId());
            alarms.remove(selectedAlarm);
            adapter.notifyItemRemoved(adapter.longClickedItemPosition);
            Toast.makeText(getContext(), "Alarm Deleted", Toast.LENGTH_SHORT).show();
            setAlarmStatus();
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
