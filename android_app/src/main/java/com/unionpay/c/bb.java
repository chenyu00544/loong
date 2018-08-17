package com.unionpay.c;

import android.content.Context;
import java.util.Map;

public final class bb implements aw {
    private static volatile bb a = null;

    public bb() {
        a = this;
    }

    static synchronized bb a() {
        bb bbVar;
        synchronized (bb.class) {
            if (a == null) {
                synchronized (bb.class) {
                    if (a == null) {
                        a = new bb();
                    }
                }
            }
            bbVar = a;
        }
        return bbVar;
    }

    public final void a(Context context, String str) {
        try {
            ax.a(context, str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void a(Context context, String str, String str2) {
        try {
            ax.a(context, str, str2);
            au.a();
            am.a(new u(this));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void a(Context context, String str, String str2, Map map) {
        try {
            ax.a(context, str, str2, map);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void b(Context context, String str) {
        try {
            ax.b(context, str);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
