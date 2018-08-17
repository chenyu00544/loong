package com.ecjia.component.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import com.baidu.mapapi.map.WeightedLatLng;
import com.ecjia.consts.b;
import com.ecmoban.android.missmall.R;

public class ECJiaSwipeListView extends ListView {
    private View mCurrentItemView;
    private final int mDuration;
    private final int mDurationStep;
    private float mFirstX;
    private float mFirstY;
    private Boolean mIsHorizontal;
    private boolean mIsShown;
    private View mPreItemView;
    private int mRightViewWidth;

    @SuppressLint({"HandlerLeak"})
    class a extends Handler {
        int a = 0;
        int b;
        int c;
        View d;
        final /* synthetic */ ECJiaSwipeListView e;
        private boolean f = false;

        a(ECJiaSwipeListView eCJiaSwipeListView) {
            this.e = eCJiaSwipeListView;
        }

        private void a() {
            this.f = false;
            this.a = 0;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (this.a == 0) {
                if (!this.f) {
                    this.f = true;
                    this.d = (View) message.obj;
                    this.b = message.arg1;
                    this.c = message.arg2;
                    this.a = (int) ((((double) ((this.c - this.b) * 10)) * WeightedLatLng.DEFAULT_INTENSITY) / 100.0d);
                    if (this.a < 0 && this.a > -1) {
                        this.a = -1;
                    } else if (this.a > 0 && this.a < 1) {
                        this.a = 1;
                    }
                    if (Math.abs(this.c - this.b) < 10) {
                        this.d.scrollTo(this.c, 0);
                        a();
                        return;
                    }
                }
                return;
            }
            this.b += this.a;
            int i = ((this.a <= 0 || this.b <= this.c) && (this.a >= 0 || this.b >= this.c)) ? 0 : true;
            if (i != 0) {
                this.b = this.c;
            }
            this.d.scrollTo(this.b, 0);
            this.e.invalidate();
            if (i == 0) {
                sendEmptyMessageDelayed(0, 10);
            } else {
                a();
            }
        }
    }

    public ECJiaSwipeListView(Context context) {
        this(context, null);
    }

    public ECJiaSwipeListView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ECJiaSwipeListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDuration = 100;
        this.mDurationStep = 10;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.swipelistviewstyle);
        this.mRightViewWidth = (int) obtainStyledAttributes.getDimension(0, b.a(getContext()).getDimension(R.dimen.double_right_item));
        obtainStyledAttributes.recycle();
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0:
                this.mIsHorizontal = null;
                this.mFirstX = x;
                this.mFirstY = y;
                int pointToPosition = pointToPosition((int) this.mFirstX, (int) this.mFirstY);
                if (pointToPosition >= 0) {
                    View childAt = getChildAt(pointToPosition - getFirstVisiblePosition());
                    this.mPreItemView = this.mCurrentItemView;
                    this.mCurrentItemView = childAt;
                    break;
                }
                break;
            case 1:
            case 3:
                if (this.mIsShown && (this.mPreItemView != this.mCurrentItemView || isHitCurItemLeft(x))) {
                    hiddenRight(this.mPreItemView);
                    break;
                }
            case 2:
                y -= this.mFirstY;
                if (Math.abs(x - this.mFirstX) >= 5.0f && Math.abs(y) >= 5.0f) {
                    return true;
                }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    private boolean isHitCurItemLeft(float f) {
        return f < ((float) (getWidth() - this.mRightViewWidth));
    }

    private boolean judgeScrollDirection(float f, float f2) {
        if (Math.abs(f) > 30.0f && Math.abs(f) > Math.abs(f2) * 2.0f) {
            this.mIsHorizontal = Boolean.valueOf(true);
            return true;
        } else if (Math.abs(f2) <= 30.0f || Math.abs(f2) <= Math.abs(f) * 2.0f) {
            return false;
        } else {
            this.mIsHorizontal = Boolean.valueOf(false);
            return true;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 1:
            case 3:
                clearPressedState();
                if (this.mIsShown) {
                    hiddenRight(this.mPreItemView);
                }
                if (this.mIsHorizontal != null && this.mIsHorizontal.booleanValue()) {
                    if (this.mFirstX - x > ((float) (this.mRightViewWidth / 2))) {
                        showRight(this.mCurrentItemView);
                        return true;
                    }
                    hiddenRight(this.mCurrentItemView);
                    return true;
                }
            case 2:
                x -= this.mFirstX;
                y -= this.mFirstY;
                if (this.mIsHorizontal != null || judgeScrollDirection(x, y)) {
                    if (!this.mIsHorizontal.booleanValue()) {
                        if (this.mIsShown) {
                            hiddenRight(this.mPreItemView);
                            break;
                        }
                    }
                    if (this.mIsShown && this.mPreItemView != this.mCurrentItemView) {
                        hiddenRight(this.mPreItemView);
                    }
                    if (this.mIsShown && this.mPreItemView == this.mCurrentItemView) {
                        x -= (float) this.mRightViewWidth;
                    }
                    if (x >= 0.0f || x <= ((float) (-this.mRightViewWidth))) {
                        return true;
                    }
                    this.mCurrentItemView.scrollTo((int) (-x), 0);
                    return true;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private void clearPressedState() {
        this.mCurrentItemView.setPressed(false);
        setPressed(false);
        refreshDrawableState();
    }

    private void showRight(View view) {
        Message obtainMessage = new a(this).obtainMessage();
        obtainMessage.obj = view;
        obtainMessage.arg1 = view.getScrollX();
        obtainMessage.arg2 = this.mRightViewWidth;
        obtainMessage.sendToTarget();
        this.mIsShown = true;
    }

    private void hiddenRight(View view) {
        if (this.mCurrentItemView != null) {
            Message obtainMessage = new a(this).obtainMessage();
            obtainMessage.obj = view;
            obtainMessage.arg1 = view.getScrollX();
            obtainMessage.arg2 = 0;
            obtainMessage.sendToTarget();
            this.mIsShown = false;
        }
    }

    public int getRightViewWidth() {
        return this.mRightViewWidth;
    }

    public void setRightViewWidth(int i) {
        this.mRightViewWidth = i;
    }

    public void deleteItem(View view) {
        hiddenRight(view);
    }
}
