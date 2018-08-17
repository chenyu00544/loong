package com.taobao.accs.internal;

import com.taobao.accs.utl.ALog;

/* compiled from: Taobao */
class f implements Runnable {
    final /* synthetic */ a a;

    f(a aVar) {
        this.a = aVar;
    }

    public void run() {
        ALog.e("ElectionServiceImpl", "serverElection time out", new Object[0]);
        this.a.a(null, -9);
    }
}
