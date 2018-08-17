package com.ecjia.component.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class ECJiaScrollView_Main extends ScrollView {
    private a mScrollListener;

    public interface a {
        void a(int i, int i2, int i3, int i4);
    }

    public ECJiaScrollView_Main(Context context) {
        super(context);
    }

    public ECJiaScrollView_Main(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ECJiaScrollView_Main(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnScrollListener(a aVar) {
        this.mScrollListener = aVar;
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        if (this.mScrollListener != null) {
            this.mScrollListener.a(i, i2, i3, i4);
        }
        super.onScrollChanged(i, i2, i3, i4);
    }
}
