package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.tauth.AuthActivity;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.upviews.a.a;
import com.unionpay.mobile.android.utils.j;

final class as implements OnClickListener {
    final /* synthetic */ g a;

    as(g gVar) {
        this.a = gVar;
    }

    public final void onClick(View view) {
        if (!this.a.o()) {
            this.a.v.d();
            a b = this.a.v.b();
            if (!b.a()) {
                this.a.a(b.b);
            } else if (this.a.t == null || this.a.t.e()) {
                this.a.b.a(c.bD.U);
                g gVar = this.a;
                b.a(this.a.d, this.a.q + "_open_apply");
                if (this.a.w) {
                    this.a.v();
                    this.a.s = 104;
                    return;
                }
                this.a.e.c(j.a(this.a.a.C, AuthActivity.ACTION_KEY), b.b);
                this.a.s = 102;
            } else {
                this.a.a(this.a.t.b());
            }
        }
    }
}
