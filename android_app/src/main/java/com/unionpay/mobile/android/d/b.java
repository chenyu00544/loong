package com.unionpay.mobile.android.d;

import android.content.Context;
import com.unionpay.mobile.android.utils.g;

public final class b {
    public static int a = 12;
    public static int b = -1;
    public static int c = -1;
    public static int d = -1;
    public static int e = 4;
    public static int f = 8;
    public static int g = 12;
    public static int h = 16;
    public static float i = 18.0f;
    public static float j = 17.0f;
    public static float k = 16.0f;
    public static float l = 14.0f;
    public static float m = 12.0f;
    public static int n = 54;
    public static int o = 20;
    public static int p = 60;
    private static boolean q = false;

    public static final void a(Context context) {
        if (!q) {
            a = g.a(context, (float) a);
            n = g.a(context, (float) n);
            o = g.a(context, (float) o);
            e = g.a(context, (float) e);
            f = g.a(context, (float) f);
            g = g.a(context, (float) g);
            h = g.a(context, (float) h);
            q = true;
        }
    }
}
