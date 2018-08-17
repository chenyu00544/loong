package com.unionpay.mobile.android.pro.views;

import android.os.Handler;
import java.util.HashMap;

final class ac implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ HashMap b;
    final /* synthetic */ k c;

    ac(k kVar, String str, HashMap hashMap) {
        this.c = kVar;
        this.a = str;
        this.b = hashMap;
    }

    public final void run() {
        k kVar = this.c;
        String str = this.a;
        HashMap hashMap = this.c.a.p;
        Object a = kVar.a(str, this.b);
        Handler w = this.c.z;
        Handler w2 = this.c.z;
        if (a == null) {
            a = null;
        }
        w.sendMessage(w2.obtainMessage(0, a));
    }
}
