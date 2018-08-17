package com.umeng.analytics.pro;

import android.content.Context;

/* compiled from: UMIdTracker */
public class ak extends y {
    private static final String a = "idmd5";
    private Context b;

    public ak(Context context) {
        super("idmd5");
        this.b = context;
    }

    public String f() {
        return bt.d(this.b);
    }
}
