package com.unionpay.mobile.android.pboctransaction.d;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.unionpay.b.a.a.a;
import com.unionpay.mobile.android.utils.k;

final class d implements ServiceConnection {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        k.a("plugin-clientV3", "startSamsungService onServiceConnected");
        try {
            this.a.c = a.a(iBinder);
            this.a.f.removeMessages(1);
            this.a.a(true);
        } catch (Exception e) {
            this.a.a(false);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        k.a("plugin-clientV3", "startSamsungService onServiceDisconnected");
        this.a.c = null;
        this.a.f.removeMessages(1);
        this.a.a(false);
    }
}
