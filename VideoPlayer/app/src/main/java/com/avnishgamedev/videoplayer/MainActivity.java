package com.avnishgamedev.videoplayer;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    VideoView vwMain;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vwMain = findViewById(R.id.vw_main);

        mediaController = new MediaController(this);
        mediaController.setAnchorView(vwMain);
        vwMain.setMediaController(mediaController);

        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.naturevideo);
        vwMain.setVideoURI(videoUri);
        vwMain.requestFocus();
        vwMain.start();
    }
}