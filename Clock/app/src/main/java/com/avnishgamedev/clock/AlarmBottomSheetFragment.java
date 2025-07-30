package com.avnishgamedev.clock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AlarmBottomSheetFragment extends BottomSheetDialogFragment {

    private EditText etAlarmLabel;
    private TimePicker timePicker;
    private AlarmManager alarmManager;

    // We need to store the time temporarily for when we return from the settings screen
    private String alarmLabel;
    private int selectedHour;
    private int selectedMinute;

    // Modern way to handle permission requests
    private final ActivityResultLauncher<Intent> requestExactAlarmPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    result -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                            if (alarmManager.canScheduleExactAlarms()) {
                                // Permission granted, now we can set the alarm
                                Log.d("BottomSheet", "Exact alarm permission granted.");
                                setAlarm(alarmLabel, selectedHour, selectedMinute);
                            } else {
                                // Permission denied
                                Log.d("BottomSheet", "Exact alarm permission denied.");
                                Toast.makeText(requireContext(), "Permission denied. Alarm not set.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alarmManager = (AlarmManager) requireActivity().getSystemService(Context.ALARM_SERVICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_add_alarm, container, false);

        etAlarmLabel = view.findViewById(R.id.etAlarmLabel);
        timePicker = view.findViewById(R.id.bottomSheetTimePicker);
        Button setAlarmButton = view.findViewById(R.id.setAlarmSheetButton);

        setAlarmButton.setOnClickListener(v -> {
            // Store the selected time
            alarmLabel = etAlarmLabel.getText().toString();
            selectedHour = timePicker.getHour();
            selectedMinute = timePicker.getMinute();

            // Check for permission and set the alarm
            checkPermissionAndSetAlarm(alarmLabel, selectedHour, selectedMinute);
        });

        return view;
    }

    private void checkPermissionAndSetAlarm(String alarmLabel, int hour, int minute) {
        // For Android 12 (S) and above, we need to check for the permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            if (alarmManager.canScheduleExactAlarms()) {
                // Permission is already granted, set the alarm
                setAlarm(alarmLabel, hour, minute);
            } else {
                // Permission is not granted, request it
                Log.d("BottomSheet", "Requesting exact alarm permission.");
                Intent intent = new Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
                intent.setData(Uri.parse("package:" + requireActivity().getPackageName()));
                requestExactAlarmPermissionLauncher.launch(intent);
            }
        } else {
            // For older versions, no special permission is needed
            setAlarm(alarmLabel, hour, minute);
        }
    }

    private void setAlarm(String alarmLabel, int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1);
        }

        Intent intent = new Intent(requireContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                requireContext(), 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                pendingIntent
        );

        // Store Alarm details locally
        Alarm alarm = new Alarm(
            alarmLabel,
            hour,
            minute,
            true
        );
        new AlarmsDBHelper(getContext()).insertAlarm(alarm);

        Toast.makeText(requireContext(), "Alarm set successfully!", Toast.LENGTH_SHORT).show();
        Log.d("BottomSheet", "Alarm set for: " + calendar.getTime());
        dismiss(); // Close the bottom sheet
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        getParentFragmentManager().setFragmentResult("bottomSheetDismissed", new Bundle());
        super.onDismiss(dialog);
    }
}
