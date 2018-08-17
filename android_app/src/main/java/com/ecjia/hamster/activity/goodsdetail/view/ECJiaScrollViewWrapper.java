package com.ecjia.hamster.activity.goodsdetail.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.ecjia.a.y;
import com.ecmoban.android.missmall.R;

public class ECJiaScrollViewWrapper extends ScrollView {
    String TAG = "ScrollViewWrapper2";
    private int height;
    public boolean isCanHorizentalScroll = true;
    private boolean isSetted = false;
    private boolean ispageOne = true;
    private b listener = null;
    private float oldX;
    private float oldY;
    private View pageOne;
    private View pageTwo;

    class ECJiaScrollViewWrapper_1 implements Runnable {
        final /* synthetic */ ECJiaScrollViewWrapper a;

        ECJiaScrollViewWrapper_1(ECJiaScrollViewWrapper eCJiaScrollViewWrapper) {
            this.a = eCJiaScrollViewWrapper;
        }

        public void run() {
            this.a.smoothScrollTo(0, 0);
            this.a.ispageOne = true;
            this.a.isCanHorizentalScroll = true;
            if (this.a.listener != null) {
                this.a.listener.a(0);
            }
            this.a.invalidate();
        }
    }

    class ECJiaScrollViewWrapper_2 implements Runnable {
        final /* synthetic */ ECJiaScrollViewWrapper a;

        ECJiaScrollViewWrapper_2(ECJiaScrollViewWrapper eCJiaScrollViewWrapper) {
            this.a = eCJiaScrollViewWrapper;
        }

        public void run() {
            this.a.smoothScrollTo(0, this.a.height);
            this.a.ispageOne = false;
            this.a.isCanHorizentalScroll = false;
            if (this.a.listener != null) {
                this.a.listener.a(1);
            }
            this.a.invalidate();
        }
    }

    public ECJiaScrollViewWrapper(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ECJiaScrollViewWrapper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public ECJiaScrollViewWrapper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ECJiaScrollViewWrapper);
        this.height = (y.a(context) - y.c(context)) - y.a(context, obtainStyledAttributes.getInteger(0, 0));
        obtainStyledAttributes.recycle();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (!this.isSetted) {
            LinearLayout linearLayout = (LinearLayout) getChildAt(0);
            this.pageOne = linearLayout.getChildAt(0);
            this.pageTwo = linearLayout.getChildAt(1);
            this.pageOne.getLayoutParams().height = this.height;
            this.pageTwo.getLayoutParams().height = this.height;
            this.isSetted = true;
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            scrollTo(0, 0);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        super.dispatchTouchEvent(motionEvent);
        return true;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        super.onInterceptTouchEvent(motionEvent);
        return false;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        a.a(this.TAG + "::onTouchEvent " + motionEvent.getAction());
        if (this.isCanHorizentalScroll) {
            return super.onTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.oldY = motionEvent.getY();
                this.oldX = motionEvent.getX();
                break;
            case 2:
                float y = motionEvent.getY() - this.oldY;
                if (Math.abs(motionEvent.getX() - this.oldX) > 120.0f) {
                    return true;
                }
                break;
        }
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setOnScrollChangeListener(b bVar) {
        this.listener = bVar;
    }

    public void closeMenu() {
        if (!this.ispageOne) {
            smoothScrollTo(0, 0);
            this.ispageOne = true;
        }
    }

    public void openMenu() {
        if (this.ispageOne) {
            smoothScrollTo(0, this.height);
            this.ispageOne = false;
        }
    }

    public void scrollToPageOne() {
        Log.i(this.TAG, "移动到第一页");
        post(new ECJiaScrollViewWrapper_1(this));
    }

    public void scrollToPageTwo() {
        Log.i(this.TAG, "移动到第二页");
        post(new ECJiaScrollViewWrapper_2(this));
    }

    public int getScrollHeight() {
        Log.i(this.TAG, ":::height" + this.height);
        return this.height;
    }

    public void fling(int i) {
        super.fling((int) (((double) i) / 1.5d));
    }
}
