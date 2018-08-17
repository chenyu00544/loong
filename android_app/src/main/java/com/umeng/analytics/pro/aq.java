package com.umeng.analytics.pro;

import android.content.Context;

/* compiled from: CacheService */
public final class aq implements at {
    private static aq c;
    private at a = new ap(this.b);
    private Context b;

    /* compiled from: CacheService */
    class aq_2 extends bz {
        final /* synthetic */ aq a;

        aq_2(aq aqVar) {
            this.a = aqVar;
        }

        public void a() {
            this.a.a.a();
        }
    }

    /* compiled from: CacheService */
    class aq_3 extends bz {
        final /* synthetic */ aq a;

        aq_3(aq aqVar) {
            this.a = aqVar;
        }

        public void a() {
            this.a.a.b();
        }
    }

    private aq(Context context) {
        this.b = context;
    }

    public synchronized ap a(Context context) {
        return (ap) this.a;
    }

    public static synchronized aq b(Context context) {
        aq aqVar;
        synchronized (aq.class) {
            if (c == null && context != null) {
                c = new aq(context);
            }
            aqVar = c;
        }
        return aqVar;
    }

    public void a(at atVar) {
        this.a = atVar;
    }

    public void a(final Object obj) {
        bx.b(new bz(this) {
            final /* synthetic */ aq b;

            public void a() {
                this.b.a.a(obj);
            }
        });
    }

    public void a() {
        bx.b(new aq_2(this));
    }

    public void b() {
        bx.c(new aq_3(this));
    }
}
