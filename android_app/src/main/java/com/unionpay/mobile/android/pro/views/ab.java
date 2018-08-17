package com.unionpay.mobile.android.pro.views;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import com.unionpay.mobile.android.nocard.views.b;

final class ab implements OnClickListener {
    final /* synthetic */ k a;

    ab(k kVar) {
        this.a = kVar;
    }

    public final void onClick(View view) {
        this.a.y.d();
        ((InputMethodManager) this.a.d.getSystemService("input_method")).hideSoftInputFromWindow(this.a.getWindowToken(), 0);
        k kVar = this.a;
        b.a(this.a.d, this.a.q + "_open_user_protocol");
        this.a.a(this.a.L.d(), this.a.L.c());
    }
}
