package com.taobao.accs.internal;

import com.taobao.accs.utl.ALog;

/* compiled from: Taobao */
class e implements Runnable {
    final /* synthetic */ a a;

    e(a aVar) {
        this.a = aVar;
    }

    public void run() {
        ALog.w("ElectionServiceImpl", "time out, onReportComplete", "pkgs", this.a.e);
        this.a.e();
    }
}
