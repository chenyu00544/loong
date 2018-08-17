package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.baidu.mapapi.map.WeightedLatLng;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ECJia_CATEGORY;
import com.ecjia.hamster.model.ECJia_FILTER;
import com.ecjia.hamster.model.ECJia_PRICE_RANGE;
import com.ecjia.hamster.model.aa;
import com.ecjia.hamster.model.ab;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.au;
import com.ecjia.hamster.model.f;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaShopDetialModel */
public class ag extends e {
    public static String d = "price_desc";
    public static String e = "price_asc";
    public static String f = "hot";
    public static String g = "new";
    public ArrayList<f> a = new ArrayList();
    public ArrayList<ECJia_PRICE_RANGE> b = new ArrayList();
    public ArrayList<ECJia_CATEGORY> c = new ArrayList();
    public ArrayList<au> h = new ArrayList();
    public aa i;
    private boolean j;

    /* compiled from: ECJiaShopDetialModel */
    class ag_1 implements OnCancelListener {
        final /* synthetic */ ag a;

        ag_1(ag agVar) {
            this.a = agVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public ag(Context context) {
        super(context);
        this.s.a((b) this);
    }

    public void a(ECJia_FILTER eCJia_FILTER, String str) {
        this.j = true;
        this.q = "merchant/goods/list";
        this.l.show();
        ab abVar = new ab();
        abVar.b(1);
        abVar.a(6);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("seller_id", str);
            jSONObject.put("filter", eCJia_FILTER.toJson());
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new ag_1(this));
    }

    public void b(ECJia_FILTER eCJia_FILTER, String str) {
        this.j = false;
        this.q = "merchant/goods/list";
        ab abVar = new ab();
        abVar.b(((int) Math.ceil((((double) this.h.size()) * WeightedLatLng.DEFAULT_INTENSITY) / 6.0d)) + 1);
        abVar.a(6);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("seller_id", str);
            jSONObject.put("filter", eCJia_FILTER.toJson());
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void a(String str) {
        this.q = "merchant/goods/category";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("seller_id", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r7, java.lang.String r8) {
        /*
        r6 = this;
        r2 = 1;
        r0 = 0;
        super.a(r7, r8);
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00a7 }
        r3.<init>(r8);	 Catch:{ JSONException -> 0x00a7 }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00a7 }
        r1.<init>();	 Catch:{ JSONException -> 0x00a7 }
        r4 = "===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00a7 }
        r1 = r1.append(r7);	 Catch:{ JSONException -> 0x00a7 }
        r4 = "返回===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00a7 }
        r4 = r3.toString();	 Catch:{ JSONException -> 0x00a7 }
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00a7 }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x00a7 }
        com.ecjia.a.q.a(r1);	 Catch:{ JSONException -> 0x00a7 }
        r1 = "status";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00a7 }
        r1 = com.ecjia.hamster.model.ax.a(r1);	 Catch:{ JSONException -> 0x00a7 }
        r6.r = r1;	 Catch:{ JSONException -> 0x00a7 }
        r1 = -1;
        r4 = r7.hashCode();	 Catch:{ JSONException -> 0x00a7 }
        switch(r4) {
            case -1175639458: goto L_0x0051;
            case 57300670: goto L_0x005b;
            default: goto L_0x0042;
        };	 Catch:{ JSONException -> 0x00a7 }
    L_0x0042:
        switch(r1) {
            case 0: goto L_0x0065;
            case 1: goto L_0x00cc;
            default: goto L_0x0045;
        };	 Catch:{ JSONException -> 0x00a7 }
    L_0x0045:
        r6.g();	 Catch:{ JSONException -> 0x00a7 }
        r0 = r6.r;	 Catch:{ JSONException -> 0x00a7 }
        r6.a(r7, r8, r0);	 Catch:{ JSONException -> 0x00a7 }
    L_0x004d:
        r6.e(r8);
        return;
    L_0x0051:
        r4 = "merchant/goods/list";
        r4 = r7.equals(r4);	 Catch:{ JSONException -> 0x00a7 }
        if (r4 == 0) goto L_0x0042;
    L_0x0059:
        r1 = r0;
        goto L_0x0042;
    L_0x005b:
        r4 = "merchant/goods/category";
        r4 = r7.equals(r4);	 Catch:{ JSONException -> 0x00a7 }
        if (r4 == 0) goto L_0x0042;
    L_0x0063:
        r1 = r2;
        goto L_0x0042;
    L_0x0065:
        r1 = r6.r;	 Catch:{ JSONException -> 0x00a7 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00a7 }
        if (r1 != r2) goto L_0x009a;
    L_0x006d:
        r1 = "data";
        r1 = r3.optJSONArray(r1);	 Catch:{ JSONException -> 0x00a7 }
        r2 = r6.j;	 Catch:{ JSONException -> 0x00a7 }
        if (r2 == 0) goto L_0x007c;
    L_0x0077:
        r2 = r6.h;	 Catch:{ JSONException -> 0x00a7 }
        r2.clear();	 Catch:{ JSONException -> 0x00a7 }
    L_0x007c:
        if (r1 == 0) goto L_0x009a;
    L_0x007e:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00a7 }
        if (r2 <= 0) goto L_0x009a;
    L_0x0084:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00a7 }
    L_0x0088:
        if (r0 >= r2) goto L_0x009a;
    L_0x008a:
        r4 = r6.h;	 Catch:{ JSONException -> 0x00a7 }
        r5 = r1.optJSONObject(r0);	 Catch:{ JSONException -> 0x00a7 }
        r5 = com.ecjia.hamster.model.au.a(r5);	 Catch:{ JSONException -> 0x00a7 }
        r4.add(r5);	 Catch:{ JSONException -> 0x00a7 }
        r0 = r0 + 1;
        goto L_0x0088;
    L_0x009a:
        r0 = "paginated";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00a7 }
        r0 = com.ecjia.hamster.model.aa.a(r0);	 Catch:{ JSONException -> 0x00a7 }
        r6.i = r0;	 Catch:{ JSONException -> 0x00a7 }
        goto L_0x0045;
    L_0x00a7:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "===";
        r0 = r0.append(r1);
        r0 = r0.append(r7);
        r1 = "返回===";
        r0 = r0.append(r1);
        r0 = r0.append(r8);
        r0 = r0.toString();
        com.ecjia.a.q.a(r0);
        goto L_0x004d;
    L_0x00cc:
        r1 = r6.r;	 Catch:{ JSONException -> 0x00a7 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00a7 }
        if (r1 != r2) goto L_0x0045;
    L_0x00d4:
        r1 = "data";
        r1 = r3.optJSONArray(r1);	 Catch:{ JSONException -> 0x00a7 }
        r2 = r6.c;	 Catch:{ JSONException -> 0x00a7 }
        r2.clear();	 Catch:{ JSONException -> 0x00a7 }
        if (r1 == 0) goto L_0x0045;
    L_0x00e1:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00a7 }
        if (r2 <= 0) goto L_0x0045;
    L_0x00e7:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00a7 }
    L_0x00eb:
        if (r0 >= r2) goto L_0x0045;
    L_0x00ed:
        r3 = r6.c;	 Catch:{ JSONException -> 0x00a7 }
        r4 = r1.optJSONObject(r0);	 Catch:{ JSONException -> 0x00a7 }
        r4 = com.ecjia.hamster.model.ECJia_CATEGORY.fromJson(r4);	 Catch:{ JSONException -> 0x00a7 }
        r3.add(r4);	 Catch:{ JSONException -> 0x00a7 }
        r0 = r0 + 1;
        goto L_0x00eb;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.ag.a(java.lang.String, java.lang.String):void");
    }
}
