package com.alipay.sdk.app.a;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.util.g;
import java.io.IOException;

final class b implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;

    b(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    public final void run() {
        com.alipay.sdk.e.a.b bVar = new com.alipay.sdk.e.a.b();
        try {
            String b = g.b(this.a, "alipay_cashier_statistic_record", null);
            if (!(TextUtils.isEmpty(b) || bVar.a(this.a, b) == null)) {
                g.a(this.a, "alipay_cashier_statistic_record");
            }
        } catch (Throwable th) {
        }
        try {
            if (!TextUtils.isEmpty(this.b)) {
                bVar.a(this.a, this.b);
            }
        } catch (IOException e) {
            g.a(this.a, "alipay_cashier_statistic_record", this.b);
        } catch (Throwable th2) {
        }
    }
}
