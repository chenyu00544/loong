package com.umeng.analytics.pro;

/* compiled from: SafeRunnable */
public abstract class bz implements Runnable {
    public abstract void a();

    public void run() {
        try {
            a();
        } catch (Throwable th) {
            if (th != null) {
                th.printStackTrace();
            }
        }
    }
}
