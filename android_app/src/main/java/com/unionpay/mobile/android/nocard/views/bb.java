package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.f.c;

final class bb implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ o b;

    bb(o oVar, String str) {
        this.b = oVar;
        this.a = str;
    }

    public final void onClick(View view) {
        this.b.a(c.bD.s, this.a);
    }
}
