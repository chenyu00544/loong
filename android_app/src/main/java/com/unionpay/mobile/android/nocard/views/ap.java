package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;

final class ap implements OnClickListener {
    final /* synthetic */ boolean a;
    final /* synthetic */ b b;

    ap(b bVar, boolean z) {
        this.b = bVar;
        this.a = z;
    }

    public final void onClick(View view) {
        this.b.j();
        if (this.a) {
            this.b.k();
        }
    }
}
