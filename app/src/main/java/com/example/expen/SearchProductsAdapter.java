package com.example.expen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expen.model_classes.Product;

import java.util.ArrayList;

public class SearchProductsAdapter extends  RecyclerView.Adapter<SearchProductsAdapter.SearchProductsViewHolder> {
    private ArrayList<Product> mProducts = new ArrayList<>();

    public SearchProductsAdapter(ArrayList<Product> products) {
        mProducts = products;
    }

    public class SearchProductsViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productID;
        TextView productPrice;

        public SearchProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            productID = itemView.findViewById(R.id.product_id);
            productPrice = itemView.findViewById(R.id.product_price);
        }
    }

    @NonNull
    @Override
    public SearchProductsAdapter.SearchProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_layout, parent, false);
        SearchProductsViewHolder viewHolder = new SearchProductsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchProductsAdapter.SearchProductsViewHolder holder, int position) {
        Product product = mProducts.get(position);

        holder.productPrice.setText(String.valueOf("$" + product.getProductPrice()));

        holder.productName.setText(product.getProductName());

        holder.productID.setText(product.getProductID());
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }
}