package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.nocard.views.bd.a;
import com.unionpay.mobile.android.utils.p;

final class ag implements OnClickListener {
    final /* synthetic */ int a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ a d;

    ag(a aVar, int i, String str, String str2) {
        this.d = aVar;
        this.a = i;
        this.b = str;
        this.c = str2;
    }

    public final void onClick(View view) {
        bd bdVar = this.d.a;
        b.a(this.d.a.d, "pay_success_click_activity", p.i, new Object[]{Integer.valueOf(this.a), this.b});
        this.d.a.a("", this.c);
    }
}
