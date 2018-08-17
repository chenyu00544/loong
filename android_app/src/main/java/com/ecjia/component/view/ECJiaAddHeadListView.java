package com.ecjia.component.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.ecjia.a.q;

public class ECJiaAddHeadListView extends ECJiaXListView {
    private int distance = 0;
    private View view = null;

    public ECJiaAddHeadListView(Context context) {
        super(context);
    }

    public ECJiaAddHeadListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ECJiaAddHeadListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        q.a("this.getScrollY()===" + getScrollY());
        if (this.distance > 0 && this.view != null) {
            if (getScrollY() > this.distance) {
                this.view.setVisibility(0);
            } else {
                this.view.setVisibility(8);
            }
        }
        super.onScrollChanged(i, i2, i3, i4);
    }

    public void addHeadView(View view) {
        this.view = view;
    }

    public void removeHeadView() {
        this.view = null;
    }

    public void setDistance(int i) {
        this.distance = i;
    }
}
