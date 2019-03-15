package com.vcvb.chenyu.shop.adapter.itemdecoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vcvb.chenyu.shop.javaBean.categoods.CategroyGoods;

public class CateItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private CategroyGoods _bean;

    public CateItemDecoration(Context context) {
        super();
        mContext = context;
    }

    public void setData(CategroyGoods bean) {
        _bean = bean;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            int i = 0;
            if (_bean.getCates() != null && _bean.getCates().size() > 0) {
                i = 1;
            }
            int pos = parent.getChildAdapterPosition(view);
            if (_bean != null) {
                if (pos < _bean.getAdses().size() + i) {
                    outRect.set(0, 0, 0, 0);
                } else {
                    if ((_bean.getAdses().size() + i) % 2 != 0) {
                        if (pos % 2 != 0) {
                            outRect.set(6, 6, 3, 3);
                        } else {
                            outRect.set(3, 6, 6, 3);
                        }
                    } else {
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