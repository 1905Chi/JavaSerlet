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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.appfood.activity.ProductDetailActivity;
import com.example.appfood.model.ProductModel;
import com.example.appfood.R;


import java.util.List;

public class ProductByCategoryIdAdapter extends RecyclerView.Adapter<ProductByCategoryIdAdapter.ProductByCategoryIdHolder>{

    private List<ProductModel> mProductModel;
    private Context mContext;

    public ProductByCategoryIdAdapter(List<ProductModel> mProductModel, Context mContext) {
        this.mProductModel = mProductModel;
        this.mContext = mContext;
    }

    @Override
    public ProductByCategoryIdHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_bycategoryid, null);
        return new ProductByCategoryIdHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductByCategoryIdHolder holder, int position) {
        ProductModel product = mProductModel.get(position);
        if (product == null) {
            return;
        }
        else {
            holder.tvProductByCategoryId.setText(product.getStrMeal());
            Glide.with(mContext)
                    .load(product.getStrMealThumb())
                    .into(holder.imgProductImageByCategoryId);
            holder.item_product_by_category.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public int getItemCount() {
        if (mProductModel != null) {
            return mProductModel.size();
        }
        return 0;
    }

    public class ProductByCategoryIdHolder extends RecyclerView.ViewHolder {

        private TextView tvProductByCategoryId;
        private ImageView imgProductImageByCategoryId;
        private ConstraintLayout item_product_by_category;

        public ProductByCategoryIdHolder(@NonNull View itemView) {
            super(itemView);
            tvProductByCategoryId = (TextView) itemView.findViewById(R.id.tvProductByCategoryId);
            imgProductImageByCategoryId = (ImageView) itemView.findViewById(R.id.imgProductImageByCategoryId);
            item_product_by_category = itemView.findViewById(R.id.item_product_by_category);
        }
    }
}
