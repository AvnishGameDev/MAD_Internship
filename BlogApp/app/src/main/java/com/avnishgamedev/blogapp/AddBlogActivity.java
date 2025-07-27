package com.avnishgamedev.blogapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class AddBlogActivity extends AppCompatActivity {
    private static final String TAG = "AddBlogActivity";

    EditText etTitle;
    EditText etContent;
    EditText etImageUrl;
    Button btnSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_blog);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        etImageUrl = findViewById(R.id.etImageUrl);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String content = etContent.getText().toString();
            String imageUrl = etImageUrl.getText().toString();

            if (title.isEmpty()) {
                etTitle.setError("Title cannot be empty");
            }
            if (content.isEmpty()) {
                etContent.setError("Content cannot be empty");
            }
            if (imageUrl.isEmpty()) {
                etImageUrl.setError("Image URL cannot be empty");
            }

            btnSubmit.setEnabled(false);

            Blog blog = new Blog(
                    title,
                    content,
                    "Avnish Kirnalli", // FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),
                    new Date(), // Current Time
                    imageUrl
            );

            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("blogs")
                    .add(blog)
                    .addOnSuccessListener(docRef -> {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + docRef.getId());
                        Toast.makeText(this, "Blog uploaded successfully!", Toast.LENGTH_SHORT).show();
                        NotificationHelper.showNotification(this, title + " Uploaded", "Your Blog was uploaded successfully!");
                        finish();
                    })
                    .addOnFailureListener(e -> {
                        Log.w(TAG, "Error adding document", e);
                        Toast.makeText(this, "Failed to upload Blog!", Toast.LENGTH_SHORT).show();
                    })
                    .addOnCompleteListener(x -> btnSubmit.setEnabled(false));
        });
    }
}
