package com.avnishgamedev.musicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    private final List<Song> songs;
    private final Context context;
    private onSongClickListener listener;

    public SongAdapter(Context context, List<Song> songs) {
        this.context = context;
        this.songs = songs;
    }

    public void setOnSongClickListener(onSongClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_song, parent, false);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        Song song = songs.get(position);
        holder.title.setText(song.title);
        holder.artist.setText(song.artist);
        holder.itemView.setOnClickListener(v -> {
            if (listener != null)
                listener.onSongClick(song);
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public static class SongViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView artist;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_song_title);
            artist = itemView.findViewById(R.id.tv_song_artist);
        }
    }

    public interface onSongClickListener {
        void onSongClick(Song song);
    }
}
