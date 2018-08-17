package com.ecjia.component.dragLayout;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.ecjia.a.q;
import java.util.ArrayList;
import java.util.List;

public class ECJiaDragLayout extends FrameLayout {
    private ViewDragHelper dragHelper;
    private Callback dragHelperCallback;
    private a dragListener;
    private GestureDetector gestureDetector;
    private int height;
    private boolean isOpen;
    private List<View> mIgnoredViews;
    private Status status;
    private FrameLayout vg_left;
    private ECJiaMyRelativeLayout vg_main;
    private int width;

    class ECJiaDragLayout_1 extends Callback {
        final /* synthetic */ ECJiaDragLayout a;

        ECJiaDragLayout_1(ECJiaDragLayout eCJiaDragLayout) {
            this.a = eCJiaDragLayout;
        }

        public int clampViewPositionHorizontal(View view, int i, int i2) {
            if (this.a.vg_main.getLeft() + i2 < 0) {
                return 0;
            }
            return i;
        }

        public boolean tryCaptureView(View view, int i) {
            return true;
        }

        public int getViewHorizontalDragRange(View view) {
            return this.a.width;
        }

        public void onViewReleased(View view, float f, float f2) {
            super.onViewReleased(view, f, f2);
            if (f > 0.0f) {
                this.a.open();
            } else if (f < 0.0f) {
                this.a.close();
            } else if (view == this.a.vg_main && this.a.vg_main.getLeft() > this.a.width / 3) {
                this.a.open();
            } else if (view != this.a.vg_left || this.a.vg_main.getLeft() <= (this.a.width * 2) / 3) {
                this.a.close();
            } else {
                this.a.open();
            }
        }

        public void onViewPositionChanged(View view, int i, int i2, int i3, int i4) {
            int left = this.a.vg_main.getLeft() + i3;
            if (left < 0) {
                left = 0;
            }
            if (left > this.a.width) {
                left = this.a.width;
            }
            if (view == this.a.vg_left) {
                this.a.vg_left.layout(0, 0, this.a.width, this.a.height);
                this.a.vg_main.layout(left, 0, this.a.width + left, this.a.height);
            }
            this.a.dispatchDragEvent(left);
        }
    }

    public enum Status {
        Drag,
        Open,
        Close
    }

    public interface a {
        void a();

        void a(float f);

        void b();
    }

    class b extends SimpleOnGestureListener {
        final /* synthetic */ ECJiaDragLayout a;

        b(ECJiaDragLayout eCJiaDragLayout) {
            this.a = eCJiaDragLayout;
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return Math.abs(f2) <= Math.abs(f);
        }
    }

    public ECJiaDragLayout(Context context) {
        this(context, null);
    }

    public ECJiaDragLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ECJiaDragLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIgnoredViews = new ArrayList();
        this.status = Status.Close;
        this.dragHelperCallback = new ECJiaDragLayout_1(this);
        this.isOpen = false;
        this.gestureDetector = new GestureDetector(new b(this));
        this.dragHelper = ViewDragHelper.create(this, this.dragHelperCallback);
    }

    public void setDragListener(a aVar) {
        this.dragListener = aVar;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.vg_left = (FrameLayout) getChildAt(0);
        this.vg_main = (ECJiaMyRelativeLayout) getChildAt(1);
        this.vg_main.setDragLayout(this);
    }

    public ViewGroup getVg_main() {
        return this.vg_main;
    }

    public ViewGroup getVg_left() {
        return this.vg_left;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.width = this.vg_left.getMeasuredWidth();
        this.height = this.vg_left.getMeasuredHeight();
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (getStatus() == Status.Drag) {
            super.onLayout(z, i, i2, i3, i4);
        } else {
            super.onLayout(z, i, i2, i3, i4);
        }
        if (this.status == Status.Open) {
            open(false);
        } else {
            close(false);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (isInIgnoredView(motionEvent)) {
            return false;
        }
        try {
            if (this.dragHelper.shouldInterceptTouchEvent(motionEvent) && this.gestureDetector.onTouchEvent(motionEvent)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            this.dragHelper.processTouchEvent(motionEvent);
        } catch (Exception e) {
            q.a("滑动");
        }
        return true;
    }

    private void dispatchDragEvent(int i) {
        if (this.dragListener != null) {
            this.dragListener.a(((float) i) / ((float) this.width));
            Status status = this.status;
            if (status != getStatus() && this.status == Status.Close) {
                this.vg_left.setEnabled(false);
                this.isOpen = false;
                this.dragListener.b();
            } else if (status != getStatus() && this.status == Status.Open) {
                this.vg_left.setEnabled(true);
                this.isOpen = true;
                this.dragListener.a();
            }
        }
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void computeScroll() {
        super.computeScroll();
        if (this.dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public Status getStatus() {
        int left = this.vg_main.getLeft();
        if (left == 0) {
            this.status = Status.Close;
        } else if (left == this.width) {
            this.status = Status.Open;
        } else {
            this.status = Status.Drag;
        }
        return this.status;
    }

    public void open() {
        open(true);
    }

    public void open(boolean z) {
        if (z) {
            this.dragHelper.smoothSlideViewTo(this.vg_main, this.width, 0);
        } else {
            this.vg_main.layout(this.width, 0, this.width * 2, this.height);
            dispatchDragEvent(this.width);
        }
        invalidate();
    }

    public void close() {
        close(true);
    }

    public void close(boolean z) {
        if (z) {
            this.dragHelper.smoothSlideViewTo(this.vg_main, 0, 0);
        } else {
            this.vg_main.layout(0, 0, this.width, this.height);
            dispatchDragEvent(0);
        }
        invalidate();
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
}
