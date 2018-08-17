package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ECJia_INVITE_RECORD;
import com.ecjia.hamster.model.ECJia_INVITE_REWARD;
import com.ecjia.hamster.model.ECJia_INVITE_TOTAL;
import com.ecjia.hamster.model.ECJia_USER_INVITE;
import com.ecjia.hamster.model.aa;
import com.ecjia.hamster.model.ab;
import com.ecjia.hamster.model.ap;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaInviteModel */
public class t extends e {
    public aa a = new aa();
    public ArrayList<ECJia_INVITE_RECORD> b = new ArrayList();
    public ArrayList<ECJia_INVITE_REWARD> c = new ArrayList();
    public ECJia_INVITE_TOTAL d = new ECJia_INVITE_TOTAL();
    public ECJia_USER_INVITE e = new ECJia_USER_INVITE();
    public String f;
    private final int g = 10;
    private boolean h;

    /* compiled from: ECJiaInviteModel */
    class t_1 implements OnCancelListener {
        final /* synthetic */ t a;

        t_1(t tVar) {
            this.a = tVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaInviteModel */
    class t_2 implements OnCancelListener {
        final /* synthetic */ t a;

        t_2(t tVar) {
            this.a = tVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaInviteModel */
    class t_3 implements OnCancelListener {
        final /* synthetic */ t a;

        t_3(t tVar) {
            this.a = tVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public t(Context context) {
        super(context);
        this.s.a((b) this);
    }

    public void a() {
        this.q = "invite/reward";
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
        this.l.setOnCancelListener(new t_1(this));
    }

    public void a(String str, boolean z) {
        this.h = true;
        this.q = "invite/record";
        if (z) {
            this.l.show();
        }
        ab abVar = new ab();
        abVar.b(1);
        abVar.a(10);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("date", str);
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new t_2(this));
    }

    public void a(String str) {
        this.h = false;
        this.q = "invite/record";
        ab abVar = new ab();
        abVar.b((this.b.size() / 10) + 1);
        abVar.a(10);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("date", str);
            jSONObject.put("pagination", abVar.a());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void b() {
        this.q = "invite/user";
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
        this.l.setOnCancelListener(new t_3(this));
    }

    public void b(String str) {
        this.q = "invite/validate";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("mobile", str);
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
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00ff }
        r3.<init>(r7);	 Catch:{ JSONException -> 0x00ff }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00ff }
        r1.<init>();	 Catch:{ JSONException -> 0x00ff }
        r4 = "===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00ff }
        r1 = r1.append(r6);	 Catch:{ JSONException -> 0x00ff }
        r4 = "返回===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00ff }
        r4 = r3.toString();	 Catch:{ JSONException -> 0x00ff }
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00ff }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x00ff }
        com.ecjia.a.q.a(r1);	 Catch:{ JSONException -> 0x00ff }
        r1 = "status";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00ff }
        r1 = com.ecjia.hamster.model.ax.a(r1);	 Catch:{ JSONException -> 0x00ff }
        r5.r = r1;	 Catch:{ JSONException -> 0x00ff }
        r1 = -1;
        r4 = r6.hashCode();	 Catch:{ JSONException -> 0x00ff }
        switch(r4) {
            case -1627336169: goto L_0x005b;
            case -1626753803: goto L_0x0051;
            case -1547957487: goto L_0x0065;
            case -1123998372: goto L_0x006f;
            default: goto L_0x0042;
        };	 Catch:{ JSONException -> 0x00ff }
    L_0x0042:
        switch(r1) {
            case 0: goto L_0x0079;
            case 1: goto L_0x00bc;
            case 2: goto L_0x0125;
            case 3: goto L_0x013b;
            default: goto L_0x0045;
        };	 Catch:{ JSONException -> 0x00ff }
    L_0x0045:
        r5.g();	 Catch:{ JSONException -> 0x00ff }
        r0 = r5.r;	 Catch:{ JSONException -> 0x00ff }
        r5.a(r6, r7, r0);	 Catch:{ JSONException -> 0x00ff }
    L_0x004d:
        r5.e(r7);
        return;
    L_0x0051:
        r4 = "invite/reward";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00ff }
        if (r4 == 0) goto L_0x0042;
    L_0x0059:
        r1 = r0;
        goto L_0x0042;
    L_0x005b:
        r4 = "invite/record";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00ff }
        if (r4 == 0) goto L_0x0042;
    L_0x0063:
        r1 = r2;
        goto L_0x0042;
    L_0x0065:
        r4 = "invite/user";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00ff }
        if (r4 == 0) goto L_0x0042;
    L_0x006d:
        r1 = 2;
        goto L_0x0042;
    L_0x006f:
        r4 = "invite/validate";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00ff }
        if (r4 == 0) goto L_0x0042;
    L_0x0077:
        r1 = 3;
        goto L_0x0042;
    L_0x0079:
        r1 = r5.r;	 Catch:{ JSONException -> 0x00ff }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00ff }
        if (r1 != r2) goto L_0x0045;
    L_0x0081:
        r1 = "data";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00ff }
        r2 = "invite_total";
        r2 = r1.optJSONObject(r2);	 Catch:{ JSONException -> 0x00ff }
        r2 = com.ecjia.hamster.model.ECJia_INVITE_TOTAL.fromJson(r2);	 Catch:{ JSONException -> 0x00ff }
        r5.d = r2;	 Catch:{ JSONException -> 0x00ff }
        r2 = "invite_record";
        r1 = r1.optJSONArray(r2);	 Catch:{ JSONException -> 0x00ff }
        r2 = r5.c;	 Catch:{ JSONException -> 0x00ff }
        r2.clear();	 Catch:{ JSONException -> 0x00ff }
        if (r1 == 0) goto L_0x0045;
    L_0x00a0:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00ff }
        if (r2 <= 0) goto L_0x0045;
    L_0x00a6:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00ff }
        if (r0 >= r2) goto L_0x0045;
    L_0x00ac:
        r2 = r1.getJSONObject(r0);	 Catch:{ JSONException -> 0x00ff }
        r2 = com.ecjia.hamster.model.ECJia_INVITE_REWARD.fromJson(r2);	 Catch:{ JSONException -> 0x00ff }
        r3 = r5.c;	 Catch:{ JSONException -> 0x00ff }
        r3.add(r2);	 Catch:{ JSONException -> 0x00ff }
        r0 = r0 + 1;
        goto L_0x00a6;
    L_0x00bc:
        r1 = r5.r;	 Catch:{ JSONException -> 0x00ff }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00ff }
        if (r1 != r2) goto L_0x00f1;
    L_0x00c4:
        r1 = "data";
        r1 = r3.optJSONArray(r1);	 Catch:{ JSONException -> 0x00ff }
        r2 = r5.h;	 Catch:{ JSONException -> 0x00ff }
        if (r2 == 0) goto L_0x00d3;
    L_0x00ce:
        r2 = r5.b;	 Catch:{ JSONException -> 0x00ff }
        r2.clear();	 Catch:{ JSONException -> 0x00ff }
    L_0x00d3:
        if (r1 == 0) goto L_0x00f1;
    L_0x00d5:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00ff }
        if (r2 <= 0) goto L_0x00f1;
    L_0x00db:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00ff }
        if (r0 >= r2) goto L_0x00f1;
    L_0x00e1:
        r2 = r1.getJSONObject(r0);	 Catch:{ JSONException -> 0x00ff }
        r2 = com.ecjia.hamster.model.ECJia_INVITE_RECORD.fromJson(r2);	 Catch:{ JSONException -> 0x00ff }
        r4 = r5.b;	 Catch:{ JSONException -> 0x00ff }
        r4.add(r2);	 Catch:{ JSONException -> 0x00ff }
        r0 = r0 + 1;
        goto L_0x00db;
    L_0x00f1:
        r0 = "paginated";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00ff }
        r0 = com.ecjia.hamster.model.aa.a(r0);	 Catch:{ JSONException -> 0x00ff }
        r5.a = r0;	 Catch:{ JSONException -> 0x00ff }
        goto L_0x0045;
    L_0x00ff:
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
    L_0x0125:
        r0 = r5.r;	 Catch:{ JSONException -> 0x00ff }
        r0 = r0.b();	 Catch:{ JSONException -> 0x00ff }
        if (r0 != r2) goto L_0x0045;
    L_0x012d:
        r0 = "data";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00ff }
        r0 = com.ecjia.hamster.model.ECJia_USER_INVITE.fromJson(r0);	 Catch:{ JSONException -> 0x00ff }
        r5.e = r0;	 Catch:{ JSONException -> 0x00ff }
        goto L_0x0045;
    L_0x013b:
        r0 = r5.r;	 Catch:{ JSONException -> 0x00ff }
        r0 = r0.b();	 Catch:{ JSONException -> 0x00ff }
        if (r0 != r2) goto L_0x0045;
    L_0x0143:
        r0 = "data";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00ff }
        r1 = "invite_code";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x00ff }
        r5.f = r1;	 Catch:{ JSONException -> 0x00ff }
        r0 = com.ecjia.hamster.model.ECJia_USER_INVITE.fromJson(r0);	 Catch:{ JSONException -> 0x00ff }
        r5.e = r0;	 Catch:{ JSONException -> 0x00ff }
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.t.a(java.lang.String, java.lang.String):void");
    }
}
