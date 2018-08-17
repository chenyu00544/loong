package com.ecjia.component.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ScrollView;
import com.tencent.open.yyb.TitleBar;

public class ECJiaNewScrollView extends ScrollView {
    private static final int BOTTOM_OVERSCROLL_STATE = 2;
    private static final float ELASTICITY_COEFFICIENT = 0.25f;
    private static final int INVALID_POINTER = -1;
    private static final int NO_OVERSCROLL_STATE = 0;
    private static final int OVERSCROLL_MAX_HEIGHT = 1200;
    private static final int TOP_OVERSCROLL_STATE = 1;
    private static final int TRIGGER_HEIGHT = 120;
    private int inertanceY;
    private boolean isInertance;
    private boolean isOnTouch;
    private boolean isRecord;
    private int mActivePointerId;
    private ECJiaOverScrollWarpLayout mContentLayout;
    private boolean mIsBanQuickScroll;
    private boolean mIsUseInertance;
    private boolean mIsUseOverScroll;
    private float mLastMotionY;
    private b mOverScrollListener;
    private c mOverScrollTinyListener;
    private int mOverScrollTrigger;
    private a mScrollListener;
    private int overScrollDistance;
    private int overScrollSate;

    public interface a {
        void a(int i, int i2, int i3, int i4);
    }

    public interface b {
        void a();

        void b();
    }

    public interface c {
        void a();

        void a(int i, int i2);
    }

    public ECJiaNewScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIsUseOverScroll = true;
        this.mActivePointerId = -1;
        this.mIsUseInertance = true;
        this.mOverScrollTrigger = 120;
        initScrollView();
    }

    public ECJiaNewScrollView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ECJiaNewScrollView(Context context) {
        this(context, null);
    }

    private void initScrollView() {
        if (VERSION.SDK_INT >= 9) {
            setOverScrollMode(2);
        } else {
            ViewCompat.setOverScrollMode(this, 2);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.mIsUseOverScroll) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0:
                if (isOverScrolled()) {
                    this.isRecord = true;
                    this.mLastMotionY = (float) ((int) motionEvent.getY());
                    this.mActivePointerId = motionEvent.getPointerId(0);
                    break;
                }
                break;
            case 2:
                if (this.isRecord && Math.abs(motionEvent.getY() - this.mLastMotionY) > TitleBar.BACKBTN_LEFT_MARGIN) {
                    return true;
                }
            case 3:
                if (this.isRecord) {
                    this.isRecord = false;
                    break;
                }
                break;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.isOnTouch = true;
        if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            if (this.mOverScrollTinyListener != null) {
                this.mOverScrollTinyListener.a();
            }
            this.isOnTouch = false;
        }
        if (!this.mIsUseOverScroll) {
            return super.onTouchEvent(motionEvent);
        }
        if (isOverScrolled()) {
            int findPointerIndex;
            switch (motionEvent.getAction() & 255) {
                case 0:
                    this.mActivePointerId = motionEvent.getPointerId(0);
                    this.mLastMotionY = (float) ((int) motionEvent.getY());
                    break;
                case 1:
                case 3:
                    this.mContentLayout.smoothScrollToNormal();
                    overScrollTrigger();
                    this.overScrollDistance = 0;
                    this.isRecord = false;
                    this.mActivePointerId = -1;
                    break;
                case 2:
                    if (this.isRecord) {
                        findPointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
                        if (findPointerIndex != -1) {
                            float y = motionEvent.getY(findPointerIndex);
                            findPointerIndex = (int) (this.mLastMotionY - y);
                            this.mLastMotionY = y;
                            if (Math.abs(this.overScrollDistance) >= OVERSCROLL_MAX_HEIGHT && this.overScrollDistance * findPointerIndex > 0) {
                                findPointerIndex = 0;
                            }
                            if (this.overScrollDistance * (this.overScrollDistance + findPointerIndex) >= 0) {
                                if ((!isOnBottom() && this.overScrollDistance > 0) || (!isOnTop() && this.overScrollDistance < 0)) {
                                    this.mContentLayout.smoothScrollToNormal();
                                    this.overScrollDistance = 0;
                                    break;
                                }
                                if (this.overScrollDistance * findPointerIndex > 0) {
                                    findPointerIndex = (int) (((float) findPointerIndex) * ELASTICITY_COEFFICIENT);
                                }
                                if (this.overScrollDistance == 0) {
                                    findPointerIndex = (int) ((((float) findPointerIndex) * ELASTICITY_COEFFICIENT) * 0.5f);
                                }
                                if (!(this.overScrollDistance == 0 && findPointerIndex == 0)) {
                                    if (Math.abs(findPointerIndex) > 20) {
                                        findPointerIndex = findPointerIndex > 0 ? 20 : -20;
                                    }
                                    this.overScrollDistance += findPointerIndex;
                                    if (!isOnTop() || this.overScrollDistance <= 0 || isOnBottom()) {
                                        if (isOnBottom() && this.overScrollDistance < 0 && !isOnTop()) {
                                            this.overScrollDistance = 0;
                                            break;
                                        }
                                        this.mContentLayout.smoothScrollBy(0, findPointerIndex);
                                        if (this.mOverScrollTinyListener != null) {
                                            this.mOverScrollTinyListener.a(findPointerIndex, this.overScrollDistance);
                                        }
                                        return true;
                                    }
                                    this.overScrollDistance = 0;
                                    break;
                                }
                            }
                            this.mContentLayout.smoothScrollToNormal();
                            this.overScrollDistance = 0;
                            break;
                        }
                    }
                    break;
                case 5:
                    findPointerIndex = motionEvent.getActionIndex();
                    this.mLastMotionY = (float) ((int) motionEvent.getY(findPointerIndex));
                    this.mActivePointerId = motionEvent.getPointerId(findPointerIndex);
                    break;
                case 6:
                    onSecondaryPointerUp(motionEvent);
                    if (this.mActivePointerId != -1) {
                        this.mLastMotionY = (float) ((int) motionEvent.getY(motionEvent.findPointerIndex(this.mActivePointerId)));
                        break;
                    }
                    break;
            }
            return super.onTouchEvent(motionEvent);
        }
        this.mLastMotionY = (float) ((int) motionEvent.getY());
        return super.onTouchEvent(motionEvent);
    }

    private void onSecondaryPointerUp(MotionEvent motionEvent) {
        int action = (motionEvent.getAction() & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
        if (motionEvent.getPointerId(action) == this.mActivePointerId) {
            action = action == 0 ? 1 : 0;
            this.mLastMotionY = (float) ((int) motionEvent.getY(action));
            this.mActivePointerId = motionEvent.getPointerId(action);
        }
    }

    public boolean isOverScrolled() {
        return isOnTop() || isOnBottom();
    }

    private boolean isOnTop() {
        return getScrollY() == 0;
    }

    private boolean isOnBottom() {
        return getScrollY() + getHeight() == this.mContentLayout.getHeight();
    }

    private void initOverScrollLayout() {
        setFillViewport(true);
        if (this.mContentLayout == null) {
            View childAt = getChildAt(0);
            this.mContentLayout = new ECJiaOverScrollWarpLayout(getContext());
            removeAllViews();
            this.mContentLayout.addView(childAt);
            addView(this.mContentLayout, new LayoutParams(-1, -1));
        }
    }

    public void setOverScroll(boolean z) {
        this.mIsUseOverScroll = z;
    }

    public void setUseInertance(boolean z) {
        this.mIsUseInertance = z;
    }

    protected void onAttachedToWindow() {
        initOverScrollLayout();
        super.onAttachedToWindow();
    }

    public int getScrollState() {
        invalidateState();
        return this.overScrollSate;
    }

    private void invalidateState() {
        if (this.mContentLayout.getScrollerCurrY() == 0) {
            this.overScrollSate = 0;
        }
        if (this.mContentLayout.getScrollerCurrY() < 0) {
            this.overScrollSate = 1;
        }
        if (this.mContentLayout.getScrollerCurrY() > 0) {
            this.overScrollSate = 2;
        }
    }

    @TargetApi(9)
    protected boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
        return super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        if (this.mScrollListener != null && this.overScrollDistance == 0) {
            this.mScrollListener.a(i, i2, i3, i4);
        }
        super.onScrollChanged(i, i2, i3, i4);
    }

    @TargetApi(9)
    protected void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        if (!(!this.mIsUseInertance || this.isInertance || i2 == 0)) {
            this.isInertance = true;
        }
        if (z2 && !this.isOnTouch && this.isInertance) {
            this.mContentLayout.smoothScrollBy(0, this.inertanceY);
            this.mContentLayout.smoothScrollToNormal();
            this.inertanceY = 0;
        }
        super.onOverScrolled(i, i2, z, z2);
    }

    public void setOverScrollListener(b bVar) {
        this.mOverScrollListener = bVar;
    }

    public void setOverScrollTinyListener(c cVar) {
        this.mOverScrollTinyListener = cVar;
    }

    public void setOnScrollListener(a aVar) {
        this.mScrollListener = aVar;
    }

    public void setOverScrollTrigger(int i) {
        if (i >= 30) {
            this.mOverScrollTrigger = i;
        }
    }

    private void overScrollTrigger() {
        if (this.mOverScrollListener != null) {
            if (this.overScrollDistance > this.mOverScrollTrigger && isOnBottom()) {
                this.mOverScrollListener.b();
            }
            if (this.overScrollDistance < (-this.mOverScrollTrigger) && isOnTop()) {
                this.mOverScrollListener.a();
            }
        }
    }

    public void setQuickScroll(boolean z) {
        this.mIsBanQuickScroll = !z;
    }

    public void computeScroll() {
        if (!this.mIsBanQuickScroll) {
            super.computeScroll();
        }
    }

    public int getScrollHeight() {
        return this.mContentLayout.getHeight() - getHeight();
    }

    public void fling(int i) {
        this.inertanceY = (i * 50) / 5000;
        super.fling(i);
    }
}
