package com.unionpay.mobile.android.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import com.unionpay.mobile.android.nocard.views.b;
import org.simalliance.openmobileapi.SEService;
import org.simalliance.openmobileapi.SEService.CallBack;

public final class l implements CallBack {
    private static SEService b = null;
    private Context a;
    private b c;
    private Callback d = new m(this);
    private Handler e = new Handler(this.d);

    public l(Context context, b bVar) {
        this.a = context;
        this.c = bVar;
        if (b == null) {
            try {
                b = new SEService(this.a, this);
                new n(this).start();
                return;
            } catch (Throwable th) {
                th.printStackTrace();
                k.c("uppay", " service ERROR!!!");
                this.e.sendEmptyMessage(2);
                return;
            }
        }
        ((com.unionpay.mobile.android.nocard.views.l) this.c).v();
    }

    public static SEService a() {
        return b;
    }
}
