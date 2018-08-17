package com.alipay.sdk.util;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.alipay.android.app.a.a;

final class e implements ServiceConnection {
    final /* synthetic */ c a;

    e(c cVar) {
        this.a = cVar;
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.a.b = null;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.a.c) {
            this.a.b = a.a(iBinder);
            this.a.c.notify();
        }
    }
}
