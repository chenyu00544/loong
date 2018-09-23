package com.vcvb.chenyu.shop.adapter.itemdecoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class LRTBItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private int left;
    private int top;
    private int right;
    private int buttom;
    public LRTBItemDecoration(Context context, int left, int top, int right, int buttom) {
        super();
        mContext = context;
        this.left = left;
        this.top = top;
        this.right = right;
        this.buttom = buttom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.set(left, top, right, buttom);
    }
}
