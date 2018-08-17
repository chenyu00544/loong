package com.unionpay.mobile.android.nocard.views;

import android.os.Handler.Callback;
import android.os.Message;
import com.unionpay.mobile.android.d.a;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.g.b;
import com.unionpay.mobile.android.g.d;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.views.order.l;
import java.util.HashMap;
import java.util.Map;

final class q implements Callback {
    final /* synthetic */ ao a;

    q(ao aoVar) {
        this.a = aoVar;
    }

    public final boolean handleMessage(Message message) {
        k.c("uppay", "mHceHandler. handleMessage");
        this.a.y();
        b.bl = true;
        if (this.a.a.aP == l.e.intValue()) {
            this.a.r.clear();
            this.a.r.remove(new HashMap());
            if (b.bb == null || b.bb.size() <= 0) {
                this.a.n();
                this.a.a.aP = l.a.intValue();
                boolean z = this.a.a.aE;
                ao aoVar = this.a;
                String str = c.bD.bq;
                String str2 = this.a.a.bh;
                int i = a.t;
                i = a.k;
                i = a.s;
                aoVar.a(str, str2, z, true);
            } else {
                int size = b.bb.size();
                for (int i2 = 0; i2 < size; i2++) {
                    d dVar = (d) b.bb.get(i2);
                    ao aoVar2 = this.a;
                    Map a = ao.b(dVar);
                    this.a.r.add(a);
                    if (i2 == 0) {
                        this.a.w.b(dVar.b() + dVar.c() + " " + a.get("text2"));
                    }
                }
                this.a.w.setVisibility(0);
                if (this.a.x != null) {
                    this.a.x.setVisibility(8);
                }
            }
        }
        return true;
    }
}
