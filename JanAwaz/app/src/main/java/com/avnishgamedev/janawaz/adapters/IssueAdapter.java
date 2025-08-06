package com.avnishgamedev.janawaz.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.avnishgamedev.janawaz.R;
import com.avnishgamedev.janawaz.models.Issue;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class IssueAdapter extends RecyclerView.Adapter<IssueAdapter.IssueViewHolder> {

    private List<Issue> issues;
    private OnIssueClickListener onIssueClickListener;

    public interface OnIssueClickListener {
        void onIssueClick(Issue issue);
    }

    public IssueAdapter(List<Issue> issues, OnIssueClickListener listener) {
        this.issues = issues;
        this.onIssueClickListener = listener;
    }

    @NonNull
    @Override
    public IssueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_issue, parent, false);
        return new IssueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IssueViewHolder holder, int position) {
        Issue issue = issues.get(position);
        holder.bind(issue);
    }

    @Override
    public int getItemCount() {
        return issues.size();
    }

    class IssueViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView tvTitle, tvCategory, tvStatus, tvLocation, tvDate;
        private View statusIndicator;

        public IssueViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_issue);
            tvTitle = itemView.findViewById(R.id.tv_issue_title);
            tvCategory = itemView.findViewById(R.id.tv_issue_category);
            tvStatus = itemView.findViewById(R.id.tv_issue_status);
            tvLocation = itemView.findViewById(R.id.tv_issue_location);
            tvDate = itemView.findViewById(R.id.tv_issue_date);
            statusIndicator = itemView.findViewById(R.id.status_indicator);
        }

        public void bind(Issue issue) {
            tvTitle.setText(issue.getTitle());
            tvCategory.setText(issue.getCategory());
            tvStatus.setText(issue.getStatus());
            tvLocation.setText(issue.getLocation());

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

            // Set click listener
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onIssueClickListener != null) {
                        onIssueClickListener.onIssueClick(issue);
                    }
                }
            });
        }
    }
}
