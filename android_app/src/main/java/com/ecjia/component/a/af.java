package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.text.TextUtils;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ECJia_CATEGORY;
import com.ecjia.hamster.model.aa;
import com.ecjia.hamster.model.ab;
import com.ecjia.hamster.model.ao;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.ar;
import com.ecjia.hamster.model.c;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaSellerModel */
public class af extends e {
    public ArrayList<ao> a = new ArrayList();
    public ArrayList<ao> b = new ArrayList();
    public ArrayList<ECJia_CATEGORY> c = new ArrayList();
    public ArrayList<c> d = new ArrayList();
    public aa e;
    public ar f = new ar();
    private boolean g;
    private boolean h;

    /* compiled from: ECJiaSellerModel */
    class af_1 implements OnCancelListener {
        final /* synthetic */ af a;

        af_1(af afVar) {
            this.a = afVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaSellerModel */
    class af_2 implements OnCancelListener {
        final /* synthetic */ af a;

        af_2(af afVar) {
            this.a = afVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaSellerModel */
    class af_3 implements OnCancelListener {
        final /* synthetic */ af a;

        af_3(af afVar) {
            this.a = afVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaSellerModel */
    class af_4 implements OnCancelListener {
        final /* synthetic */ af a;

        af_4(af afVar) {
            this.a = afVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaSellerModel */
    class af_5 implements OnCancelListener {
        final /* synthetic */ af a;

        af_5(af afVar) {
            this.a = afVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaSellerModel */
    class af_6 implements OnCancelListener {
        final /* synthetic */ af a;

        af_6(af afVar) {
            this.a = afVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaSellerModel */
    class af_7 implements OnCancelListener {
        final /* synthetic */ af a;

        af_7(af afVar) {
            this.a = afVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public af(Context context) {
        super(context);
        this.s.a((b) this);
    }

    public void a(String str) {
        a(str, false);
    }

    private void a(String str, boolean z) {
        this.g = true;
        this.q = "seller/list";
        ab abVar = new ab();
        abVar.b(1);
        abVar.a(6);
        if (z) {
            this.l.show();
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("area_id", e());
            if (!(TextUtils.isEmpty(str) || WeiboAuthException.DEFAULT_AUTH_ERROR_CODE.equals(str))) {
                jSONObject.put("category_id", str);
            }
            jSONObject.put("location", f().toJson());
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new af_1(this));
    }

    public void b(String str) {
        this.q = "seller/list";
        ab abVar = new ab();
        abVar.b((this.a.size() / 6) + 1);
        abVar.a(6);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("area_id", e());
            if (!(TextUtils.isEmpty(str) || WeiboAuthException.DEFAULT_AUTH_ERROR_CODE.equals(str))) {
                jSONObject.put("category_id", str);
            }
            jSONObject.put("location", f().toJson());
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void a() {
        this.q = "seller/category";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new af_2(this));
    }

    public void b() {
        this.h = true;
        this.q = "seller/collect/list";
        ab abVar = new ab();
        abVar.b(1);
        abVar.a(10);
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new af_3(this));
    }

    public void c() {
        this.h = false;
        this.q = "seller/collect/list";
        ab abVar = new ab();
        abVar.b((this.b.size() / 10) + 1);
        abVar.a(10);
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void c(String str) {
        this.q = "seller/collect/create";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("seller_id", Integer.parseInt(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new af_4(this));
    }

    public void d(String str) {
        this.q = "seller/collect/delete";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("seller_id", Integer.parseInt(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new af_5(this));
    }

    public void j() {
        this.q = "seller/home/data";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new af_6(this));
    }

    public void f(String str) {
        this.q = "merchant/home/data";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("seller_id", Integer.parseInt(str));
            jSONObject.put("location", f().toJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new af_7(this));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r6, java.lang.String r7) {
        /*
        r5 = this;
        r0 = 0;
        r2 = 1;
        super.a(r6, r7);
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00d4 }
        r3.<init>(r7);	 Catch:{ JSONException -> 0x00d4 }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00d4 }
        r1.<init>();	 Catch:{ JSONException -> 0x00d4 }
        r4 = "===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00d4 }
        r1 = r1.append(r6);	 Catch:{ JSONException -> 0x00d4 }
        r4 = "返回===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00d4 }
        r4 = r3.toString();	 Catch:{ JSONException -> 0x00d4 }
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00d4 }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x00d4 }
        com.ecjia.a.q.a(r1);	 Catch:{ JSONException -> 0x00d4 }
        r1 = "status";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00d4 }
        r1 = com.ecjia.hamster.model.ax.a(r1);	 Catch:{ JSONException -> 0x00d4 }
        r5.r = r1;	 Catch:{ JSONException -> 0x00d4 }
        r1 = -1;
        r4 = r6.hashCode();	 Catch:{ JSONException -> 0x00d4 }
        switch(r4) {
            case -2073837039: goto L_0x006f;
            case -2057001280: goto L_0x0079;
            case -189303981: goto L_0x008d;
            case 449564046: goto L_0x005b;
            case 1203407150: goto L_0x0051;
            case 1392513779: goto L_0x0065;
            case 1559134282: goto L_0x0083;
            default: goto L_0x0042;
        };	 Catch:{ JSONException -> 0x00d4 }
    L_0x0042:
        switch(r1) {
            case 0: goto L_0x0097;
            case 1: goto L_0x00fa;
            case 2: goto L_0x0134;
            case 3: goto L_0x0045;
            case 4: goto L_0x0045;
            case 5: goto L_0x0177;
            case 6: goto L_0x01ae;
            default: goto L_0x0045;
        };	 Catch:{ JSONException -> 0x00d4 }
    L_0x0045:
        r5.g();	 Catch:{ JSONException -> 0x00d4 }
        r0 = r5.r;	 Catch:{ JSONException -> 0x00d4 }
        r5.a(r6, r7, r0);	 Catch:{ JSONException -> 0x00d4 }
    L_0x004d:
        r5.e(r7);
        return;
    L_0x0051:
        r4 = "seller/list";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00d4 }
        if (r4 == 0) goto L_0x0042;
    L_0x0059:
        r1 = r0;
        goto L_0x0042;
    L_0x005b:
        r4 = "seller/category";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00d4 }
        if (r4 == 0) goto L_0x0042;
    L_0x0063:
        r1 = r2;
        goto L_0x0042;
    L_0x0065:
        r4 = "seller/collect/list";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00d4 }
        if (r4 == 0) goto L_0x0042;
    L_0x006d:
        r1 = 2;
        goto L_0x0042;
    L_0x006f:
        r4 = "seller/collect/create";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00d4 }
        if (r4 == 0) goto L_0x0042;
    L_0x0077:
        r1 = 3;
        goto L_0x0042;
    L_0x0079:
        r4 = "seller/collect/delete";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00d4 }
        if (r4 == 0) goto L_0x0042;
    L_0x0081:
        r1 = 4;
        goto L_0x0042;
    L_0x0083:
        r4 = "seller/home/data";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00d4 }
        if (r4 == 0) goto L_0x0042;
    L_0x008b:
        r1 = 5;
        goto L_0x0042;
    L_0x008d:
        r4 = "merchant/home/data";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00d4 }
        if (r4 == 0) goto L_0x0042;
    L_0x0095:
        r1 = 6;
        goto L_0x0042;
    L_0x0097:
        r1 = r5.r;	 Catch:{ JSONException -> 0x00d4 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00d4 }
        if (r1 != r2) goto L_0x00c6;
    L_0x009f:
        r1 = "data";
        r1 = r3.optJSONArray(r1);	 Catch:{ JSONException -> 0x00d4 }
        r2 = r5.g;	 Catch:{ JSONException -> 0x00d4 }
        if (r2 == 0) goto L_0x00ae;
    L_0x00a9:
        r2 = r5.a;	 Catch:{ JSONException -> 0x00d4 }
        r2.clear();	 Catch:{ JSONException -> 0x00d4 }
    L_0x00ae:
        if (r1 == 0) goto L_0x00c6;
    L_0x00b0:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00d4 }
        if (r2 <= 0) goto L_0x00c6;
    L_0x00b6:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00d4 }
        if (r0 >= r2) goto L_0x00c6;
    L_0x00bc:
        r2 = r1.getJSONObject(r0);	 Catch:{ JSONException -> 0x00d4 }
        com.ecjia.hamster.model.ao.a(r2);	 Catch:{ JSONException -> 0x00d4 }
        r0 = r0 + 1;
        goto L_0x00b6;
    L_0x00c6:
        r0 = "paginated";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00d4 }
        r0 = com.ecjia.hamster.model.aa.a(r0);	 Catch:{ JSONException -> 0x00d4 }
        r5.e = r0;	 Catch:{ JSONException -> 0x00d4 }
        goto L_0x0045;
    L_0x00d4:
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
    L_0x00fa:
        r1 = r5.r;	 Catch:{ JSONException -> 0x00d4 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00d4 }
        if (r1 != r2) goto L_0x0045;
    L_0x0102:
        r1 = "data";
        r1 = r3.optJSONArray(r1);	 Catch:{ JSONException -> 0x00d4 }
        r2 = r5.c;	 Catch:{ JSONException -> 0x00d4 }
        r2.clear();	 Catch:{ JSONException -> 0x00d4 }
        if (r1 == 0) goto L_0x0045;
    L_0x010f:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00d4 }
        if (r2 <= 0) goto L_0x0045;
    L_0x0115:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00d4 }
        if (r0 >= r2) goto L_0x0125;
    L_0x011b:
        r2 = r1.getJSONObject(r0);	 Catch:{ JSONException -> 0x00d4 }
        com.ecjia.hamster.model.ECJia_CATEGORY.fromJson(r2);	 Catch:{ JSONException -> 0x00d4 }
        r0 = r0 + 1;
        goto L_0x0115;
    L_0x0125:
        r0 = r5.c;	 Catch:{ JSONException -> 0x00d4 }
        r1 = 0;
        r0 = r0.get(r1);	 Catch:{ JSONException -> 0x00d4 }
        r0 = (com.ecjia.hamster.model.ECJia_CATEGORY) r0;	 Catch:{ JSONException -> 0x00d4 }
        r1 = 1;
        r0.setIschecked(r1);	 Catch:{ JSONException -> 0x00d4 }
        goto L_0x0045;
    L_0x0134:
        r1 = r5.r;	 Catch:{ JSONException -> 0x00d4 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00d4 }
        if (r1 != r2) goto L_0x0169;
    L_0x013c:
        r1 = "data";
        r1 = r3.optJSONArray(r1);	 Catch:{ JSONException -> 0x00d4 }
        r2 = r5.h;	 Catch:{ JSONException -> 0x00d4 }
        if (r2 == 0) goto L_0x014b;
    L_0x0146:
        r2 = r5.b;	 Catch:{ JSONException -> 0x00d4 }
        r2.clear();	 Catch:{ JSONException -> 0x00d4 }
    L_0x014b:
        if (r1 == 0) goto L_0x0169;
    L_0x014d:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00d4 }
        if (r2 <= 0) goto L_0x0169;
    L_0x0153:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00d4 }
        if (r0 >= r2) goto L_0x0169;
    L_0x0159:
        r2 = r1.getJSONObject(r0);	 Catch:{ JSONException -> 0x00d4 }
        r2 = com.ecjia.hamster.model.ao.a(r2);	 Catch:{ JSONException -> 0x00d4 }
        r4 = r5.b;	 Catch:{ JSONException -> 0x00d4 }
        r4.add(r2);	 Catch:{ JSONException -> 0x00d4 }
        r0 = r0 + 1;
        goto L_0x0153;
    L_0x0169:
        r0 = "paginated";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00d4 }
        r0 = com.ecjia.hamster.model.aa.a(r0);	 Catch:{ JSONException -> 0x00d4 }
        r5.e = r0;	 Catch:{ JSONException -> 0x00d4 }
        goto L_0x0045;
    L_0x0177:
        r1 = r5.r;	 Catch:{ JSONException -> 0x00d4 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00d4 }
        if (r1 != r2) goto L_0x0045;
    L_0x017f:
        r1 = "data";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00d4 }
        r2 = r5.d;	 Catch:{ JSONException -> 0x00d4 }
        r2.clear();	 Catch:{ JSONException -> 0x00d4 }
        r2 = "adsense";
        r1 = r1.optJSONArray(r2);	 Catch:{ JSONException -> 0x00d4 }
        if (r1 == 0) goto L_0x0045;
    L_0x0192:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00d4 }
        if (r2 <= 0) goto L_0x0045;
    L_0x0198:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00d4 }
        if (r0 >= r2) goto L_0x0045;
    L_0x019e:
        r2 = r5.d;	 Catch:{ JSONException -> 0x00d4 }
        r3 = r1.optJSONObject(r0);	 Catch:{ JSONException -> 0x00d4 }
        r3 = com.ecjia.hamster.model.c.a(r3);	 Catch:{ JSONException -> 0x00d4 }
        r2.add(r3);	 Catch:{ JSONException -> 0x00d4 }
        r0 = r0 + 1;
        goto L_0x0198;
    L_0x01ae:
        r0 = r5.r;	 Catch:{ JSONException -> 0x00d4 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x00d4 }
        if (r0 != r2) goto L_0x0045;
    L_0x01b6:
        r0 = "data";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00d4 }
        r0 = com.ecjia.hamster.model.ar.a(r0);	 Catch:{ JSONException -> 0x00d4 }
        r5.f = r0;	 Catch:{ JSONException -> 0x00d4 }
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.af.a(java.lang.String, java.lang.String):void");
    }
}
