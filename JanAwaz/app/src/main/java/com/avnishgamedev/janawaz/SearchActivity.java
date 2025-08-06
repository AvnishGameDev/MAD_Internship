package com.avnishgamedev.janawaz;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import com.avnishgamedev.janawaz.adapters.IssueAdapter;
import com.avnishgamedev.janawaz.models.Issue;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements IssueAdapter.OnIssueClickListener {

    private static final String TAG = "SearchActivity";

    private FirebaseFirestore db;
    private Toolbar toolbar;
    private EditText etSearch;
    private Spinner spinnerCategory, spinnerStatus;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tvNoResults;

    private IssueAdapter issueAdapter;
    private List<Issue> allIssues;
    private List<Issue> filteredIssues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initialize Firebase
        db = FirebaseFirestore.getInstance();

        // Initialize UI components
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Search Issues");
        }

        etSearch = findViewById(R.id.et_search);
        spinnerCategory = findViewById(R.id.spinner_category);
        spinnerStatus = findViewById(R.id.spinner_status);
        recyclerView = findViewById(R.id.recycler_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        tvNoResults = findViewById(R.id.tv_no_results);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Initialize lists
        allIssues = new ArrayList<>();
        filteredIssues = new ArrayList<>();

        // Setup RecyclerView
        issueAdapter = new IssueAdapter(filteredIssues, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(issueAdapter);

        // Setup search functionality
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterIssues();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Setup spinners
        setupSpinners();

        // Setup swipe refresh
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadAllIssues();
            }
        });

        // Load issues
        loadAllIssues();
    }

    private void setupSpinners() {
        // Category spinner
        String[] categories = {"All Categories", "Road/Infrastructure", "Electricity", 
                              "Water Supply", "Waste Management", "Street Light", "Other"};
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, 
                android.R.layout.simple_spinner_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);
        
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterIssues();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Status spinner
        String[] statuses = {"All Status", "Pending", "In Queue", "Fixing", "Resolved"};
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(this, 
                android.R.layout.simple_spinner_item, statuses);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatus.setAdapter(statusAdapter);
        
        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterIssues();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void loadAllIssues() {
        swipeRefreshLayout.setRefreshing(true);
        
        db.collection("issues")
                .orderBy("submittedAt", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        swipeRefreshLayout.setRefreshing(false);
                        
                        if (task.isSuccessful()) {
                            allIssues.clear();
                            
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Issue issue = document.toObject(Issue.class);
                                issue.setId(document.getId());
                                allIssues.add(issue);
                            }
                            
                            filterIssues();
                            Log.d(TAG, "Loaded " + allIssues.size() + " issues");
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            Toast.makeText(SearchActivity.this, "Error loading issues", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void filterIssues() {
        String searchText = etSearch.getText().toString().toLowerCase().trim();
        String selectedCategory = spinnerCategory.getSelectedItem().toString();
        String selectedStatus = spinnerStatus.getSelectedItem().toString();
        
        filteredIssues.clear();
        
        for (Issue issue : allIssues) {
            boolean matchesSearch = searchText.isEmpty() || 
                    issue.getTitle().toLowerCase().contains(searchText) ||
                    issue.getDescription().toLowerCase().contains(searchText) ||
                    issue.getLocation().toLowerCase().contains(searchText);
            
            boolean matchesCategory = selectedCategory.equals("All Categories") ||
                    issue.getCategory().equals(selectedCategory);
            
            boolean matchesStatus = selectedStatus.equals("All Status") ||
                    issue.getStatus().equals(selectedStatus);
            
            if (matchesSearch && matchesCategory && matchesStatus) {
                filteredIssues.add(issue);
            }
        }
        
        issueAdapter.notifyDataSetChanged();
        
        // Show/hide no results message
        if (filteredIssues.isEmpty()) {
            tvNoResults.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvNoResults.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onIssueClick(Issue issue) {
        Intent intent = new Intent(this, IssueDetailActivity.class);
        intent.putExtra("issue_id", issue.getId());
        startActivity(intent);
    }
}
