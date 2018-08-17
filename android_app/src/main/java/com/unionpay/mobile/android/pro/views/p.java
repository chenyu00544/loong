package com.unionpay.mobile.android.pro.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.nocard.views.b;
import com.unionpay.mobile.android.upviews.a.a;

final class p implements OnClickListener {
    final /* synthetic */ a a;

    p(a aVar) {
        this.a = aVar;
    }

    public final void onClick(View view) {
        if (!this.a.o()) {
            this.a.x.d();
            if (this.a.F) {
                a.p(this.a);
            } else if (this.a.a.p != null) {
                a a = this.a.x.a();
                if (a.a()) {
                    a aVar = this.a;
                    b.a(this.a.d, this.a.q + "_apply");
                    this.a.b.a(c.bD.U);
                    this.a.u = 101;
                    this.a.a(this.a.a.p);
                    return;
                }
                this.a.a(a.b);
            }
        }
    }
}
