package com.unionpay.mobile.android.views.order;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.views.order.AbstractMethod.b;

final class f implements OnClickListener {
    final /* synthetic */ b a;

    f(b bVar) {
        this.a = bVar;
    }

    public final void onClick(View view) {
        if (this.a.e != null) {
            b bVar = this.a;
            AbstractMethod.a(this.a.b, "bankpay_support_bank");
            b bVar2 = this.a.e;
            b bVar3 = this.a;
            String a = AbstractMethod.a(this.a.g, "title");
            b bVar4 = this.a;
            bVar2.a(a, AbstractMethod.a(this.a.g, "href"));
        }
    }
}
