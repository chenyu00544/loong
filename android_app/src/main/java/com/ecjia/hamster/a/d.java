package com.ecjia.hamster.a;

import android.app.Activity;
import java.util.ArrayList;

/* compiled from: ECJiaHomeView */
public abstract class d<T> {
    protected Activity a;
    protected int b = b();
    public ArrayList<T> c;

    public d(Activity activity) {
        this.a = activity;
        a();
    }

    protected void a() {
    }

    public int b() {
        return Math.min(this.a.getWindowManager().getDefaultDisplay().getWidth(), this.a.getWindowManager().getDefaultDisplay().getHeight());
    }
}
