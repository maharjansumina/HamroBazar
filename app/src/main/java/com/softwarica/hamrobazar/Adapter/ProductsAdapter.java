package com.softwarica.hamrobazar.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softwarica.hamrobazar.R;
import com.softwarica.hamrobazar.model.Product;
import com.softwarica.hamrobazar.url.URL;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.http.Url;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.productViewHolder> {

    Context context;
    List<Product> productsList;

    public ProductsAdapter(Context context, List<Product> productsList) {
        this.context = context;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public productViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_product,parent,false);
        return new productViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull productViewHolder holder, final int position) {
        final Product product = productsList.get(position);
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(product.getPrice()+"");
        holder.tvCondition.setText(product.getCondition());

        String imgae= product.getImage();
        String imgPath= URL.imagePath+imgae;
        Picasso.get().load(imgPath).into(holder.imgProduct);

    }

    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public class productViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvPrice, tvCondition;
        ImageView imgProduct;
        public productViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvCondition = itemView.findViewById(R.id.tvCondition);
            imgProduct = itemView.findViewById(R.id.imgProduct);
        }
    }

}
