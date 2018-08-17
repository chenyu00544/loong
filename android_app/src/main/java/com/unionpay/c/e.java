package com.unionpay.c;

import android.os.Message;

final class e implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ boolean b;

    e(String str, boolean z) {
        this.a = str;
        this.b = z;
    }

    public final void run() {
        try {
            y.a("onPause being called! pageName: " + this.a);
            a aVar = new a();
            aVar.a.put("isPageOrSession", Boolean.valueOf(this.b));
            aVar.a.put("apiType", Integer.valueOf(3));
            aVar.a.put("occurTime", String.valueOf(System.currentTimeMillis()));
            aVar.a.put("pageName", this.a);
            Message.obtain(w.a(), 102, aVar).sendToTarget();
        } catch (Throwable th) {
            if (a.a) {
                th.printStackTrace();
            }
        }
    }
}
