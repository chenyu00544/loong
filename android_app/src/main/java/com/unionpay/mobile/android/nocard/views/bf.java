package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;

final class bf implements OnClickListener {
    final /* synthetic */ o a;

    bf(o oVar) {
        this.a = oVar;
    }

    public final void onClick(View view) {
        String str = (String) view.getTag();
        this.a.F = str;
        this.a.B = 5;
        this.a.d(str, "");
    }
}
