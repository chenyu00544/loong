package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;

final class ba implements OnClickListener {
    final /* synthetic */ o a;

    ba(o oVar) {
        this.a = oVar;
    }

    public final void onClick(View view) {
        o oVar = this.a;
        b.a(this.a.d, this.a.q + "_open_user_protocol");
        this.a.a(this.a.C.d(), this.a.C.c());
    }
}
