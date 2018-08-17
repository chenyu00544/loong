package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;

final class ax implements OnClickListener {
    final /* synthetic */ String a;
    final /* synthetic */ l b;

    ax(l lVar, String str) {
        this.b = lVar;
        this.a = str;
    }

    public final void onClick(View view) {
        this.b.c(this.a);
    }
}
