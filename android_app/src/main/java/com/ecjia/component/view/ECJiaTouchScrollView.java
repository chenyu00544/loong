package com.ecjia.component.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class ECJiaTouchScrollView extends ScrollView {
    a mListener;
    boolean toTop = false;
    private float xDistance;
    private float xLast;
    private float yDistance;
    private float yLast;

    public interface a {
        void a(int i);
    }

    public ECJiaTouchScrollView(Context context) {
        super(context);
    }

    public ECJiaTouchScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ECJiaTouchScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.yDistance = 0.0f;
                this.xDistance = 0.0f;
                this.xLast = motionEvent.getX();
                this.yLast = motionEvent.getY();
                break;
            case 2:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                this.xDistance += Math.abs(x - this.xLast);
                this.yDistance += Math.abs(y - this.yLast);
                this.xLast = x;
                this.yLast = y;
                if (getScrollY() == 0 && y - this.xLast < 0.0f && Math.abs(this.yDistance) > 0.0f) {
                    break;
                }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        if (getScrollY() > 0) {
            this.mListener.a(0);
        } else {
            this.mListener.a(8);
        }
        super.onScrollChanged(i, i2, i3, i4);
    }

    public void setOnTabVisibleChangeListener(a aVar) {
        this.mListener = aVar;
    }
}
