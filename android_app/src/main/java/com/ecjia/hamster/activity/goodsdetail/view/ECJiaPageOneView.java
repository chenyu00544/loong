package com.ecjia.hamster.activity.goodsdetail.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import com.ecjia.a.y;
import com.ecmoban.android.missmall.R;

public class ECJiaPageOneView extends LinearLayout {
    String TAG = "PageOneView::";
    Context context;
    private int height;
    private float oldX;
    public float oldY;
    int padding;
    private int t;

    public ECJiaPageOneView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ECJiaPageOneView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0);
    }

    public ECJiaPageOneView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        this.context = context;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Damp);
        this.padding = obtainStyledAttributes.getInteger(0, 0);
        obtainStyledAttributes.recycle();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.height = (y.a(this.context) - y.c(this.context)) - y.a(this.context, this.padding);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                this.oldY = motionEvent.getY();
                this.oldX = motionEvent.getX();
                break;
            case 1:
                a.a(this.TAG + "onTouchEvent ACTION_UP");
                getParent().getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case 2:
                float y = motionEvent.getY() - this.oldY;
                float x = motionEvent.getX() - this.oldX;
                int measuredHeight = getChildAt(0).getMeasuredHeight();
                int i = measuredHeight - this.t;
                a.a("t==" + this.t + "   childHeight==" + measuredHeight + "  gap===" + y + "  padding==" + i + "  height==" + this.height);
                if (y < 0.0f && i == this.height) {
                    getParent().getParent().requestDisallowInterceptTouchEvent(false);
                    super.onTouchEvent(motionEvent);
                    return false;
                }
        }
        return super.onTouchEvent(motionEvent);
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        this.t = i2;
        super.onScrollChanged(i, i2, i3, i4);
    }
}
