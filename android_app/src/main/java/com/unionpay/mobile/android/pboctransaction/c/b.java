package com.unionpay.mobile.android.pboctransaction.c;

import android.os.Handler.Callback;
import android.os.Message;

final class b implements Callback {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case b.REQUEST_MERGE_PERIOD /*3000*/:
                if (this.a.a != null) {
                    this.a.a.b();
                    break;
                }
                break;
        }
        return false;
    }
}
