package org.android.agoo.common;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/* compiled from: Taobao */
public final class e {
    public static final String TAG = "ThreadUtil";

    /* compiled from: Taobao */
    private static class a {
        private static final ScheduledThreadPoolExecutor a = new ScheduledThreadPoolExecutor(1, new f());

        private a() {
        }
    }

    private e() {
        if (a.a != null) {
            throw new IllegalStateException("Already instantiated");
        }
    }

    public static ScheduledThreadPoolExecutor a() {
        return a.a;
    }
}
