package com.example.streetyouletapp;



import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        ViewHolder holder = new ViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                String link = allProduct.get(position).getLink();
                Toast.makeText(parent.getContext(), link, Toast.LENGTH_LONG).show();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                parent.getContext().startActivity(browserIntent);

            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.productText.setText(allProduct.get(position).getTitle());

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
