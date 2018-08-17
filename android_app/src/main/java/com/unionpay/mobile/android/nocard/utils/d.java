package com.unionpay.mobile.android.nocard.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.umeng.analytics.pro.x;
import com.unionpay.mobile.android.a.a;
import com.unionpay.mobile.android.g.b;
import com.unionpay.mobile.android.utils.k;

public final class d {
    private static a a = null;

    public static void a(Context context, b bVar) {
        k.b("uppay", "exit() +++");
        k.a("uppay", "reqId=" + bVar.I.a);
        com.unionpay.mobile.android.i.a aVar = (com.unionpay.mobile.android.i.a) context;
        if (bVar.I.f.length() > 0) {
            k.a("uppay", "result=" + bVar.I.f);
            Intent intent;
            switch (bVar.I.a) {
                case 0:
                case 2:
                case 5:
                    k.b("uppay", "notifyAppResult() +++");
                    intent = new Intent();
                    intent.putExtra("pay_result", bVar.I.f);
                    intent.putExtra("result_data", bVar.bj);
                    if (!(bVar.V == null || bVar.V.length() <= 0 || bVar.W == null || bVar.W.length() <= 0 || bVar.U)) {
                        intent.putExtra("notify_url", bVar.V);
                        intent.putExtra("notify_msg", bVar.W);
                    }
                    if (bVar.n != null) {
                        intent.putExtra("qn", bVar.n);
                        intent.putExtra("sid", bVar.k);
                        intent.putExtra(x.c, bVar.l);
                    }
                    if (a != null) {
                        a.a(bVar.I.f, bVar.n, bVar.k, bVar.l);
                    }
                    aVar.setResult(-1, intent);
                    k.b("uppay", "notifyAppResult() ---");
                    break;
                case 1:
                case 4:
                case 1000:
                    k.b("uppay", " notifyBrowserResult() +++ ");
                    intent = null;
                    String str = bVar.I.f;
                    str = str.equalsIgnoreCase("fail") ? "1" : str.equalsIgnoreCase("cancel") ? WeiboAuthException.DEFAULT_AUTH_ERROR_CODE : "0";
                    switch (bVar.I.a) {
                        case 1:
                            intent = new Intent(bVar.I.b);
                            k.b("uppay", " other browser ");
                            k.a("uppay", " result Action=" + bVar.I.b);
                            break;
                        case 4:
                            intent = new Intent("com.UCMobile.PluginApp.ActivityState");
                            intent.putExtra("ActivityState", "inactive");
                            intent.addCategory("android.intent.category.DEFAULT");
                            aVar.sendBroadcast(intent);
                            intent = new Intent("com.unionpay.uppay.resultURL");
                            k.b("uppay", " uc browser ");
                            break;
                    }
                    if (!(TextUtils.isEmpty(bVar.r) || "exit".equalsIgnoreCase(bVar.r))) {
                        str = bVar.r + str;
                        k.a("uppay", "result URL= " + str);
                        try {
                            if (1000 == bVar.I.a) {
                                intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                                intent.addCategory("android.intent.category.BROWSABLE");
                                aVar.startActivity(intent);
                            } else {
                                intent.putExtra("ResultURL", str);
                                k.a("browser", intent.toURI());
                                aVar.sendBroadcast(intent);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        k.b("uppay", " notifyBrowserResult() --- ");
                        break;
                    }
                case 3:
                    k.b("uppay", "notifyTencentJarResult() +++");
                    Intent intent2 = new Intent();
                    intent2.putExtra("pay_result", bVar.I.f);
                    intent2.putExtra("tencentWID", bVar.I.h);
                    intent2.putExtra("tencentUID", bVar.I.g);
                    intent2.putExtra("bankInfo", bVar.I.j);
                    intent2.putExtra("cardType", bVar.I.k);
                    intent2.putExtra("cardNo", bVar.I.i);
                    aVar.setResult(-1, intent2);
                    k.b("uppay", "notifyTencentJarResult() ---");
                    break;
            }
        }
        if (bVar.V != null && bVar.V.length() > 0 && bVar.W != null && bVar.W.length() > 0) {
            boolean z = bVar.U;
            String str2 = bVar.V;
            String str3 = bVar.W;
            if (z) {
                new Thread(new e(str2, str3, context)).start();
            }
        }
        aVar.finish();
        k.b("uppay", "exit() +++");
    }
}
