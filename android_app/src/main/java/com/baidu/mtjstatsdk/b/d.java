package com.baidu.mtjstatsdk.b;

import android.text.format.DateFormat;
import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public final class d {
    private static DateFormat a;

    static {
        a = null;
        a();
        a = new DateFormat();
    }

    public static int a(String str) {
        return a("statsdk", str);
    }

    public static int a(String str, String str2) {
        if (!a(3)) {
            return -1;
        }
        b(str, str2);
        return Log.d(str, str2);
    }

    public static int a(String str, Throwable th) {
        if (!a(3)) {
            return -1;
        }
        a("statsdk", str, th);
        return Log.d("statsdk", str, th);
    }

    public static int a(Throwable th) {
        return a("", th);
    }

    public static int a(Object... objArr) {
        return !a(3) ? -1 : a(d(objArr));
    }

    public static void a() {
        b.a("_b_sdk.log");
    }

    private static void a(String str, String str2, Throwable th) {
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        b(str, "\n" + stringWriter.toString());
        printWriter.close();
        try {
            stringWriter.close();
        } catch (Throwable e) {
            Log.w("Log.debug", "", e);
        }
    }

    public static boolean a(int i) {
        return a("statsdk", i);
    }

    public static boolean a(String str, int i) {
        return i >= a.a;
    }

    public static int b(String str) {
        if (!a(5)) {
            return -1;
        }
        b("statsdk", str);
        return Log.w("statsdk", str);
    }

    public static int b(Object... objArr) {
        return !a(5) ? -1 : b(d(objArr));
    }

    private static synchronized void b(String str, String str2) {
        synchronized (d.class) {
        }
    }

    public static int c(String str) {
        if (!a(6)) {
            return -1;
        }
        b("statsdk", str);
        return Log.e("statsdk", str);
    }

    public static int c(Object... objArr) {
        return !a(6) ? -1 : c(d(objArr));
    }

    private static String d(Object[] objArr) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Object append : objArr) {
            stringBuilder.append(append).append(' ');
        }
        return stringBuilder.toString();
    }
}
