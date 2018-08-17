package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.view.View.OnClickListener;

final class ai implements OnClickListener {
    final /* synthetic */ g a;

    ai(g gVar) {
        this.a = gVar;
    }

    public final void onClick(View view) {
        if (this.a.r != null) {
            this.a.r.dismiss();
        }
    }
}
