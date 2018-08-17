package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;

final class w implements OnClickListener {
    final /* synthetic */ at a;

    w(at atVar) {
        this.a = atVar;
    }

    public final void onClick(View view) {
        String str = (String) view.getTag();
        this.a.E = str;
        this.a.w = 5;
        this.a.d(str, "");
    }
}
