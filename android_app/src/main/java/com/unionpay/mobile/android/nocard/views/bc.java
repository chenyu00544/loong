package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;

final class bc implements OnClickListener {
    final /* synthetic */ o a;

    bc(o oVar) {
        this.a = oVar;
    }

    public final void onClick(View view) {
        this.a.j();
        this.a.a.I.f = "cancel";
        this.a.k();
    }
}
