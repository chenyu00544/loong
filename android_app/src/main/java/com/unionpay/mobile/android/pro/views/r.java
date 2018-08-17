package com.unionpay.mobile.android.pro.views;

import com.unionpay.mobile.android.g.c;
import com.unionpay.mobile.android.pro.a.a.a;
import com.unionpay.mobile.android.utils.k;
import java.util.ArrayList;

final class r implements a {
    final /* synthetic */ h a;

    r(h hVar) {
        this.a = hVar;
    }

    public final void a(ArrayList<c> arrayList) {
        k.a("uppay", "deviceReady +++");
        if (arrayList != null && arrayList.size() > 0) {
            if (this.a.s == null) {
                this.a.s = new ArrayList(arrayList.size());
            }
            this.a.s.addAll(arrayList);
        }
        this.a.A();
        k.a("uppay", "deviceReady ---");
    }
}
