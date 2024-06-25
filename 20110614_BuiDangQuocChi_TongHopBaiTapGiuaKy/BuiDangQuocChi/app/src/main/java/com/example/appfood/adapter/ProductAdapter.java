package com.example.appfood.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfood.activity.ProductDetailActivity;
import com.example.appfood.model.ProductModel;
import com.example.appfood.R;


import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<ProductModel> mProductModel;

    private Context mContext;

    public ProductAdapter(List<ProductModel> mProductModel, Context mContext) {
        this.mProductModel = mProductModel;
        this.mContext = mContext;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, null);
        return new ProductViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mProductModel != null) {
            return mProductModel.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductModel product = mProductModel.get(position);
        if (product == null) {
            return;
        }
        else {
            Glide.with(mContext)
                    .load(product.getStrMealThumb())
                    .into(holder.imgProductImages);
            holder.tvid.setText(product.getStrMeal());
            holder.imgProductImages.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ProductDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("id_product", product.getIdMeal());
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgProductImages;
        private TextView tvid;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProductImages = (ImageView) itemView.findViewById(R.id.imgProductImages);
            tvid = itemView.findViewById(R.id.tvid);
        }
    }
}
