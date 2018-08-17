package com.unionpay.mobile.android.nocard.utils;

import android.content.Context;
import com.unionpay.mobile.android.h.c;
import com.unionpay.mobile.android.h.d;

final class e implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ Context c;

    e(String str, String str2, Context context) {
        this.a = str;
        this.b = str2;
        this.c = context;
    }

    public final void run() {
        new c(new d(1, this.a, this.b.getBytes()), this.c).a();
    }
}
