package com.unionpay.mobile.android.nocard.views;

import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import com.unionpay.mobile.android.views.order.l;

final class r implements Callback {
    final /* synthetic */ ao a;

    r(ao aoVar) {
        this.a = aoVar;
    }

    public final boolean handleMessage(Message message) {
        if (this.a.a.aP == l.a.intValue() && !this.a.a.J) {
            if (!TextUtils.isEmpty(this.a.a.u)) {
                this.a.a(13, this.a.p);
            } else if (this.a.a.aC) {
                ao.d(this.a);
            }
        }
        return true;
    }
}
