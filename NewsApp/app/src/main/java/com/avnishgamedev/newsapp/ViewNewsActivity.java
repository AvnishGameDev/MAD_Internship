package com.avnishgamedev.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewNewsActivity extends AppCompatActivity {

    RecyclerView rvNews;

    Button btnAddNews;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_news);

        rvNews = findViewById(R.id.rvNews);
        btnAddNews = findViewById(R.id.btnAddNews);

        btnAddNews.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddNewsActivity.class);
            startActivity(intent);
        });

        DBHelper dbHelper = new DBHelper(this);
        List<Pair<String, String>> newsList = dbHelper.getAllNews();
        NewsAdapter adapter = new NewsAdapter(newsList);
        rvNews.setAdapter(adapter);
    }
}
