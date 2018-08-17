package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.f.b;
import com.alipay.sdk.util.c;
import com.alipay.sdk.util.g;
import com.alipay.sdk.util.i;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class a {
    static final Object a = c.class;
    private Activity b;
    private com.alipay.sdk.h.a c;
    private String d = "wappaygw.alipay.com/service/rest.htm";
    private String e = "mclient.alipay.com/service/rest.htm";
    private String f = "mclient.alipay.com/home/exterfaceAssign.htm";
    private Map<String, Object> g = new HashMap();

    public a(Activity activity) {
        this.b = activity;
        b a = b.a();
        Context context = this.b;
        com.alipay.sdk.b.c.a();
        a.a(context);
        com.alipay.sdk.app.a.a.a(activity);
        this.c = new com.alipay.sdk.h.a(activity, "去支付宝付款");
    }

    public synchronized String a(String str, boolean z) {
        String str2;
        Object obj = null;
        synchronized (this) {
            String a;
            if (z) {
                a();
            }
            try {
                Context context;
                String[] split;
                int i;
                String[] split2;
                int i2;
                String a2 = new com.alipay.sdk.f.a(this.b).a(str);
                if (!a2.contains("paymethod=\"expressGateway\"") && i.b(this.b)) {
                    c cVar = new c(this.b, new g(this));
                    a = cVar.a(a2);
                    cVar.a = null;
                    if (!TextUtils.equals(a, "failed")) {
                        if (TextUtils.isEmpty(a)) {
                            a = h.a();
                        }
                        context = this.b;
                        if (!TextUtils.isEmpty(a)) {
                            split = a.split(";");
                            i = 0;
                            while (i < split.length) {
                                if (split[i].startsWith("result={") && split[i].endsWith("}")) {
                                    split2 = split[i].substring(8, split[i].length() - 1).split("&");
                                    i2 = 0;
                                    while (i2 < split2.length) {
                                        if (!split2[i2].startsWith("trade_token=\"") && split2[i2].endsWith("\"")) {
                                            obj = split2[i2].substring(13, split2[i2].length() - 1);
                                            break;
                                        } else if (split2[i2].startsWith("trade_token=")) {
                                            obj = split2[i2].substring(12);
                                            break;
                                        } else {
                                            i2++;
                                        }
                                    }
                                }
                                i++;
                            }
                        }
                        if (!TextUtils.isEmpty(obj)) {
                            g.a(context, "pref_trade_token", obj);
                        }
                        com.alipay.sdk.b.a.b().a(this.b);
                        b();
                        com.alipay.sdk.app.a.a.a(this.b, str);
                        str2 = a;
                    }
                }
                a = a(a2);
                context = this.b;
                if (TextUtils.isEmpty(a)) {
                    split = a.split(";");
                    i = 0;
                    while (i < split.length) {
                        split2 = split[i].substring(8, split[i].length() - 1).split("&");
                        i2 = 0;
                        while (i2 < split2.length) {
                            if (!split2[i2].startsWith("trade_token=\"")) {
                            }
                            if (split2[i2].startsWith("trade_token=")) {
                                obj = split2[i2].substring(12);
                                break;
                            }
                            i2++;
                        }
                        i++;
                    }
                }
                if (TextUtils.isEmpty(obj)) {
                    g.a(context, "pref_trade_token", obj);
                }
            } catch (Throwable th) {
                try {
                    str2 = h.a();
                    com.alipay.sdk.b.a.b().a(this.b);
                    b();
                    com.alipay.sdk.app.a.a.a(this.b, str);
                } catch (Throwable th2) {
                    com.alipay.sdk.b.a.b().a(this.b);
                    b();
                    com.alipay.sdk.app.a.a.a(this.b, str);
                }
            }
            com.alipay.sdk.b.a.b().a(this.b);
            b();
            com.alipay.sdk.app.a.a.a(this.b, str);
            str2 = a;
        }
        return str2;
    }

    private void a() {
        if (this.c != null) {
            this.c.a();
        }
    }

    private void b() {
        if (this.c != null) {
            this.c.b();
        }
    }

    private String a(String str) {
        com.alipay.sdk.g.a aVar;
        i iVar;
        int i = 0;
        a();
        try {
            List a = com.alipay.sdk.protocol.b.a(new com.alipay.sdk.e.a.c().a(this.b, str).a().optJSONObject("form").optJSONObject("onload"));
            for (int i2 = 0; i2 < a.size(); i2++) {
                if (((com.alipay.sdk.protocol.b) a.get(i2)).a == com.alipay.sdk.protocol.a.Update) {
                    String[] strArr = ((com.alipay.sdk.protocol.b) a.get(i2)).b;
                    if (strArr.length == 3 && TextUtils.equals("tid", strArr[0])) {
                        Context context = b.a().a;
                        com.alipay.sdk.g.b a2 = com.alipay.sdk.g.b.a();
                        if (!(TextUtils.isEmpty(strArr[1]) || TextUtils.isEmpty(strArr[2]))) {
                            a2.a = strArr[1];
                            a2.b = strArr[2];
                            aVar = new com.alipay.sdk.g.a(context);
                            aVar.a(com.alipay.sdk.util.a.a(context).a(), com.alipay.sdk.util.a.a(context).b(), a2.a, a2.b);
                            aVar.close();
                        }
                    }
                }
            }
            b();
            while (i < a.size()) {
                if (((com.alipay.sdk.protocol.b) a.get(i)).a == com.alipay.sdk.protocol.a.WapPay) {
                    String a3 = a((com.alipay.sdk.protocol.b) a.get(i));
                    b();
                    return a3;
                }
                i++;
            }
            b();
            iVar = null;
        } catch (Exception e) {
            aVar.close();
        } catch (Throwable e2) {
            i a4 = i.a(i.NETWORK_ERROR.h);
            com.alipay.sdk.app.a.a.a("net", e2);
            b();
            iVar = a4;
        } catch (Throwable th) {
            b();
        }
        if (iVar == null) {
            iVar = i.a(i.FAILED.h);
        }
        return h.a(iVar.h, iVar.i, "");
    }

    private String a(com.alipay.sdk.protocol.b bVar) {
        String[] strArr = bVar.b;
        Intent intent = new Intent(this.b, H5PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", strArr[0]);
        if (strArr.length == 2) {
            bundle.putString("cookie", strArr[1]);
        }
        intent.putExtras(bundle);
        this.b.startActivity(intent);
        synchronized (a) {
            try {
                a.wait();
            } catch (InterruptedException e) {
                return h.a();
            }
        }
        String str = h.a;
        if (TextUtils.isEmpty(str)) {
            return h.a();
        }
        return str;
    }
}
