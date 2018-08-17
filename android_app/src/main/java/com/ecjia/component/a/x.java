package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ECJia_GOODORDER;
import com.ecjia.hamster.model.ECJia_ORDERDETAIL;
import com.ecjia.hamster.model.ECJia_ORDER_GOODS_LIST;
import com.ecjia.hamster.model.aa;
import com.ecjia.hamster.model.ab;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.m;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaOrderModel */
public class x extends e {
    public aa a;
    public ArrayList<ECJia_GOODORDER> b = new ArrayList();
    public ArrayList<m> c = new ArrayList();
    public ArrayList<Object> d = new ArrayList();
    public ECJia_ORDERDETAIL e;
    public ArrayList<ECJia_ORDER_GOODS_LIST> f = new ArrayList();
    public String g;
    public String h;
    public String i;
    public String j;
    private boolean t;

    /* compiled from: ECJiaOrderModel */
    class x_1 implements OnCancelListener {
        final /* synthetic */ x a;

        x_1(x xVar) {
            this.a = xVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaOrderModel */
    class x_2 implements OnCancelListener {
        final /* synthetic */ x a;

        x_2(x xVar) {
            this.a = xVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaOrderModel */
    class x_3 implements OnCancelListener {
        final /* synthetic */ x a;

        x_3(x xVar) {
            this.a = xVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaOrderModel */
    class x_4 implements OnCancelListener {
        final /* synthetic */ x a;

        x_4(x xVar) {
            this.a = xVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaOrderModel */
    class x_5 implements OnCancelListener {
        final /* synthetic */ x a;

        x_5(x xVar) {
            this.a = xVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaOrderModel */
    class x_6 implements OnCancelListener {
        final /* synthetic */ x a;

        x_6(x xVar) {
            this.a = xVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public x(Context context) {
        super(context);
        this.s.a((b) this);
    }

    public void a(String str, String str2, boolean z) {
        this.t = true;
        this.q = "order/list";
        if (z) {
            this.l.show();
        }
        ab abVar = new ab();
        abVar.b(1);
        abVar.a(8);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("pagination", abVar.a());
            jSONObject.put("keywords", str2);
            jSONObject.put("type", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new x_1(this));
    }

    public void b(String str, String str2) {
        this.t = false;
        this.q = "order/list";
        ab abVar = new ab();
        abVar.b((this.b.size() / 8) + 1);
        abVar.a(8);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("pagination", abVar.a());
            jSONObject.put("type", str);
            jSONObject.put("keywords", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void a(String str) {
        this.g = str;
        this.q = "order/detail";
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
        this.l.setOnCancelListener(new x_2(this));
    }

    public void b(String str) {
        this.g = str;
        this.q = "order/cancel";
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
        this.l.setOnCancelListener(new x_3(this));
    }

    public void c(String str) {
        this.g = str;
        this.q = "order/affirmReceived";
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
        this.l.setOnCancelListener(new x_4(this));
    }

    public void c(String str, String str2) {
        this.g = str;
        this.q = "order/update";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("order_id", str);
            jSONObject.put("pay_id", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new x_5(this));
    }

    public void d(String str) {
        this.q = "order/express";
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
        this.l.setOnCancelListener(new x_6(this));
    }

    public void d(String str, String str2) {
        this.q = "order/reminder";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("order_id", str);
            jSONObject.put("time", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r6, java.lang.String r7) {
        /*
        r5 = this;
        r0 = 0;
        r2 = 1;
        super.a(r6, r7);
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00da }
        r3.<init>(r7);	 Catch:{ JSONException -> 0x00da }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00da }
        r1.<init>();	 Catch:{ JSONException -> 0x00da }
        r4 = "===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00da }
        r1 = r1.append(r6);	 Catch:{ JSONException -> 0x00da }
        r4 = "返回===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00da }
        r4 = r3.toString();	 Catch:{ JSONException -> 0x00da }
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00da }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x00da }
        com.ecjia.a.q.a(r1);	 Catch:{ JSONException -> 0x00da }
        r1 = "status";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00da }
        r1 = com.ecjia.hamster.model.ax.a(r1);	 Catch:{ JSONException -> 0x00da }
        r5.r = r1;	 Catch:{ JSONException -> 0x00da }
        r1 = -1;
        r4 = r6.hashCode();	 Catch:{ JSONException -> 0x00da }
        switch(r4) {
            case -948047341: goto L_0x008d;
            case 278583237: goto L_0x006f;
            case 711842495: goto L_0x0051;
            case 755377583: goto L_0x0083;
            case 915625051: goto L_0x0065;
            case 948125234: goto L_0x005b;
            case 1444503210: goto L_0x0079;
            default: goto L_0x0042;
        };	 Catch:{ JSONException -> 0x00da }
    L_0x0042:
        switch(r1) {
            case 0: goto L_0x0097;
            case 1: goto L_0x0100;
            case 2: goto L_0x0045;
            case 3: goto L_0x0045;
            case 4: goto L_0x0045;
            case 5: goto L_0x0145;
            default: goto L_0x0045;
        };	 Catch:{ JSONException -> 0x00da }
    L_0x0045:
        r5.g();	 Catch:{ JSONException -> 0x00da }
        r0 = r5.r;	 Catch:{ JSONException -> 0x00da }
        r5.a(r6, r7, r0);	 Catch:{ JSONException -> 0x00da }
    L_0x004d:
        r5.e(r7);
        return;
    L_0x0051:
        r4 = "order/list";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00da }
        if (r4 == 0) goto L_0x0042;
    L_0x0059:
        r1 = r0;
        goto L_0x0042;
    L_0x005b:
        r4 = "order/detail";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00da }
        if (r4 == 0) goto L_0x0042;
    L_0x0063:
        r1 = r2;
        goto L_0x0042;
    L_0x0065:
        r4 = "order/cancel";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00da }
        if (r4 == 0) goto L_0x0042;
    L_0x006d:
        r1 = 2;
        goto L_0x0042;
    L_0x006f:
        r4 = "order/affirmReceived";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00da }
        if (r4 == 0) goto L_0x0042;
    L_0x0077:
        r1 = 3;
        goto L_0x0042;
    L_0x0079:
        r4 = "order/update";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00da }
        if (r4 == 0) goto L_0x0042;
    L_0x0081:
        r1 = 4;
        goto L_0x0042;
    L_0x0083:
        r4 = "order/express";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00da }
        if (r4 == 0) goto L_0x0042;
    L_0x008b:
        r1 = 5;
        goto L_0x0042;
    L_0x008d:
        r4 = "order/reminder";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00da }
        if (r4 == 0) goto L_0x0042;
    L_0x0095:
        r1 = 6;
        goto L_0x0042;
    L_0x0097:
        r1 = r5.r;	 Catch:{ JSONException -> 0x00da }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00da }
        if (r1 != r2) goto L_0x00cc;
    L_0x009f:
        r1 = "data";
        r1 = r3.optJSONArray(r1);	 Catch:{ JSONException -> 0x00da }
        r2 = r5.t;	 Catch:{ JSONException -> 0x00da }
        if (r2 == 0) goto L_0x00ae;
    L_0x00a9:
        r2 = r5.b;	 Catch:{ JSONException -> 0x00da }
        r2.clear();	 Catch:{ JSONException -> 0x00da }
    L_0x00ae:
        if (r1 == 0) goto L_0x00cc;
    L_0x00b0:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00da }
        if (r2 <= 0) goto L_0x00cc;
    L_0x00b6:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00da }
        if (r0 >= r2) goto L_0x00cc;
    L_0x00bc:
        r2 = r1.getJSONObject(r0);	 Catch:{ JSONException -> 0x00da }
        r2 = com.ecjia.hamster.model.ECJia_GOODORDER.fromJson(r2);	 Catch:{ JSONException -> 0x00da }
        r4 = r5.b;	 Catch:{ JSONException -> 0x00da }
        r4.add(r2);	 Catch:{ JSONException -> 0x00da }
        r0 = r0 + 1;
        goto L_0x00b6;
    L_0x00cc:
        r0 = "paginated";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00da }
        r0 = com.ecjia.hamster.model.aa.a(r0);	 Catch:{ JSONException -> 0x00da }
        r5.a = r0;	 Catch:{ JSONException -> 0x00da }
        goto L_0x0045;
    L_0x00da:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "===";
        r0 = r0.append(r1);
        r0 = r0.append(r6);
        r1 = "返回===";
        r0 = r0.append(r1);
        r0 = r0.append(r7);
        r0 = r0.toString();
        com.ecjia.a.q.a(r0);
        goto L_0x004d;
    L_0x0100:
        r1 = r5.r;	 Catch:{ JSONException -> 0x00da }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00da }
        if (r1 != r2) goto L_0x0045;
    L_0x0108:
        r1 = "data";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00da }
        r2 = "shipping_name";
        r2 = r1.optString(r2);	 Catch:{ JSONException -> 0x00da }
        r5.h = r2;	 Catch:{ JSONException -> 0x00da }
        r2 = "invoice_no";
        r2 = r1.optString(r2);	 Catch:{ JSONException -> 0x00da }
        r5.i = r2;	 Catch:{ JSONException -> 0x00da }
        r2 = "goods_list";
        r2 = r1.getJSONArray(r2);	 Catch:{ JSONException -> 0x00da }
        r1 = com.ecjia.hamster.model.ECJia_ORDERDETAIL.fromJson(r1);	 Catch:{ JSONException -> 0x00da }
        r5.e = r1;	 Catch:{ JSONException -> 0x00da }
        r1 = r5.f;	 Catch:{ JSONException -> 0x00da }
        r1.clear();	 Catch:{ JSONException -> 0x00da }
    L_0x012f:
        r1 = r2.length();	 Catch:{ JSONException -> 0x00da }
        if (r0 >= r1) goto L_0x0045;
    L_0x0135:
        r1 = r5.f;	 Catch:{ JSONException -> 0x00da }
        r3 = r2.optJSONObject(r0);	 Catch:{ JSONException -> 0x00da }
        r3 = com.ecjia.hamster.model.ECJia_ORDER_GOODS_LIST.fromJson(r3);	 Catch:{ JSONException -> 0x00da }
        r1.add(r3);	 Catch:{ JSONException -> 0x00da }
        r0 = r0 + 1;
        goto L_0x012f;
    L_0x0145:
        r1 = r5.r;	 Catch:{ JSONException -> 0x00da }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00da }
        if (r1 != r2) goto L_0x01a5;
    L_0x014d:
        r1 = "data";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00da }
        r3 = "status";
        r3 = r1.optInt(r3);	 Catch:{ JSONException -> 0x00da }
        if (r3 != r2) goto L_0x0198;
    L_0x015b:
        r2 = "签收成功";
        r5.j = r2;	 Catch:{ JSONException -> 0x00da }
    L_0x015f:
        r2 = "content";
        r2 = r1.optJSONArray(r2);	 Catch:{ JSONException -> 0x00da }
        r3 = "shipping_name";
        r3 = r1.optString(r3);	 Catch:{ JSONException -> 0x00da }
        r5.h = r3;	 Catch:{ JSONException -> 0x00da }
        r3 = "shipping_number";
        r1 = r1.optString(r3);	 Catch:{ JSONException -> 0x00da }
        r5.i = r1;	 Catch:{ JSONException -> 0x00da }
        r1 = r5.c;	 Catch:{ JSONException -> 0x00da }
        r1.clear();	 Catch:{ JSONException -> 0x00da }
        if (r2 == 0) goto L_0x0045;
    L_0x017c:
        r1 = r2.length();	 Catch:{ JSONException -> 0x00da }
        if (r1 <= 0) goto L_0x0045;
    L_0x0182:
        r1 = r2.length();	 Catch:{ JSONException -> 0x00da }
        if (r0 >= r1) goto L_0x0045;
    L_0x0188:
        r1 = r5.c;	 Catch:{ JSONException -> 0x00da }
        r3 = r2.optJSONObject(r0);	 Catch:{ JSONException -> 0x00da }
        r3 = com.ecjia.hamster.model.m.a(r3);	 Catch:{ JSONException -> 0x00da }
        r1.add(r3);	 Catch:{ JSONException -> 0x00da }
        r0 = r0 + 1;
        goto L_0x0182;
    L_0x0198:
        r2 = "status";
        r2 = r1.optInt(r2);	 Catch:{ JSONException -> 0x00da }
        if (r2 != 0) goto L_0x015f;
    L_0x01a0:
        r2 = "未签收";
        r5.j = r2;	 Catch:{ JSONException -> 0x00da }
        goto L_0x015f;
    L_0x01a5:
        r0 = new com.ecjia.component.view.k;	 Catch:{ JSONException -> 0x00da }
        r1 = r5.n;	 Catch:{ JSONException -> 0x00da }
        r2 = r5.r;	 Catch:{ JSONException -> 0x00da }
        r2 = r2.d();	 Catch:{ JSONException -> 0x00da }
        r0.<init>(r1, r2);	 Catch:{ JSONException -> 0x00da }
        r0.a();	 Catch:{ JSONException -> 0x00da }
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.x.a(java.lang.String, java.lang.String):void");
    }
}
