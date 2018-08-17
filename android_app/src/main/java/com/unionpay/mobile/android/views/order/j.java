package com.unionpay.mobile.android.views.order;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.views.order.AbstractMethod.b;

final class j implements OnClickListener {
    final /* synthetic */ i a;

    j(i iVar) {
        this.a = iVar;
    }

    public final void onClick(View view) {
        if (this.a.e != null) {
            i iVar = this.a;
            AbstractMethod.a(this.a.b, "login_forget_pwd");
            b bVar = this.a.e;
            i iVar2 = this.a;
            String a = AbstractMethod.a(this.a.g, "label");
            i iVar3 = this.a;
            bVar.a(a, AbstractMethod.a(this.a.g, "href"));
        }
    }
}
