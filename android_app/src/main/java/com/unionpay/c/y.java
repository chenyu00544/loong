package com.unionpay.c;

import android.util.Log;

final class y {
    static void a(String str) {
        if (a.a) {
            Log.i("UPLog", str);
        }
    }

    static void a(String str, Throwable th) {
        if (a.a) {
            Log.e("UPLog", str, th);
        }
    }

    static void b(String str) {
        if (a.a) {
            Log.e("UPLog", str);
        }
    }
}
