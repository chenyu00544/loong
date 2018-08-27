package com.vcvb.chenyu.shop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vcvb.chenyu.shop.R;

import java.util.ArrayList;
import java.util.HashMap;

public class EvaluateListViewAdapter extends RecyclerView.Adapter<RecyclerView
        .ViewHolder> {
    private Context context;
    private ArrayList<HashMap> list;

    public EvaluateListViewAdapter(Context context, ArrayList<HashMap> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_evaluate_sub_item,
                parent, false);
        return new EvaluateListViewAdapter.SimpleViewHolder(view);
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
