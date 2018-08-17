package com.umeng.analytics.pro;

import android.text.TextUtils;
import android.util.Log;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.umeng.analytics.a;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Formatter;
import java.util.Locale;

/* compiled from: MLog */
public class bw {
    public static boolean a = false;
    private static String b = a.c;
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static final int f = 4;
    private static final int g = 5;
    private static int h = m_AppUI.MSG_APP_DATA_OK;

    private bw() {
    }

    public static void a(Locale locale, String str, Object... objArr) {
        try {
            c(b, new Formatter(locale).format(str, objArr).toString(), null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void b(Locale locale, String str, Object... objArr) {
        try {
            b(b, new Formatter(locale).format(str, objArr).toString(), null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void c(Locale locale, String str, Object... objArr) {
        try {
            e(b, new Formatter(locale).format(str, objArr).toString(), null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void d(Locale locale, String str, Object... objArr) {
        try {
            a(b, new Formatter(locale).format(str, objArr).toString(), null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void e(Locale locale, String str, Object... objArr) {
        try {
            d(b, new Formatter(locale).format(str, objArr).toString(), null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void a(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                c(b, new Formatter().format(str, objArr).toString(), null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            c(str, str2, null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void b(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                b(b, new Formatter().format(str, objArr).toString(), null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            b(str, str2, null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void c(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                e(b, new Formatter().format(str, objArr).toString(), null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            e(str, str2, null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void d(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                a(b, new Formatter().format(str, objArr).toString(), null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            a(str, str2, null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void e(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                d(b, new Formatter().format(str, objArr).toString(), null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            d(str, str2, null);
        } catch (Throwable th) {
            e(th);
        }
    }

    public static void a(Throwable th) {
        c(b, null, th);
    }

    public static void b(Throwable th) {
        a(b, null, th);
    }

    public static void c(Throwable th) {
        d(b, null, th);
    }

    public static void d(Throwable th) {
        b(b, null, th);
    }

    public static void e(Throwable th) {
        e(b, null, th);
    }

    public static void a(String str, Throwable th) {
        c(b, str, th);
    }

    public static void b(String str, Throwable th) {
        a(b, str, th);
    }

    public static void c(String str, Throwable th) {
        d(b, str, th);
    }

    public static void d(String str, Throwable th) {
        b(b, str, th);
    }

    public static void e(String str, Throwable th) {
        e(b, str, th);
    }

    public static void a(String str) {
        a(b, str, null);
    }

    public static void b(String str) {
        b(b, str, null);
    }

    public static void c(String str) {
        c(b, str, null);
    }

    public static void d(String str) {
        d(b, str, null);
    }

    public static void e(String str) {
        e(b, str, null);
    }

    public static void a(String str, String str2, Throwable th) {
        if (a) {
            a(1, str, str2, th);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (a) {
            a(2, str, str2, th);
        }
    }

    public static void c(String str, String str2, Throwable th) {
        if (a) {
            a(3, str, str2, th);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (a) {
            a(4, str, str2, th);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (a) {
            a(5, str, str2, th);
        }
    }

    private static void a(int i, String str, String str2, Throwable th) {
        int i2 = 0;
        if (!TextUtils.isEmpty(str2)) {
            int length = str2.length();
            int i3 = h;
            int i4 = 0;
            while (i2 < 100) {
                if (length <= i3) {
                    switch (i) {
                        case 1:
                            Log.v(str, str2.substring(i4, length));
                            break;
                        case 2:
                            Log.d(str, str2.substring(i4, length));
                            break;
                        case 3:
                            Log.i(str, str2.substring(i4, length));
                            break;
                        case 4:
                            Log.w(str, str2.substring(i4, length));
                            break;
                        case 5:
                            Log.e(str, str2.substring(i4, length));
                            break;
                    }
                }
                switch (i) {
                    case 1:
                        Log.v(str, str2.substring(i4, i3));
                        break;
                    case 2:
                        Log.d(str, str2.substring(i4, i3));
                        break;
                    case 3:
                        Log.i(str, str2.substring(i4, i3));
                        break;
                    case 4:
                        Log.w(str, str2.substring(i4, i3));
                        break;
                    case 5:
                        Log.e(str, str2.substring(i4, i3));
                        break;
                    default:
                        break;
                }
                i2++;
                i4 = i3;
                i3 = h + i3;
            }
        }
        if (th != null) {
            String f = f(th);
            if (!TextUtils.isEmpty(f)) {
                switch (i) {
                    case 1:
                        Log.v(str, f);
                        return;
                    case 2:
                        Log.d(str, f);
                        return;
                    case 3:
                        Log.i(str, f);
                        return;
                    case 4:
                        Log.w(str, f);
                        return;
                    case 5:
                        Log.e(str, f);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public static String f(Throwable th) {
        StringWriter stringWriter;
        PrintWriter printWriter;
        Throwable th2;
        StringWriter stringWriter2 = null;
        String str = "";
        try {
            stringWriter = new StringWriter();
            try {
                printWriter = new PrintWriter(stringWriter);
                try {
                    th.printStackTrace(printWriter);
                    printWriter.flush();
                    stringWriter.flush();
                    str = stringWriter.toString();
                    if (stringWriter != null) {
                        try {
                            stringWriter.close();
                        } catch (Throwable th3) {
                        }
                    }
                    if (printWriter != null) {
                        printWriter.close();
                    }
                } catch (Throwable th4) {
                    th2 = th4;
                    if (stringWriter != null) {
                        try {
                            stringWriter.close();
                        } catch (Throwable th5) {
                        }
                    }
                    if (printWriter != null) {
                        printWriter.close();
                    }
                    throw th2;
                }
            } catch (Throwable th6) {
                th2 = th6;
                printWriter = null;
                if (stringWriter != null) {
                    stringWriter.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
                throw th2;
            }
        } catch (Throwable th7) {
            th2 = th7;
            printWriter = null;
            stringWriter = null;
            if (stringWriter != null) {
                stringWriter.close();
            }
            if (printWriter != null) {
                printWriter.close();
            }
            throw th2;
        }
        return str;
    }
}
