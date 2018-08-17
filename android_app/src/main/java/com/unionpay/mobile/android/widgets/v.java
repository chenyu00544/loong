package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.view.View.OnClickListener;

final class v implements OnClickListener {
    final /* synthetic */ a a;

    v(a aVar) {
        this.a = aVar;
    }

    public final void onClick(View view) {
        if (this.a.r != null) {
            this.a.r.dismiss();
        }
    }
}
