package com.ecjia.hamster.activity.goodsdetail.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.webkit.WebView;

public class ECJiaFixWebView extends WebView {
    public ECJiaFixWebView(Context context) {
        super(context);
    }

    public ECJiaFixWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ECJiaFixWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public ECJiaFixWebView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public ECJiaFixWebView(Context context, AttributeSet attributeSet, int i, boolean z) {
        super(context, attributeSet, i, z);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
