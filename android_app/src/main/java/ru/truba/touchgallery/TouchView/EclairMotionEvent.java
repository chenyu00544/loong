package ru.truba.touchgallery.TouchView;

import android.view.MotionEvent;

public class EclairMotionEvent extends WrapMotionEvent {
    protected EclairMotionEvent(MotionEvent motionEvent) {
        super(motionEvent);
    }

    public float getX(int i) {
        return this.event.getX(i);
    }

    public float getY(int i) {
        return this.event.getY(i);
    }

    public int getPointerCount() {
        return this.event.getPointerCount();
    }

    public int getPointerId(int i) {
        return this.event.getPointerId(i);
    }
}
