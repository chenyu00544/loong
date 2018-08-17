package com.unionpay.mobile.android.pro.a.a;

import com.unionpay.mobile.android.utils.k;
import java.util.ArrayList;

final class g implements Runnable {
    final /* synthetic */ b a;

    g(b bVar) {
        this.a = bVar;
    }

    public final void run() {
        k.c("UPCardEngine", " sd_return : 1");
        ArrayList b = this.a.n.b();
        if (this.a.h != null) {
            this.a.h.sendMessage(this.a.h.obtainMessage(1, b));
        }
    }
}
