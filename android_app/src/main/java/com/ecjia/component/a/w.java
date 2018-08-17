package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ECJia_PHOTO;
import com.ecjia.hamster.model.ap;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaMobileShakeModel */
public class w extends e {
    public String a;
    public a b;

    /* compiled from: ECJiaMobileShakeModel */
    class w_1 implements OnCancelListener {
        final /* synthetic */ w a;

        w_1(w wVar) {
            this.a = wVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaMobileShakeModel */
    public static class a {
        int a;
        String b;
        String c;
        a d;
        String e;
        b f;
        String g;

        /* compiled from: ECJiaMobileShakeModel */
        public static class a {
            String a;
            String b;
            String c;
            String d;
            String e;
            String f;
            int g;
            int h;
            String i;
            String j;

            public String a() {
                return this.b;
            }

            public String b() {
                return this.d;
            }

            public String c() {
                return this.e;
            }

            public String d() {
                return this.i;
            }

            public String e() {
                return this.j;
            }

            public static a a(JSONObject jSONObject) throws JSONException {
                if (jSONObject == null) {
                    return null;
                }
                a aVar = new a();
                aVar.a = jSONObject.optString("bonus_id");
                aVar.b = jSONObject.optString("bonus_name");
                aVar.c = jSONObject.optString("bonus_amount");
                aVar.d = jSONObject.optString("formatted_bonus_amount");
                aVar.e = jSONObject.optString("request_amount");
                aVar.f = jSONObject.optString("formatted_request_amount");
                aVar.g = jSONObject.optInt("start_date");
                aVar.h = jSONObject.optInt("end_date");
                aVar.i = jSONObject.optString("formatted_start_date");
                aVar.j = jSONObject.optString("formatted_end_date");
                return aVar;
            }

            public JSONObject f() throws JSONException {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("bonus_id", this.a);
                jSONObject.put("bonus_name", this.b);
                jSONObject.put("bonus_amount", this.c);
                jSONObject.put("formatted_bonus_amount", this.d);
                jSONObject.put("request_amount", this.e);
                jSONObject.put("formatted_request_amount", this.f);
                jSONObject.put("start_date", this.g);
                jSONObject.put("end_date", this.h);
                jSONObject.put("formatted_start_date", this.i);
                jSONObject.put("formatted_end_date", this.j);
                return jSONObject;
            }
        }

        /* compiled from: ECJiaMobileShakeModel */
        public static class b {
            String a;
            String b;
            String c;
            String d;
            String e;
            ECJia_PHOTO f;

            public String a() {
                return this.a;
            }

            public String b() {
                return this.b;
            }

            public String c() {
                return this.d;
            }

            public ECJia_PHOTO d() {
                return this.f;
            }

            public static b a(JSONObject jSONObject) throws JSONException {
                if (jSONObject == null) {
                    return null;
                }
                b bVar = new b();
                bVar.a = jSONObject.optString("goods_id");
                bVar.b = jSONObject.optString("name");
                bVar.c = jSONObject.optString("market_price");
                bVar.d = jSONObject.optString("shop_price");
                bVar.e = jSONObject.optString("promote_price");
                bVar.f = ECJia_PHOTO.fromJson(jSONObject.optJSONObject("img"));
                return bVar;
            }
        }

        public int a() {
            return this.a;
        }

        public void a(int i) {
            this.a = i;
        }

        public String b() {
            return this.b;
        }

        public void a(String str) {
            this.b = str;
        }

        public void b(String str) {
            this.e = str;
        }

        public String c() {
            return this.c;
        }

        public void c(String str) {
            this.c = str;
        }

        public a d() {
            return this.d;
        }

        public void a(a aVar) {
            this.d = aVar;
        }

        public b e() {
            return this.f;
        }

        public void a(b bVar) {
            this.f = bVar;
        }

        public String f() {
            return this.g;
        }

        public void d(String str) {
            this.g = str;
        }
    }

    public w(Context context) {
        super(context);
        this.s.a((b) this);
    }

    public void a() {
        this.l.show();
        this.q = "mobile/shake";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new w_1(this));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r6, java.lang.String r7) {
        /*
        r5 = this;
        r1 = -1;
        r0 = 0;
        super.a(r6, r7);
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x006f }
        r3.<init>(r7);	 Catch:{ JSONException -> 0x006f }
        r2 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x006f }
        r2.<init>();	 Catch:{ JSONException -> 0x006f }
        r4 = "===";
        r2 = r2.append(r4);	 Catch:{ JSONException -> 0x006f }
        r2 = r2.append(r6);	 Catch:{ JSONException -> 0x006f }
        r4 = "返回===";
        r2 = r2.append(r4);	 Catch:{ JSONException -> 0x006f }
        r4 = r3.toString();	 Catch:{ JSONException -> 0x006f }
        r2 = r2.append(r4);	 Catch:{ JSONException -> 0x006f }
        r2 = r2.toString();	 Catch:{ JSONException -> 0x006f }
        com.ecjia.a.q.a(r2);	 Catch:{ JSONException -> 0x006f }
        r2 = "status";
        r2 = r3.optJSONObject(r2);	 Catch:{ JSONException -> 0x006f }
        r2 = com.ecjia.hamster.model.ax.a(r2);	 Catch:{ JSONException -> 0x006f }
        r5.r = r2;	 Catch:{ JSONException -> 0x006f }
        r2 = r6.hashCode();	 Catch:{ JSONException -> 0x006f }
        switch(r2) {
            case 847848281: goto L_0x0051;
            default: goto L_0x0041;
        };	 Catch:{ JSONException -> 0x006f }
    L_0x0041:
        r2 = r1;
    L_0x0042:
        switch(r2) {
            case 0: goto L_0x005b;
            default: goto L_0x0045;
        };	 Catch:{ JSONException -> 0x006f }
    L_0x0045:
        r5.g();	 Catch:{ JSONException -> 0x006f }
        r0 = r5.r;	 Catch:{ JSONException -> 0x006f }
        r5.a(r6, r7, r0);	 Catch:{ JSONException -> 0x006f }
    L_0x004d:
        r5.e(r7);
        return;
    L_0x0051:
        r2 = "mobile/shake";
        r2 = r6.equals(r2);	 Catch:{ JSONException -> 0x006f }
        if (r2 == 0) goto L_0x0041;
    L_0x0059:
        r2 = r0;
        goto L_0x0042;
    L_0x005b:
        r2 = "data";
        r2 = r3.optJSONObject(r2);	 Catch:{ JSONException -> 0x006f }
        r4 = "data";
        r3 = r3.optJSONObject(r4);	 Catch:{ JSONException -> 0x006f }
        if (r3 != 0) goto L_0x0094;
    L_0x0069:
        r0 = "摇一摇返回值错误";
        com.ecjia.a.q.b(r0);	 Catch:{ JSONException -> 0x006f }
        goto L_0x0045;
    L_0x006f:
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
    L_0x0094:
        r3 = new com.ecjia.component.a.w$a;	 Catch:{ JSONException -> 0x006f }
        r3.<init>();	 Catch:{ JSONException -> 0x006f }
        r5.b = r3;	 Catch:{ JSONException -> 0x006f }
        r3 = r5.b;	 Catch:{ JSONException -> 0x006f }
        r4 = r5.m;	 Catch:{ JSONException -> 0x006f }
        r4 = r4.e();	 Catch:{ JSONException -> 0x006f }
        r4 = r4.m();	 Catch:{ JSONException -> 0x006f }
        r4 = java.lang.Integer.parseInt(r4);	 Catch:{ JSONException -> 0x006f }
        r3.a(r4);	 Catch:{ JSONException -> 0x006f }
        r3 = "type";
        r3 = r2.optString(r3);	 Catch:{ JSONException -> 0x006f }
        r5.a = r3;	 Catch:{ JSONException -> 0x006f }
        r3 = r5.b;	 Catch:{ JSONException -> 0x006f }
        r4 = r5.a;	 Catch:{ JSONException -> 0x006f }
        r3.c(r4);	 Catch:{ JSONException -> 0x006f }
        r3 = r5.a;	 Catch:{ JSONException -> 0x006f }
        r4 = r3.hashCode();	 Catch:{ JSONException -> 0x006f }
        switch(r4) {
            case 93921311: goto L_0x00e8;
            case 98539350: goto L_0x00f1;
            case 570086828: goto L_0x00fb;
            case 2129323981: goto L_0x0105;
            default: goto L_0x00c6;
        };	 Catch:{ JSONException -> 0x006f }
    L_0x00c6:
        r0 = r1;
    L_0x00c7:
        switch(r0) {
            case 0: goto L_0x00cc;
            case 1: goto L_0x010f;
            case 2: goto L_0x0120;
            case 3: goto L_0x013e;
            default: goto L_0x00ca;
        };	 Catch:{ JSONException -> 0x006f }
    L_0x00ca:
        goto L_0x0045;
    L_0x00cc:
        r0 = r5.b;	 Catch:{ JSONException -> 0x006f }
        r1 = "bonus";
        r1 = r2.optJSONObject(r1);	 Catch:{ JSONException -> 0x006f }
        r1 = com.ecjia.component.a.w.a.a.a(r1);	 Catch:{ JSONException -> 0x006f }
        r0.a(r1);	 Catch:{ JSONException -> 0x006f }
        r0 = r5.n;	 Catch:{ JSONException -> 0x006f }
        r0 = com.ecjia.a.c.a.a(r0);	 Catch:{ JSONException -> 0x006f }
        r1 = r5.b;	 Catch:{ JSONException -> 0x006f }
        r0.a(r1);	 Catch:{ JSONException -> 0x006f }
        goto L_0x0045;
    L_0x00e8:
        r4 = "bonus";
        r3 = r3.equals(r4);	 Catch:{ JSONException -> 0x006f }
        if (r3 == 0) goto L_0x00c6;
    L_0x00f0:
        goto L_0x00c7;
    L_0x00f1:
        r0 = "goods";
        r0 = r3.equals(r0);	 Catch:{ JSONException -> 0x006f }
        if (r0 == 0) goto L_0x00c6;
    L_0x00f9:
        r0 = 1;
        goto L_0x00c7;
    L_0x00fb:
        r0 = "integral";
        r0 = r3.equals(r0);	 Catch:{ JSONException -> 0x006f }
        if (r0 == 0) goto L_0x00c6;
    L_0x0103:
        r0 = 2;
        goto L_0x00c7;
    L_0x0105:
        r0 = "nothing";
        r0 = r3.equals(r0);	 Catch:{ JSONException -> 0x006f }
        if (r0 == 0) goto L_0x00c6;
    L_0x010d:
        r0 = 3;
        goto L_0x00c7;
    L_0x010f:
        r0 = r5.b;	 Catch:{ JSONException -> 0x006f }
        r1 = "goods";
        r1 = r2.optJSONObject(r1);	 Catch:{ JSONException -> 0x006f }
        r1 = com.ecjia.component.a.w.a.b.a(r1);	 Catch:{ JSONException -> 0x006f }
        r0.a(r1);	 Catch:{ JSONException -> 0x006f }
        goto L_0x0045;
    L_0x0120:
        r0 = r5.b;	 Catch:{ JSONException -> 0x006f }
        r1 = "integral";
        r1 = r2.optJSONObject(r1);	 Catch:{ JSONException -> 0x006f }
        r2 = "integral";
        r1 = r1.optString(r2);	 Catch:{ JSONException -> 0x006f }
        r0.d(r1);	 Catch:{ JSONException -> 0x006f }
        r0 = r5.n;	 Catch:{ JSONException -> 0x006f }
        r0 = com.ecjia.a.c.a.a(r0);	 Catch:{ JSONException -> 0x006f }
        r1 = r5.b;	 Catch:{ JSONException -> 0x006f }
        r0.a(r1);	 Catch:{ JSONException -> 0x006f }
        goto L_0x0045;
    L_0x013e:
        r0 = r5.b;	 Catch:{ JSONException -> 0x006f }
        r1 = "nothing";
        r1 = r2.optJSONObject(r1);	 Catch:{ JSONException -> 0x006f }
        r2 = "message";
        r1 = r1.optString(r2);	 Catch:{ JSONException -> 0x006f }
        r0.b(r1);	 Catch:{ JSONException -> 0x006f }
        r0 = r5.r;	 Catch:{ JSONException -> 0x006f }
        r1 = 0;
        r0.b(r1);	 Catch:{ JSONException -> 0x006f }
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.w.a(java.lang.String, java.lang.String):void");
    }
}
