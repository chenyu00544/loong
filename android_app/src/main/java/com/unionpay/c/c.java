package com.unionpay.c;

import android.app.Activity;
import android.os.Message;
import com.umeng.analytics.pro.x;

final class c implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ boolean b;
    final /* synthetic */ Activity c;

    c(String str, boolean z, Activity activity) {
        this.a = str;
        this.b = z;
        this.c = activity;
    }

    public final void run() {
        try {
            y.a("onResume being called! pageName: " + this.a + ", FromAPI: " + String.valueOf(this.b));
            a aVar = new a();
            aVar.a.put(x.aI, this.c);
            aVar.a.put("isPageOrSession", Boolean.valueOf(this.b));
            aVar.a.put("apiType", Integer.valueOf(2));
            aVar.a.put("occurTime", Long.valueOf(System.currentTimeMillis()));
            aVar.a.put("pageName", this.a);
            Message.obtain(w.a(), 102, aVar).sendToTarget();
        } catch (Throwable th) {
            if (a.a) {
                th.printStackTrace();
            }
        }
    }
}
