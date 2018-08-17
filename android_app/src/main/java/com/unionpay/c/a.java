package com.unionpay.c;

import android.content.Context;
import android.util.Log;
import java.util.Map;

public final class a {
    public static boolean a = true;
    public static boolean b = false;
    private static aw c;

    private static synchronized void a(Context context) {
        synchronized (a.class) {
            d.c = context.getApplicationContext();
            if (c == null) {
                System.currentTimeMillis();
                c = bb.a();
            }
        }
    }

    public static void a(Context context, String str) {
        try {
            a(context);
            c.a(context, str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static synchronized void a(Context context, String str, String str2) {
        synchronized (a.class) {
            try {
                a(context);
                c.a(context, str, str2);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void a(Context context, String str, String str2, Map map) {
        try {
            a(context);
            c.a(context, str, str2, map);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(String str, long j) {
        try {
            ax.a(str, j);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(boolean z) {
        try {
            d.b = z;
            if (a) {
                Log.i("UPLog", "[PreSettings] setReportUncaughtExceptions: " + z);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void b(Context context, String str) {
        try {
            a(context);
            c.b(context, str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void c(Context context, String str) {
        a(context, str, "", null);
    }
}
