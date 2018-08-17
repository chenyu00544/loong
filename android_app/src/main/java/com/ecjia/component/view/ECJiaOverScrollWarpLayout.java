package com.ecjia.component.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class ECJiaOverScrollWarpLayout extends LinearLayout {
    private static final float OVERSHOOT_TENSION = 0.75f;
    private Scroller mScroller = new Scroller(getContext(), new OvershootInterpolator(OVERSHOOT_TENSION));

    public ECJiaOverScrollWarpLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(1);
    }

    public ECJiaOverScrollWarpLayout(Context context) {
        super(context);
        setOrientation(1);
    }

    public void smoothScrollTo(int i, int i2) {
        smoothScrollBy(i - this.mScroller.getFinalX(), i2 - this.mScroller.getFinalY());
    }

    public void smoothScrollBy(int i, int i2) {
        this.mScroller.startScroll(this.mScroller.getFinalX(), this.mScroller.getFinalY(), i, i2);
        invalidate();
    }

    public void computeScroll() {
        if (this.mScroller.computeScrollOffset()) {
            scrollTo(this.mScroller.getCurrX(), this.mScroller.getCurrY());
            postInvalidate();
        }
        super.computeScroll();
    }

    public final void smoothScrollToNormal() {
        smoothScrollTo(0, 0);
    }

    public final int getScrollerCurrY() {
        return this.mScroller.getCurrY();
    }
}
