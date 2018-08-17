package com.unionpay.mobile.android.widgets;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

final class c implements TextWatcher {
    final /* synthetic */ aa a;

    c(aa aaVar) {
        this.a = aaVar;
    }

    public final void afterTextChanged(Editable editable) {
        this.a.a(editable);
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.a.b.hasFocus() && TextUtils.isEmpty(this.a.b.b())) {
            this.a.a(this.a.d, this.a.s() + this.a.d());
        }
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.a.c != null) {
            this.a.c.a(this.a.b, charSequence.toString());
        }
    }
}
