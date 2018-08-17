package com.ecjia.component.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ECJiaMyTextView extends TextView {
    Context context;

    public ECJiaMyTextView(Context context) {
        super(context);
        this.context = context;
    }

    public ECJiaMyTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = context;
    }

    public ECJiaMyTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.context = context;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (getLayout() != null) {
            setMeasuredDimension(getMeasuredWidth(), (((int) Math.ceil((double) getMaxLineHeight(getText().toString()))) + getCompoundPaddingTop()) + getCompoundPaddingBottom());
        }
    }

    private float getMaxLineHeight(String str) {
        float paddingRight = (float) ((LinearLayout) getParent()).getPaddingRight();
        return ((float) ((int) Math.ceil((double) (getPaint().measureText(str) / ((((float) ((Activity) this.context).getWindowManager().getDefaultDisplay().getWidth()) - ((float) ((LinearLayout) getParent()).getPaddingLeft())) - paddingRight))))) * (getPaint().getFontMetrics().descent - getPaint().getFontMetrics().ascent);
    }
}
