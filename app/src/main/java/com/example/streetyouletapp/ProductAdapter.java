package com.example.streetyouletapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.streetyouletapp.Model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    List<Product> allProduct;

    public ProductAdapter(List<Product> allProduct) {
        this.allProduct = allProduct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productText.setText(allProduct.get(position).getLink());
    }

    @Override
    public int getItemCount() {
        return allProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView productImage;
        TextView productText;
        View view;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            view = itemView;
            productText = itemView.findViewById(R.id.productTitle);
            productImage = itemView.findViewById(R.id.imageView);

        }
    }

}
