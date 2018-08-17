package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ab;
import com.ecjia.hamster.model.aj;
import com.ecjia.hamster.model.ap;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaRechargeModel */
public class aa extends e {
    public String a;
    public ArrayList<aj> b = new ArrayList();
    public com.ecjia.hamster.model.aa c;
    public String d;
    public String e;
    private boolean f;

    /* compiled from: ECJiaRechargeModel */
    class aa_1 implements OnCancelListener {
        final /* synthetic */ aa a;

        aa_1(aa aaVar) {
            this.a = aaVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaRechargeModel */
    class aa_2 implements OnCancelListener {
        final /* synthetic */ aa a;

        aa_2(aa aaVar) {
            this.a = aaVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaRechargeModel */
    class aa_3 implements OnCancelListener {
        final /* synthetic */ aa a;

        aa_3(aa aaVar) {
            this.a = aaVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public aa(Context context) {
        super(context);
        this.s.a((b) this);
    }

    public void a(String str, String str2, String str3, String str4) {
        this.q = "user/account/deposit";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("amount", str);
            jSONObject.put("note", str2);
            jSONObject.put("payment_id", str3);
            jSONObject.put("account_id", str4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new aa_1(this));
    }

    public void b(String str, String str2) {
        this.q = "user/account/raply";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("amount", str);
            jSONObject.put("note", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new aa_2(this));
    }

    public void a(String str) {
        this.f = true;
        this.q = "user/account/record";
        ab abVar = new ab();
        abVar.b(1);
        abVar.a(10);
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("type", str);
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new aa_3(this));
    }

    public void b(String str) {
        this.f = true;
        this.q = "user/account/record";
        ab abVar = new ab();
        abVar.b((this.b.size() / 10) + 1);
        abVar.a(10);
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("process_type", str);
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void c(String str) {
        this.q = "user/account/cancel";
        if (!this.l.isShowing()) {
            this.l.show();
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("account_id", str);
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
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x009e }
        r3.<init>(r7);	 Catch:{ JSONException -> 0x009e }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x009e }
        r1.<init>();	 Catch:{ JSONException -> 0x009e }
        r4 = "===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x009e }
        r1 = r1.append(r6);	 Catch:{ JSONException -> 0x009e }
        r4 = "返回===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x009e }
        r4 = r3.toString();	 Catch:{ JSONException -> 0x009e }
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x009e }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x009e }
        com.ecjia.a.q.a(r1);	 Catch:{ JSONException -> 0x009e }
        r1 = "status";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x009e }
        r1 = com.ecjia.hamster.model.ax.a(r1);	 Catch:{ JSONException -> 0x009e }
        r5.r = r1;	 Catch:{ JSONException -> 0x009e }
        r1 = -1;
        r4 = r6.hashCode();	 Catch:{ JSONException -> 0x009e }
        switch(r4) {
            case -1121250696: goto L_0x0051;
            case 463039432: goto L_0x005b;
            case 1039814496: goto L_0x006f;
            case 1472630071: goto L_0x0065;
            default: goto L_0x0042;
        };	 Catch:{ JSONException -> 0x009e }
    L_0x0042:
        switch(r1) {
            case 0: goto L_0x0079;
            case 1: goto L_0x00d5;
            case 2: goto L_0x00f9;
            case 3: goto L_0x014c;
            default: goto L_0x0045;
        };	 Catch:{ JSONException -> 0x009e }
    L_0x0045:
        r5.g();	 Catch:{ JSONException -> 0x009e }
        r0 = r5.r;	 Catch:{ JSONException -> 0x009e }
        r5.a(r6, r7, r0);	 Catch:{ JSONException -> 0x009e }
    L_0x004d:
        r5.e(r7);
        return;
    L_0x0051:
        r4 = "user/account/deposit";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x009e }
        if (r4 == 0) goto L_0x0042;
    L_0x0059:
        r1 = r0;
        goto L_0x0042;
    L_0x005b:
        r4 = "user/account/raply";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x009e }
        if (r4 == 0) goto L_0x0042;
    L_0x0063:
        r1 = r2;
        goto L_0x0042;
    L_0x0065:
        r4 = "user/account/record";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x009e }
        if (r4 == 0) goto L_0x0042;
    L_0x006d:
        r1 = 2;
        goto L_0x0042;
    L_0x006f:
        r4 = "user/account/cancel";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x009e }
        if (r4 == 0) goto L_0x0042;
    L_0x0077:
        r1 = 3;
        goto L_0x0042;
    L_0x0079:
        r0 = r5.r;	 Catch:{ JSONException -> 0x009e }
        r0 = r0.b();	 Catch:{ JSONException -> 0x009e }
        if (r0 != r2) goto L_0x00c3;
    L_0x0081:
        r0 = "data";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x009e }
        r1 = "payment";
        r0 = r0.optJSONObject(r1);	 Catch:{ JSONException -> 0x009e }
        r1 = "account_id";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x009e }
        r5.d = r1;	 Catch:{ JSONException -> 0x009e }
        r1 = "payment_id";
        r0 = r0.optString(r1);	 Catch:{ JSONException -> 0x009e }
        r5.e = r0;	 Catch:{ JSONException -> 0x009e }
        goto L_0x0045;
    L_0x009e:
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
    L_0x00c3:
        r0 = new com.ecjia.component.view.k;	 Catch:{ JSONException -> 0x009e }
        r1 = r5.n;	 Catch:{ JSONException -> 0x009e }
        r2 = r5.r;	 Catch:{ JSONException -> 0x009e }
        r2 = r2.d();	 Catch:{ JSONException -> 0x009e }
        r0.<init>(r1, r2);	 Catch:{ JSONException -> 0x009e }
        r0.a();	 Catch:{ JSONException -> 0x009e }
        goto L_0x0045;
    L_0x00d5:
        r0 = r5.r;	 Catch:{ JSONException -> 0x009e }
        r0 = r0.b();	 Catch:{ JSONException -> 0x009e }
        if (r0 != r2) goto L_0x00e7;
    L_0x00dd:
        r0 = "data";
        r0 = r3.optString(r0);	 Catch:{ JSONException -> 0x009e }
        r5.a = r0;	 Catch:{ JSONException -> 0x009e }
        goto L_0x0045;
    L_0x00e7:
        r0 = new com.ecjia.component.view.k;	 Catch:{ JSONException -> 0x009e }
        r1 = r5.n;	 Catch:{ JSONException -> 0x009e }
        r2 = r5.r;	 Catch:{ JSONException -> 0x009e }
        r2 = r2.d();	 Catch:{ JSONException -> 0x009e }
        r0.<init>(r1, r2);	 Catch:{ JSONException -> 0x009e }
        r0.a();	 Catch:{ JSONException -> 0x009e }
        goto L_0x0045;
    L_0x00f9:
        r1 = r5.r;	 Catch:{ JSONException -> 0x009e }
        r1 = r1.b();	 Catch:{ JSONException -> 0x009e }
        if (r1 != r2) goto L_0x012e;
    L_0x0101:
        r1 = "data";
        r1 = r3.optJSONArray(r1);	 Catch:{ JSONException -> 0x009e }
        r2 = r5.f;	 Catch:{ JSONException -> 0x009e }
        if (r2 == 0) goto L_0x0110;
    L_0x010b:
        r2 = r5.b;	 Catch:{ JSONException -> 0x009e }
        r2.clear();	 Catch:{ JSONException -> 0x009e }
    L_0x0110:
        if (r1 == 0) goto L_0x013e;
    L_0x0112:
        r2 = r1.length();	 Catch:{ JSONException -> 0x009e }
        if (r2 <= 0) goto L_0x013e;
    L_0x0118:
        r2 = r1.length();	 Catch:{ JSONException -> 0x009e }
        if (r0 >= r2) goto L_0x013e;
    L_0x011e:
        r2 = r1.getJSONObject(r0);	 Catch:{ JSONException -> 0x009e }
        r4 = r5.b;	 Catch:{ JSONException -> 0x009e }
        r2 = com.ecjia.hamster.model.aj.a(r2);	 Catch:{ JSONException -> 0x009e }
        r4.add(r2);	 Catch:{ JSONException -> 0x009e }
        r0 = r0 + 1;
        goto L_0x0118;
    L_0x012e:
        r0 = new com.ecjia.component.view.k;	 Catch:{ JSONException -> 0x009e }
        r1 = r5.n;	 Catch:{ JSONException -> 0x009e }
        r2 = r5.r;	 Catch:{ JSONException -> 0x009e }
        r2 = r2.d();	 Catch:{ JSONException -> 0x009e }
        r0.<init>(r1, r2);	 Catch:{ JSONException -> 0x009e }
        r0.a();	 Catch:{ JSONException -> 0x009e }
    L_0x013e:
        r0 = "paginated";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x009e }
        r0 = com.ecjia.hamster.model.aa.a(r0);	 Catch:{ JSONException -> 0x009e }
        r5.c = r0;	 Catch:{ JSONException -> 0x009e }
        goto L_0x0045;
    L_0x014c:
        r0 = r5.r;	 Catch:{ JSONException -> 0x009e }
        r0 = r0.b();	 Catch:{ JSONException -> 0x009e }
        if (r0 != 0) goto L_0x0045;
    L_0x0154:
        r0 = new com.ecjia.component.view.k;	 Catch:{ JSONException -> 0x009e }
        r1 = r5.n;	 Catch:{ JSONException -> 0x009e }
        r2 = "取消失败！";
        r0.<init>(r1, r2);	 Catch:{ JSONException -> 0x009e }
        r0.a();	 Catch:{ JSONException -> 0x009e }
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.aa.a(java.lang.String, java.lang.String):void");
    }
}
