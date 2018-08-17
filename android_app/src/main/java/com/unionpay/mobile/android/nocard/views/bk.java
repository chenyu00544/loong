package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.f.c;

final class bk implements OnClickListener {
    final /* synthetic */ o a;

    bk(o oVar) {
        this.a = oVar;
    }

    public final void onClick(View view) {
        o oVar = this.a;
        b.a(this.a.d, "loginpay_support_bank");
        this.a.a(c.bD.k, this.a.a.s);
    }
}
