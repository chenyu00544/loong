package com.unionpay.mobile.android.pboctransaction.d;

import android.util.Log;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.tsmservice.i.a;

final class h implements a {
    final /* synthetic */ f a;

    h(f fVar) {
        this.a = fVar;
    }

    public final void a() {
        k.c("uppay", "TsmService connected.");
        this.a.f();
    }

    public final void b() {
        Log.e("uppay", "TsmService disconnected.");
        this.a.a(false);
    }
}
