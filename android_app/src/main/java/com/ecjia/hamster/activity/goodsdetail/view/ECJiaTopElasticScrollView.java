package com.ecjia.hamster.activity.goodsdetail.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;
import com.ecjia.a.q;

public class ECJiaTopElasticScrollView extends ScrollView {
    private static final int ANIM_TIME = 200;
    private static final float MOVE_FACTOR = 0.3f;
    private static final String TAG = "TopElasticScrollView::";
    private boolean canPullDown = false;
    private boolean canPullUp = false;
    private View contentView;
    private boolean isMoved = false;
    private Rect originalRect = new Rect();
    ScrollView scrollView;
    private float startX;
    private float startY;

    public ECJiaTopElasticScrollView(Context context) {
        super(context);
    }

    public ECJiaTopElasticScrollView(Context context, AttributeSet attributeSet) {
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
                this.startX = motionEvent.getX();
                break;
            case 1:
                if (this.isMoved) {
                    Animation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) this.originalRect.top);
                    translateAnimation.setDuration(200);
                    this.contentView.startAnimation(translateAnimation);
                    Log.i(TAG, ":::canPullDown:::" + this.canPullDown + "");
                    if (this.canPullDown && (motionEvent.getY() - this.startY) * MOVE_FACTOR > 120.0f && this.scrollView != null && (this.scrollView instanceof ECJiaScrollViewWrapper)) {
                        ((ECJiaScrollViewWrapper) this.scrollView).scrollToPageOne();
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
        q.a("TopElasticScrollView::::dispatchTouchEvent" + super.dispatchTouchEvent(motionEvent) + "");
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 2:
                a.a("MotionEvent.ACTION_MOVE");
                super.onTouchEvent(motionEvent);
                return true;
            default:
                q.a("TopElasticScrollView::::onTouchEvent" + super.onTouchEvent(motionEvent) + "");
                return super.onTouchEvent(motionEvent);
        }
    }

    public void setParentScrollView(ScrollView scrollView) {
        this.scrollView = scrollView;
    }

    private boolean isCanPullDown() {
        return getScrollY() == 0 || this.contentView.getHeight() < getHeight() + getScrollY();
    }

    private boolean isCanPullUp() {
        return false;
    }

    public void fling(int i) {
        super.fling((int) (((double) i) / 1.5d));
    }
}
