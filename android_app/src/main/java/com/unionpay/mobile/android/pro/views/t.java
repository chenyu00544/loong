package com.unionpay.mobile.android.pro.views;

import android.view.View;
import android.view.View.OnClickListener;

final class t implements OnClickListener {
    final /* synthetic */ boolean a;
    final /* synthetic */ k b;

    t(k kVar, boolean z) {
        this.b = kVar;
        this.a = z;
    }

    public final void onClick(View view) {
        this.b.j();
        if (this.a) {
            this.b.k();
            return;
        }
        this.b.M = true;
        this.b.S.removeAllViews();
        this.b.A.removeAllViews();
        this.b.F.b();
        this.b.F.a(0);
        this.b.c();
        this.b.a(this.b.R);
    }
}
