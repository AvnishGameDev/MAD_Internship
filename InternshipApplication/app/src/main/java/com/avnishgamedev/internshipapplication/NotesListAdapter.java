package com.avnishgamedev.internshipapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NotesViewHolder> {

    static class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView txtNoteTitle;
        TextView txtNoteDescription;
        Button btnDeleteNote;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNoteTitle = itemView.findViewById(R.id.TXT_NoteTitle);
            txtNoteDescription = itemView.findViewById(R.id.TXT_NoteDescription);
            btnDeleteNote = itemView.findViewById(R.id.BTN_DeleteNote);
        }
    }
    public List<Note> notes;

    public NotesListAdapter() {
        notes = new ArrayList<>();
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.support_noteslist_item, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.txtNoteTitle.setText(currentNote.title);
        holder.txtNoteDescription.setText(currentNote.description);
        holder.btnDeleteNote.setOnClickListener(v -> {
            Executors.newSingleThreadExecutor().execute(() -> Notes_DB.getInstance(holder.itemView.getContext()).notesDAO().deleteNote(currentNote));

            notes.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
