package com.unionpay.mobile.android.pro.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.nocard.utils.c;
import com.unionpay.mobile.android.views.order.l;

final class d implements OnClickListener {
    final /* synthetic */ y a;

    d(y yVar) {
        this.a = yVar;
    }

    public final void onClick(View view) {
        if (c.a(this.a.d, this.a.a) == l.c.intValue()) {
            this.a.n();
            this.a.n();
            return;
        }
        this.a.n();
        if (this.a.a.J) {
            this.a.n();
            this.a.a.J = false;
        }
        this.a.a.aP = l.c.intValue();
        this.a.d(2);
    }
}