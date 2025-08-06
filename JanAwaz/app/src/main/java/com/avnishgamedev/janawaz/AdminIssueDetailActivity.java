package com.avnishgamedev.janawaz;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

public class AdminIssueDetailActivity extends AppCompatActivity {

    private static final String TAG = "AdminIssueDetailActivity";

    private FirebaseFirestore db;
    private String issueId;
    private Issue currentIssue;

    private Toolbar toolbar;
    private TextView tvTitle, tvDescription, tvCategory, tvLocation, tvStatus, tvContactInfo;
    private TextView tvSubmittedDate, tvUpdatedDate, tvCoordinates, tvUserInfo;
    private Button btnUpdateStatus;
    private CardView statusCard;
    private View statusIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_issue_detail);

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
            getSupportActionBar().setTitle("Issue Management");
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
        tvUserInfo = findViewById(R.id.tv_user_info);
        btnUpdateStatus = findViewById(R.id.btn_update_status);
        statusCard = findViewById(R.id.status_card);
        statusIndicator = findViewById(R.id.status_indicator);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnUpdateStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateStatusDialog();
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
                                currentIssue = document.toObject(Issue.class);
                                if (currentIssue != null) {
                                    currentIssue.setId(document.getId());
                                    displayIssueDetails(currentIssue);
                                }
                            } else {
                                Log.d(TAG, "No such document");
                                Toast.makeText(AdminIssueDetailActivity.this, "Issue not found", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                            Toast.makeText(AdminIssueDetailActivity.this, "Error loading issue details", Toast.LENGTH_SHORT).show();
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

        // User information
        String userInfo = "Submitted by: " + (issue.getUserName() != null ? issue.getUserName() : "Unknown");
        if (issue.getUserEmail() != null) {
            userInfo += "\nEmail: " + issue.getUserEmail();
        }
        tvUserInfo.setText(userInfo);

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

    private void showUpdateStatusDialog() {
        if (currentIssue == null) return;

        String[] statusOptions = {"Pending", "In Queue", "Fixing", "Resolved"};
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update Issue Status");
        
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_update_status, null);
        Spinner statusSpinner = dialogView.findViewById(R.id.spinner_status);
        
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 
                android.R.layout.simple_spinner_item, statusOptions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);
        
        // Set current status as selected
        for (int i = 0; i < statusOptions.length; i++) {
            if (statusOptions[i].equals(currentIssue.getStatus())) {
                statusSpinner.setSelection(i);
                break;
            }
        }
        
        builder.setView(dialogView);
        builder.setPositiveButton("Update", (dialog, which) -> {
            String newStatus = statusSpinner.getSelectedItem().toString();
            updateIssueStatus(newStatus);
        });
        builder.setNegativeButton("Cancel", null);
        
        builder.show();
    }

    private void updateIssueStatus(String newStatus) {
        if (currentIssue == null) return;

        btnUpdateStatus.setEnabled(false);
        btnUpdateStatus.setText("Updating...");

        db.collection("issues").document(issueId)
                .update("status", newStatus, "updatedAt", new java.util.Date())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        btnUpdateStatus.setEnabled(true);
                        btnUpdateStatus.setText("Update Status");

                        if (task.isSuccessful()) {
                            Log.d(TAG, "Issue status updated successfully");
                            Toast.makeText(AdminIssueDetailActivity.this, 
                                    getString(R.string.status_updated_successfully), 
                                    Toast.LENGTH_SHORT).show();
                            
                            // Update local issue object and refresh UI
                            currentIssue.setStatus(newStatus);
                            currentIssue.setUpdatedAt(new java.util.Date());
                            displayIssueDetails(currentIssue);
                        } else {
                            Log.w(TAG, "Error updating issue status", task.getException());
                            Toast.makeText(AdminIssueDetailActivity.this, 
                                    getString(R.string.error_updating_status), 
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
