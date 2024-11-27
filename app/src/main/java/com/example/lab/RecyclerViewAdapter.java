package com.example.lab;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<ProductModel> products;
    private Context context;

    public RecyclerViewAdapter(ArrayList<ProductModel> products, Context context) {
        this.products = products;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView productName, productDescription;
        public ImageView productImage;

        public ViewHolder(View view) {
            super(view);
            productName = view.findViewById(R.id.productName);
            productDescription = view.findViewById(R.id.productDescription);
            productImage = view.findViewById(R.id.productImage);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the item layout for the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ProductModel product = products.get(position);
        holder.productName.setText(product.getProductName());
        holder.productDescription.setText(product.getProductDescription());
        holder.productImage.setImageResource(product.getProductImageId());


        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RecyclerDescriptionActivity.class);
            intent.putExtra("image", product.getProductImageId());
            intent.putExtra("title", product.getProductName());
            intent.putExtra("description", product.getProductDescription());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Necessary when using context from RecyclerView adapter
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
