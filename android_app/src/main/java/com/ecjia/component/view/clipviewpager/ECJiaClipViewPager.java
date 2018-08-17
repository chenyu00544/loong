package com.ecjia.component.view.clipviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ECJiaClipViewPager extends ViewPager {
    private boolean scrollble = true;

    public ECJiaClipViewPager(Context context) {
        super(context);
    }

    public ECJiaClipViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            View viewOfClickOnScreen = viewOfClickOnScreen(motionEvent);
            if (viewOfClickOnScreen != null) {
                int childCount = (getChildCount() - indexOfChild(viewOfClickOnScreen)) - 1;
                if (getCurrentItem() != childCount) {
                    setCurrentItem(childCount);
                }
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    private View viewOfClickOnScreen(MotionEvent motionEvent) {
        int childCount = getChildCount();
        int[] iArr = new int[2];
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            childAt.getLocationOnScreen(iArr);
            int i2 = iArr[0];
            int top = getTop();
            int width = iArr[0] + childAt.getWidth();
            int bottom = getBottom();
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (x > ((float) i2) && x < ((float) width) && y > ((float) top) && y < ((float) bottom)) {
                return childAt;
            }
        }
        return null;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.scrollble) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public boolean isScrollble() {
        return this.scrollble;
    }

    public void setScrollble(boolean z) {
        this.scrollble = z;
    }
}
