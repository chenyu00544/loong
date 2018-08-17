package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.g.b;

final class t implements OnClickListener {
    final /* synthetic */ ao a;

    t(ao aoVar) {
        this.a = aoVar;
    }

    public final void onClick(View view) {
        this.a.j();
        if (b.bm) {
            this.a.d(this.a.a.bp, this.a.a.bq);
        }
    }
}
