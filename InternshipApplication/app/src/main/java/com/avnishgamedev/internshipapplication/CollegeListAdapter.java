package com.avnishgamedev.internshipapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CollegeListAdapter extends RecyclerView.Adapter<CollegeListAdapter.CollegeViewHolder> {
    static class CollegeViewHolder extends RecyclerView.ViewHolder {
        TextView txtCollegeName;
        TextView txtCollegeLocation;

        public CollegeViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCollegeName = itemView.findViewById(R.id.TXT_CollegeName);
            txtCollegeLocation = itemView.findViewById(R.id.TXT_CollegeLocation);
        }
    }

    private List<CollegeListItem> colleges;
    private Context context;

    public CollegeListAdapter(Context context, List<CollegeListItem> colleges) {
        this.context = context;
        this.colleges = colleges;
    }

    @NonNull
    @Override
    public CollegeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.support_collegelist_item, parent, false);
        return new CollegeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollegeViewHolder holder, int position) {
        CollegeListItem currentCollege = colleges.get(position);
        holder.txtCollegeName.setText(currentCollege.name);
        holder.txtCollegeLocation.setText(currentCollege.location);
    }

    @Override
    public int getItemCount() {
        return colleges.size();
    }
}
