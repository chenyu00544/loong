package com.unionpay.mobile.android.pro.a.a;

import com.unionpay.mobile.android.g.b;
import com.unionpay.mobile.android.utils.k;
import java.util.ArrayList;

final class j implements Runnable {
    final /* synthetic */ b a;

    j(b bVar) {
        this.a = bVar;
    }

    public final void run() {
        synchronized (this.a) {
            k.c("UPCardEngine", " se_return : 8");
            if (b.bm) {
                if (this.a.h != null) {
                    this.a.h.sendMessage(this.a.h.obtainMessage(8, new ArrayList()));
                }
            } else if (this.a.w != null) {
                ArrayList b = this.a.w.b();
                if (!(this.a.a("com.unionpay.tsmservice", 18) || this.a.h == null)) {
                    this.a.h.sendMessage(this.a.h.obtainMessage(8, b));
                }
            }
        }
    }
}
