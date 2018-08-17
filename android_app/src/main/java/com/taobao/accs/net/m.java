package com.taobao.accs.net;

import com.baidu.mapapi.UIMsg.m_AppUI;
import com.taobao.accs.data.Message;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.b;

/* compiled from: Taobao */
class m implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ boolean b;
    final /* synthetic */ int c;
    final /* synthetic */ j d;

    m(j jVar, int i, boolean z, int i2) {
        this.d = jVar;
        this.a = i;
        this.b = z;
        this.c = i2;
    }

    public void run() {
        if (this.a > 0) {
            Message b = this.d.e.b(UtilityImpl.int2String(this.a));
            if (b != null) {
                if (this.b) {
                    if (!this.d.a(b, (int) m_AppUI.MSG_APP_DATA_OK)) {
                        this.d.e.a(b, this.c);
                    }
                    if (b.getNetPermanceMonitor() != null) {
                        b.a("accs", BaseMonitor.COUNT_POINT_RESEND, "total_tnet", 0.0d);
                    }
                } else {
                    this.d.e.a(b, this.c);
                }
            }
        }
        if (this.a < 0 && this.b) {
            this.d.b(this.a);
        }
    }
}
