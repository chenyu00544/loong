package com.unionpay.mobile.android.pboctransaction.simapdu;

import android.os.Handler.Callback;
import android.os.Message;

final class c implements Callback {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                if (this.a.c != null) {
                    this.a.c.a();
                    this.a.c = null;
                    break;
                }
                break;
            case 2:
                if (this.a.c != null) {
                    this.a.c.b();
                    this.a.c = null;
                    break;
                }
                break;
        }
        return true;
    }
}
