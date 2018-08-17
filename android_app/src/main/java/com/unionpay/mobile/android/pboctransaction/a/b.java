package com.unionpay.mobile.android.pboctransaction.a;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import cn.a.a.a.a.a.a.a.a.a.a;

final class b implements ServiceConnection {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            this.a.c = a.a(iBinder);
            if (this.a.d != null) {
                this.a.d.a();
            }
        } catch (Exception e) {
            if (this.a.d != null) {
                this.a.d.b();
            }
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.a.c = null;
    }
}
