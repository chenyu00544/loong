package com.taobao.accs.net;

import android.text.TextUtils;
import com.taobao.accs.utl.ALog;

/* compiled from: Taobao */
class n implements Runnable {
    final /* synthetic */ j a;

    n(j jVar) {
        this.a = jVar;
    }

    public void run() {
        try {
            if (this.a.d != null && !TextUtils.isEmpty(this.a.i())) {
                ALog.i(this.a.d(), "mTryStartServiceRunable bindapp", new Object[0]);
                this.a.b(this.a.d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
