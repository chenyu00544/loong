package com.vcvb.chenyu.shop.adapter.itemdecoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vcvb.chenyu.shop.javaBean.faat.Faat;
import com.vcvb.chenyu.shop.javaBean.goods.Goods;

public class FaatItemDecoration extends RecyclerView.ItemDecoration {

    Faat faats;
    Context context;
    private int pos = 2;

    public FaatItemDecoration(Faat faat, Context context) {
        this.faats = faat;
        this.context = context;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        int position = parent.getChildAdapterPosition(view);
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            if (position >= 2) {
                if (faats.getGoodses().get(position - pos) instanceof Goods) {
                    outRect.set(5, 5, 5, 5);
                } else {
                    outRect.set(0, 0, 0, 0);
                }
            } else {
                outRect.set(0, 0, 0, 0);
            }
        } else if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            outRect.set(0, 0, 0, 0);
        } else {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }
}
