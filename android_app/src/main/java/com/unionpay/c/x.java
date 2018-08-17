package com.unionpay.c;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class x extends Handler {
    x(Looper looper) {
        super(looper);
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 102:
                if (message.obj != null && (message.obj instanceof a)) {
                    Object obj = (a) message.obj;
                    ad.d().a();
                    try {
                        i.a().b(obj);
                    } catch (Throwable th) {
                    }
                    ad.d().b();
                    return;
                }
                return;
            case 103:
                if (message.obj != null && (message.obj instanceof ab)) {
                    try {
                        i.a().b((ab) message.obj);
                        return;
                    } catch (Throwable th2) {
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }
}
