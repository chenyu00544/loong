package com.unionpay.mobile.android.widgets;

import android.os.Message;

final class q extends Thread {
    final /* synthetic */ int a;
    final /* synthetic */ ap b;

    q(ap apVar, int i) {
        this.b = apVar;
        this.a = i;
    }

    public final void run() {
        if (this.b.p != null) {
            long currentTimeMillis = System.currentTimeMillis() + ((long) (this.a * 1000));
            while (true) {
                long currentTimeMillis2 = System.currentTimeMillis();
                if (currentTimeMillis2 > currentTimeMillis) {
                    break;
                }
                int i = (int) (((currentTimeMillis - currentTimeMillis2) + ((long) this.a)) / 1000);
                if (i <= 0) {
                    break;
                }
                Message obtain = Message.obtain();
                obtain.what = 0;
                obtain.arg1 = i;
                this.b.p.sendMessage(obtain);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    this.b.p.sendEmptyMessage(1);
                    return;
                }
            }
            this.b.p.sendEmptyMessage(1);
        }
    }
}
