package com.avnishgamedev.newsapp;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvContent;
        ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
        }
    }

    private List<Pair<String, String>> newsList;
    public NewsAdapter(List<Pair<String, String>> newsList) {
        this.newsList = newsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pair<String, String> news = newsList.get(position);
        holder.tvTitle.setText(news.first);
        holder.tvContent.setText(news.second);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

}
