package com.unionpay.mobile.android.e;

import android.os.Handler;
import com.baidu.mapapi.UIMsg.f_FUN;

final class j implements Runnable {
    final /* synthetic */ f a;

    j(f fVar) {
        this.a = fVar;
    }

    public final void run() {
        Object a = this.a.a(this.a.l, this.a.f, this.a.h);
        f.k(this.a);
        if (this.a.y != null) {
            Handler d = this.a.y;
            Handler d2 = this.a.y;
            if (a == null) {
                a = null;
            }
            d.sendMessage(d2.obtainMessage(f_FUN.FUN_ID_VOICE_SCH, a));
        }
    }
}
