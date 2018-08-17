package com.alipay.sdk.b;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.f.b;
import com.alipay.sdk.util.g;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import org.json.JSONObject;

public final class a {
    private static a c;
    int a = 3500;
    public String b = "http://h5.m.taobao.com/trade/paySuccess.html?bizOrderId=$OrderId$&";

    public final int a() {
        if (this.a < 1000 || this.a > BaseImageDownloader.DEFAULT_HTTP_READ_TIMEOUT) {
            return 3500;
        }
        new StringBuilder("DynamicConfig::getJumpTimeout >").append(this.a);
        return this.a;
    }

    public static a b() {
        if (c == null) {
            a aVar = new a();
            c = aVar;
            Object b = g.b(b.a().a, "alipay_cashier_dynamic_config", null);
            if (!TextUtils.isEmpty(b)) {
                try {
                    JSONObject jSONObject = new JSONObject(b);
                    aVar.a = jSONObject.optInt("timeout", 3500);
                    aVar.b = jSONObject.optString("tbreturl", "http://h5.m.taobao.com/trade/paySuccess.html?bizOrderId=$OrderId$&").trim();
                } catch (Throwable th) {
                }
            }
        }
        return c;
    }

    public final void a(Context context) {
        new Thread(new b(this, context)).start();
    }
}
