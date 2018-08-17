package com.unionpay.mobile.android.utils;

import android.os.Handler.Callback;
import android.os.Message;
import com.unionpay.mobile.android.nocard.views.l;

final class m implements Callback {
    final /* synthetic */ l a;

    m(l lVar) {
        this.a = lVar;
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                if (this.a.c != null) {
                    ((l) this.a.c).v();
                    break;
                }
                break;
            case 2:
                if (this.a.c != null) {
                    ((l) this.a.c).v();
                    break;
                }
                break;
        }
        return true;
    }
}
