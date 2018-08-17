package com.unionpay.mobile.android.widgets;

import android.text.Editable;
import android.text.TextWatcher;

final class bb implements TextWatcher {
    final /* synthetic */ u a;

    bb(u uVar) {
        this.a = uVar;
    }

    public final void afterTextChanged(Editable editable) {
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.a.c != null) {
            if (u.b(this.a) && this.a.b.isFocused()) {
                this.a.c.setVisibility(0);
            } else {
                this.a.c.setVisibility(8);
            }
        }
    }
}
