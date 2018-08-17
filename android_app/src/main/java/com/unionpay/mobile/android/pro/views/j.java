package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import com.unionpay.mobile.android.e.f;
import com.unionpay.mobile.android.pro.a.a.b;
import com.unionpay.uppay.PayActivity;

public final class j extends h {
    public j(Context context) {
        super(context);
    }

    public final b D() {
        Object a = ((PayActivity) this.d).a(b.class.toString());
        return a != null ? (b) a : null;
    }

    public final void a(int i, int i2, String str, String str2, int i3, String str3) {
        Object a = ((PayActivity) this.d).a(f.class.toString());
        if (a != null) {
            f fVar = (f) a;
            fVar.a();
            fVar.a(i);
            fVar.b(i2);
            fVar.a(str);
            fVar.b(str2);
            fVar.c(str3);
            fVar.c(i3);
            fVar.c();
        }
    }

    public final boolean w() {
        return true;
    }

    public final boolean x() {
        return true;
    }
}
