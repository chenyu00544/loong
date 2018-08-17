package com.unionpay.c;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

final class an extends Handler {
    an(Looper looper) {
        super(looper);
    }

    public final void handleMessage(Message message) {
        try {
            if (message.obj != null && (message.obj instanceof ab)) {
                ai.e = (ab) message.obj;
                int i = ai.e.i;
                if (ai.e.f == null) {
                    ai.a();
                    ai.c();
                    return;
                }
                ax.b = false;
                ai.a().a(ai.e.a, ai.e.b, ai.e.c, ai.e.e, ai.e.f, ai.e.g, i, ai.e.h);
            }
        } catch (Throwable th) {
        }
    }
}
