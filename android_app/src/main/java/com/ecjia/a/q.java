package com.ecjia.a;

import android.util.Log;
import com.ecjia.consts.a;

/* compiled from: ECJiaLG */
public class q {
    public static void a(String str) {
        if (a.c) {
            Log.i("米时", c(str));
        }
    }

    public static void a(String str, Throwable th) {
        if (a.c) {
            Log.i("米时", c(str), th);
        }
    }

    public static void b(String str) {
        if (a.c) {
            Log.e("米时", c(str));
        }
    }

    protected static String c(String str) {
        StackTraceElement stackTraceElement = new Throwable().fillInStackTrace().getStackTrace()[2];
        return stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + "(): " + str;
    }
}
