package com.avnishgamedev.whatsappclone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<Chat> chatList;

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageProfile;
        public TextView textUsername, textLastMessage, textTime;

        public ChatViewHolder(View itemView) {
            super(itemView);
            imageProfile = itemView.findViewById(R.id.image_profile);
            textUsername = itemView.findViewById(R.id.text_username);
            textLastMessage = itemView.findViewById(R.id.text_last_message);
            textTime = itemView.findViewById(R.id.text_time);
        }
    }

    public ChatAdapter(List<Chat> chats) {
        this.chatList = chats;
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new ChatViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        Chat chat = chatList.get(position);
        holder.textUsername.setText(chat.getName());
        holder.textLastMessage.setText(chat.getLastMessage());
        holder.textTime.setText(chat.getTime());
        holder.imageProfile.setImageResource(R.drawable.empty_profile); // Static placeholder
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }
}
