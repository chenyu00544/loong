package com.alipay.c.a.a.b;

import android.content.Context;

public final class a {
    private static a a = new a();

    private a() {
    }

    public static a a() {
        return a;
    }

    public static String a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16).versionName;
        } catch (Exception e) {
            return "0.0.0";
        }
    }
}
