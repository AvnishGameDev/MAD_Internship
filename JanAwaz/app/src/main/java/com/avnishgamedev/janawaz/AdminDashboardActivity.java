package com.avnishgamedev.janawaz;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import com.avnishgamedev.janawaz.adapters.AdminIssueAdapter;
import com.avnishgamedev.janawaz.models.Issue;

import java.util.ArrayList;
import java.util.List;

public class AdminDashboardActivity extends AppCompatActivity implements AdminIssueAdapter.OnAdminIssueClickListener {

    private static final String TAG = "AdminDashboardActivity";

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private Toolbar toolbar;
    private TextView tvTotalIssues, tvPendingIssues, tvResolvedIssues;
    private CardView tvNoIssues;
    private RecyclerView recyclerViewIssues;
    private SwipeRefreshLayout swipeRefreshLayout;

    private AdminIssueAdapter adminIssueAdapter;
    private List<Issue> allIssues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Initialize UI components
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Admin Dashboard");
        }

        tvTotalIssues = findViewById(R.id.tv_total_issues);
        tvPendingIssues = findViewById(R.id.tv_pending_issues);
        tvResolvedIssues = findViewById(R.id.tv_resolved_issues);
        tvNoIssues = findViewById(R.id.tv_no_issues);
        recyclerViewIssues = findViewById(R.id.recycler_view_issues);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        // Setup RecyclerView
        allIssues = new ArrayList<>();
        adminIssueAdapter = new AdminIssueAdapter(allIssues, this);
        recyclerViewIssues.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewIssues.setAdapter(adminIssueAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadAllIssues();
            }
        });

        // Load all issues
        loadAllIssues();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAllIssues();
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
                            adminIssueAdapter.notifyDataSetChanged();
                            updateStatistics();
                            
                            if (allIssues.isEmpty()) {
                                tvNoIssues.setVisibility(View.VISIBLE);
                                recyclerViewIssues.setVisibility(View.GONE);
                            } else {
                                tvNoIssues.setVisibility(View.GONE);
                                recyclerViewIssues.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Log.w(TAG, "Error getting issues", task.getException());
                            Toast.makeText(AdminDashboardActivity.this, "Error loading issues", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void updateStatistics() {
        int totalIssues = allIssues.size();
        int pendingIssues = 0;
        int resolvedIssues = 0;

        for (Issue issue : allIssues) {
            if ("Pending".equals(issue.getStatus()) || "In Queue".equals(issue.getStatus()) || "Fixing".equals(issue.getStatus())) {
                pendingIssues++;
            } else if ("Resolved".equals(issue.getStatus())) {
                resolvedIssues++;
            }
        }

        tvTotalIssues.setText(String.valueOf(totalIssues));
        tvPendingIssues.setText(String.valueOf(pendingIssues));
        tvResolvedIssues.setText(String.valueOf(resolvedIssues));
    }

    @Override
    public void onAdminIssueClick(Issue issue) {
        Intent intent = new Intent(AdminDashboardActivity.this, AdminIssueDetailActivity.class);
        intent.putExtra("issue_id", issue.getId());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        mAuth.signOut();
        Intent intent = new Intent(AdminDashboardActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
