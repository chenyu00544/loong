package com.unionpay.tsmservice;

import android.os.Handler.Callback;
import android.os.Message;

final class j implements Callback {
    final /* synthetic */ i a;

    j(i iVar) {
        this.a = iVar;
    }

    public final synchronized boolean handleMessage(Message message) {
        boolean z = true;
        synchronized (this) {
            switch (message.what) {
                case 0:
                    this.a.g();
                    break;
                case 1:
                    this.a.h();
                    break;
                default:
                    z = false;
                    break;
            }
        }
        return z;
    }
}
