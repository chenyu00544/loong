package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import android.os.Handler;
import com.unionpay.mobile.android.e.c;
import com.unionpay.mobile.android.e.f;
import com.unionpay.mobile.android.g.b;
import com.unionpay.mobile.android.g.d;
import com.unionpay.mobile.android.g.e;
import com.unionpay.mobile.android.nocard.views.ao;
import com.unionpay.uppay.PayActivity;

public final class v extends ao {
    public v(Context context, e eVar) {
        super(context, eVar);
    }

    protected final void a(Handler handler) {
        Object a = ((PayActivity) this.d).a(f.class.toString());
        if (a != null) {
            ((f) a).a(handler);
        }
    }

    protected final void d(String str, String str2) {
        if (b.bn) {
            a(this.a.ap, false);
            return;
        }
        Object a = ((PayActivity) this.d).a(com.unionpay.mobile.android.pro.a.a.b.class.toString());
        if (a != null) {
            ((com.unionpay.mobile.android.pro.a.a.b) a).a(new Handler(new af(this)), str, str2);
        }
    }

    protected final boolean w() {
        return true;
    }

    protected final void x() {
        if (b.bb != null) {
            for (d dVar : b.bb) {
                try {
                    this.d.unbindService(((c) dVar).h());
                } catch (IllegalArgumentException e) {
                }
            }
        }
    }
}
