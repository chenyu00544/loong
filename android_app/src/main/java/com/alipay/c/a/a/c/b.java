package com.alipay.c.a.a.c;

import android.content.Context;
import com.alipay.android.phone.mrpc.core.aa;
import com.alipay.android.phone.mrpc.core.l;
import com.alipay.tscenter.biz.rpc.a.a;
import com.alipay.tscenter.biz.rpc.vkeydfp.request.DeviceDataReportRequest;
import com.alipay.tscenter.biz.rpc.vkeydfp.result.DeviceDataReportResult;
import org.json.JSONObject;

public final class b implements a {
    private static b d = null;
    private static DeviceDataReportResult e;
    private aa a = null;
    private a b = null;
    private com.alipay.tscenter.biz.rpc.vkeydfp.a c = null;

    private b(Context context, String str) {
        com.alipay.android.phone.mrpc.core.b bVar = new com.alipay.android.phone.mrpc.core.b();
        bVar.a(str);
        this.a = new l(context);
        this.b = (a) this.a.a(a.class, bVar);
        this.c = (com.alipay.tscenter.biz.rpc.vkeydfp.a) this.a.a(com.alipay.tscenter.biz.rpc.vkeydfp.a.class, bVar);
    }

    public static synchronized b a(Context context, String str) {
        b bVar;
        synchronized (b.class) {
            if (d == null) {
                d = new b(context, str);
            }
            bVar = d;
        }
        return bVar;
    }

    public final DeviceDataReportResult a(DeviceDataReportRequest deviceDataReportRequest) {
        if (this.c != null) {
            e = null;
            new Thread(new c(this, deviceDataReportRequest)).start();
            int i = 300000;
            while (e == null && i >= 0) {
                Thread.sleep(50);
                i -= 50;
            }
        }
        return e;
    }

    public final boolean a(String str) {
        if (com.alipay.c.a.a.a.a.a(str)) {
            return false;
        }
        boolean booleanValue;
        if (this.b != null) {
            String str2 = null;
            try {
                str2 = this.b.a(com.alipay.c.a.a.a.a.e(str));
            } catch (Exception e) {
            }
            if (!com.alipay.c.a.a.a.a.a(str2)) {
                booleanValue = ((Boolean) new JSONObject(str2).get("success")).booleanValue();
                return booleanValue;
            }
        }
        booleanValue = false;
        return booleanValue;
    }
}
