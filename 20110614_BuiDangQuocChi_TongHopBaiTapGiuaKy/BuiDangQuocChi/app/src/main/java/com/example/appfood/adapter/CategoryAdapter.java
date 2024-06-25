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
import com.example.appfood.activity.ProductByCategoryIdActivity;
import com.example.appfood.model.CategoryModel;
import com.example.appfood.R;


import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {


    private List<CategoryModel> mCategoryModel;
    private Context mContext;

    public CategoryAdapter(List<CategoryModel> mCategoryModel, Context mContext) {
        this.mCategoryModel = mCategoryModel;
        this.mContext = mContext;
    }

    // Tạo 1 ViewHolder mới
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, null);
        return new CategoryViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mCategoryModel != null) {
            return mCategoryModel.size();
        }
        return 0;
    }

    // Liên kết ViewHolder với dữ liệu
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        final CategoryModel category = mCategoryModel.get(position);
        if (category == null) {
            return;
        }
        // Xét dữ liệu cho thành phần
        else {
            holder.tvCategoryName.setText(category.getName());

            // Load ảnh với GLide
            Glide.with(mContext)
                    .load(category.getImages())
                    .into(holder.imgCategoryImages);

            holder.layoutItemCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickItemCategory(category);
                }
            });
        }
    }

    // B1: tạo RecyclerView.ViewHolder
    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        // Khai báo thành phần có trong item View
        private ImageView imgCategoryImages;
        private TextView tvCategoryName;
        private ConstraintLayout layoutItemCategory;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            // Ánh xạ View Item
            imgCategoryImages = (ImageView) itemView.findViewById(R.id.imgCategoryImages);
            tvCategoryName = (TextView) itemView.findViewById(R.id.tvCategoryName);
            layoutItemCategory = (ConstraintLayout) itemView.findViewById(R.id.layoutItemCategory);
        }
    }

    private void onClickItemCategory(CategoryModel category) {
        Intent intent = new Intent(mContext, ProductByCategoryIdActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_category", category);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    public void  release() {
        mContext = null;
    }
}
