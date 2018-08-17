package com.ecjia.component.view.banner;

import android.support.v4.view.ViewPager.PageTransformer;
import android.view.View;
import com.nineoldandroids.b.a;
import com.tencent.open.yyb.TitleBar;

public class ECJiaRotateDownPageTransformer implements PageTransformer {
    private float a;

    public void transformPage(View view, float f) {
        if (f < -1.0f) {
            a.c(view, 0.0f);
        } else if (f > 1.0f) {
            a.c(view, 0.0f);
        } else if (f < 0.0f) {
            this.a = TitleBar.BACKBTN_LEFT_MARGIN * f;
            a.a(view, ((float) view.getMeasuredWidth()) * 0.5f);
            a.b(view, (float) view.getMeasuredHeight());
            a.c(view, this.a);
        } else {
            this.a = TitleBar.BACKBTN_LEFT_MARGIN * f;
            a.a(view, ((float) view.getMeasuredWidth()) * 0.5f);
            a.b(view, (float) view.getMeasuredHeight());
            a.c(view, this.a);
        }
    }
}
