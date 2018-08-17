package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.widgets.ah.a;

final class h implements OnClickListener {
    final /* synthetic */ ah a;

    h(ah ahVar) {
        this.a = ahVar;
    }

    public final void onClick(View view) {
        if (this.a.c != null) {
            this.a.a(this.a.d, this.a.s() + "_change_phoneNO");
            a a = this.a.c;
            ah ahVar = this.a;
            a.e(this.a.p);
        }
    }
}
