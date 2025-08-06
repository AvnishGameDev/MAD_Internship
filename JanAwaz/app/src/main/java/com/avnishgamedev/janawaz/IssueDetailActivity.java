package com.avnishgamedev.janawaz;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.avnishgamedev.janawaz.models.Issue;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class IssueDetailActivity extends AppCompatActivity {

    private static final String TAG = "IssueDetailActivity";

    private FirebaseFirestore db;
    private String issueId;

    private Toolbar toolbar;
    private TextView tvTitle, tvDescription, tvCategory, tvLocation, tvStatus, tvContactInfo;
    private TextView tvSubmittedDate, tvUpdatedDate, tvCoordinates;
    private CardView statusCard;
    private View statusIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_detail);

        // Initialize Firebase
        db = FirebaseFirestore.getInstance();

        // Get issue ID from intent
        issueId = getIntent().getStringExtra("issue_id");
        if (issueId == null) {
            Toast.makeText(this, "Error: Issue not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Initialize UI components
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Issue Details");
        }

        tvTitle = findViewById(R.id.tv_title);
        tvDescription = findViewById(R.id.tv_description);
        tvCategory = findViewById(R.id.tv_category);
        tvLocation = findViewById(R.id.tv_location);
        tvStatus = findViewById(R.id.tv_status);
        tvContactInfo = findViewById(R.id.tv_contact_info);
        tvSubmittedDate = findViewById(R.id.tv_submitted_date);
        tvUpdatedDate = findViewById(R.id.tv_updated_date);
        tvCoordinates = findViewById(R.id.tv_coordinates);
        statusCard = findViewById(R.id.status_card);
        statusIndicator = findViewById(R.id.status_indicator);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Load issue details
        loadIssueDetails();
    }

    private void loadIssueDetails() {
        db.collection("issues").document(issueId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Issue issue = document.toObject(Issue.class);
                                if (issue != null) {
                                    issue.setId(document.getId());
                                    displayIssueDetails(issue);
                                }
                            } else {
                                Log.d(TAG, "No such document");
                                Toast.makeText(IssueDetailActivity.this, "Issue not found", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                            Toast.makeText(IssueDetailActivity.this, "Error loading issue details", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void displayIssueDetails(Issue issue) {
        tvTitle.setText(issue.getTitle());
        tvDescription.setText(issue.getDescription());
        tvCategory.setText(issue.getCategory());
        tvLocation.setText(issue.getLocation());
        tvStatus.setText(issue.getStatus());

        // Handle contact info
        if (issue.getContactInfo() != null && !issue.getContactInfo().isEmpty()) {
            tvContactInfo.setText(issue.getContactInfo());
            tvContactInfo.setVisibility(View.VISIBLE);
        } else {
            tvContactInfo.setVisibility(View.GONE);
        }

        // Format dates
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy 'at' HH:mm", Locale.getDefault());
        
        if (issue.getSubmittedAt() != null) {
            tvSubmittedDate.setText("Submitted: " + sdf.format(issue.getSubmittedAt()));
        }
        
        if (issue.getUpdatedAt() != null && !issue.getUpdatedAt().equals(issue.getSubmittedAt())) {
            tvUpdatedDate.setText("Last Updated: " + sdf.format(issue.getUpdatedAt()));
            tvUpdatedDate.setVisibility(View.VISIBLE);
        } else {
            tvUpdatedDate.setVisibility(View.GONE);
        }

        // Display coordinates if available
        if (issue.getLatitude() != 0.0 && issue.getLongitude() != 0.0) {
            String coordinates = String.format(Locale.getDefault(), 
                    "Coordinates: %.6f, %.6f", issue.getLatitude(), issue.getLongitude());
            tvCoordinates.setText(coordinates);
            tvCoordinates.setVisibility(View.VISIBLE);
        } else {
            tvCoordinates.setVisibility(View.GONE);
        }

        // Set status styling
        setStatusStyling(issue.getStatus());
    }

    private void setStatusStyling(String status) {
        int statusColor;
        int backgroundColor;
        
        switch (status) {
            case "Pending":
                statusColor = getColor(R.color.status_pending);
                backgroundColor = getColor(R.color.status_pending);
                break;
            case "In Queue":
                statusColor = getColor(R.color.status_in_queue);
                backgroundColor = getColor(R.color.status_in_queue);
                break;
            case "Fixing":
                statusColor = getColor(R.color.status_fixing);
                backgroundColor = getColor(R.color.status_fixing);
                break;
            case "Resolved":
                statusColor = getColor(R.color.status_resolved);
                backgroundColor = getColor(R.color.status_resolved);
                break;
            default:
                statusColor = getColor(R.color.status_pending);
                backgroundColor = getColor(R.color.status_pending);
                break;
        }
        
        tvStatus.setTextColor(statusColor);
        statusIndicator.setBackgroundColor(backgroundColor);
        
        // Set card background with alpha
        int alphaColor = (backgroundColor & 0x00FFFFFF) | 0x20000000; // 12.5% alpha
        statusCard.setCardBackgroundColor(alphaColor);
    }
}
