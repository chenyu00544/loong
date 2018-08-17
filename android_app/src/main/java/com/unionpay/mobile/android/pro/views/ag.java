package com.unionpay.mobile.android.pro.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.nocard.views.b;
import com.unionpay.mobile.android.upviews.a.a;

final class ag implements OnClickListener {
    final /* synthetic */ y a;

    ag(y yVar) {
        this.a = yVar;
    }

    public final void onClick(View view) {
        if (!this.a.o()) {
            this.a.A.d();
            a a = this.a.A.a();
            if (!a.a()) {
                this.a.a(a.b);
            } else if (this.a.x != null && !this.a.x.e()) {
                this.a.a(this.a.x.b());
            } else if (this.a.w == null || this.a.w.e()) {
                this.a.b.a(c.bD.U);
                y yVar = this.a;
                b.a(this.a.d, this.a.q + "_apply");
                if (this.a.a.br) {
                    y.a(this.a, this.a.a.bs, this.a.A.a().b, y.j(this.a));
                } else {
                    y.a(this.a, (com.unionpay.mobile.android.g.c) this.a.a.q.get(this.a.a.N), this.a.A.a().b, y.j(this.a));
                }
            } else {
                this.a.a(this.a.w.b());
            }
        }
    }
}
