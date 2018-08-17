package com.unionpay.c;

import java.util.HashMap;

class av {
    private static volatile av a = null;
    private HashMap b = new HashMap();
    private String c = null;
    private s d = ad.d();
    private boolean e = false;

    static {
        try {
            i.a().a(a());
        } catch (Throwable th) {
        }
    }

    private av() {
    }

    public static av a() {
        if (a == null) {
            synchronized (av.class) {
                if (a == null) {
                    a = new av();
                }
            }
        }
        return a;
    }
}
