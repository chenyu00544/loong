package com.unionpay.c;

import com.sina.weibo.sdk.exception.WeiboAuthException;

final class z {
    static String a() {
        return d.c == null ? null : aj.b(d.c, "UPpref_longtime", "UPaes_key", null);
    }

    static void a(long j) {
        if (d.c != null) {
            aj.a(d.c, "UPpref_shorttime", "UPpref.end.key", j);
        }
    }

    static void a(String str) {
        if (d.c != null) {
            aj.a(d.c, "UPpref_longtime", "UPisAppQuiting", str);
        }
    }

    static long b() {
        return d.c == null ? 0 : aj.b(d.c, "UPpref_longtime", "UPpref.init.key", 0);
    }

    static String c() {
        return d.c == null ? WeiboAuthException.DEFAULT_AUTH_ERROR_CODE : aj.b(d.c, "UPpref_longtime", "UPisAppQuiting", WeiboAuthException.DEFAULT_AUTH_ERROR_CODE);
    }

    static int d() {
        try {
            return g() != -1 ? Integer.parseInt(String.valueOf(g())) : b.a().a(d.c);
        } catch (Throwable th) {
            return -1;
        }
    }

    static String e() {
        try {
            return f() != null ? f() : b.a().b(d.c);
        } catch (Throwable th) {
            return "unknown";
        }
    }

    private static String f() {
        return d.c == null ? null : aj.b(d.c, "UPpref_longtime", "UPadditionalVersionName", null);
    }

    private static long g() {
        return d.c == null ? -1 : aj.b(d.c, "UPpref_longtime", "UPadditionalVersionCode", -1);
    }
}
