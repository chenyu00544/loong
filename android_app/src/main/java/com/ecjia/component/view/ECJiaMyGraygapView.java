package com.ecjia.component.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.ecmoban.android.missmall.R;

public class ECJiaMyGraygapView extends LinearLayout {
    public ECJiaMyGraygapView(Context context) {
        super(context);
        ViewGroup.inflate(context, R.layout.layout_graygap, this);
    }

    public ECJiaMyGraygapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        ViewGroup.inflate(context, R.layout.layout_graygap, this);
    }

    @TargetApi(11)
    public ECJiaMyGraygapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        ViewGroup.inflate(context, R.layout.layout_graygap, this);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }
}
