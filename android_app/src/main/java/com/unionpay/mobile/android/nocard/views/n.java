package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;

final class n implements OnClickListener {
    final /* synthetic */ ak a;

    n(ak akVar) {
        this.a = akVar;
    }

    public final void onClick(View view) {
        this.a.v.d();
        ((InputMethodManager) this.a.d.getSystemService("input_method")).hideSoftInputFromWindow(this.a.getWindowToken(), 0);
        ak akVar = this.a;
        b.a(this.a.d, this.a.q + "_open_user_protocol");
        this.a.a(this.a.t.d(), this.a.t.c());
    }
}
