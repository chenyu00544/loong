package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;

final class e implements OnClickListener {
    final /* synthetic */ b a;

    e(b bVar) {
        this.a = bVar;
    }

    public final void onClick(View view) {
        if (this.a.b != null) {
            this.a.b.dismiss();
        }
    }
}
