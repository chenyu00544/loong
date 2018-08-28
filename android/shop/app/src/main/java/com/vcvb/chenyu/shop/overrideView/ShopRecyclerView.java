package com.vcvb.chenyu.shop.overrideView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class ShopRecyclerView extends RecyclerView {
    private double scale;

    public ShopRecyclerView(Context context) {
        super(context);
    }

    public ShopRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShopRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setFlingScale(double scale){
        this.scale = scale;
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        velocityX *= scale;
        return super.fling(velocityX, velocityY);
    }
}
