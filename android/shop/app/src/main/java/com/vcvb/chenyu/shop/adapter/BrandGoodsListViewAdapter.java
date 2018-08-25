package com.vcvb.chenyu.shop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vcvb.chenyu.shop.R;

import java.util.ArrayList;

public class BrandGoodsListViewAdapter extends RecyclerView.Adapter<RecyclerView
        .ViewHolder> {
    private Context context;
    private ArrayList<Integer> list;

    public BrandGoodsListViewAdapter(Context context, ArrayList<Integer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_brand_item,
                parent, false);
        return new BrandGoodsListViewAdapter.SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SimpleViewHolder extends RecyclerView.ViewHolder {
        SimpleViewHolder(View view) {
            super(view);
        }
    }
}
