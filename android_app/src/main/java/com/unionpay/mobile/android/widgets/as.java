package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.unionpay.mobile.android.d.a;
import com.unionpay.mobile.android.j.c;

public final class as extends LinearLayout {
    private c a = null;
    private ImageView b = null;
    private ImageView c = null;

    public as(Context context) {
        super(context);
        this.a = c.a(context);
        setBackgroundColor(0);
        setOrientation(1);
        this.c = new ImageView(context);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, a.C);
        layoutParams.gravity = 80;
        addView(this.c, layoutParams);
        Drawable a = this.a.a(1001);
        if (this.b != null) {
            this.b.setBackgroundDrawable(a);
        }
    }
}
