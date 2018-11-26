package com.vcvb.chenyu.shop.adapter.itemdecoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class OrderItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    public OrderItemDecoration(Context context) {
        super();
        mContext = context;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            outRect.set(8, 8, 8, 0);
        } else if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            outRect.set(0, 0, 0, 0);
        } else {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }
}
