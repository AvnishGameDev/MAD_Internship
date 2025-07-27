package com.avnishgamedev.audiorecorder;

import android.net.Uri;

public class Audio {
    public String fileName;
    public String createdAt;
    public String duration;
    public String fileUri;
    public boolean isPlaying;

    // Add this no-argument constructor for Gson
    public Audio() {
    }

    public Audio(String fileName, String createdAt, String duration, String fileUri) {
        this.fileName = fileName;
        this.createdAt = createdAt;
        this.duration = duration;
        this.fileUri = fileUri;
    }
}