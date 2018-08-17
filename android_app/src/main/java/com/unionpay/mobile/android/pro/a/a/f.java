package com.unionpay.mobile.android.pro.a.a;

import com.unionpay.mobile.android.g.b;
import com.unionpay.mobile.android.pboctransaction.c;
import com.unionpay.mobile.android.utils.k;

final class f implements c {
    final /* synthetic */ b a;

    f(b bVar) {
        this.a = bVar;
    }

    public final void a() {
        k.c("uppay-spay", "tsmservice  init success");
        b.bn = false;
        if (this.a.y == null) {
            this.a.b(8);
        } else if (!this.a.y.e()) {
            b.aB = false;
            this.a.b(8);
        }
    }

    public final void b() {
        k.c("UPCardEngine", "mSE init failed");
        k.c("uppay-spay", "tsmservice  init fail");
        if (this.a.h != null) {
            this.a.h.sendMessage(this.a.h.obtainMessage(8, null));
        }
    }
}
