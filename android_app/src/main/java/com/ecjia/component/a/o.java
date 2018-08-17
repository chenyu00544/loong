package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.baidu.mapapi.map.WeightedLatLng;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ECJia_FILTER;
import com.ecjia.hamster.model.aa;
import com.ecjia.hamster.model.ab;
import com.ecjia.hamster.model.ac;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.au;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaGoodsListModel */
public class o extends e {
    public static String d = "price_desc";
    public static String e = "price_asc";
    public static String f = "is_hot";
    public static String g = "is_new";
    public ArrayList<au> a = new ArrayList();
    public ArrayList<ac> b = new ArrayList();
    public ArrayList<ac> c = new ArrayList();
    public aa h;
    public aa i;
    public String j;
    public String t;
    private boolean u;
    private boolean v;

    /* compiled from: ECJiaGoodsListModel */
    class o_1 implements OnCancelListener {
        final /* synthetic */ o a;

        o_1(o oVar) {
            this.a = oVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaGoodsListModel */
    class o_2 implements OnCancelListener {
        final /* synthetic */ o a;

        o_2(o oVar) {
            this.a = oVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaGoodsListModel */
    class o_3 implements OnCancelListener {
        final /* synthetic */ o a;

        o_3(o oVar) {
            this.a = oVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public o(Context context) {
        super(context);
        this.s.a((b) this);
    }

    public void a(ECJia_FILTER eCJia_FILTER, boolean z) {
        this.u = true;
        this.q = "goods/list";
        if (z) {
            this.l.show();
        }
        ap c = ap.c();
        ab abVar = new ab();
        abVar.b(1);
        abVar.a(6);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("area_id", e());
            jSONObject.put("filter", eCJia_FILTER.toJson());
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new o_1(this));
    }

    public void a(ECJia_FILTER eCJia_FILTER) {
        this.u = false;
        this.q = "goods/list";
        ab abVar = new ab();
        ap c = ap.c();
        abVar.b(((int) Math.ceil((((double) this.a.size()) * WeightedLatLng.DEFAULT_INTENSITY) / 6.0d)) + 1);
        abVar.a(6);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("area_id", e());
            jSONObject.put("filter", eCJia_FILTER.toJson());
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void a() {
        this.q = "goods/spike/period";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        ap c = ap.c();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new o_2(this));
    }

    public void a(String str) {
        this.v = true;
        this.q = "goods/spike/goodslist";
        this.l.show();
        ap c = ap.c();
        ab abVar = new ab();
        abVar.b(1);
        abVar.a(6);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("id", str);
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new o_3(this));
    }

    public void b(String str) {
        this.v = false;
        this.q = "goods/spike/goodslist";
        ab abVar = new ab();
        ap c = ap.c();
        abVar.b(((int) Math.ceil((((double) this.c.size()) * WeightedLatLng.DEFAULT_INTENSITY) / 6.0d)) + 1);
        abVar.a(6);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("pagination", abVar.a());
            jSONObject.put("id", str);
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
        r2 = 1;
        r0 = 0;
        super.a(r6, r7);
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00b1 }
        r3.<init>(r7);	 Catch:{ JSONException -> 0x00b1 }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00b1 }
        r1.<init>();	 Catch:{ JSONException -> 0x00b1 }
        r4 = "===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00b1 }
        r1 = r1.append(r6);	 Catch:{ JSONException -> 0x00b1 }
        r4 = "返回===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00b1 }
        r4 = r3.toString();	 Catch:{ JSONException -> 0x00b1 }
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00b1 }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x00b1 }
        com.ecjia.a.q.a(r1);	 Catch:{ JSONException -> 0x00b1 }
        r1 = "status";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00b1 }
        r1 = com.ecjia.hamster.model.ax.a(r1);	 Catch:{ JSONException -> 0x00b1 }
        r5.r = r1;	 Catch:{ JSONException -> 0x00b1 }
        r1 = -1;
        r4 = r6.hashCode();	 Catch:{ JSONException -> 0x00b1 }
        switch(r4) {
            case 248549303: goto L_0x0051;
            case 1092721010: goto L_0x0065;
            case 1938950659: goto L_0x005b;
            default: goto L_0x0042;
        };	 Catch:{ JSONException -> 0x00b1 }
    L_0x0042:
        switch(r1) {
            case 0: goto L_0x006f;
            case 1: goto L_0x00d7;
            case 2: goto L_0x0108;
            default: goto L_0x0045;
        };	 Catch:{ JSONException -> 0x00b1 }
    L_0x0045:
        r5.g();	 Catch:{ JSONException -> 0x00b1 }
        r0 = r5.r;	 Catch:{ JSONException -> 0x00b1 }
        r5.a(r6, r7, r0);	 Catch:{ JSONException -> 0x00b1 }
    L_0x004d:
        r5.e(r7);
        return;
    L_0x0051:
        r4 = "goods/list";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00b1 }
        if (r4 == 0) goto L_0x0042;
    L_0x0059:
        r1 = r0;
        goto L_0x0042;
    L_0x005b:
        r4 = "goods/spike/period";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00b1 }
        if (r4 == 0) goto L_0x0042;
    L_0x0063:
        r1 = r2;
        goto L_0x0042;
    L_0x0065:
        r4 = "goods/spike/goodslist";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00b1 }
        if (r4 == 0) goto L_0x0042;
    L_0x006d:
        r1 = 2;
        goto L_0x0042;
    L_0x006f:
        r1 = r5.r;	 Catch:{ JSONException -> 0x00b1 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00b1 }
        if (r1 != r2) goto L_0x00a4;
    L_0x0077:
        r1 = "data";
        r1 = r3.optJSONArray(r1);	 Catch:{ JSONException -> 0x00b1 }
        r2 = r5.u;	 Catch:{ JSONException -> 0x00b1 }
        if (r2 == 0) goto L_0x0086;
    L_0x0081:
        r2 = r5.a;	 Catch:{ JSONException -> 0x00b1 }
        r2.clear();	 Catch:{ JSONException -> 0x00b1 }
    L_0x0086:
        if (r1 == 0) goto L_0x00a4;
    L_0x0088:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00b1 }
        if (r2 <= 0) goto L_0x00a4;
    L_0x008e:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00b1 }
        if (r0 >= r2) goto L_0x00a4;
    L_0x0094:
        r2 = r1.getJSONObject(r0);	 Catch:{ JSONException -> 0x00b1 }
        r2 = com.ecjia.hamster.model.au.a(r2);	 Catch:{ JSONException -> 0x00b1 }
        r4 = r5.a;	 Catch:{ JSONException -> 0x00b1 }
        r4.add(r2);	 Catch:{ JSONException -> 0x00b1 }
        r0 = r0 + 1;
        goto L_0x008e;
    L_0x00a4:
        r0 = "paginated";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00b1 }
        r0 = com.ecjia.hamster.model.aa.a(r0);	 Catch:{ JSONException -> 0x00b1 }
        r5.h = r0;	 Catch:{ JSONException -> 0x00b1 }
        goto L_0x0045;
    L_0x00b1:
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
    L_0x00d7:
        r1 = r5.r;	 Catch:{ JSONException -> 0x00b1 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00b1 }
        if (r1 != r2) goto L_0x0045;
    L_0x00df:
        r1 = "data";
        r1 = r3.optJSONArray(r1);	 Catch:{ JSONException -> 0x00b1 }
        r2 = r5.b;	 Catch:{ JSONException -> 0x00b1 }
        r2.clear();	 Catch:{ JSONException -> 0x00b1 }
        if (r1 == 0) goto L_0x0045;
    L_0x00ec:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00b1 }
        if (r2 <= 0) goto L_0x0045;
    L_0x00f2:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00b1 }
        if (r0 >= r2) goto L_0x0045;
    L_0x00f8:
        r2 = r1.getJSONObject(r0);	 Catch:{ JSONException -> 0x00b1 }
        r2 = com.ecjia.hamster.model.ac.a(r2);	 Catch:{ JSONException -> 0x00b1 }
        r3 = r5.b;	 Catch:{ JSONException -> 0x00b1 }
        r3.add(r2);	 Catch:{ JSONException -> 0x00b1 }
        r0 = r0 + 1;
        goto L_0x00f2;
    L_0x0108:
        r1 = r5.r;	 Catch:{ JSONException -> 0x00b1 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00b1 }
        if (r1 != r2) goto L_0x0153;
    L_0x0110:
        r1 = "data";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00b1 }
        r2 = "begin_time";
        r2 = r1.optString(r2);	 Catch:{ JSONException -> 0x00b1 }
        r5.j = r2;	 Catch:{ JSONException -> 0x00b1 }
        r2 = "end_time";
        r2 = r1.optString(r2);	 Catch:{ JSONException -> 0x00b1 }
        r5.t = r2;	 Catch:{ JSONException -> 0x00b1 }
        r2 = "goods_list";
        r1 = r1.optJSONArray(r2);	 Catch:{ JSONException -> 0x00b1 }
        r2 = r5.v;	 Catch:{ JSONException -> 0x00b1 }
        if (r2 == 0) goto L_0x0135;
    L_0x0130:
        r2 = r5.c;	 Catch:{ JSONException -> 0x00b1 }
        r2.clear();	 Catch:{ JSONException -> 0x00b1 }
    L_0x0135:
        if (r1 == 0) goto L_0x0153;
    L_0x0137:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00b1 }
        if (r2 <= 0) goto L_0x0153;
    L_0x013d:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00b1 }
        if (r0 >= r2) goto L_0x0153;
    L_0x0143:
        r2 = r1.getJSONObject(r0);	 Catch:{ JSONException -> 0x00b1 }
        r2 = com.ecjia.hamster.model.ac.a(r2);	 Catch:{ JSONException -> 0x00b1 }
        r4 = r5.c;	 Catch:{ JSONException -> 0x00b1 }
        r4.add(r2);	 Catch:{ JSONException -> 0x00b1 }
        r0 = r0 + 1;
        goto L_0x013d;
    L_0x0153:
        r0 = "paginated";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00b1 }
        r0 = com.ecjia.hamster.model.aa.a(r0);	 Catch:{ JSONException -> 0x00b1 }
        r5.i = r0;	 Catch:{ JSONException -> 0x00b1 }
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.o.a(java.lang.String, java.lang.String):void");
    }
}
