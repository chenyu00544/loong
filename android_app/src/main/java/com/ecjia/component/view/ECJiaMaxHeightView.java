package com.ecjia.component.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import com.ecjia.a.y;
import com.ecmoban.android.missmall.R;

public class ECJiaMaxHeightView extends FrameLayout {
    private static final float DEFAULT_MAX_HEIGHT = 0.0f;
    private static final float DEFAULT_MAX_RATIO = 0.6f;
    private float mMaxHeight = 0.0f;
    private float mMaxRatio = DEFAULT_MAX_RATIO;

    public ECJiaMaxHeightView(Context context) {
        super(context);
        init();
    }

    public ECJiaMaxHeightView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initAttrs(context, attributeSet);
        init();
    }

    public ECJiaMaxHeightView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initAttrs(context, attributeSet);
        init();
    }

    private void initAttrs(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ECJiaMaxHeightView);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = obtainStyledAttributes.getIndex(i);
            if (index == 0) {
                this.mMaxRatio = obtainStyledAttributes.getFloat(index, DEFAULT_MAX_RATIO);
            } else if (index == 1) {
                this.mMaxHeight = obtainStyledAttributes.getDimension(index, 0.0f);
            }
        }
        obtainStyledAttributes.recycle();
    }

    private void init() {
        if (this.mMaxHeight <= 0.0f) {
            this.mMaxHeight = this.mMaxRatio * ((float) getScreenHeight(getContext()));
        } else {
            this.mMaxHeight = Math.min(this.mMaxHeight, this.mMaxRatio * ((float) getScreenHeight(getContext())));
        }
    }

    protected void onMeasure(int i, int i2) {
        int mode = MeasureSpec.getMode(i2);
        int size = MeasureSpec.getSize(i2);
        if (mode == 1073741824 && ((float) size) > this.mMaxHeight) {
            size = (int) this.mMaxHeight;
        }
        if (mode == 0 && ((float) r0) > this.mMaxHeight) {
            size = (int) this.mMaxHeight;
        }
        if (mode == Integer.MIN_VALUE && ((float) r0) > this.mMaxHeight) {
            size = (int) this.mMaxHeight;
        }
        super.onMeasure(i, MeasureSpec.makeMeasureSpec(size, mode));
    }

    private int getScreenHeight(Context context) {
        return y.a(context);
    }
}
