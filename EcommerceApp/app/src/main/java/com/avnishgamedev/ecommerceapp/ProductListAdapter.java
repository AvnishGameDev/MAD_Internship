package com.avnishgamedev.ecommerceapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductName;
        TextView tvProductPrice;
        TextView tvProductDescription;
        Button btnBuy;
        public ViewHolder(View itemView) {
            super(itemView);

            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            tvProductDescription = itemView.findViewById(R.id.tvProductDescription);
            btnBuy = itemView.findViewById(R.id.btnBuy);
        }
    }
    public interface OnBuyClickListener {
        void onBuyClick(Product product);
    }

    private List<Product> products;
    private OnBuyClickListener onBuyClickListener;
    public ProductListAdapter(List<Product> products) {
        this.products = products;
    }

    public void setOnBuyClickListener(OnBuyClickListener onBuyClickListener) {
        this.onBuyClickListener = onBuyClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.tvProductName.setText(product.name);
        holder.tvProductPrice.setText(String.valueOf(product.price));
        holder.tvProductDescription.setText(product.description);

        if (onBuyClickListener != null) {
            holder.btnBuy.setOnClickListener(v -> onBuyClickListener.onBuyClick(products.get(position)));
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
