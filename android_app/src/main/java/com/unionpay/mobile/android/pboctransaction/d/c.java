package com.unionpay.mobile.android.pboctransaction.d;

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
                this.a.a(false);
                break;
        }
        return true;
    }
}
