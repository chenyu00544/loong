package com.unionpay.mobile.android.pboctransaction.d;

import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.unionpay.mobile.android.g.a;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.tsmservice.a.i;
import com.unionpay.tsmservice.a.m;
import java.util.ArrayList;

final class g implements Callback {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.a.G.removeMessages(4);
                k.c("uppay", "msg error");
                f.a(this.a, message.arg1, (String) message.obj);
                break;
            case 3:
                k.c("uppay-spay", "send apdu time out");
                this.a.u = true;
                break;
            case 4:
                k.c("uppay", "timeout");
                f.a(this.a, message.arg1, "");
                break;
            case 1000:
                this.a.G.removeMessages(4);
                k.c("uppay", "init success");
                this.a.a(true);
                this.a.h = true;
                break;
            case 1011:
                k.c("uppay", "channel success");
                Bundle bundle = (Bundle) message.obj;
                this.a.s = bundle.getString("channel");
                this.a.t = bundle.getString("apdu");
                break;
            case 1012:
                k.c("uppay", "apdu success version 3.3.1");
                this.a.G.removeMessages(3);
                this.a.v = (String) message.obj;
                break;
            case 1013:
                k.c("uppay", "close channel success");
                this.a.x = "success";
                break;
            case 1014:
                this.a.G.removeMessages(4);
                k.c("uppay", "list success");
                if (this.a.o != null) {
                    Object obj;
                    ArrayList arrayList = new ArrayList();
                    i[] a = ((com.unionpay.tsmservice.c.k) message.obj).a();
                    if (a == null || a.length <= 0) {
                        ArrayList arrayList2 = arrayList;
                    } else {
                        ArrayList arrayList3 = new ArrayList();
                        int i = 0;
                        while (i < a.length) {
                            if (!(a[i] == null || a[i].a() == null || a[i].a().a() == null)) {
                                boolean z;
                                f fVar = this.a;
                                String a2 = a[i].a().a().a();
                                if (a2 != null && a2.length() > 16) {
                                    if (!"06".equalsIgnoreCase(a2.substring(14, 16))) {
                                        z = false;
                                        if (!z) {
                                            arrayList3.add(new a(1, a[i].a().a().a(), "", a[i].a().b(), 1));
                                        }
                                    }
                                }
                                z = true;
                                if (!z) {
                                    arrayList3.add(new a(1, a[i].a().a().a(), "", a[i].a().b(), 1));
                                }
                            }
                            i++;
                        }
                        obj = arrayList3;
                    }
                    this.a.o.sendMessage(this.a.G.obtainMessage(8, obj));
                    break;
                }
                break;
            case 1015:
                this.a.G.removeMessages(4);
                k.c("uppay-spay", "get spay list call back");
                m mVar = (m) message.obj;
                this.a.o.sendMessage(this.a.o.obtainMessage(m_AppUI.MSG_APP_DATA_OK, new a(32, mVar.a().a(), "", mVar.b(), 1)));
                break;
            case 1016:
                this.a.G.removeMessages(4);
                k.c("uppay-spay", "check spay support");
                this.a.m.a(true);
                break;
            case 1018:
                this.a.G.removeMessages(4);
                this.a.E = ((Bundle) message.obj).getBoolean("KEY_SUCCESS_VENDOR");
                k.c("uppay-spay", "mIsVendorStateReady: " + this.a.E);
                k.c("uppay-spay", "get vendor pay status");
                this.a.m.a(true);
                break;
        }
        return false;
    }
}
