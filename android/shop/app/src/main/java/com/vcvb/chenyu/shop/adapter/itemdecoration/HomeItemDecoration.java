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
    private HomeBean homeBean;

    public HomeItemDecoration(Context context) {
        super();
        mContext = context;
    }

    public void setData(HomeBean bean){
        homeBean = bean;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            int pos = parent.getChildAdapterPosition(view);
            if(homeBean != null){
                if (pos < homeBean.getAdses().size()) {
                    outRect.set(0, 0, 0, 0);
                } else {
                    if (homeBean.getAdses().size() % 2 != 0) {
                        if (pos % 2 != 0) {
                            outRect.set(6, 6, 3, 3);
                        } else {
                            outRect.set(3, 6, 6, 3);
                        }
                    }else{
                        if (pos % 2 == 0) {
                            outRect.set(6, 6, 3, 3);
                        } else {
                            outRect.set(3, 6, 6, 3);
                        }
                    }
                }
            }
        } else if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            outRect.set(0, 0, 0, 0);
        } else {
            super.getItemOffsets(outRect, view, parent, state);
        }
    }
}
