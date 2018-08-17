package com.unionpay.mobile.android.d;

import android.content.Context;
import com.unionpay.mobile.android.utils.g;

public final class a {
    public static int A = 45;
    public static int B = 32;
    public static int C = 6;
    public static int D = 25;
    public static int E = 95;
    public static int F = 25;
    public static int G = 25;
    public static int H = 1;
    public static int I = 0;
    public static int J = 5;
    public static int K = 1;
    public static boolean L = true;
    public static int M = 0;
    public static int N = 0;
    private static boolean O = false;
    public static int a = 48;
    public static int b = 8;
    public static int c = 4;
    public static int d = 12;
    public static int e = 8;
    public static int f = 8;
    public static int g = 4;
    public static int h = 8;
    public static int i = 2;
    public static int j = 16;
    public static int k = 52;
    public static int l = 320;
    public static int m = 32;
    public static int n = 54;
    public static int o = 45;
    public static int p = 35;
    public static int q = 40;
    public static int r = 54;
    public static int s = 10;
    public static int t = 0;
    public static int u = 28;
    public static int v = 30;
    public static int w = 22;
    public static int x = 40;
    public static int y = n;
    public static int z = 46;

    public static void a(Context context) {
        b.a(context);
        if (!O) {
            a = g.a(context, (float) a);
            b = g.a(context, (float) b);
            c = g.a(context, (float) c);
            d = g.a(context, (float) d);
            e = g.a(context, (float) e);
            f = g.a(context, (float) f);
            g = g.a(context, (float) g);
            h = g.a(context, (float) h);
            i = g.a(context, (float) i);
            j = g.a(context, (float) j);
            k = g.a(context, (float) k);
            m = g.a(context, (float) m);
            n = g.a(context, (float) n);
            o = g.a(context, (float) o);
            p = g.a(context, (float) p);
            q = g.a(context, (float) q);
            r = g.a(context, (float) r);
            s = g.a(context, (float) s);
            u = g.a(context, (float) u);
            v = g.a(context, (float) v);
            w = g.a(context, (float) w);
            z = g.a(context, (float) z);
            x = g.a(context, (float) x);
            A = g.a(context, (float) A);
            B = g.a(context, (float) B);
            C = g.a(context, (float) C);
            y = g.a(context, (float) y);
            D = g.a(context, (float) D);
            E = g.a(context, (float) E);
            F = g.a(context, (float) F);
            G = g.a(context, (float) G);
            H = g.a(context, (float) H);
            K = (int) (((double) context.getResources().getDisplayMetrics().density) + 0.5d);
            I = context.getResources().getDisplayMetrics().widthPixels;
            t = context.getResources().getDisplayMetrics().heightPixels;
            if (I > t) {
                int i = I + t;
                I = i;
                t = i - t;
                I -= t;
            }
            J = g.a(context, (float) J);
            O = true;
        }
    }

    public static int b(Context context) {
        try {
            Class cls = Class.forName("com.android.internal.R$dimen");
            return context.getResources().getDimensionPixelSize(Integer.parseInt(cls.getField("mz_action_button_min_height").get(cls.newInstance()).toString()));
        } catch (Exception e) {
            return 0;
        }
    }
}
