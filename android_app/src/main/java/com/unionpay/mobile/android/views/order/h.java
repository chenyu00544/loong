package com.unionpay.mobile.android.views.order;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.utils.k;

final class h implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ CViewMethods b;

    h(CViewMethods cViewMethods, int i) {
        this.b = cViewMethods;
        this.a = i;
    }

    public final void onClick(View view) {
        k.c("uppay", " touched " + this.a);
        if (this.b.j != null) {
            this.b.j.c(this.a);
        }
    }
}
