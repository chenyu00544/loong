package com.unionpay.mobile.android.pro.views;

import android.view.View;
import android.view.View.OnClickListener;

final class w implements OnClickListener {
    final /* synthetic */ k a;

    w(k kVar) {
        this.a = kVar;
    }

    public final void onClick(View view) {
        String str = (String) view.getTag();
        this.a.P = str;
        this.a.Q = 5;
        this.a.d(str, "");
    }
}
