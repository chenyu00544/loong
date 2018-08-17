package com.unionpay.tsmservice;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.unionpay.tsmservice.f.a;

final class k implements ServiceConnection {
    final /* synthetic */ i a;

    k(i iVar) {
        this.a = iVar;
    }

    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.a.f = true;
        this.a.e = a.a(iBinder);
        this.a.U.sendEmptyMessage(0);
    }

    public final synchronized void onServiceDisconnected(ComponentName componentName) {
        this.a.f = false;
        this.a.e = null;
        this.a.U.sendEmptyMessage(1);
    }
}
