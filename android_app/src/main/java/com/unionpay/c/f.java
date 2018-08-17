package com.unionpay.c;

import android.os.Message;

final class f implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ String b;

    f(int i, String str) {
        this.a = i;
        this.b = str;
    }

    public final void run() {
        try {
            if (ax.a) {
                a aVar = new a();
                aVar.a.put("apiType", Integer.valueOf(this.a));
                aVar.a.put("occurTime", String.valueOf(System.currentTimeMillis()));
                aVar.a.put("pageName", this.b == null ? "" : am.a(this.b));
                Message.obtain(w.a(), 102, aVar).sendToTarget();
            }
        } catch (Throwable th) {
        }
    }
}
