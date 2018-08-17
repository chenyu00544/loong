package com.unionpay.mobile.android.pro.a.a;

import com.unionpay.mobile.android.utils.k;

final class c implements com.unionpay.mobile.android.pboctransaction.c {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public final void a() {
        this.a.b(1);
    }

    public final void b() {
        k.c("UPCardEngine", "mSDInitCallback.initFailed!!!!");
        this.a.h.sendMessage(this.a.h.obtainMessage(1, null));
    }
}
