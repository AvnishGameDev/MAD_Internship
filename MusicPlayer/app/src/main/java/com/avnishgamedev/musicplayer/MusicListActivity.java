package com.avnishgamedev.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.ArrayList;
import java.util.List;

public class MusicListActivity extends AppCompatActivity {

    public static final int GENRE_HIP_HOP = 1;
    public static final int GENRE_LOFI = 2;
    public static final int GENRE_CLASSICAL = 3;
    public static final int GENRE_INDIE = 4;

    Button btnBack;
    ImageView ivGenreBanner;
    RecyclerView rvMusicList;
    SongAdapter adapter;

    MediaPlayer mediaPlayer;

    Button btnPause;
    Button btnPlay;
    Button btnStop;
    MaterialButtonToggleGroup btgMusicControls;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        btnBack = findViewById(R.id.btn_back);
        ivGenreBanner = findViewById(R.id.iv_genre_banner);
        rvMusicList = findViewById(R.id.rv_music_list);

        btnPause = findViewById(R.id.btn_pause);
        btnPlay = findViewById(R.id.btn_play);
        btnStop = findViewById(R.id.btn_stop);
        btgMusicControls = findViewById(R.id.btg_music_controls);

        btnBack.setOnClickListener(v -> finish());

        List<Song> songs = new ArrayList<>();

        switch (getIntent().getIntExtra("genre", -1)) {
            case GENRE_HIP_HOP:
                ivGenreBanner.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.hip_hop_banner));
                songs.add(new Song("Third Eye Freeverse", "Hanumankind", R.raw.hiphop_third_eye_freeverse));
                songs.add(new Song("Not Like Us", "Kendrick Lamar", R.raw.hiphop_not_like_us));
                break;
            case GENRE_LOFI:
                ivGenreBanner.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.lofi_banner));
                songs.add(new Song("Lofi Piano", "Unknown", R.raw.lofi_piano));
                songs.add(new Song("Lofi Chill Beats", "Unknown", R.raw.lofi_chill_beats));
                break;
            case GENRE_CLASSICAL:
                ivGenreBanner.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.classical_banner));
                songs.add(new Song("After the Rain", "Keys of Moon", R.raw.classical_after_the_rain));
                songs.add(new Song("Rise Above", "Scott Buckley", R.raw.classical_rise_above));
                break;
            case GENRE_INDIE:
                ivGenreBanner.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.indie_banner));
                songs.add(new Song("Summer Walk", "Lesfm and Olexy", R.raw.indie_summer_walk));
                songs.add(new Song("A Call to the Soul", "Marko Topa", R.raw.indie_a_call_to_the_soul));
                break;
        }

        btgMusicControls.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (!isChecked) return;
            if (mediaPlayer == null) return;
            if (checkedId == R.id.btn_play) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
            } else if (checkedId == R.id.btn_pause) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
            } else if (checkedId == R.id.btn_stop) {
                if (mediaPlayer.isPlaying() || mediaPlayer.getCurrentPosition() > 0) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    btgMusicControls.clearChecked();
                }
            }
        });

        adapter = new SongAdapter(this, songs);

        adapter.setOnSongClickListener(this::playSong);

        rvMusicList.setAdapter(adapter);
    }

    private void playSong(Song song) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        mediaPlayer = MediaPlayer.create(this, song.audioResId);
        mediaPlayer.start();

        btgMusicControls.check(R.id.btn_play);

        mediaPlayer.setOnCompletionListener(mp -> {
            btgMusicControls.clearChecked();
            mediaPlayer.release();
            mediaPlayer = null;
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
