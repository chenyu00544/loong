package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.aa;
import com.ecjia.hamster.model.ab;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.l;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaConsultModel */
public class k extends e {
    public aa a;
    public ArrayList<l> b = new ArrayList();
    private boolean c;

    /* compiled from: ECJiaConsultModel */
    class k_1 implements OnCancelListener {
        final /* synthetic */ k a;

        k_1(k kVar) {
            this.a = kVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaConsultModel */
    class k_2 implements OnCancelListener {
        final /* synthetic */ k a;

        k_2(k kVar) {
            this.a = kVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaConsultModel */
    class k_3 implements OnCancelListener {
        final /* synthetic */ k a;

        k_3(k kVar) {
            this.a = kVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaConsultModel */
    class k_4 implements OnCancelListener {
        final /* synthetic */ k a;

        k_4(k kVar) {
            this.a = kVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public k(Context context) {
        super(context);
        this.s.a((b) this);
    }

    public void b(String str, String str2) {
        this.c = true;
        this.q = "feedback/list";
        this.l.show();
        ap c = ap.c();
        ab abVar = new ab();
        abVar.b(1);
        abVar.a(10);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("pagination", abVar.a());
            if (str != null) {
                jSONObject.put("object_id", str);
            }
            jSONObject.put("object_type", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new k_1(this));
    }

    public void c(String str, String str2) {
        this.c = false;
        this.q = "feedback/list";
        this.l.show();
        ap c = ap.c();
        ab abVar = new ab();
        abVar.b((this.b.size() / 10) + 1);
        abVar.a(10);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("pagination", abVar.a());
            if (str != null) {
                jSONObject.put("object_id", str);
            }
            jSONObject.put("object_type", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new k_2(this));
    }

    public void a(String str, String str2, String str3, String str4) {
        this.q = "feedback/create";
        ap c = ap.c();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            if (str != null) {
                jSONObject.put("object_id", str);
            }
            jSONObject.put("object_type", str2);
            jSONObject.put("user_name", str3);
            jSONObject.put("content", str4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new k_3(this));
    }

    public void a(String str, String str2, String str3) {
        this.q = "feedback/create";
        ap c = ap.c();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("object_type", str);
            jSONObject.put("content", str2);
            jSONObject.put("contact", str3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new k_4(this));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r6, java.lang.String r7) {
        /*
        r5 = this;
        r2 = 1;
        r0 = 0;
        super.a(r6, r7);
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00a1 }
        r3.<init>(r7);	 Catch:{ JSONException -> 0x00a1 }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00a1 }
        r1.<init>();	 Catch:{ JSONException -> 0x00a1 }
        r4 = "===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00a1 }
        r1 = r1.append(r6);	 Catch:{ JSONException -> 0x00a1 }
        r4 = "返回===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00a1 }
        r4 = r3.toString();	 Catch:{ JSONException -> 0x00a1 }
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00a1 }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x00a1 }
        com.ecjia.a.q.a(r1);	 Catch:{ JSONException -> 0x00a1 }
        r1 = "status";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00a1 }
        r1 = com.ecjia.hamster.model.ax.a(r1);	 Catch:{ JSONException -> 0x00a1 }
        r5.r = r1;	 Catch:{ JSONException -> 0x00a1 }
        r1 = -1;
        r4 = r6.hashCode();	 Catch:{ JSONException -> 0x00a1 }
        switch(r4) {
            case -1994227480: goto L_0x0051;
            case -1146976570: goto L_0x005b;
            default: goto L_0x0042;
        };	 Catch:{ JSONException -> 0x00a1 }
    L_0x0042:
        switch(r1) {
            case 0: goto L_0x0065;
            default: goto L_0x0045;
        };	 Catch:{ JSONException -> 0x00a1 }
    L_0x0045:
        r5.g();	 Catch:{ JSONException -> 0x00a1 }
        r0 = r5.r;	 Catch:{ JSONException -> 0x00a1 }
        r5.a(r6, r7, r0);	 Catch:{ JSONException -> 0x00a1 }
    L_0x004d:
        r5.e(r7);
        return;
    L_0x0051:
        r4 = "feedback/list";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00a1 }
        if (r4 == 0) goto L_0x0042;
    L_0x0059:
        r1 = r0;
        goto L_0x0042;
    L_0x005b:
        r4 = "feedback/create";
        r4 = r6.equals(r4);	 Catch:{ JSONException -> 0x00a1 }
        if (r4 == 0) goto L_0x0042;
    L_0x0063:
        r1 = r2;
        goto L_0x0042;
    L_0x0065:
        r1 = r5.r;	 Catch:{ JSONException -> 0x00a1 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00a1 }
        if (r1 != r2) goto L_0x0045;
    L_0x006d:
        r1 = "data";
        r1 = r3.optJSONArray(r1);	 Catch:{ JSONException -> 0x00a1 }
        r2 = r5.c;	 Catch:{ JSONException -> 0x00a1 }
        if (r2 == 0) goto L_0x009a;
    L_0x0077:
        r2 = r5.b;	 Catch:{ JSONException -> 0x00a1 }
        r2.clear();	 Catch:{ JSONException -> 0x00a1 }
    L_0x007c:
        if (r1 == 0) goto L_0x00c6;
    L_0x007e:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00a1 }
        if (r2 <= 0) goto L_0x00c6;
    L_0x0084:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00a1 }
        if (r0 >= r2) goto L_0x00c6;
    L_0x008a:
        r2 = r1.getJSONObject(r0);	 Catch:{ JSONException -> 0x00a1 }
        r2 = com.ecjia.hamster.model.l.a(r2);	 Catch:{ JSONException -> 0x00a1 }
        r4 = r5.b;	 Catch:{ JSONException -> 0x00a1 }
        r4.add(r2);	 Catch:{ JSONException -> 0x00a1 }
        r0 = r0 + 1;
        goto L_0x0084;
    L_0x009a:
        r2 = r5.r;	 Catch:{ JSONException -> 0x00a1 }
        r4 = 2;
        r2.b(r4);	 Catch:{ JSONException -> 0x00a1 }
        goto L_0x007c;
    L_0x00a1:
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
    L_0x00c6:
        r0 = "paginated";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x00a1 }
        r0 = com.ecjia.hamster.model.aa.a(r0);	 Catch:{ JSONException -> 0x00a1 }
        r5.a = r0;	 Catch:{ JSONException -> 0x00a1 }
        goto L_0x0045;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.k.a(java.lang.String, java.lang.String):void");
    }
}
