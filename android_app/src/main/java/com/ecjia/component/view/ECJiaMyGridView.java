package com.ecjia.component.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.GridView;

public class ECJiaMyGridView extends GridView {
    public ECJiaMyGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ECJiaMyGridView(Context context) {
        super(context);
    }

    public ECJiaMyGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
