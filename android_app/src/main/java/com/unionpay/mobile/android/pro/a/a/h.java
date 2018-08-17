package com.unionpay.mobile.android.pro.a.a;

import com.unionpay.mobile.android.utils.k;
import java.util.ArrayList;

final class h implements Runnable {
    final /* synthetic */ b a;

    h(b bVar) {
        this.a = bVar;
    }

    public final void run() {
        synchronized (this.a) {
            k.c("UPCardEngine", " cmcc_return : 2");
            ArrayList b = this.a.q.b();
            if (this.a.h != null) {
                this.a.h.sendMessage(this.a.h.obtainMessage(2, b));
            }
        }
    }
}
