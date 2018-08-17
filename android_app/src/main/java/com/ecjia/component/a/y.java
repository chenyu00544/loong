package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.paycenter.b.a;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaPayModel */
public class y extends e {
    public String a;
    public String b;
    public a c;
    public com.ecjia.hamster.paycenter.a.a d;
    public com.ecjia.hamster.paycenter.c.a e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;

    /* compiled from: ECJiaPayModel */
    class y_1 implements OnCancelListener {
        final /* synthetic */ y a;

        y_1(y yVar) {
            this.a = yVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaPayModel */
    class y_2 implements OnCancelListener {
        final /* synthetic */ y a;

        y_2(y yVar) {
            this.a = yVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public y(Context context) {
        super(context);
        this.s.a((b) this);
    }

    public void a(String str) {
        this.a = str;
        this.q = "order/pay";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("order_id", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new y_1(this));
    }

    public void b(String str, String str2) {
        this.q = "user/account/pay";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("payment_id", str);
            jSONObject.put("account_id", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new y_2(this));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r5, java.lang.String r6) {
        /*
        r4 = this;
        r1 = 1;
        super.a(r5, r6);
        r2 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00f7 }
        r2.<init>(r6);	 Catch:{ JSONException -> 0x00f7 }
        r0 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00f7 }
        r0.<init>();	 Catch:{ JSONException -> 0x00f7 }
        r3 = "===";
        r0 = r0.append(r3);	 Catch:{ JSONException -> 0x00f7 }
        r0 = r0.append(r5);	 Catch:{ JSONException -> 0x00f7 }
        r3 = "返回===";
        r0 = r0.append(r3);	 Catch:{ JSONException -> 0x00f7 }
        r3 = r2.toString();	 Catch:{ JSONException -> 0x00f7 }
        r0 = r0.append(r3);	 Catch:{ JSONException -> 0x00f7 }
        r0 = r0.toString();	 Catch:{ JSONException -> 0x00f7 }
        com.ecjia.a.q.a(r0);	 Catch:{ JSONException -> 0x00f7 }
        r0 = "status";
        r0 = r2.optJSONObject(r0);	 Catch:{ JSONException -> 0x00f7 }
        r0 = com.ecjia.hamster.model.ax.a(r0);	 Catch:{ JSONException -> 0x00f7 }
        r4.r = r0;	 Catch:{ JSONException -> 0x00f7 }
        r0 = -1;
        r3 = r5.hashCode();	 Catch:{ JSONException -> 0x00f7 }
        switch(r3) {
            case -392675737: goto L_0x0050;
            case 1698802050: goto L_0x005a;
            default: goto L_0x0041;
        };	 Catch:{ JSONException -> 0x00f7 }
    L_0x0041:
        switch(r0) {
            case 0: goto L_0x0064;
            case 1: goto L_0x01c6;
            default: goto L_0x0044;
        };	 Catch:{ JSONException -> 0x00f7 }
    L_0x0044:
        r4.g();	 Catch:{ JSONException -> 0x00f7 }
        r0 = r4.r;	 Catch:{ JSONException -> 0x00f7 }
        r4.a(r5, r6, r0);	 Catch:{ JSONException -> 0x00f7 }
    L_0x004c:
        r4.e(r6);
        return;
    L_0x0050:
        r3 = "order/pay";
        r3 = r5.equals(r3);	 Catch:{ JSONException -> 0x00f7 }
        if (r3 == 0) goto L_0x0041;
    L_0x0058:
        r0 = 0;
        goto L_0x0041;
    L_0x005a:
        r3 = "user/account/pay";
        r3 = r5.equals(r3);	 Catch:{ JSONException -> 0x00f7 }
        if (r3 == 0) goto L_0x0041;
    L_0x0062:
        r0 = r1;
        goto L_0x0041;
    L_0x0064:
        r0 = r4.r;	 Catch:{ JSONException -> 0x00f7 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x00f7 }
        if (r0 != r1) goto L_0x0044;
    L_0x006c:
        r0 = "data";
        r0 = r2.optJSONObject(r0);	 Catch:{ JSONException -> 0x00f7 }
        r1 = "payment";
        r0 = r0.optJSONObject(r1);	 Catch:{ JSONException -> 0x00f7 }
        r1 = "pay_name";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x00f7 }
        r4.b = r1;	 Catch:{ JSONException -> 0x00f7 }
        r1 = "pay_code";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x00f7 }
        r4.f = r1;	 Catch:{ JSONException -> 0x00f7 }
        r1 = "pay_alipay";
        r2 = r4.f;	 Catch:{ JSONException -> 0x00f7 }
        r1 = r1.equals(r2);	 Catch:{ JSONException -> 0x00f7 }
        if (r1 == 0) goto L_0x011d;
    L_0x0092:
        r1 = new com.ecjia.hamster.paycenter.a.a;	 Catch:{ JSONException -> 0x00f7 }
        r1.<init>();	 Catch:{ JSONException -> 0x00f7 }
        r4.d = r1;	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.d;	 Catch:{ JSONException -> 0x00f7 }
        r2 = r4.a;	 Catch:{ JSONException -> 0x00f7 }
        r1.c(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.d;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "subject";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.d(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.d;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "partner";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.e(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.d;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "order_amount";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.b(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.d;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "seller_id";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.f(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.d;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "notify_url";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.g(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.d;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "pay_order_sn";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.h(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.d;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "private_key";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.a(r2);	 Catch:{ JSONException -> 0x00f7 }
    L_0x00ed:
        r1 = "pay_online";
        r0 = r0.optString(r1);	 Catch:{ JSONException -> 0x00f7 }
        r4.g = r0;	 Catch:{ JSONException -> 0x00f7 }
        goto L_0x0044;
    L_0x00f7:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "===";
        r0 = r0.append(r1);
        r0 = r0.append(r5);
        r1 = "返回===";
        r0 = r0.append(r1);
        r0 = r0.append(r6);
        r0 = r0.toString();
        com.ecjia.a.q.a(r0);
        goto L_0x004c;
    L_0x011d:
        r1 = "pay_upmp";
        r2 = r4.f;	 Catch:{ JSONException -> 0x00f7 }
        r1 = r1.equals(r2);	 Catch:{ JSONException -> 0x00f7 }
        if (r1 == 0) goto L_0x013a;
    L_0x0127:
        r1 = new com.ecjia.hamster.paycenter.b.a;	 Catch:{ JSONException -> 0x00f7 }
        r1.<init>();	 Catch:{ JSONException -> 0x00f7 }
        r4.c = r1;	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.c;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "pay_upmp_tn";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.a(r2);	 Catch:{ JSONException -> 0x00f7 }
        goto L_0x00ed;
    L_0x013a:
        r1 = "pay_bank";
        r2 = r4.f;	 Catch:{ JSONException -> 0x00f7 }
        r1 = r1.equals(r2);	 Catch:{ JSONException -> 0x00f7 }
        if (r1 == 0) goto L_0x014d;
    L_0x0144:
        r1 = "pay_online";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x00f7 }
        r4.g = r1;	 Catch:{ JSONException -> 0x00f7 }
        goto L_0x00ed;
    L_0x014d:
        r1 = "pay_balance";
        r2 = r4.f;	 Catch:{ JSONException -> 0x00f7 }
        r1 = r1.equals(r2);	 Catch:{ JSONException -> 0x00f7 }
        if (r1 == 0) goto L_0x0171;
    L_0x0157:
        r1 = "pay_status";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x00f7 }
        r4.h = r1;	 Catch:{ JSONException -> 0x00f7 }
        r1 = "error_message";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x00f7 }
        r4.i = r1;	 Catch:{ JSONException -> 0x00f7 }
        r1 = "user_money";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x00f7 }
        r4.j = r1;	 Catch:{ JSONException -> 0x00f7 }
        goto L_0x00ed;
    L_0x0171:
        r1 = "pay_wxpay";
        r2 = r4.f;	 Catch:{ JSONException -> 0x00f7 }
        r1 = r1.equals(r2);	 Catch:{ JSONException -> 0x00f7 }
        if (r1 == 0) goto L_0x00ed;
    L_0x017b:
        r1 = new com.ecjia.hamster.paycenter.c.a;	 Catch:{ JSONException -> 0x00f7 }
        r1.<init>();	 Catch:{ JSONException -> 0x00f7 }
        r4.e = r1;	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.e;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "subject";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.d(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.e;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "order_amount";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.c(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.e;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "notify_url";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.e(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.e;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "pay_order_sn";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.f(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.e;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "mch_id";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.b(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.e;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "private_key";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.a(r2);	 Catch:{ JSONException -> 0x00f7 }
        goto L_0x00ed;
    L_0x01c6:
        r0 = r4.r;	 Catch:{ JSONException -> 0x00f7 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x00f7 }
        if (r0 != r1) goto L_0x02fa;
    L_0x01ce:
        r0 = "data";
        r0 = r2.optJSONObject(r0);	 Catch:{ JSONException -> 0x00f7 }
        r1 = "payment";
        r0 = r0.optJSONObject(r1);	 Catch:{ JSONException -> 0x00f7 }
        r1 = "pay_name";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x00f7 }
        r4.b = r1;	 Catch:{ JSONException -> 0x00f7 }
        r1 = "pay_code";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x00f7 }
        r4.f = r1;	 Catch:{ JSONException -> 0x00f7 }
        r1 = "pay_alipay";
        r2 = r4.f;	 Catch:{ JSONException -> 0x00f7 }
        r1 = r1.equals(r2);	 Catch:{ JSONException -> 0x00f7 }
        if (r1 == 0) goto L_0x0252;
    L_0x01f4:
        r1 = new com.ecjia.hamster.paycenter.a.a;	 Catch:{ JSONException -> 0x00f7 }
        r1.<init>();	 Catch:{ JSONException -> 0x00f7 }
        r4.d = r1;	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.d;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "subject";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.d(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.d;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "partner";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.e(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.d;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "order_amount";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.b(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.d;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "seller_id";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.f(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.d;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "notify_url";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.g(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.d;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "pay_order_sn";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.h(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.d;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "private_key";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.a(r2);	 Catch:{ JSONException -> 0x00f7 }
    L_0x0248:
        r1 = "pay_online";
        r0 = r0.optString(r1);	 Catch:{ JSONException -> 0x00f7 }
        r4.g = r0;	 Catch:{ JSONException -> 0x00f7 }
        goto L_0x0044;
    L_0x0252:
        r1 = "pay_upmp";
        r2 = r4.f;	 Catch:{ JSONException -> 0x00f7 }
        r1 = r1.equals(r2);	 Catch:{ JSONException -> 0x00f7 }
        if (r1 == 0) goto L_0x026f;
    L_0x025c:
        r1 = new com.ecjia.hamster.paycenter.b.a;	 Catch:{ JSONException -> 0x00f7 }
        r1.<init>();	 Catch:{ JSONException -> 0x00f7 }
        r4.c = r1;	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.c;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "pay_upmp_tn";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.a(r2);	 Catch:{ JSONException -> 0x00f7 }
        goto L_0x0248;
    L_0x026f:
        r1 = "pay_bank";
        r2 = r4.f;	 Catch:{ JSONException -> 0x00f7 }
        r1 = r1.equals(r2);	 Catch:{ JSONException -> 0x00f7 }
        if (r1 == 0) goto L_0x0282;
    L_0x0279:
        r1 = "pay_online";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x00f7 }
        r4.g = r1;	 Catch:{ JSONException -> 0x00f7 }
        goto L_0x0248;
    L_0x0282:
        r1 = "pay_balance";
        r2 = r4.f;	 Catch:{ JSONException -> 0x00f7 }
        r1 = r1.equals(r2);	 Catch:{ JSONException -> 0x00f7 }
        if (r1 == 0) goto L_0x02a5;
    L_0x028c:
        r1 = "pay_status";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x00f7 }
        r4.h = r1;	 Catch:{ JSONException -> 0x00f7 }
        r1 = "error_message";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x00f7 }
        r4.i = r1;	 Catch:{ JSONException -> 0x00f7 }
        r1 = "user_money";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x00f7 }
        r4.j = r1;	 Catch:{ JSONException -> 0x00f7 }
        goto L_0x0248;
    L_0x02a5:
        r1 = "pay_wxpay";
        r2 = r4.f;	 Catch:{ JSONException -> 0x00f7 }
        r1 = r1.equals(r2);	 Catch:{ JSONException -> 0x00f7 }
        if (r1 == 0) goto L_0x0248;
    L_0x02af:
        r1 = new com.ecjia.hamster.paycenter.c.a;	 Catch:{ JSONException -> 0x00f7 }
        r1.<init>();	 Catch:{ JSONException -> 0x00f7 }
        r4.e = r1;	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.e;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "subject";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.d(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.e;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "order_amount";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.c(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.e;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "notify_url";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.e(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.e;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "pay_order_sn";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.f(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.e;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "mch_id";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.b(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.e;	 Catch:{ JSONException -> 0x00f7 }
        r2 = "private_key";
        r2 = r0.optString(r2);	 Catch:{ JSONException -> 0x00f7 }
        r1.a(r2);	 Catch:{ JSONException -> 0x00f7 }
        goto L_0x0248;
    L_0x02fa:
        r0 = new com.ecjia.component.view.k;	 Catch:{ JSONException -> 0x00f7 }
        r1 = r4.n;	 Catch:{ JSONException -> 0x00f7 }
        r2 = r4.r;	 Catch:{ JSONException -> 0x00f7 }
        r2 = r2.d();	 Catch:{ JSONException -> 0x00f7 }
        r0.<init>(r1, r2);	 Catch:{ JSONException -> 0x00f7 }
        r0.a();	 Catch:{ JSONException -> 0x00f7 }
        goto L_0x0044;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.y.a(java.lang.String, java.lang.String):void");
    }
}
