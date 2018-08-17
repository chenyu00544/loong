package com.unionpay.mobile.android.views.order;

import android.view.View;
import android.view.View.OnClickListener;

final class a implements OnClickListener {
    final /* synthetic */ AbstractMethod a;

    a(AbstractMethod abstractMethod) {
        this.a = abstractMethod;
    }

    public final void onClick(View view) {
        if (this.a.f != null) {
            this.a.f.a(this.a.b(), this.a.c() == null, this.a.d(), this.a.c());
        }
    }
}
