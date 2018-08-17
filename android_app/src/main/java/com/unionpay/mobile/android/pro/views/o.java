package com.unionpay.mobile.android.pro.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.nocard.utils.c;
import com.unionpay.mobile.android.views.order.l;

final class o implements OnClickListener {
    final /* synthetic */ a a;

    o(a aVar) {
        this.a = aVar;
    }

    public final void onClick(View view) {
        int a = c.a(this.a.d, this.a.a);
        this.a.n();
        this.a.n();
        if (a != l.c.intValue()) {
            this.a.a.aP = l.c.intValue();
            this.a.d(2);
        }
    }
}
