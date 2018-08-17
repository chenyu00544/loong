package com.umeng.analytics.pro;

import android.content.Context;

/* compiled from: MacTracker */
public class ag extends y {
    private static final String a = "mac";
    private Context b;

    public ag(Context context) {
        super("mac");
        this.b = context;
    }

    public String f() {
        String str = null;
        try {
            str = bt.q(this.b);
        } catch (Exception e) {
        }
        return str;
    }
}
