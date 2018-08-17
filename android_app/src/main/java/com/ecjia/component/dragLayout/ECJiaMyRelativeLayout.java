package com.ecjia.component.dragLayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import com.ecjia.component.dragLayout.ECJiaDragLayout.Status;

public class ECJiaMyRelativeLayout extends RelativeLayout {
    private ECJiaDragLayout dl;

    public ECJiaMyRelativeLayout(Context context) {
        super(context);
    }

    public ECJiaMyRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ECJiaMyRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setDragLayout(ECJiaDragLayout eCJiaDragLayout) {
        this.dl = eCJiaDragLayout;
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.dl.getStatus() != Status.Close) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.dl.getStatus() == Status.Close) {
            return super.onTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() != 1) {
            return true;
        }
        this.dl.close();
        return true;
    }
}
