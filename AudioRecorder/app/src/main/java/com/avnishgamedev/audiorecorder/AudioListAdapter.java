package com.avnishgamedev.audiorecorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AudioListAdapter extends RecyclerView.Adapter<AudioListAdapter.ViewHolder> {

    // 1. Define an interface for click events
    public interface OnItemClickListener {
        void onItemClick(Audio audio);
    }

    private final List<Audio> audioList;
    private final OnItemClickListener listener; // 2. Add a listener member variable

    // 3. Update the constructor to accept the listener
    public AudioListAdapter(List<Audio> audioList, OnItemClickListener listener) {
        this.audioList = audioList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_audio, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Audio audio = audioList.get(position);
        // 4. Bind the data and the click listener
        holder.bind(audio, listener);
    }

    @Override
    public int getItemCount() {
        return audioList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cvAudio;
        TextView tvFileName;
        TextView tvCreatedAt;
        TextView tvDuration;

        public ViewHolder(View itemView) {
            super(itemView);
            cvAudio = itemView.findViewById(R.id.cvAudio);
            tvFileName = itemView.findViewById(R.id.tvFileName);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            tvDuration = itemView.findViewById(R.id.tvDuration);
        }

        // Helper method to bind data
        public void bind(final Audio audio, final OnItemClickListener listener) {
            tvFileName.setText(audio.fileName);
            tvCreatedAt.setText(audio.createdAt);

            // ✨ SET THE DURATION AND MANAGE VISIBILITY
            if (audio.duration != null && !audio.duration.isEmpty()) {
                tvDuration.setVisibility(View.VISIBLE);
                tvDuration.setText(audio.duration);
            } else {
                tvDuration.setVisibility(View.GONE);
            }

            itemView.setOnClickListener(v -> listener.onItemClick(audio));
        }
    }
}