package com.ecjia.component.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class ECJiaMyScrollView extends ScrollView {
    public ECJiaMyScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        requestDisallowInterceptTouchEvent(true);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
        }
        return super.onInterceptTouchEvent(motionEvent);
    }
}
