package com.ecjia.component.view.banner;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;

public class ECJiaZoomOutPageTransformer implements PageTransformer {
    private static float a = 0.85f;
    private static float b = 0.5f;

    public void transformPage(View view, float f) {
        int width = view.getWidth();
        int height = view.getHeight();
        if (f < -1.0f) {
            view.setAlpha(0.0f);
        } else if (f <= 1.0f) {
            float max = Math.max(a, 1.0f - Math.abs(f));
            float f2 = (((float) height) * (1.0f - max)) / 2.0f;
            float f3 = (((float) width) * (1.0f - max)) / 2.0f;
            if (f < 0.0f) {
                view.setTranslationX(f3 - (f2 / 2.0f));
            } else {
                view.setTranslationX((-f3) + (f2 / 2.0f));
            }
            view.setScaleX(max);
            view.setScaleY(max);
            view.setAlpha(b + (((max - a) / (1.0f - a)) * (1.0f - b)));
        } else {
            view.setAlpha(0.0f);
        }
    }
}
