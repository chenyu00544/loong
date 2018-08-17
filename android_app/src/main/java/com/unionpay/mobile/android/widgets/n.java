package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.view.View.OnClickListener;

final class n implements OnClickListener {
    final /* synthetic */ ap a;

    n(ap apVar) {
        this.a = apVar;
    }

    public final void onClick(View view) {
        if (this.a.c != null) {
            this.a.a(this.a.d, this.a.s() + "_click_get_msg");
            this.a.c.a(this.a);
        }
    }
}
