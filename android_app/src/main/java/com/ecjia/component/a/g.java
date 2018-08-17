package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ap;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaCodeModel */
public class g extends e {
    private static g a;
    private String b = "mobile";

    /* compiled from: ECJiaCodeModel */
    class g_1 implements OnCancelListener {
        final /* synthetic */ g a;

        g_1(g gVar) {
            this.a = gVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaCodeModel */
    class g_2 implements OnCancelListener {
        final /* synthetic */ g a;

        g_2(g gVar) {
            this.a = gVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaCodeModel */
    class g_3 implements OnCancelListener {
        final /* synthetic */ g a;

        g_3(g gVar) {
            this.a = gVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaCodeModel */
    class g_4 implements OnCancelListener {
        final /* synthetic */ g a;

        g_4(g gVar) {
            this.a = gVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public static g a() {
        return a;
    }

    public g(Context context) {
        super(context);
        a = this;
        this.s.a((b) this);
    }

    public void a(String str) {
        ap c = ap.c();
        this.l.show();
        this.q = "user/userbind";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("type", this.b);
            jSONObject.put("value", str);
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new g_1(this));
    }

    public void b(String str, String str2) {
        ap c = ap.c();
        this.l.show();
        this.q = "validate/bind";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("type", this.b);
            jSONObject.put("value", str);
            jSONObject.put("code", str2);
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new g_2(this));
    }

    public void c(String str, String str2) {
        ap c = ap.c();
        this.l.show();
        this.q = "shop/captcha/mail";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("type", str);
            if (str.equals("user_modify_mail")) {
                jSONObject.put("value", str2);
            } else if (str.equals("user_modify_mobile")) {
                jSONObject.put("value", str2);
            }
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new g_3(this));
    }

    public void a(String str, String str2, String str3) {
        ap c = ap.c();
        this.l.show();
        this.q = "user/bind";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
            if (str.equals("user_modify_mail")) {
                jSONObject.put("type", "email");
            } else if (str.equals("user_modify_mobile")) {
                jSONObject.put("type", "mobile");
            }
            jSONObject.put("value", str2);
            jSONObject.put("code", str3);
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new g_4(this));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r7, java.lang.String r8) {
        /*
        r6 = this;
        r3 = 2;
        r0 = 0;
        r2 = 1;
        super.a(r7, r8);
        r4 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00a0 }
        r4.<init>(r8);	 Catch:{ JSONException -> 0x00a0 }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00a0 }
        r1.<init>();	 Catch:{ JSONException -> 0x00a0 }
        r5 = "===";
        r1 = r1.append(r5);	 Catch:{ JSONException -> 0x00a0 }
        r1 = r1.append(r7);	 Catch:{ JSONException -> 0x00a0 }
        r5 = "返回===";
        r1 = r1.append(r5);	 Catch:{ JSONException -> 0x00a0 }
        r5 = r4.toString();	 Catch:{ JSONException -> 0x00a0 }
        r1 = r1.append(r5);	 Catch:{ JSONException -> 0x00a0 }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x00a0 }
        com.ecjia.a.q.a(r1);	 Catch:{ JSONException -> 0x00a0 }
        r1 = "status";
        r1 = r4.optJSONObject(r1);	 Catch:{ JSONException -> 0x00a0 }
        r1 = com.ecjia.hamster.model.ax.a(r1);	 Catch:{ JSONException -> 0x00a0 }
        r6.r = r1;	 Catch:{ JSONException -> 0x00a0 }
        r1 = -1;
        r5 = r7.hashCode();	 Catch:{ JSONException -> 0x00a0 }
        switch(r5) {
            case -940489045: goto L_0x0066;
            case -875718250: goto L_0x005c;
            case 294662145: goto L_0x007a;
            case 642956364: goto L_0x0053;
            case 909420197: goto L_0x0070;
            default: goto L_0x0043;
        };	 Catch:{ JSONException -> 0x00a0 }
    L_0x0043:
        r0 = r1;
    L_0x0044:
        switch(r0) {
            case 0: goto L_0x0084;
            case 1: goto L_0x0047;
            case 2: goto L_0x0047;
            case 3: goto L_0x0047;
            default: goto L_0x0047;
        };	 Catch:{ JSONException -> 0x00a0 }
    L_0x0047:
        r6.g();	 Catch:{ JSONException -> 0x00a0 }
        r0 = r6.r;	 Catch:{ JSONException -> 0x00a0 }
        r6.a(r7, r8, r0);	 Catch:{ JSONException -> 0x00a0 }
    L_0x004f:
        r6.e(r8);
        return;
    L_0x0053:
        r3 = "user/userbind";
        r3 = r7.equals(r3);	 Catch:{ JSONException -> 0x00a0 }
        if (r3 == 0) goto L_0x0043;
    L_0x005b:
        goto L_0x0044;
    L_0x005c:
        r0 = "validate/bind";
        r0 = r7.equals(r0);	 Catch:{ JSONException -> 0x00a0 }
        if (r0 == 0) goto L_0x0043;
    L_0x0064:
        r0 = r2;
        goto L_0x0044;
    L_0x0066:
        r0 = "shop/captcha/sms";
        r0 = r7.equals(r0);	 Catch:{ JSONException -> 0x00a0 }
        if (r0 == 0) goto L_0x0043;
    L_0x006e:
        r0 = r3;
        goto L_0x0044;
    L_0x0070:
        r0 = "shop/captcha/mail";
        r0 = r7.equals(r0);	 Catch:{ JSONException -> 0x00a0 }
        if (r0 == 0) goto L_0x0043;
    L_0x0078:
        r0 = 3;
        goto L_0x0044;
    L_0x007a:
        r0 = "user/bind";
        r0 = r7.equals(r0);	 Catch:{ JSONException -> 0x00a0 }
        if (r0 == 0) goto L_0x0043;
    L_0x0082:
        r0 = 4;
        goto L_0x0044;
    L_0x0084:
        r0 = r6.r;	 Catch:{ JSONException -> 0x00a0 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x00a0 }
        if (r0 != r2) goto L_0x00dd;
    L_0x008c:
        r0 = "data";
        r0 = r4.optJSONObject(r0);	 Catch:{ JSONException -> 0x00a0 }
        r1 = "data";
        r1 = r4.optJSONObject(r1);	 Catch:{ JSONException -> 0x00a0 }
        if (r1 != 0) goto L_0x00c5;
    L_0x009a:
        r0 = "注册信息返回值错误";
        com.ecjia.a.q.b(r0);	 Catch:{ JSONException -> 0x00a0 }
        goto L_0x0047;
    L_0x00a0:
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
        goto L_0x004f;
    L_0x00c5:
        r1 = "registered";
        r0 = r0.getInt(r1);	 Catch:{ JSONException -> 0x00a0 }
        if (r0 != r2) goto L_0x00d5;
    L_0x00cd:
        r0 = r6.r;	 Catch:{ JSONException -> 0x00a0 }
        r1 = 1;
        r0.a(r1);	 Catch:{ JSONException -> 0x00a0 }
        goto L_0x0047;
    L_0x00d5:
        r0 = r6.r;	 Catch:{ JSONException -> 0x00a0 }
        r1 = 0;
        r0.a(r1);	 Catch:{ JSONException -> 0x00a0 }
        goto L_0x0047;
    L_0x00dd:
        r0 = r6.r;	 Catch:{ JSONException -> 0x00a0 }
        r1 = 2;
        r0.a(r1);	 Catch:{ JSONException -> 0x00a0 }
        goto L_0x0047;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.g.a(java.lang.String, java.lang.String):void");
    }
}
