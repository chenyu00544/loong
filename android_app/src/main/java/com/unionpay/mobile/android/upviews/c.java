package com.unionpay.mobile.android.upviews;

import java.util.TimerTask;

final class c extends TimerTask {
    final /* synthetic */ d a;

    c(d dVar) {
        this.a = dVar;
    }

    public final void run() {
        if (!this.a.a.e) {
            this.a.a.b.sendEmptyMessage(3);
        }
        this.a.a.d.cancel();
        this.a.a.d.purge();
    }
}
