package com.unionpay.mobile.android.nocard.utils;

import android.content.Context;
import android.text.TextUtils;
import com.unionpay.mobile.android.g.b;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.views.order.l;

public final class c {
    public static int a(Context context, b bVar) {
        Object b = PreferenceUtils.b(context);
        int intValue = l.b.intValue();
        if (!TextUtils.isEmpty(b) && TextUtils.isDigitsOnly(b)) {
            intValue = Integer.valueOf(b).intValue();
        }
        boolean equalsIgnoreCase = "0".equalsIgnoreCase(bVar.an);
        if (bVar.aw && (bVar.ao & 69889) == 0) {
            equalsIgnoreCase = true;
        }
        if (equalsIgnoreCase) {
            return l.a.intValue();
        }
        if (!TextUtils.isEmpty(bVar.u)) {
            intValue = l.b.intValue();
        }
        return f.a(bVar.t) ? l.b.intValue() : intValue;
    }
}
