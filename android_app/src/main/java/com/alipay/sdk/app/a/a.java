package com.alipay.sdk.app.a;

import android.content.Context;
import android.text.TextUtils;

public final class a {
    private static c a;

    public static void a(Context context) {
        if (a == null) {
            a = new c(context);
        }
    }

    public static synchronized void a(Context context, String str) {
        String str2 = null;
        synchronized (a.class) {
            if (a != null) {
                c cVar = a;
                if (TextUtils.isEmpty(cVar.i)) {
                    str2 = "";
                } else {
                    String str3;
                    String[] split = str.split("&");
                    if (split != null) {
                        str3 = null;
                        for (String split2 : split) {
                            String[] split3 = split2.split("=");
                            if (split3 != null && split3.length == 2) {
                                if (split3[0].equalsIgnoreCase("partner")) {
                                    split3[1].replace("\"", "");
                                } else if (split3[0].equalsIgnoreCase("out_trade_no")) {
                                    str3 = split3[1].replace("\"", "");
                                } else if (split3[0].equalsIgnoreCase("trade_no")) {
                                    str2 = split3[1].replace("\"", "");
                                }
                            }
                        }
                    } else {
                        str3 = null;
                    }
                    str2 = c.a(str2);
                    String a = c.a(c.a(str3));
                    cVar.b = String.format("%s,%s,-,%s,-,-,-", new Object[]{str2, str3, a});
                    str2 = String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", new Object[]{cVar.a, cVar.b, cVar.c, cVar.d, cVar.e, cVar.f, cVar.g, cVar.h, cVar.i, cVar.j});
                }
                new Thread(new b(context, str2)).start();
                a = null;
            }
        }
    }

    public static void a(String str, Throwable th) {
        if (a != null && th.getClass() != null) {
            a.a(str, th.getClass().getSimpleName(), th);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (a != null) {
            a.a(str, str2, th);
        }
    }

    public static void a(String str, String str2, String str3) {
        if (a != null) {
            a.a(str, str2, str3);
        }
    }
}
