package com.unionpay.c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class p extends BroadcastReceiver {
    final /* synthetic */ Object a;
    final /* synthetic */ Context b;

    p(Object obj, Context context) {
        this.a = obj;
        this.b = context;
    }

    public final void onReceive(Context context, Intent intent) {
        try {
            synchronized (this.a) {
                try {
                    this.a.notifyAll();
                    this.b.unregisterReceiver(this);
                } catch (Throwable th) {
                    this.b.unregisterReceiver(this);
                }
            }
        } catch (Throwable th2) {
        }
    }
}
