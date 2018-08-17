package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.upviews.a.a;

final class u implements OnClickListener {
    final /* synthetic */ at a;

    u(at atVar) {
        this.a = atVar;
    }

    public final void onClick(View view) {
        if (!this.a.o()) {
            String str = "";
            if (this.a.A != null) {
                this.a.A.d();
                a b = this.a.A.b();
                if (b.a()) {
                    str = b.b;
                } else {
                    this.a.a(b.b);
                    return;
                }
            }
            this.a.B.d();
            a b2 = this.a.B.b();
            if (!b2.a()) {
                this.a.a(b2.b);
            } else if (this.a.y != null && !this.a.y.e()) {
                this.a.a(this.a.y.b());
            } else if (this.a.x == null || this.a.x.e()) {
                String str2 = b2.b;
                at atVar = this.a;
                str = b.b(str) ? str2 + "," + str : str2;
                this.a.b.a(c.bD.U);
                at atVar2 = this.a;
                b.a(this.a.d, this.a.q + "_apply");
                if (this.a.a.E == null || this.a.a.E.length() <= 0) {
                    this.a.e(str);
                } else {
                    at.a(this.a, this.a.C, str);
                }
            } else {
                this.a.a(this.a.x.b());
            }
        }
    }
}
