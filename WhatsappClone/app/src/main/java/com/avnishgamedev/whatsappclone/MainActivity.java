package com.avnishgamedev.whatsappclone;

import android.os.Bundle;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewChats;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("WhatsApp Clone");
        }

        recyclerViewChats = findViewById(R.id.recycler_view_chats);
        recyclerViewChats.setLayoutManager(new LinearLayoutManager(this));

        List<Chat> chatList = Arrays.asList(
                new Chat("Family Group", "Good Afternoon!", "01:05"),
                new Chat("Avnish Kirnalli (You)", "Hello World", "12:12"),
                new Chat("Brother", "Hello", "09:45"),
                new Chat("V2V Group", "MAD - Internship", "09:20"),
                new Chat("Dad", "OK", "08:45")
        );

        chatAdapter = new ChatAdapter(chatList);
        recyclerViewChats.setAdapter(chatAdapter);
    }
}