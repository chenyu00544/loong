package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final class bc implements OnFocusChangeListener {
    final /* synthetic */ u a;

    bc(u uVar) {
        this.a = uVar;
    }

    public final void onFocusChange(View view, boolean z) {
        if (z) {
            if (u.b(this.a) && this.a.c != null) {
                this.a.c.setVisibility(0);
            }
        } else if (u.b(this.a) && this.a.c != null) {
            this.a.c.setVisibility(8);
        }
        if (this.a.e != null) {
            this.a.e.a(z);
        }
        if (this.a.f != null) {
            this.a.f.a(z);
        }
        u uVar = this.a;
        u.g();
        this.a.invalidate();
    }
}
