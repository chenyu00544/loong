package com.unionpay.mobile.android.pro.a.a;

import com.unionpay.mobile.android.utils.k;
import java.util.ArrayList;

final class i implements Runnable {
    final /* synthetic */ b a;

    i(b bVar) {
        this.a = bVar;
    }

    public final void run() {
        synchronized (this.a) {
            k.c("UPCardEngine", " ic_return : 4");
            ArrayList b = this.a.t.b();
            if (this.a.h != null) {
                this.a.h.sendMessage(this.a.h.obtainMessage(4, b));
            }
        }
    }
}
