package com.ecjia.component.view.clipviewpager;

import android.os.Build.VERSION;
import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;
import com.ecmoban.android.missmall.R;

public class ECJiaScalePageTransformer implements PageTransformer {
    public void transformPage(View view, float f) {
        if (f < -1.0f) {
            f = -1.0f;
        } else if (f > 1.0f) {
            f = 1.0f;
        }
        view.setBackgroundResource(R.drawable.invite_selected_bg);
        float f2 = ((f < 0.0f ? 1.0f + f : 1.0f - f) * 0.19999999f) + 0.8f;
        view.setScaleX(f2);
        view.setScaleY(f2);
        view.setAlpha(f2);
        if (VERSION.SDK_INT < 19) {
            view.getParent().requestLayout();
        }
    }
}
