package com.vcvb.chenyu.shop.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

public class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View itemView, int viewType, AdapterView.OnItemClickListener
            listener) {
        super(itemView);
    }

    public void bindViewHolder(T t) {

    }
}
