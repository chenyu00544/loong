package com.vcvb.chenyu.shop.adapter.itemdecoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vcvb.chenyu.shop.javaBean.home.HomeBean;

public class HomeItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private int pos = 0;

    public HomeItemDecoration(Context context) {
        super();
        mContext = context;

    }

    public void setData(HomeBean bean){
        pos = bean.getAdses().size();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            int position = parent.getChildAdapterPosition(view);
            if (position > pos) {
                if (pos % 2 == 0) {
                    if (position % 2 != 0) {
                        outRect.set(6, 3, 3, 3);
                    } else {
                        outRect.set(3, 3, 6, 3);
                    }
                }else{
                    if (position % 2 == 0) {
                        outRect.set(6, 3, 3, 3);
                    } else {
                        outRect.set(3, 3, 6, 3);
                    }
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
