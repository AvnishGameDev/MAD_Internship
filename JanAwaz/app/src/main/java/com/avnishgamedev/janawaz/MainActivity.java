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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import com.avnishgamedev.janawaz.adapters.IssueAdapter;
import com.avnishgamedev.janawaz.models.Issue;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IssueAdapter.OnIssueClickListener {

    private static final String TAG = "MainActivity";

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseUser currentUser;

    private TextView tvWelcome;
    private CardView tvNoIssues, cardSubmitIssue;
    private RecyclerView recyclerViewIssues;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Toolbar toolbar;

    private IssueAdapter issueAdapter;
    private List<Issue> userIssues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            redirectToLogin();
            return;
        }

        // Initialize UI components
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvWelcome = findViewById(R.id.tv_welcome);
        tvNoIssues = findViewById(R.id.tv_no_issues);
        cardSubmitIssue = findViewById(R.id.card_submit_issue);
        recyclerViewIssues = findViewById(R.id.recycler_view_issues);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        // Setup RecyclerView
        userIssues = new ArrayList<>();
        issueAdapter = new IssueAdapter(userIssues, this);
        recyclerViewIssues.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewIssues.setAdapter(issueAdapter);

        // Set welcome message
        // String userName = currentUser.getDisplayName();
        String userName = "Avnish";
        if (userName != null && !userName.isEmpty()) {
            tvWelcome.setText("Welcome, " + userName + "!");
        } else {
            tvWelcome.setText("Welcome to JanAwaz!");
        }

        // Set click listeners
        cardSubmitIssue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubmitIssueActivity.class);
                startActivity(intent);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadUserIssues();
            }
        });

        // Load user issues
        loadUserIssues();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadUserIssues();
    }

    private void loadUserIssues() {
        swipeRefreshLayout.setRefreshing(true);

        db.collection("issues")
                .whereEqualTo("userId", currentUser.getUid())
                .orderBy("submittedAt", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        swipeRefreshLayout.setRefreshing(false);
                        
                        if (task.isSuccessful()) {
                            userIssues.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Issue issue = document.toObject(Issue.class);
                                issue.setId(document.getId());
                                userIssues.add(issue);
                            }
                            issueAdapter.notifyDataSetChanged();
                            
                            if (userIssues.isEmpty()) {
                                tvNoIssues.setVisibility(View.VISIBLE);
                                recyclerViewIssues.setVisibility(View.GONE);
                            } else {
                                tvNoIssues.setVisibility(View.GONE);
                                recyclerViewIssues.setVisibility(View.VISIBLE);
                            }
                        } else {
                            Log.w(TAG, "Error getting user issues", task.getException());
                            Toast.makeText(MainActivity.this, "Error loading issues", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onIssueClick(Issue issue) {
        Intent intent = new Intent(MainActivity.this, IssueDetailActivity.class);
        intent.putExtra("issue_id", issue.getId());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            logout();
            return true;
        } else if (item.getItemId() == R.id.action_search) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        mAuth.signOut();
        redirectToLogin();
    }

    private void redirectToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}