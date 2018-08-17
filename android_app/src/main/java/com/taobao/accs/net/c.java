package com.taobao.accs.net;

import com.taobao.accs.data.Message;
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
        Message b = this.b.e.b(this.a);
        if (b != null) {
            this.b.e.a(b, -9);
            this.b.a(this.a, "receive data time out");
            ALog.e(this.b.d(), this.b.c + "receive data time out! ", new Object[0]);
        }
    }
}
