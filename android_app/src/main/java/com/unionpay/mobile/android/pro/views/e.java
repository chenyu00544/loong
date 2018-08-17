package com.unionpay.mobile.android.pro.views;

import android.os.Handler;
import com.unionpay.mobile.android.g.c;
import com.unionpay.mobile.android.pro.a.a.b;
import java.util.HashMap;

final class e implements Runnable {
    final /* synthetic */ b a;
    final /* synthetic */ c b;
    final /* synthetic */ String c;
    final /* synthetic */ HashMap d;
    final /* synthetic */ y e;

    e(y yVar, b bVar, c cVar, String str, HashMap hashMap) {
        this.e = yVar;
        this.a = bVar;
        this.b = cVar;
        this.c = str;
        this.d = hashMap;
    }

    public final void run() {
        Object a = this.a.a(this.b, this.c, this.e.a.p, this.d, this.e.a.m);
        Handler w = this.e.D;
        Handler w2 = this.e.D;
        if (a == null) {
            a = null;
        }
        w.sendMessage(w2.obtainMessage(0, a));
    }
}
