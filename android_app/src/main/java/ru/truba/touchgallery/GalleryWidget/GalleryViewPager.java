package ru.truba.touchgallery.GalleryWidget;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import ru.truba.touchgallery.TouchView.TouchImageView;

public class GalleryViewPager extends ViewPager {
    PointF last;
    public TouchImageView mCurrentView;

    public GalleryViewPager(Context context) {
        super(context);
    }

    public GalleryViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private float[] handleMotionEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.last = new PointF(motionEvent.getX(0), motionEvent.getY(0));
                break;
            case 1:
            case 2:
                PointF pointF = new PointF(motionEvent.getX(0), motionEvent.getY(0));
                return new float[]{pointF.x - this.last.x, pointF.y - this.last.y};
        }
        return null;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & 255) == 1) {
            super.onTouchEvent(motionEvent);
        }
        float[] handleMotionEvent = handleMotionEvent(motionEvent);
        if (this.mCurrentView.pagerCanScroll()) {
            return super.onTouchEvent(motionEvent);
        }
        if (handleMotionEvent != null && this.mCurrentView.onRightSide && handleMotionEvent[0] < 0.0f) {
            return super.onTouchEvent(motionEvent);
        }
        if (handleMotionEvent != null && this.mCurrentView.onLeftSide && handleMotionEvent[0] > 0.0f) {
            return super.onTouchEvent(motionEvent);
        }
        if (handleMotionEvent != null) {
            return false;
        }
        if (this.mCurrentView.onLeftSide || this.mCurrentView.onRightSide) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if ((motionEvent.getAction() & 255) == 1) {
            super.onInterceptTouchEvent(motionEvent);
        }
        float[] handleMotionEvent = handleMotionEvent(motionEvent);
        if (this.mCurrentView.pagerCanScroll()) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (handleMotionEvent != null && this.mCurrentView.onRightSide && handleMotionEvent[0] < 0.0f) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (handleMotionEvent != null && this.mCurrentView.onLeftSide && handleMotionEvent[0] > 0.0f) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (handleMotionEvent != null) {
            return false;
        }
        if (this.mCurrentView.onLeftSide || this.mCurrentView.onRightSide) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }
}
