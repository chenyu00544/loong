package com.taobao.accs.net;

import anet.channel.session.AccsSession;
import com.taobao.accs.ut.statistics.d;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;

/* compiled from: Taobao */
class l implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ byte[] b;
    final /* synthetic */ AccsSession c;
    final /* synthetic */ j d;

    l(j jVar, int i, byte[] bArr, AccsSession accsSession) {
        this.d = jVar;
        this.a = i;
        this.b = bArr;
        this.c = accsSession;
    }

    public void run() {
        if (this.a == 200) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                this.d.e.a(this.b, this.c.getHost());
                d f = this.d.e.f();
                if (f != null) {
                    f.c = String.valueOf(currentTimeMillis);
                    f.g = this.d.c == 0 ? "service" : "inapp";
                    f.commitUT();
                }
            } catch (Throwable th) {
                ALog.e(this.d.d(), "onDataReceive ", th, new Object[0]);
                th.printStackTrace();
                UTMini.getInstance().commitEvent(66001, "DATA_RECEIVE", UtilityImpl.getStackMsg(th));
            }
            ALog.d(this.d.d(), "try handle msg", new Object[0]);
            return;
        }
        ALog.e(this.d.d(), "drop frame len:" + this.b.length + " frameType" + this.a, new Object[0]);
    }
}
