package com.ecjia.component.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class ECJiaGoodsViewPager extends ViewPager {
    private GestureDetector gestureDetector = new GestureDetector(new a(this));
    boolean isCanScroll = true;
    private List<View> mIgnoredViews = new ArrayList();

    class a extends SimpleOnGestureListener {
        final /* synthetic */ ECJiaGoodsViewPager a;

        a(ECJiaGoodsViewPager eCJiaGoodsViewPager) {
            this.a = eCJiaGoodsViewPager;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return Math.abs(f2) <= Math.abs(f);
        }
    }

    public ECJiaGoodsViewPager(Context context) {
        super(context);
    }

    public ECJiaGoodsViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private boolean isInIgnoredView(MotionEvent motionEvent) {
        Rect rect = new Rect();
        for (View globalVisibleRect : this.mIgnoredViews) {
            globalVisibleRect.getGlobalVisibleRect(rect);
            if (rect.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                return true;
            }
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (isInIgnoredView(motionEvent)) {
            return false;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public void removeIgnoredView(View view) {
        this.mIgnoredViews.remove(view);
    }

    public void clearIgnoredViews() {
        this.mIgnoredViews.clear();
    }

    public void addIgnoredView(View view) {
        if (!this.mIgnoredViews.contains(view)) {
            this.mIgnoredViews.add(view);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 2:
                if (!this.isCanScroll) {
                    return true;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setScanScroll(boolean z) {
        this.isCanScroll = z;
    }

    public void scrollTo(int i, int i2) {
        if (this.isCanScroll) {
            super.scrollTo(i, i2);
        }
    }

    public void scrollBy(int i, int i2) {
        if (this.isCanScroll) {
            super.scrollBy(i, i2);
        }
    }

    public void setAdapter(PagerAdapter pagerAdapter) {
        super.setAdapter(pagerAdapter);
    }

    public void addOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        super.addOnPageChangeListener(onPageChangeListener);
    }
}
