package com.unionpay.mobile.android.upwidget;

import android.view.View;
import android.view.View.OnClickListener;

final class n implements OnClickListener {
    final /* synthetic */ j a;

    n(j jVar) {
        this.a = jVar;
    }

    public final void onClick(View view) {
        this.a.m.setEnabled(false);
        this.a.l.setVisibility(8);
        this.a.k.setVisibility(0);
    }
}
