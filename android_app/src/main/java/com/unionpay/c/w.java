package com.unionpay.c;

import android.os.Handler;
import android.os.HandlerThread;

final class w {
    private static Handler a;
    private static final HandlerThread b;

    static {
        a = null;
        HandlerThread handlerThread = new HandlerThread("ProcessingThread");
        b = handlerThread;
        handlerThread.start();
        a = new x(b.getLooper());
    }

    static final Handler a() {
        return a;
    }
}
