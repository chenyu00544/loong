package com.unionpay.c;

class at {
    private static volatile at a = null;
    private s b = ad.d();

    static {
        try {
            i.a().a(a());
        } catch (Throwable th) {
        }
    }

    private at() {
    }

    public static at a() {
        if (a == null) {
            synchronized (at.class) {
                if (a == null) {
                    a = new at();
                }
            }
        }
        return a;
    }
}
