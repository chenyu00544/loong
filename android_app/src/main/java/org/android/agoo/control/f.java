package org.android.agoo.control;

import com.taobao.accs.utl.ALog;

/* compiled from: Taobao */
class f implements Runnable {
    final /* synthetic */ a a;

    f(a aVar) {
        this.a = aVar;
    }

    public void run() {
        try {
            ALog.d("AgooFactory", "onConnected running tid:" + Thread.currentThread().getId(), new Object[0]);
            this.a.d.doSend(this.a.b);
            ALog.d("AgooFactory", "send finish. close this connection", new Object[0]);
            this.a.d = null;
            AgooFactory.mContext.unbindService(this.a.e);
        } catch (Throwable e) {
            ALog.e("AgooFactory", "send error", e, new Object[0]);
            ALog.d("AgooFactory", "send finish. close this connection", new Object[0]);
            this.a.d = null;
            AgooFactory.mContext.unbindService(this.a.e);
        } catch (Throwable th) {
            ALog.d("AgooFactory", "send finish. close this connection", new Object[0]);
            this.a.d = null;
            AgooFactory.mContext.unbindService(this.a.e);
        }
    }
}
