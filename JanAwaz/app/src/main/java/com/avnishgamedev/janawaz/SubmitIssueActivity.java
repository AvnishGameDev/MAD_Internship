package com.avnishgamedev.janawaz;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import com.avnishgamedev.janawaz.models.Issue;

public class SubmitIssueActivity extends AppCompatActivity {

    private static final String TAG = "SubmitIssueActivity";
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1001;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseUser currentUser;
    private FusedLocationProviderClient fusedLocationClient;

    private Toolbar toolbar;
    private EditText etTitle, etDescription, etLocation, etContactInfo;
    private Spinner spinnerCategory;
    private Button btnGetLocation, btnSubmit;
    private ProgressBar progressBar;

    private double currentLatitude = 0.0;
    private double currentLongitude = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_issue);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser = mAuth.getCurrentUser();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (currentUser == null) {
            finish();
            return;
        }

        // Initialize UI components
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Submit New Issue");
        }

        etTitle = findViewById(R.id.et_title);
        etDescription = findViewById(R.id.et_description);
        etLocation = findViewById(R.id.et_location);
        etContactInfo = findViewById(R.id.et_contact_info);
        spinnerCategory = findViewById(R.id.spinner_category);
        btnGetLocation = findViewById(R.id.btn_get_location);
        btnSubmit = findViewById(R.id.btn_submit);
        progressBar = findViewById(R.id.progress_bar);

        // Setup category spinner
        setupCategorySpinner();

        // Set click listeners
        btnGetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentLocation();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitIssue();
            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupCategorySpinner() {
        String[] categories = {
                getString(R.string.category_road),
                getString(R.string.category_electricity),
                getString(R.string.category_water),
                getString(R.string.category_waste),
                getString(R.string.category_streetlight),
                getString(R.string.category_other)
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
            return;
        }

        btnGetLocation.setEnabled(false);
        btnGetLocation.setText("Getting location...");

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        btnGetLocation.setEnabled(true);
                        btnGetLocation.setText(getString(R.string.get_current_location));

                        if (location != null) {
                            currentLatitude = location.getLatitude();
                            currentLongitude = location.getLongitude();
                            
                            String locationText = String.format("Lat: %.6f, Lng: %.6f", 
                                    currentLatitude, currentLongitude);
                            etLocation.setText(locationText);
                            
                            Toast.makeText(SubmitIssueActivity.this, 
                                    "Location obtained successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SubmitIssueActivity.this, 
                                    "Unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            } else {
                Toast.makeText(this, getString(R.string.location_permission_required), 
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void submitIssue() {
        String title = etTitle.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String category = spinnerCategory.getSelectedItem().toString();
        String location = etLocation.getText().toString().trim();
        String contactInfo = etContactInfo.getText().toString().trim();

        // Validation
        if (TextUtils.isEmpty(title)) {
            etTitle.setError("Title is required");
            return;
        }

        if (TextUtils.isEmpty(description)) {
            etDescription.setError("Description is required");
            return;
        }

        if (TextUtils.isEmpty(location)) {
            etLocation.setError("Location is required");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        btnSubmit.setEnabled(false);

        // Create issue object
        Issue issue = new Issue(
                title,
                description,
                category,
                location,
                contactInfo,
                currentUser.getUid(),
                currentUser.getEmail(),
                "Avnish Kirnalli", // currentUser.getDisplayName(),
                currentLatitude,
                currentLongitude
        );

        // Save to Firestore
        db.collection("issues")
                .add(issue)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        progressBar.setVisibility(View.GONE);
                        btnSubmit.setEnabled(true);

                        if (task.isSuccessful()) {
                            Log.d(TAG, "Issue submitted successfully");
                            Toast.makeText(SubmitIssueActivity.this, 
                                    getString(R.string.issue_submitted_successfully), 
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Log.w(TAG, "Error submitting issue", task.getException());
                            Toast.makeText(SubmitIssueActivity.this, 
                                    getString(R.string.error_submitting_issue), 
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
