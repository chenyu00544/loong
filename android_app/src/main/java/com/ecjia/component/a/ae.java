package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.aa;
import com.ecjia.hamster.model.ab;
import com.ecjia.hamster.model.ao;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.au;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaSearchNewModel */
public class ae extends e {
    public ArrayList<au> a = new ArrayList();
    public ArrayList<ao> b = new ArrayList();
    public aa c;
    public aa d;
    public int e = 0;
    private boolean f;
    private boolean g;

    /* compiled from: ECJiaSearchNewModel */
    class ae_1 implements OnCancelListener {
        final /* synthetic */ ae a;

        ae_1(ae aeVar) {
            this.a = aeVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaSearchNewModel */
    class ae_2 implements OnCancelListener {
        final /* synthetic */ ae a;

        ae_2(ae aeVar) {
            this.a = aeVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public ae(Context context) {
        super(context);
        this.s.a((b) this);
    }

    public void a(String str) {
        this.f = true;
        this.q = "goods/search";
        ab abVar = new ab();
        abVar.b(1);
        abVar.a(10);
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("keywords", str);
            jSONObject.put("area_id", e());
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new ae_1(this));
    }

    public void b(String str) {
        this.f = false;
        this.q = "goods/search";
        ab abVar = new ab();
        if (this.e == 1) {
            abVar.b((this.b.size() / 10) + 1);
        } else if (this.e == 2) {
            abVar.b((this.a.size() / 10) + 1);
        }
        abVar.a(10);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("keywords", str);
            jSONObject.put("area_id", e());
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void c(String str) {
        this.g = true;
        this.q = "seller/search";
        ab abVar = new ab();
        abVar.b(1);
        abVar.a(10);
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("keywords", str);
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new ae_2(this));
    }

    public void d(String str) {
        this.g = true;
        this.q = "seller/search";
        ab abVar = new ab();
        abVar.b((this.b.size() / 10) + 1);
        abVar.a(10);
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("keywords", str);
            jSONObject.put("pagination", abVar.a());
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
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00ff }
        r3.<init>(r8);	 Catch:{ JSONException -> 0x00ff }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00ff }
        r1.<init>();	 Catch:{ JSONException -> 0x00ff }
        r4 = "===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00ff }
        r1 = r1.append(r7);	 Catch:{ JSONException -> 0x00ff }
        r4 = "返回===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00ff }
        r4 = r3.toString();	 Catch:{ JSONException -> 0x00ff }
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00ff }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x00ff }
        com.ecjia.a.q.a(r1);	 Catch:{ JSONException -> 0x00ff }
        r1 = "status";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00ff }
        r1 = com.ecjia.hamster.model.ax.a(r1);	 Catch:{ JSONException -> 0x00ff }
        r6.r = r1;	 Catch:{ JSONException -> 0x00ff }
        r1 = -1;
        r4 = r7.hashCode();	 Catch:{ JSONException -> 0x00ff }
        switch(r4) {
            case -1466113407: goto L_0x0051;
            case 1324243512: goto L_0x005b;
            default: goto L_0x0042;
        };	 Catch:{ JSONException -> 0x00ff }
    L_0x0042:
        switch(r1) {
            case 0: goto L_0x0065;
            case 1: goto L_0x0125;
            default: goto L_0x0045;
        };	 Catch:{ JSONException -> 0x00ff }
    L_0x0045:
        r6.g();	 Catch:{ JSONException -> 0x00ff }
        r0 = r6.r;	 Catch:{ JSONException -> 0x00ff }
        r6.a(r7, r8, r0);	 Catch:{ JSONException -> 0x00ff }
    L_0x004d:
        r6.e(r8);
        return;
    L_0x0051:
        r4 = "goods/search";
        r4 = r7.equals(r4);	 Catch:{ JSONException -> 0x00ff }
        if (r4 == 0) goto L_0x0042;
    L_0x0059:
        r1 = r0;
        goto L_0x0042;
    L_0x005b:
        r4 = "seller/search";
        r4 = r7.equals(r4);	 Catch:{ JSONException -> 0x00ff }
        if (r4 == 0) goto L_0x0042;
    L_0x0063:
        r1 = r2;
        goto L_0x0042;
    L_0x0065:
        r1 = r6.r;	 Catch:{ JSONException -> 0x00ff }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00ff }
        if (r1 != r2) goto L_0x0045;
    L_0x006d:
        r1 = "data";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00ff }
        r1 = com.ecjia.hamster.model.am.a(r1);	 Catch:{ JSONException -> 0x00ff }
        r2 = "data";
        r2 = r3.optJSONObject(r2);	 Catch:{ JSONException -> 0x00ff }
        if (r2 == 0) goto L_0x00f1;
    L_0x007f:
        r4 = "result";
        r2 = r2.optJSONArray(r4);	 Catch:{ JSONException -> 0x00ff }
        r4 = "seller";
        r5 = r1.a();	 Catch:{ JSONException -> 0x00ff }
        r4 = r4.equals(r5);	 Catch:{ JSONException -> 0x00ff }
        if (r4 == 0) goto L_0x00bb;
    L_0x0091:
        r1 = 1;
        r6.e = r1;	 Catch:{ JSONException -> 0x00ff }
        r1 = r6.f;	 Catch:{ JSONException -> 0x00ff }
        if (r1 == 0) goto L_0x009d;
    L_0x0098:
        r1 = r6.b;	 Catch:{ JSONException -> 0x00ff }
        r1.clear();	 Catch:{ JSONException -> 0x00ff }
    L_0x009d:
        if (r2 == 0) goto L_0x00f1;
    L_0x009f:
        r1 = r2.length();	 Catch:{ JSONException -> 0x00ff }
        if (r1 <= 0) goto L_0x00f1;
    L_0x00a5:
        r1 = r2.length();	 Catch:{ JSONException -> 0x00ff }
        if (r0 >= r1) goto L_0x00f1;
    L_0x00ab:
        r1 = r2.getJSONObject(r0);	 Catch:{ JSONException -> 0x00ff }
        r1 = com.ecjia.hamster.model.ao.a(r1);	 Catch:{ JSONException -> 0x00ff }
        r4 = r6.b;	 Catch:{ JSONException -> 0x00ff }
        r4.add(r1);	 Catch:{ JSONException -> 0x00ff }
        r0 = r0 + 1;
        goto L_0x00a5;
    L_0x00bb:
        r4 = "goods";
        r1 = r1.a();	 Catch:{ JSONException -> 0x00ff }
        r1 = r4.equals(r1);	 Catch:{ JSONException -> 0x00ff }
        if (r1 == 0) goto L_0x00f1;
    L_0x00c7:
        r1 = 2;
        r6.e = r1;	 Catch:{ JSONException -> 0x00ff }
        r1 = r6.f;	 Catch:{ JSONException -> 0x00ff }
        if (r1 == 0) goto L_0x00d3;
    L_0x00ce:
        r1 = r6.a;	 Catch:{ JSONException -> 0x00ff }
        r1.clear();	 Catch:{ JSONException -> 0x00ff }
    L_0x00d3:
        if (r2 == 0) goto L_0x00f1;
    L_0x00d5:
        r1 = r2.length();	 Catch:{ JSONException -> 0x00ff }
        if (r1 <= 0) goto L_0x00f1;
    L_0x00db:
        r1 = r2.length();	 Catch:{ JSONException -> 0x00ff }
        if (r0 >= r1) goto L_0x00f1;
    L_0x00e1:
        r1 = r2.getJSONObject(r0);	 Catch:{ JSONException -> 0x00ff }
        r1 = com.ecjia.hamster.model.au.a(r1);	 Catch:{ JSONException -> 0x00ff }
        r4 = r6.a;	 Catch:{ JSONException -> 0x00ff }
        r4.add(r1);	 Catch:{ JSONException -> 0x00ff }
        r0 = r0 + 1;
        goto L_0x00db;
    L_0x00f1:
        r0 = "paginated";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00ff }
        r0 = com.ecjia.hamster.model.aa.a(r0);	 Catch:{ JSONException -> 0x00ff }
        r6.c = r0;	 Catch:{ JSONException -> 0x00ff }
        goto L_0x0045;
    L_0x00ff:
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
    L_0x0125:
        r1 = r6.r;	 Catch:{ JSONException -> 0x00ff }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00ff }
        if (r1 != r2) goto L_0x0045;
    L_0x012d:
        r1 = "data";
        r1 = r3.optJSONArray(r1);	 Catch:{ JSONException -> 0x00ff }
        r2 = r6.g;	 Catch:{ JSONException -> 0x00ff }
        if (r2 == 0) goto L_0x013c;
    L_0x0137:
        r2 = r6.b;	 Catch:{ JSONException -> 0x00ff }
        r2.clear();	 Catch:{ JSONException -> 0x00ff }
    L_0x013c:
        if (r1 == 0) goto L_0x015a;
    L_0x013e:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00ff }
        if (r2 <= 0) goto L_0x015a;
    L_0x0144:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00ff }
        if (r0 >= r2) goto L_0x015a;
    L_0x014a:
        r2 = r1.getJSONObject(r0);	 Catch:{ JSONException -> 0x00ff }
        r2 = com.ecjia.hamster.model.ao.a(r2);	 Catch:{ JSONException -> 0x00ff }
        r4 = r6.b;	 Catch:{ JSONException -> 0x00ff }
        r4.add(r2);	 Catch:{ JSONException -> 0x00ff }
        r0 = r0 + 1;
        goto L_0x0144;
    L_0x015a:
        r0 = "paginated";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00ff }
        r0 = com.ecjia.hamster.model.aa.a(r0);	 Catch:{ JSONException -> 0x00ff }
        r6.d = r0;	 Catch:{ JSONException -> 0x00ff }
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.ae.a(java.lang.String, java.lang.String):void");
    }
}
