package com.avnishgamedev.blogapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewBlogsActivity extends AppCompatActivity {
    private static final String TAG = "ViewBlogsActivity";

    RecyclerView rvBlogs;

    View vBackgroundBlur;
    CircularProgressIndicator cpiLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_blogs);

        rvBlogs = findViewById(R.id.rvBlogs);
        vBackgroundBlur = findViewById(R.id.vBackgroundBlur);
        cpiLoading = findViewById(R.id.cpiLoading);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("blogs")
                .orderBy("time", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        List<Blog> blogList = new ArrayList<>();

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Blog blog = document.toObject(Blog.class);
                            blogList.add(blog);
                        }

                        vBackgroundBlur.setVisibility(View.GONE);
                        cpiLoading.setVisibility(View.GONE);

                        BlogAdapter adapter = new BlogAdapter(blogList);
                        adapter.setOnItemClickListener((parent, view, position, id) -> {
                            Blog blog = blogList.get(position);
                            Intent intent = new Intent(this, BlogDisplayActivity.class);
                            intent.putExtra("blog", blog);
                            startActivity(intent);
                        });
                        rvBlogs.setAdapter(adapter);
                    } else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                        Toast.makeText(this, "Failed to read Blogs!", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
