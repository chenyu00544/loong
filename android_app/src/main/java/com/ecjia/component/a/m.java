package com.ecjia.component.a;

import android.content.Context;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ECJia_DEVICE;
import com.ecjia.hamster.model.a;
import com.ecjia.hamster.model.ap;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaGetPasswordModel */
public class m extends e {
    private static m c;
    public a a;
    private Context b;
    private ap d = new ap();

    public m(Context context) {
        super(context);
        this.b = context;
        c = this;
        this.s.a((b) this);
    }

    public static m a() {
        return c;
    }

    public void b() {
        this.q = "shop/token";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device", this.o.toJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void b(String str, String str2) {
        this.q = "user/forget_password";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("session", this.d.d());
            jSONObject.put("type", str);
            jSONObject.put("value", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void a(String str, String str2, String str3) {
        this.q = "validate/forget_password";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("session", this.d.d());
            jSONObject.put("type", str);
            jSONObject.put("value", str2);
            jSONObject.put("code", str3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void b(String str, String str2, String str3) {
        this.q = "user/reset_password";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device", ECJia_DEVICE.getInstance().toJson());
            jSONObject.put("session", this.d.d());
            jSONObject.put("type", str);
            jSONObject.put("value", str2);
            jSONObject.put("password", str3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r5, java.lang.String r6) {
        /*
        r4 = this;
        r1 = 1;
        super.a(r5, r6);
        r2 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00a3 }
        r2.<init>(r6);	 Catch:{ JSONException -> 0x00a3 }
        r0 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00a3 }
        r0.<init>();	 Catch:{ JSONException -> 0x00a3 }
        r3 = "===";
        r0 = r0.append(r3);	 Catch:{ JSONException -> 0x00a3 }
        r0 = r0.append(r5);	 Catch:{ JSONException -> 0x00a3 }
        r3 = "返回===";
        r0 = r0.append(r3);	 Catch:{ JSONException -> 0x00a3 }
        r3 = r2.toString();	 Catch:{ JSONException -> 0x00a3 }
        r0 = r0.append(r3);	 Catch:{ JSONException -> 0x00a3 }
        r0 = r0.toString();	 Catch:{ JSONException -> 0x00a3 }
        com.ecjia.a.q.a(r0);	 Catch:{ JSONException -> 0x00a3 }
        r0 = "status";
        r0 = r2.optJSONObject(r0);	 Catch:{ JSONException -> 0x00a3 }
        r0 = com.ecjia.hamster.model.ax.a(r0);	 Catch:{ JSONException -> 0x00a3 }
        r4.r = r0;	 Catch:{ JSONException -> 0x00a3 }
        r0 = -1;
        r3 = r5.hashCode();	 Catch:{ JSONException -> 0x00a3 }
        switch(r3) {
            case -1322136140: goto L_0x0064;
            case -329935255: goto L_0x005a;
            case -228819377: goto L_0x006e;
            case 2129775008: goto L_0x0050;
            default: goto L_0x0041;
        };	 Catch:{ JSONException -> 0x00a3 }
    L_0x0041:
        switch(r0) {
            case 0: goto L_0x0078;
            case 1: goto L_0x0044;
            case 2: goto L_0x0044;
            default: goto L_0x0044;
        };	 Catch:{ JSONException -> 0x00a3 }
    L_0x0044:
        r4.g();	 Catch:{ JSONException -> 0x00a3 }
        r0 = r4.r;	 Catch:{ JSONException -> 0x00a3 }
        r4.a(r5, r6, r0);	 Catch:{ JSONException -> 0x00a3 }
    L_0x004c:
        r4.e(r6);
        return;
    L_0x0050:
        r3 = "shop/token";
        r3 = r5.equals(r3);	 Catch:{ JSONException -> 0x00a3 }
        if (r3 == 0) goto L_0x0041;
    L_0x0058:
        r0 = 0;
        goto L_0x0041;
    L_0x005a:
        r3 = "user/forget_password";
        r3 = r5.equals(r3);	 Catch:{ JSONException -> 0x00a3 }
        if (r3 == 0) goto L_0x0041;
    L_0x0062:
        r0 = r1;
        goto L_0x0041;
    L_0x0064:
        r3 = "validate/forget_password";
        r3 = r5.equals(r3);	 Catch:{ JSONException -> 0x00a3 }
        if (r3 == 0) goto L_0x0041;
    L_0x006c:
        r0 = 2;
        goto L_0x0041;
    L_0x006e:
        r3 = "user/reset_password";
        r3 = r5.equals(r3);	 Catch:{ JSONException -> 0x00a3 }
        if (r3 == 0) goto L_0x0041;
    L_0x0076:
        r0 = 3;
        goto L_0x0041;
    L_0x0078:
        r0 = r4.r;	 Catch:{ JSONException -> 0x00a3 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x00a3 }
        if (r0 != r1) goto L_0x00c8;
    L_0x0080:
        r0 = "data";
        r0 = r2.optJSONObject(r0);	 Catch:{ JSONException -> 0x00a3 }
        r0 = com.ecjia.hamster.model.a.a(r0);	 Catch:{ JSONException -> 0x00a3 }
        r4.a = r0;	 Catch:{ JSONException -> 0x00a3 }
        r0 = r4.d;	 Catch:{ JSONException -> 0x00a3 }
        r1 = r4.a;	 Catch:{ JSONException -> 0x00a3 }
        r1 = r1.a();	 Catch:{ JSONException -> 0x00a3 }
        r0.b(r1);	 Catch:{ JSONException -> 0x00a3 }
        r0 = r4.d;	 Catch:{ JSONException -> 0x00a3 }
        r1 = r4.a;	 Catch:{ JSONException -> 0x00a3 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00a3 }
        r0.a(r1);	 Catch:{ JSONException -> 0x00a3 }
        goto L_0x0044;
    L_0x00a3:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "===";
        r0 = r0.append(r1);
        r0 = r0.append(r5);
        r1 = "返回===";
        r0 = r0.append(r1);
        r0 = r0.append(r6);
        r0 = r0.toString();
        com.ecjia.a.q.a(r0);
        goto L_0x004c;
    L_0x00c8:
        r0 = new com.ecjia.component.view.k;	 Catch:{ JSONException -> 0x00a3 }
        r1 = r4.b;	 Catch:{ JSONException -> 0x00a3 }
        r2 = r4.r;	 Catch:{ JSONException -> 0x00a3 }
        r2 = r2.d();	 Catch:{ JSONException -> 0x00a3 }
        r0.<init>(r1, r2);	 Catch:{ JSONException -> 0x00a3 }
        r0.a();	 Catch:{ JSONException -> 0x00a3 }
        goto L_0x0044;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.m.a(java.lang.String, java.lang.String):void");
    }
}
