package com.taobao.accs.internal;

import android.content.Context;
import android.content.Intent;
import com.taobao.accs.a.a;
import com.taobao.accs.utl.ALog;

/* compiled from: Taobao */
class d implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ a b;

    d(a aVar, Context context) {
        this.b = aVar;
        this.a = context;
    }

    public void run() {
        try {
            if (!this.b.d) {
                a.b = true;
                ALog.e("ElectionServiceImpl", "wait app election time out", new Object[0]);
                Intent intent = new Intent();
                intent.setClassName(this.a.getPackageName(), com.taobao.accs.utl.a.channelService);
                this.a.startService(intent);
            }
        } catch (Throwable th) {
            ALog.e("ElectionServiceImpl", "mSelectAppTimeOutTask", th, new Object[0]);
        }
    }
}
