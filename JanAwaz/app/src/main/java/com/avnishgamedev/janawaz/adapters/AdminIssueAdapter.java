package com.avnishgamedev.janawaz.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.avnishgamedev.janawaz.R;
import com.avnishgamedev.janawaz.models.Issue;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class AdminIssueAdapter extends RecyclerView.Adapter<AdminIssueAdapter.AdminIssueViewHolder> {

    private List<Issue> issues;
    private OnAdminIssueClickListener onAdminIssueClickListener;

    public interface OnAdminIssueClickListener {
        void onAdminIssueClick(Issue issue);
    }

    public AdminIssueAdapter(List<Issue> issues, OnAdminIssueClickListener listener) {
        this.issues = issues;
        this.onAdminIssueClickListener = listener;
    }

    @NonNull
    @Override
    public AdminIssueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_admin_issue, parent, false);
        return new AdminIssueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminIssueViewHolder holder, int position) {
        Issue issue = issues.get(position);
        holder.bind(issue);
    }

    @Override
    public int getItemCount() {
        return issues.size();
    }

    class AdminIssueViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView tvTitle, tvCategory, tvStatus, tvLocation, tvDate, tvUserName;
        private Button btnUpdateStatus;
        private View statusIndicator;

        public AdminIssueViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_admin_issue);
            tvTitle = itemView.findViewById(R.id.tv_issue_title);
            tvCategory = itemView.findViewById(R.id.tv_issue_category);
            tvStatus = itemView.findViewById(R.id.tv_issue_status);
            tvLocation = itemView.findViewById(R.id.tv_issue_location);
            tvDate = itemView.findViewById(R.id.tv_issue_date);
            tvUserName = itemView.findViewById(R.id.tv_user_name);
            btnUpdateStatus = itemView.findViewById(R.id.btn_update_status);
            statusIndicator = itemView.findViewById(R.id.status_indicator);
        }

        public void bind(Issue issue) {
            tvTitle.setText(issue.getTitle());
            tvCategory.setText(issue.getCategory());
            tvStatus.setText(issue.getStatus());
            tvLocation.setText(issue.getLocation());
            tvUserName.setText("By: " + (issue.getUserName() != null ? issue.getUserName() : issue.getUserEmail()));

            // Format date
            SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
            tvDate.setText(sdf.format(issue.getSubmittedAt()));

            // Set status color
            int statusColor;
            switch (issue.getStatus()) {
                case "Pending":
                    statusColor = itemView.getContext().getColor(R.color.status_pending);
                    break;
                case "In Queue":
                    statusColor = itemView.getContext().getColor(R.color.status_in_queue);
                    break;
                case "Fixing":
                    statusColor = itemView.getContext().getColor(R.color.status_fixing);
                    break;
                case "Resolved":
                    statusColor = itemView.getContext().getColor(R.color.status_resolved);
                    break;
                default:
                    statusColor = itemView.getContext().getColor(R.color.status_pending);
                    break;
            }
            statusIndicator.setBackgroundColor(statusColor);
            tvStatus.setTextColor(statusColor);

            // Set click listeners
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onAdminIssueClickListener != null) {
                        onAdminIssueClickListener.onAdminIssueClick(issue);
                    }
                }
            });

            btnUpdateStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onAdminIssueClickListener != null) {
                        onAdminIssueClickListener.onAdminIssueClick(issue);
                    }
                }
            });
        }
    }
}
