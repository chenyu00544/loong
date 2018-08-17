package com.alipay.sdk.b;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.e.a.a;
import com.alipay.sdk.e.d;
import com.alipay.sdk.util.g;
import com.alipay.sdk.util.h;
import org.json.JSONObject;

final class b implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ a b;

    b(a aVar, Context context) {
        this.b = aVar;
        this.a = context;
    }

    public final void run() {
        try {
            d aVar = new a();
            Context context = this.a;
            com.alipay.sdk.e.b a = aVar.a(context, "", h.a(context), true);
            if (a != null) {
                a aVar2 = this.b;
                Object obj = a.b;
                if (!TextUtils.isEmpty(obj)) {
                    try {
                        JSONObject optJSONObject = new JSONObject(obj).optJSONObject("st_sdk_config");
                        aVar2.a = optJSONObject.optInt("timeout", 3500);
                        aVar2.b = optJSONObject.optString("tbreturl", "http://h5.m.taobao.com/trade/paySuccess.html?bizOrderId=$OrderId$&").trim();
                    } catch (Throwable th) {
                    }
                }
                a aVar3 = this.b;
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("timeout", aVar3.a());
                    jSONObject.put("tbreturl", aVar3.b);
                    g.a(com.alipay.sdk.f.b.a().a, "alipay_cashier_dynamic_config", jSONObject.toString());
                } catch (Exception e) {
                }
            }
        } catch (Throwable th2) {
        }
    }
}
