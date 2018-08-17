package com.unionpay.mobile.android.views.order;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.views.order.AbstractMethod.b;

final class k implements OnClickListener {
    final /* synthetic */ i a;

    k(i iVar) {
        this.a = iVar;
    }

    public final void onClick(View view) {
        if (this.a.e != null) {
            i iVar = this.a;
            AbstractMethod.a(this.a.b, "login_by_register");
            b bVar = this.a.e;
            i iVar2 = this.a;
            String a = AbstractMethod.a(this.a.h, "label");
            i iVar3 = this.a;
            bVar.a(a, AbstractMethod.a(this.a.h, "href"));
        }
    }
}
