package com.avnishgamedev.blogapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;

public class BlogDisplayActivity extends AppCompatActivity {

    ImageView ivBlog;
    TextView tvTitle;
    TextView tvContent;
    TextView tvDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_display);

        ivBlog = findViewById(R.id.ivBlog);
        tvTitle = findViewById(R.id.tvTitle);
        tvContent = findViewById(R.id.tvContent);
        tvDescription = findViewById(R.id.tvDescription);

        Blog blog = (Blog) getIntent().getSerializableExtra("blog");

        if (blog != null) {
            Glide.with(this)
                    .load(blog.getImageUrl())
                    .apply(
                        new RequestOptions()
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .error(R.drawable.ic_launcher_background)
                    )
                    .into(ivBlog);
            tvTitle.setText(blog.getTitle());
            tvContent.setText(blog.getContent());
            tvDescription.setText("Uploaded by " + blog.getAuthor() + " on " + new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm a").format(blog.getTime()));
        }
    }
}
