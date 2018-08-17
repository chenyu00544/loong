package org.android.agoo.common;

import java.util.concurrent.ThreadFactory;

/* compiled from: Taobao */
final class f implements ThreadFactory {
    f() {
    }

    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, "AGOO");
    }
}
