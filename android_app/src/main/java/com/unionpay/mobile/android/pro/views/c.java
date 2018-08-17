package com.unionpay.mobile.android.pro.views;

import android.view.View;
import android.view.View.OnClickListener;

final class c implements OnClickListener {
    final /* synthetic */ y a;

    c(y yVar) {
        this.a = yVar;
    }

    public final void onClick(View view) {
        String str = (String) view.getTag();
        this.a.B = str;
        this.a.v = 5;
        this.a.d(str, "");
    }
}
