package com.avnishgamedev.blogapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBlog;
        CardView cvBlog;
        TextView tvTitle;
        TextView tvContent;
        TextView tvAuthor;
        TextView tvTime;

        ViewHolder(View view) {
            super(view);
            ivBlog = view.findViewById(R.id.ivBlog);
            cvBlog = view.findViewById(R.id.cvBlog);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvContent = view.findViewById(R.id.tvContent);
            tvAuthor = view.findViewById(R.id.tvAuthor);
            tvTime = view.findViewById(R.id.tvTime);
        }
    }

    private List<Blog> blogs;
    public BlogAdapter(List<Blog> blogs) {
        this.blogs = blogs;
    }

    AdapterView.OnItemClickListener itemClickListener;
    public void setOnItemClickListener(AdapterView.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blog, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Blog blog = blogs.get(position);
        holder.tvTitle.setText(blog.getTitle());
        holder.tvContent.setText(blog.getContent());
        holder.tvAuthor.setText(blog.getAuthor());
        holder.tvTime.setText(new SimpleDateFormat("dd/MM/yyyy 'at' hh:mm a", Locale.getDefault()).format(blog.getTime()));

        holder.cvBlog.setOnClickListener(v -> {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(null, v, position, 0);
            }
        });

        // Load Image
        Glide.with(holder.ivBlog.getContext())
                .load(blog.getImageUrl())
                .apply(
                    new RequestOptions()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_background)
                )
                .into(holder.ivBlog);
    }

    @Override
    public int getItemCount() {
        return blogs.size();
    }

}
