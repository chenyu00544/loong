package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;

final class au implements OnClickListener {
    final /* synthetic */ g a;

    au(g gVar) {
        this.a = gVar;
    }

    public final void onClick(View view) {
        this.a.v.d();
        g gVar = this.a;
        b.a(this.a.d, this.a.q + "_open_user_protocol");
        this.a.a(this.a.t.d(), this.a.t.c());
    }
}
