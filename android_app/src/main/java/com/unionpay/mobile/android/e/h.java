package com.unionpay.mobile.android.e;

import com.baidu.mapapi.UIMsg.m_AppUI;

final class h extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ f c;

    h(f fVar, String str, String str2) {
        this.c = fVar;
        this.a = str;
        this.b = str2;
    }

    public final void run() {
        if (this.c.a(this.a, this.b)) {
            if (this.c.y != null) {
                this.c.y.sendMessageDelayed(this.c.y.obtainMessage(m_AppUI.MSG_APP_VERSION_COMMEND, this.a), (long) this.c.i);
            }
        } else if (this.c.y != null) {
            this.c.y.sendMessage(this.c.y.obtainMessage(m_AppUI.MSG_APP_VERSION_FORCE, this.a));
        }
    }
}
