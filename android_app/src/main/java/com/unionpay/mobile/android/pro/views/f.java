package com.unionpay.mobile.android.pro.views;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import com.unionpay.mobile.android.nocard.views.b;

final class f implements OnClickListener {
    final /* synthetic */ y a;

    f(y yVar) {
        this.a = yVar;
    }

    public final void onClick(View view) {
        this.a.A.d();
        ((InputMethodManager) this.a.d.getSystemService("input_method")).hideSoftInputFromWindow(this.a.getWindowToken(), 0);
        y yVar = this.a;
        b.a(this.a.d, this.a.q + "_open_user_protocol");
        this.a.a(this.a.x.d(), this.a.x.c());
    }
}
