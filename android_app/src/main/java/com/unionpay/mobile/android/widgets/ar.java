package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.view.View.OnClickListener;

final class ar implements OnClickListener {
    final /* synthetic */ p a;

    ar(p pVar) {
        this.a = pVar;
    }

    public final void onClick(View view) {
        if (this.a.q != null) {
            this.a.q.dismiss();
        }
    }
}
