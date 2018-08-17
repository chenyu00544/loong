package com.unionpay.mobile.android.utils;

final class n extends Thread {
    final /* synthetic */ l a;

    n(l lVar) {
        this.a = lVar;
    }

    public final void run() {
        super.run();
        try {
            synchronized (this) {
                wait(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            k.c("uppay", e.getMessage());
        }
        synchronized (this.a) {
            if (l.b == null || !l.b.isConnected()) {
                k.c("uppay", "se service connection time out");
                if (this.a.e != null) {
                    this.a.e.sendEmptyMessage(2);
                }
            }
        }
    }
}
