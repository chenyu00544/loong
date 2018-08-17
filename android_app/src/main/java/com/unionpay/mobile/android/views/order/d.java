package com.unionpay.mobile.android.views.order;

import android.view.View;
import android.view.View.OnClickListener;

final class d implements OnClickListener {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    public final void onClick(View view) {
        if (this.a.m != null) {
            this.a.m.dismiss();
        }
    }
}
