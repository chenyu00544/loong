package com.vcvb.chenyu.shop.overrideView;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

public class ItemWebView extends WebView {
    public float oldY;
    private int t;
    private float oldX;

    public ItemWebView(Context context) {
        super(context);
    }

    public ItemWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ItemWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        this.t = t;
        super.onScrollChanged(l, t, oldl, oldt);
    }

}
