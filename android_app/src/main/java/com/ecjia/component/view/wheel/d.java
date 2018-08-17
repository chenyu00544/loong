package com.ecjia.component.view.wheel;

import android.app.Activity;
import android.util.DisplayMetrics;

/* compiled from: ECJiaScreenInfo */
public class d {
    private Activity a;
    private int b;
    private int c;
    private float d;
    private int e;

    public int a() {
        return this.c;
    }

    public d(Activity activity) {
        this.a = activity;
        b();
    }

    private void b() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.a.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.b = displayMetrics.widthPixels;
        this.c = displayMetrics.heightPixels;
        this.d = displayMetrics.density;
        this.e = displayMetrics.densityDpi;
    }
}
