package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;

final class bh implements OnClickListener {
    final /* synthetic */ o a;

    bh(o oVar) {
        this.a = oVar;
    }

    public final void onClick(View view) {
        o oVar = this.a;
        b.a(this.a.d, "loginpay_input_cardNO_next");
        o.e(this.a);
    }
}
