package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.f.c;

final class j implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ af b;

    j(af afVar, String str) {
        this.b = afVar;
        this.a = str;
    }

    public final void onClick(View view) {
        this.b.a(c.bD.s, this.a);
    }
}
