package com.unionpay.c;

import android.util.Log;
import java.lang.Thread.UncaughtExceptionHandler;

class au {
    private static volatile au a = null;

    static class a implements UncaughtExceptionHandler {
        private UncaughtExceptionHandler a = Thread.getDefaultUncaughtExceptionHandler();

        a() {
        }

        public final void uncaughtException(Thread thread, Throwable th) {
            if (d.b) {
                au.a(th, String.valueOf(System.currentTimeMillis()));
                Log.w("UPLog", "UncaughtException in Thread " + thread.getName(), th);
            }
            if (this.a != null) {
                this.a.uncaughtException(thread, th);
            }
        }
    }

    static {
        try {
            i.a().a(a());
        } catch (Throwable th) {
        }
    }

    private au() {
        Thread.setDefaultUncaughtExceptionHandler(new a());
    }

    public static au a() {
        if (a == null) {
            synchronized (au.class) {
                if (a == null) {
                    a = new au();
                }
            }
        }
        return a;
    }

    private static final String a(Throwable th) {
        int i = 50;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(th.toString());
        stringBuilder.append("\r\n");
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace.length <= 50) {
            i = stackTrace.length;
        }
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append("\t");
            stringBuilder.append(stackTrace[i2]);
            stringBuilder.append("\r\n");
        }
        Throwable cause = th.getCause();
        if (cause != null) {
            a(stringBuilder, stackTrace, cause, 1);
        }
        return stringBuilder.toString();
    }

    private static final void a(StringBuilder stringBuilder, StackTraceElement[] stackTraceElementArr, Throwable th, int i) {
        while (true) {
            StackTraceElement[] stackTrace = th.getStackTrace();
            int length = stackTrace.length - 1;
            int length2 = stackTraceElementArr.length - 1;
            while (length >= 0 && length2 >= 0 && stackTrace[length].equals(stackTraceElementArr[length2])) {
                length2--;
                length--;
            }
            if (length > 50) {
                length = 50;
            }
            stringBuilder.append("Caused by : ");
            stringBuilder.append(th);
            stringBuilder.append("\r\n");
            for (length2 = 0; length2 <= length; length2++) {
                stringBuilder.append("\t");
                stringBuilder.append(stackTrace[length2]);
                stringBuilder.append("\r\n");
            }
            if (i < 5 && th.getCause() != null) {
                i++;
                stackTraceElementArr = stackTrace;
            } else {
                return;
            }
        }
    }

    static final void a(Throwable th, String str) {
        int i = 0;
        if (d.c != null) {
            Throwable th2 = th;
            while (th2.getCause() != null) {
                th2 = th2.getCause();
            }
            StackTraceElement[] stackTrace = th2.getStackTrace();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(th2.getClass().getName()).append(":");
            String packageName = d.c.getPackageName();
            int i2 = 0;
            while (i2 < 3 && i < stackTrace.length) {
                String className = stackTrace[i].getClassName();
                if ((!className.startsWith("java.") || packageName.startsWith("java.")) && ((!className.startsWith("javax.") || packageName.startsWith("javax.")) && ((!className.startsWith("android.") || packageName.startsWith("android.")) && (!className.startsWith("com.android.") || packageName.startsWith("com.android."))))) {
                    stringBuilder.append(stackTrace[i].toString()).append(":");
                    i2++;
                }
                i++;
            }
            long currentTimeMillis = str.trim().isEmpty() ? System.currentTimeMillis() : Long.valueOf(str).longValue();
            ad.d().a();
            ad.d().a(currentTimeMillis, a(th));
            ad.d().b();
            z.a(System.currentTimeMillis());
        }
    }
}
