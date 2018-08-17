package com.unionpay.mobile.android.pro.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.nocard.views.b;
import com.unionpay.mobile.android.upviews.a.a;

final class s implements OnClickListener {
    final /* synthetic */ k a;

    s(k kVar) {
        this.a = kVar;
    }

    public final void onClick(View view) {
        if (!this.a.o()) {
            this.a.y.d();
            if (this.a.L != null && !this.a.L.e()) {
                this.a.a(this.a.L.b());
            } else if (this.a.K != null && !this.a.K.e()) {
                this.a.a(this.a.K.b());
            } else if (this.a.a.p != null) {
                a a = this.a.y.a();
                if (a.a()) {
                    k kVar = this.a;
                    b.a(this.a.d, this.a.q + "_apply");
                    this.a.b.a(c.bD.U);
                    this.a.v = 101;
                    this.a.b(this.a.y.a().b, this.a.w());
                    return;
                }
                this.a.a(a.b);
            }
        }
    }
}
