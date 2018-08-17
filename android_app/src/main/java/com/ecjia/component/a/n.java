package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ECJia_PHOTO;
import com.ecjia.hamster.model.ai;
import com.ecjia.hamster.model.al;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.p;
import com.ecjia.hamster.model.r;
import com.ecjia.hamster.model.x;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaGoodsDetailModel */
public class n extends e {
    public ArrayList<ECJia_PHOTO> a = new ArrayList();
    public p b = new p();
    public List<p> c = new ArrayList();
    public x d = null;
    public String e;
    public String f;
    public Boolean g = Boolean.valueOf(false);
    public ArrayList<r> h = new ArrayList();
    public ArrayList<ai> i = new ArrayList();
    public ArrayList<al> j = new ArrayList();
    public String t;
    public Map<String, a> u = new HashMap();

    /* compiled from: ECJiaGoodsDetailModel */
    class n_1 implements OnCancelListener {
        final /* synthetic */ n a;

        n_1(n nVar) {
            this.a = nVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaGoodsDetailModel */
    class n_2 implements OnCancelListener {
        final /* synthetic */ n a;

        n_2(n nVar) {
            this.a = nVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaGoodsDetailModel */
    class n_3 implements OnCancelListener {
        final /* synthetic */ n a;

        n_3(n nVar) {
            this.a = nVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaGoodsDetailModel */
    class n_4 implements OnCancelListener {
        final /* synthetic */ n a;

        n_4(n nVar) {
            this.a = nVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaGoodsDetailModel */
    public static class a {
        String a;
        int b;

        public int a() {
            return this.b;
        }

        public static a a(JSONObject jSONObject) throws JSONException {
            a aVar = new a();
            aVar.a = jSONObject.optString("attr_id", "");
            aVar.b = jSONObject.optInt("goods_number", 0);
            return aVar;
        }
    }

    public n(Context context) {
        super(context);
        this.s.a((b) this);
    }

    public void a(String str, String str2, String str3, boolean z) {
        this.q = "goods/detail";
        if (z) {
            this.l.show();
        }
        ap c = ap.c();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("goods_id", str);
            jSONObject.put("area_id", e());
            jSONObject.put("object_id", str2);
            jSONObject.put("rec_type", str3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new n_1(this));
    }

    public void a(String str) {
        this.q = "user/collect/create";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        ap c = ap.c();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("goods_id", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new n_2(this));
    }

    public void b(String str) {
        this.q = "user/collect/delete";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        ap c = ap.c();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("goods_id", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new n_3(this));
    }

    public void c(String str) {
        this.q = "goods/desc";
        JSONObject jSONObject = new JSONObject();
        ap c = ap.c();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("goods_id", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void b(String str, String str2) {
        this.q = "goods/stock";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        ap c = ap.c();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("area_id", e());
            jSONObject.put("goods_id", str);
            jSONObject.put("area_id", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new n_4(this));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r8, java.lang.String r9) {
        /*
        r7 = this;
        r2 = 1;
        r0 = 0;
        super.a(r8, r9);
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0161 }
        r3.<init>(r9);	 Catch:{ JSONException -> 0x0161 }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0161 }
        r1.<init>();	 Catch:{ JSONException -> 0x0161 }
        r4 = "===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x0161 }
        r1 = r1.append(r8);	 Catch:{ JSONException -> 0x0161 }
        r4 = "返回===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x0161 }
        r4 = r3.toString();	 Catch:{ JSONException -> 0x0161 }
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x0161 }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x0161 }
        com.ecjia.a.q.a(r1);	 Catch:{ JSONException -> 0x0161 }
        r1 = "status";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x0161 }
        r1 = com.ecjia.hamster.model.ax.a(r1);	 Catch:{ JSONException -> 0x0161 }
        r7.r = r1;	 Catch:{ JSONException -> 0x0161 }
        r1 = -1;
        r4 = r8.hashCode();	 Catch:{ JSONException -> 0x0161 }
        switch(r4) {
            case -1895000790: goto L_0x0051;
            case -878118115: goto L_0x0079;
            case 248307114: goto L_0x006f;
            case 332301381: goto L_0x005b;
            case 349137140: goto L_0x0065;
            default: goto L_0x0042;
        };	 Catch:{ JSONException -> 0x0161 }
    L_0x0042:
        switch(r1) {
            case 0: goto L_0x0083;
            case 1: goto L_0x0045;
            case 2: goto L_0x0045;
            case 3: goto L_0x0187;
            case 4: goto L_0x0199;
            default: goto L_0x0045;
        };	 Catch:{ JSONException -> 0x0161 }
    L_0x0045:
        r7.g();	 Catch:{ JSONException -> 0x0161 }
        r0 = r7.r;	 Catch:{ JSONException -> 0x0161 }
        r7.a(r8, r9, r0);	 Catch:{ JSONException -> 0x0161 }
    L_0x004d:
        r7.e(r9);
        return;
    L_0x0051:
        r4 = "goods/detail";
        r4 = r8.equals(r4);	 Catch:{ JSONException -> 0x0161 }
        if (r4 == 0) goto L_0x0042;
    L_0x0059:
        r1 = r0;
        goto L_0x0042;
    L_0x005b:
        r4 = "user/collect/create";
        r4 = r8.equals(r4);	 Catch:{ JSONException -> 0x0161 }
        if (r4 == 0) goto L_0x0042;
    L_0x0063:
        r1 = r2;
        goto L_0x0042;
    L_0x0065:
        r4 = "user/collect/delete";
        r4 = r8.equals(r4);	 Catch:{ JSONException -> 0x0161 }
        if (r4 == 0) goto L_0x0042;
    L_0x006d:
        r1 = 2;
        goto L_0x0042;
    L_0x006f:
        r4 = "goods/desc";
        r4 = r8.equals(r4);	 Catch:{ JSONException -> 0x0161 }
        if (r4 == 0) goto L_0x0042;
    L_0x0077:
        r1 = 3;
        goto L_0x0042;
    L_0x0079:
        r4 = "goods/stock";
        r4 = r8.equals(r4);	 Catch:{ JSONException -> 0x0161 }
        if (r4 == 0) goto L_0x0042;
    L_0x0081:
        r1 = 4;
        goto L_0x0042;
    L_0x0083:
        r1 = r7.r;	 Catch:{ JSONException -> 0x0161 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x0161 }
        if (r1 != r2) goto L_0x0045;
    L_0x008b:
        r1 = "data";
        r2 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x0161 }
        if (r2 == 0) goto L_0x0045;
    L_0x0093:
        r1 = com.ecjia.hamster.model.p.a(r2);	 Catch:{ JSONException -> 0x0161 }
        r7.b = r1;	 Catch:{ JSONException -> 0x0161 }
        r1 = "seller_id";
        r1 = r2.optString(r1);	 Catch:{ JSONException -> 0x0161 }
        r7.e = r1;	 Catch:{ JSONException -> 0x0161 }
        r1 = "server_desc";
        r1 = r2.optString(r1);	 Catch:{ JSONException -> 0x0161 }
        r7.f = r1;	 Catch:{ JSONException -> 0x0161 }
        r1 = "is_warehouse";
        r1 = r2.optBoolean(r1);	 Catch:{ JSONException -> 0x0161 }
        r1 = java.lang.Boolean.valueOf(r1);	 Catch:{ JSONException -> 0x0161 }
        r7.g = r1;	 Catch:{ JSONException -> 0x0161 }
        r1 = "related_goods";
        r3 = r2.optJSONArray(r1);	 Catch:{ JSONException -> 0x0161 }
        r1 = r7.j;	 Catch:{ JSONException -> 0x0161 }
        r1.clear();	 Catch:{ JSONException -> 0x0161 }
        if (r3 == 0) goto L_0x00df;
    L_0x00c2:
        r1 = r3.length();	 Catch:{ JSONException -> 0x0161 }
        if (r1 <= 0) goto L_0x00df;
    L_0x00c8:
        r1 = r0;
    L_0x00c9:
        r4 = r3.length();	 Catch:{ JSONException -> 0x0161 }
        if (r1 >= r4) goto L_0x00df;
    L_0x00cf:
        r4 = r7.j;	 Catch:{ JSONException -> 0x0161 }
        r5 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x0161 }
        r5 = com.ecjia.hamster.model.al.a(r5);	 Catch:{ JSONException -> 0x0161 }
        r4.add(r5);	 Catch:{ JSONException -> 0x0161 }
        r1 = r1 + 1;
        goto L_0x00c9;
    L_0x00df:
        r1 = "favourable_list";
        r3 = r2.optJSONArray(r1);	 Catch:{ JSONException -> 0x0161 }
        r1 = r7.h;	 Catch:{ JSONException -> 0x0161 }
        r1.clear();	 Catch:{ JSONException -> 0x0161 }
        if (r3 == 0) goto L_0x0109;
    L_0x00ec:
        r1 = r3.length();	 Catch:{ JSONException -> 0x0161 }
        if (r1 <= 0) goto L_0x0109;
    L_0x00f2:
        r4 = r3.length();	 Catch:{ JSONException -> 0x0161 }
        r1 = r0;
    L_0x00f7:
        if (r1 >= r4) goto L_0x0109;
    L_0x00f9:
        r5 = r7.h;	 Catch:{ JSONException -> 0x0161 }
        r6 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x0161 }
        r6 = com.ecjia.hamster.model.r.a(r6);	 Catch:{ JSONException -> 0x0161 }
        r5.add(r6);	 Catch:{ JSONException -> 0x0161 }
        r1 = r1 + 1;
        goto L_0x00f7;
    L_0x0109:
        r1 = r7.e;	 Catch:{ JSONException -> 0x0161 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ JSONException -> 0x0161 }
        if (r1 != 0) goto L_0x012f;
    L_0x0111:
        r1 = "0";
        r3 = r7.e;	 Catch:{ JSONException -> 0x0161 }
        r1 = r1.equals(r3);	 Catch:{ JSONException -> 0x0161 }
        if (r1 != 0) goto L_0x012f;
    L_0x011b:
        r1 = "merchant_info";
        r1 = r2.optJSONObject(r1);	 Catch:{ JSONException -> 0x0161 }
        if (r1 == 0) goto L_0x012f;
    L_0x0123:
        r1 = "merchant_info";
        r1 = r2.optJSONObject(r1);	 Catch:{ JSONException -> 0x0161 }
        r1 = com.ecjia.hamster.model.x.a(r1);	 Catch:{ JSONException -> 0x0161 }
        r7.d = r1;	 Catch:{ JSONException -> 0x0161 }
    L_0x012f:
        r1 = "rank_prices";
        r1 = r2.optJSONArray(r1);	 Catch:{ JSONException -> 0x0161 }
        r2 = r7.i;	 Catch:{ JSONException -> 0x0161 }
        r2.clear();	 Catch:{ JSONException -> 0x0161 }
        if (r1 == 0) goto L_0x0158;
    L_0x013c:
        r2 = r1.length();	 Catch:{ JSONException -> 0x0161 }
        if (r2 <= 0) goto L_0x0158;
    L_0x0142:
        r2 = r1.length();	 Catch:{ JSONException -> 0x0161 }
        if (r0 >= r2) goto L_0x0158;
    L_0x0148:
        r2 = r7.i;	 Catch:{ JSONException -> 0x0161 }
        r3 = r1.optJSONObject(r0);	 Catch:{ JSONException -> 0x0161 }
        r3 = com.ecjia.hamster.model.ai.a(r3);	 Catch:{ JSONException -> 0x0161 }
        r2.add(r3);	 Catch:{ JSONException -> 0x0161 }
        r0 = r0 + 1;
        goto L_0x0142;
    L_0x0158:
        r0 = r7.c;	 Catch:{ JSONException -> 0x0161 }
        r1 = r7.b;	 Catch:{ JSONException -> 0x0161 }
        r0.add(r1);	 Catch:{ JSONException -> 0x0161 }
        goto L_0x0045;
    L_0x0161:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "===";
        r0 = r0.append(r1);
        r0 = r0.append(r8);
        r1 = "返回===";
        r0 = r0.append(r1);
        r0 = r0.append(r9);
        r0 = r0.toString();
        com.ecjia.a.q.a(r0);
        goto L_0x004d;
    L_0x0187:
        r0 = r7.r;	 Catch:{ JSONException -> 0x0161 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x0161 }
        if (r0 != r2) goto L_0x0045;
    L_0x018f:
        r0 = "data";
        r0 = r3.optString(r0);	 Catch:{ JSONException -> 0x0161 }
        r7.t = r0;	 Catch:{ JSONException -> 0x0161 }
        goto L_0x0045;
    L_0x0199:
        r1 = r7.r;	 Catch:{ JSONException -> 0x0161 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x0161 }
        if (r1 != r2) goto L_0x0045;
    L_0x01a1:
        r1 = r7.u;	 Catch:{ JSONException -> 0x0161 }
        r1.clear();	 Catch:{ JSONException -> 0x0161 }
        r1 = "data";
        r1 = r3.optJSONArray(r1);	 Catch:{ JSONException -> 0x0161 }
        if (r1 == 0) goto L_0x0045;
    L_0x01ae:
        r2 = r1.length();	 Catch:{ JSONException -> 0x0161 }
        if (r2 <= 0) goto L_0x0045;
    L_0x01b4:
        r2 = r1.length();	 Catch:{ JSONException -> 0x0161 }
    L_0x01b8:
        if (r0 >= r2) goto L_0x0045;
    L_0x01ba:
        r3 = r1.optJSONObject(r0);	 Catch:{ JSONException -> 0x0161 }
        r4 = r7.u;	 Catch:{ JSONException -> 0x0161 }
        r5 = "attr_id";
        r5 = r3.optString(r5);	 Catch:{ JSONException -> 0x0161 }
        r3 = com.ecjia.component.a.n.a.a(r3);	 Catch:{ JSONException -> 0x0161 }
        r4.put(r5, r3);	 Catch:{ JSONException -> 0x0161 }
        r0 = r0 + 1;
        goto L_0x01b8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.n.a(java.lang.String, java.lang.String):void");
    }
}
