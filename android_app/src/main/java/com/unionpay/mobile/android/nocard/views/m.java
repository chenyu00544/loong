package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.upviews.a.a;

final class m implements OnClickListener {
    final /* synthetic */ ak a;

    m(ak akVar) {
        this.a = akVar;
    }

    public final void onClick(View view) {
        if (!this.a.o()) {
            this.a.v.d();
            a b = this.a.v.b();
            if (!b.a()) {
                this.a.a(b.b);
            } else if (this.a.t == null || this.a.t.e()) {
                String str = b.b;
                this.a.b.a(c.bD.U);
                this.a.e.l(str);
                this.a.r = 102;
            } else {
                this.a.a(this.a.t.b());
            }
        }
    }
}
