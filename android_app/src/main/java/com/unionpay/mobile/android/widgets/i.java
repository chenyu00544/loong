package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.view.View.OnClickListener;

final class i implements OnClickListener {
    final /* synthetic */ aj a;

    i(aj ajVar) {
        this.a = ajVar;
    }

    public final void onClick(View view) {
        if (this.a.p != null) {
            this.a.p.dismiss();
        }
    }
}
