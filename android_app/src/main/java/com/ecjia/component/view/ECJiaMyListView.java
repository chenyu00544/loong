package com.ecjia.component.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.widget.ListView;

@TargetApi(9)
public class ECJiaMyListView extends ListView {
    public ECJiaMyListView(Context context) {
        super(context);
        setOverScrollMode(2);
    }

    public ECJiaMyListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOverScrollMode(2);
    }

    public ECJiaMyListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOverScrollMode(2);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
