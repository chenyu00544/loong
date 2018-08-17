package com.unionpay.mobile.android.pboctransaction.c;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.unionpay.mobile.a.a.b.a;
import com.unionpay.mobile.android.utils.k;

final class c implements ServiceConnection {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        k.a("plugin-tsm", "mConnection.onServiceConnected()");
        this.a.e.removeMessages(b.REQUEST_MERGE_PERIOD);
        try {
            this.a.b = a.a(iBinder);
            this.a.b.a(this.a.h);
            this.a.b.a();
        } catch (Exception e) {
            if (this.a.a != null) {
                this.a.a.b();
            }
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        k.a("plugin-tsm", "mConnection.onServiceDisconnected()");
        this.a.e.removeMessages(b.REQUEST_MERGE_PERIOD);
        this.a.b = null;
        if (this.a.a != null) {
            this.a.a.b();
        }
    }
}
