package com.unionpay.mobile.android.pro.views;

import android.view.View;
import android.view.View.OnClickListener;

final class ad implements OnClickListener {
    final /* synthetic */ k a;

    ad(k kVar) {
        this.a = kVar;
    }

    public final void onClick(View view) {
        this.a.j();
        this.a.a.I.f = "cancel";
        this.a.k();
    }
}
