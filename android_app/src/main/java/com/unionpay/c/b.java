package com.unionpay.c;

import android.content.Context;
import android.content.pm.PackageInfo;

public class b {
    private static volatile b a = null;
    private PackageInfo b = null;

    private b() {
    }

    public static b a() {
        if (a == null) {
            synchronized (b.class) {
                if (a == null) {
                    a = new b();
                }
            }
        }
        return a;
    }

    private synchronized boolean e(Context context) {
        boolean z;
        try {
            if (this.b == null) {
                this.b = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            }
            z = true;
        } catch (Throwable th) {
            z = false;
        }
        return z;
    }

    public int a(Context context) {
        int i = -1;
        if (context != null) {
            try {
                if (e(context)) {
                    i = this.b.versionCode;
                }
            } catch (Throwable th) {
            }
        }
        return i;
    }

    public String b(Context context) {
        if (context == null) {
            return "unknown";
        }
        try {
            return !e(context) ? "unknown" : this.b.versionName;
        } catch (Throwable th) {
            return "unknown";
        }
    }

    public long c(Context context) {
        long j = -1;
        if (context != null) {
            try {
                if (e(context) && am.a(9)) {
                    j = this.b.firstInstallTime;
                }
            } catch (Throwable th) {
            }
        }
        return j;
    }

    public long d(Context context) {
        long j = -1;
        if (context != null) {
            try {
                if (e(context) && am.a(9)) {
                    j = this.b.lastUpdateTime;
                }
            } catch (Throwable th) {
            }
        }
        return j;
    }
}
