package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.upviews.a.a;

final class bg implements OnClickListener {
    final /* synthetic */ o a;

    bg(o oVar) {
        this.a = oVar;
    }

    public final void onClick(View view) {
        if (!this.a.o()) {
            this.a.t.d();
            a b = this.a.t.b();
            if (!b.a()) {
                this.a.a(b.b);
            } else if (this.a.C == null || this.a.C.e()) {
                String str = "";
                if (this.a.D != null) {
                    a b2 = this.a.D.b();
                    if (b2.a()) {
                        str = b2.b;
                    } else {
                        this.a.a(b2.b);
                        return;
                    }
                }
                String str2 = b.b;
                o oVar = this.a;
                str = b.b(str) ? str2 + "," + str : str2;
                this.a.b.a(c.bD.U);
                o oVar2 = this.a;
                b.a(this.a.d, this.a.q + "_apply");
                o.b(this.a, str);
            } else {
                this.a.a(this.a.C.b());
            }
        }
    }
}
