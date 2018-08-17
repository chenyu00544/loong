package com.ecjia.hamster.activity.goodsdetail.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class ECJiaPageTwoView extends LinearLayout {
    String TAG = "PageTwoScrollView";
    private float oldX;
    public float oldY;
    private int oldt;
    private int t;

    public ECJiaPageTwoView(Context context) {
        super(context);
    }

    public ECJiaPageTwoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ECJiaPageTwoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                this.oldY = motionEvent.getY();
                this.oldX = motionEvent.getX();
                break;
            case 1:
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case 2:
                float y = motionEvent.getY() - this.oldY;
                if (Math.abs(motionEvent.getX() - this.oldX) > 120.0f) {
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }
                Log.i(this.TAG, "Ys::" + y + "      t::" + this.t + "   oldt::" + (getChildAt(0).getMeasuredHeight() - getMeasuredHeight()));
                if (y > 0.0f && this.t == 0) {
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                }
                if (y < 0.0f && this.t == getChildAt(0).getMeasuredHeight() - getMeasuredHeight()) {
                    getParent().getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                }
        }
        super.onTouchEvent(motionEvent);
        return super.onTouchEvent(motionEvent);
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        this.t = i2;
        this.oldt = i4;
        super.onScrollChanged(i, i2, i3, i4);
    }
}
