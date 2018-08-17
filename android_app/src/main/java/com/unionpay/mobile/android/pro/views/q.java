package com.unionpay.mobile.android.pro.views;

import android.os.Handler;
import java.util.HashMap;

final class q implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ HashMap b;
    final /* synthetic */ a c;

    q(a aVar, String str, HashMap hashMap) {
        this.c = aVar;
        this.a = str;
        this.b = hashMap;
    }

    public final void run() {
        a aVar = this.c;
        String str = this.a;
        HashMap hashMap = this.c.a.p;
        Object a = aVar.a(str, this.b);
        Handler x = this.c.z;
        Handler x2 = this.c.z;
        if (a == null) {
            a = null;
        }
        x.sendMessage(x2.obtainMessage(0, a));
    }
}
