package com.ecjia.hamster.module.goodsReturn;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;

public class ECJiaWidthChangeAbleRecyclerView extends RecyclerView {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private int mOritation = 0;

    public ECJiaWidthChangeAbleRecyclerView(Context context) {
        super(context);
    }

    public ECJiaWidthChangeAbleRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ECJiaWidthChangeAbleRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onMeasure(int i, int i2) {
        if (this.mOritation == 1) {
            super.onMeasure(i, MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
        } else {
            super.onMeasure(MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE), i2);
        }
    }

    public void setOritation(int i) {
        this.mOritation = i;
    }
}
