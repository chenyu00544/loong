package com.taobao.accs.internal;

import com.taobao.accs.utl.ALog;

/* compiled from: Taobao */
class c implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ a b;

    c(a aVar, String str) {
        this.b = aVar;
        this.a = str;
    }

    public void run() {
        ALog.i("ElectionServiceImpl", "tryElection", "isPing", Boolean.valueOf(a.h));
        if (a.h) {
            ALog.i("ElectionServiceImpl", "no need election, stop self", new Object[0]);
            this.b.a(true);
            return;
        }
        ALog.e("ElectionServiceImpl", "tryElection curr host unreceive ping, try selectAppToElection", "curr host", this.a);
        this.b.b(this.b.b, "host invaid");
    }
}
