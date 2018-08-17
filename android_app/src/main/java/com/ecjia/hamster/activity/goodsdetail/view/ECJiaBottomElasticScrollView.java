package com.ecjia.hamster.activity.goodsdetail.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

public class ECJiaBottomElasticScrollView extends ScrollView {
    private static final int ANIM_TIME = 200;
    private static final float MOVE_FACTOR = 0.3f;
    private static final String TAG = "BottomElasticScrollView::";
    private boolean canPullDown = false;
    private boolean canPullUp = false;
    private View contentView;
    private boolean isMoved = false;
    private Rect originalRect = new Rect();
    ScrollView scrollView;
    private float startY;

    public ECJiaBottomElasticScrollView(Context context) {
        super(context);
    }

    public ECJiaBottomElasticScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @SuppressLint({"MissingSuperCall"})
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            this.contentView = getChildAt(0);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.contentView != null) {
            this.originalRect.set(this.contentView.getLeft(), this.contentView.getTop(), this.contentView.getRight(), this.contentView.getBottom());
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.contentView == null) {
            return super.dispatchTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.canPullDown = isCanPullDown();
                this.canPullUp = isCanPullUp();
                this.startY = motionEvent.getY();
                break;
            case 1:
            case 3:
                a.a("BottomElasticScrollView::dispatchTouchEvent ACTION_UP isMoved::" + this.isMoved);
                a.a("BottomElasticScrollView::dispatchTouchEvent ACTION_UP canPullUp::" + this.canPullUp);
                if (this.isMoved) {
                    Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) this.originalRect.top);
                    translateAnimation.setDuration(200);
                    this.contentView.startAnimation(translateAnimation);
                    a.a("BottomElasticScrollView::dispatchTouchEvent ACTION_UP startY::" + this.startY + " ev.getY()::" + motionEvent.getY());
                    a.a("BottomElasticScrollView::dispatchTouchEvent ACTION_UP detY::" + ((this.startY - motionEvent.getY()) * MOVE_FACTOR));
                    if (this.canPullUp && (this.startY - motionEvent.getY()) * MOVE_FACTOR > 120.0f && this.scrollView != null && (this.scrollView instanceof ECJiaScrollViewWrapper)) {
                        ((ECJiaScrollViewWrapper) this.scrollView).scrollToPageTwo();
                    }
                    this.contentView.layout(this.originalRect.left, this.originalRect.top, this.originalRect.right, this.originalRect.bottom);
                    this.canPullDown = false;
                    this.canPullUp = false;
                    this.isMoved = false;
                    break;
                }
                break;
            case 2:
                if (!this.canPullDown && !this.canPullUp) {
                    this.startY = motionEvent.getY();
                    this.canPullDown = isCanPullDown();
                    this.canPullUp = isCanPullUp();
                    break;
                }
                int y = (int) (motionEvent.getY() - this.startY);
                boolean z = (this.canPullDown && y > 0) || ((this.canPullUp && y < 0) || (this.canPullUp && this.canPullDown));
                if (z) {
                    int i = (int) (((float) y) * MOVE_FACTOR);
                    this.contentView.layout(this.originalRect.left, this.originalRect.top + i, this.originalRect.right, i + this.originalRect.bottom);
                    this.isMoved = true;
                    break;
                }
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 1:
                super.onTouchEvent(motionEvent);
                return false;
            case 2:
                a.a("BottomElasticScrollView::onTouchEvent  ACTION_MOVE");
                super.onTouchEvent(motionEvent);
                return true;
            default:
                return super.onTouchEvent(motionEvent);
        }
    }

    public void setParentScrollView(ScrollView scrollView) {
        this.scrollView = scrollView;
    }

    private boolean isCanPullDown() {
        return false;
    }

    private boolean isCanPullUp() {
        return this.contentView.getHeight() <= getHeight() + getScrollY();
    }

    public void fling(int i) {
        super.fling((int) (((double) i) / 1.5d));
    }
}
