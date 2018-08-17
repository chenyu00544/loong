package com.unionpay.mobile.android.pro.views;

import android.view.View;
import android.view.View.OnClickListener;

final class n implements OnClickListener {
    final /* synthetic */ a a;

    n(a aVar) {
        this.a = aVar;
    }

    public final void onClick(View view) {
        String str = (String) view.getTag();
        this.a.E = str;
        this.a.D = 5;
        this.a.e(str, "");
    }
}
