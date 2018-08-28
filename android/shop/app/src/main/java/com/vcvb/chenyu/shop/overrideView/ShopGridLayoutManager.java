package com.vcvb.chenyu.shop.overrideView;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ShopGridLayoutManager extends GridLayoutManager {
    private Context contxt;
    private double speedRatio;

    public ShopGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
        this.contxt = context;
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State
            state) {
        int a = super.scrollHorizontallyBy((int)(speedRatio*dx), recycler, state);
        if(a == (int)(speedRatio*dx)){
            return dx;
        }
        return a;
    }

    public void setSpeedRatio(double speedRatio){
        this.speedRatio = speedRatio;
    }
}
