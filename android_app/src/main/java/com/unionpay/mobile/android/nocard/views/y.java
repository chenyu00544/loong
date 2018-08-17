package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;

final class y implements OnClickListener {
    final /* synthetic */ at a;

    y(at atVar) {
        this.a = atVar;
    }

    public final void onClick(View view) {
        this.a.B.d();
        ((InputMethodManager) this.a.d.getSystemService("input_method")).hideSoftInputFromWindow(this.a.getWindowToken(), 0);
        at atVar = this.a;
        b.a(this.a.d, this.a.q + "_open_user_protocol");
        this.a.a(this.a.y.d(), this.a.y.c());
    }
}
