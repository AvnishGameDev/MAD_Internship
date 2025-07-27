package com.avnishgamedev.audiorecorder;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements AudioListAdapter.OnItemClickListener {
    private static final int AUDIO_FILE_REQUEST_CODE = 1001;
    private static final String SHARED_PREFS_NAME = "AudioRecorderPrefs";
    private static final String AUDIO_LIST_KEY = "AudioList";

    RecyclerView rvAudioList;
    Button btnRecord;
    AudioListAdapter adapter;

    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    private boolean isRecording = false;

    private Audio currentRecordingAudio;

    private List<Audio> audioList;
    private final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvAudioList = findViewById(R.id.rvAudioList);
        btnRecord = findViewById(R.id.btnRecord);

        checkPermission();
        loadAudioList();
        setupRecyclerView();

        btnRecord.setOnClickListener(v -> {
            if (isRecording) {
                stopRecording();
            } else {
                startRecordingFlow();
            }
        });
    }

    private void startRecordingFlow() {
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        // ✨ CHANGE 1: Set the MIME type to audio/mp4 for .m4a files
        intent.setType("audio/mp4");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.getDefault());
        String currentDateTime = sdf.format(new Date());
        // ✨ CHANGE 2: Change the file extension to .m4a
        String fileName = "Recording_" + currentDateTime + ".m4a";

        intent.putExtra(Intent.EXTRA_TITLE, fileName);
        startActivityForResult(intent, AUDIO_FILE_REQUEST_CODE);
    }

    public void startRecordingToFileDescriptor(FileDescriptor fd, Uri fileUri) {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        // ✨ CHANGE 3: Set the output format to MPEG_4
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        // ✨ CHANGE 4: Set the audio encoder to AAC (a good default for M4A)
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mediaRecorder.setOutputFile(fd);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            isRecording = true;
            btnRecord.setText("Stop");
            Toast.makeText(this, "Recording started", Toast.LENGTH_SHORT).show();

            String fileName = getFileNameFromUri(fileUri);
            String createdAt = new SimpleDateFormat("dd/MM/yyyy HH:mm a", Locale.getDefault()).format(System.currentTimeMillis());

            Audio newAudio = new Audio(fileName, createdAt, "", fileUri.toString());

            currentRecordingAudio = newAudio;

            audioList.add(newAudio);
            adapter.notifyItemInserted(audioList.size() - 1);
            rvAudioList.scrollToPosition(audioList.size() - 1);

            saveAudioList();

        } catch (IOException e) {
            e.printStackTrace();
            isRecording = false;
            btnRecord.setText("Record");
        }
    }

    // ... The rest of the file is unchanged ...

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 200);
        }
    }

    private void loadAudioList() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(AUDIO_LIST_KEY, null);
        Type type = new TypeToken<ArrayList<Audio>>() {}.getType();
        audioList = gson.fromJson(json, type);

        if (audioList == null) {
            audioList = new ArrayList<>();
        }
    }

    private void setupRecyclerView() {
        rvAudioList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AudioListAdapter(audioList, this);
        rvAudioList.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AUDIO_FILE_REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                Uri uri = data.getData();
                try {
                    getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    ParcelFileDescriptor pfd = getContentResolver().openFileDescriptor(uri, "w");
                    if (pfd != null) {
                        FileDescriptor fd = pfd.getFileDescriptor();
                        startRecordingToFileDescriptor(fd, uri);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Failed to open file for recording.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void stopRecording() {
        if (mediaRecorder != null) {
            try {
                mediaRecorder.stop();
                mediaRecorder.release();
            } catch (RuntimeException e) {
                e.printStackTrace();
            } finally {
                mediaRecorder = null;
                isRecording = false;
                btnRecord.setText("Record");
                Toast.makeText(this, "Recording stopped", Toast.LENGTH_SHORT).show();

                if (currentRecordingAudio != null) {
                    updateDuration(currentRecordingAudio);
                    currentRecordingAudio = null;
                }
            }
        }
    }

    private void updateDuration(Audio audio) {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            Uri audioUri = Uri.parse(audio.fileUri);
            retriever.setDataSource(this, audioUri);
            String durationStr = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);

            long timeInMillis = Long.parseLong(durationStr);
            String formattedDuration = String.format(Locale.getDefault(), "%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(timeInMillis) % 60,
                    TimeUnit.MILLISECONDS.toSeconds(timeInMillis) % 60
            );

            audio.duration = formattedDuration;

            int itemIndex = audioList.indexOf(audio);
            if (itemIndex != -1) {
                adapter.notifyItemChanged(itemIndex);
            }

            saveAudioList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                retriever.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saveAudioList() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String json = gson.toJson(audioList);
        editor.putString(AUDIO_LIST_KEY, json);
        editor.apply();
    }

    private String getFileNameFromUri(Uri uri) {
        String result = null;
        if (uri.getScheme() != null && uri.getScheme().equals("content")) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    if (nameIndex != -1) {
                        result = cursor.getString(nameIndex);
                    }
                }
            }
        }
        if (result == null) {
            result = uri.getPath();
            if (result != null) {
                int cut = result.lastIndexOf('/');
                if (cut != -1) {
                    result = result.substring(cut + 1);
                }
            }
        }
        return result != null ? result : "Unknown File";
    }

    @Override
    public void onItemClick(Audio audio) {
        playAudio(audio);
    }

    private void playAudio(Audio audio) {
        stopPlaying();

        btnRecord.setEnabled(false);
        Toast.makeText(this, "Playing...", Toast.LENGTH_SHORT).show();

        mediaPlayer = new MediaPlayer();
        try {
            Uri audioUri = Uri.parse(audio.fileUri);
            mediaPlayer.setDataSource(getApplicationContext(), audioUri);

            mediaPlayer.setOnPreparedListener(mp -> mp.start());

            mediaPlayer.setOnCompletionListener(mp -> {
                Toast.makeText(this, "Playback finished", Toast.LENGTH_SHORT).show();
                stopPlaying();
            });

            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to play audio", Toast.LENGTH_SHORT).show();
            stopPlaying();
        }
    }

    private void stopPlaying() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        btnRecord.setEnabled(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (isRecording) {
            stopRecording();
        }
        stopPlaying();
    }
}