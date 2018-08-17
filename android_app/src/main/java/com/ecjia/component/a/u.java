package com.ecjia.component.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.ecjia.a.q;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ax;
import com.taobao.accs.common.Constants;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaLoginModel */
public class u extends e {
    public String a;
    public String b;
    public String c;
    public String d;
    public ax e;
    private SharedPreferences f;
    private Editor g = this.f.edit();

    /* compiled from: ECJiaLoginModel */
    class u_1 implements OnCancelListener {
        final /* synthetic */ u a;

        u_1(u uVar) {
            this.a = uVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaLoginModel */
    class u_2 implements OnCancelListener {
        final /* synthetic */ u a;

        u_2(u uVar) {
            this.a = uVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaLoginModel */
    class u_3 implements OnCancelListener {
        final /* synthetic */ u a;

        u_3(u uVar) {
            this.a = uVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    @SuppressLint({"CommitPrefEdits"})
    public u(Context context) {
        super(context);
        this.s.a((b) this);
        this.f = context.getSharedPreferences(Constants.KEY_USER_ID, 0);
    }

    public void b(String str, String str2) {
        this.q = "user/signin";
        this.a = str;
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("name", str);
            jSONObject.put("password", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new u_1(this));
    }

    public void a(String str, String str2, String str3) {
        this.q = "connect/signin";
        this.g.putString("thirdWay", str3);
        this.g.commit();
        JSONObject jSONObject = new JSONObject();
        if (!this.l.isShowing()) {
            this.l.show();
        }
        try {
            jSONObject.put("device", this.o.toJson());
            jSONObject.put(SocializeProtocolConstants.PROTOCOL_KEY_USER_NAME2, str);
            jSONObject.put("openid", str2);
            jSONObject.put("code", str3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new u_2(this));
    }

    public void a(String str, String str2, String str3, String str4) {
        this.q = "connect/bind";
        this.g.putString("thirdWay", str4);
        this.g.commit();
        JSONObject jSONObject = new JSONObject();
        if (!this.l.isShowing()) {
            this.l.show();
        }
        try {
            jSONObject.put("device", this.o.toJson());
            jSONObject.put(SocializeProtocolConstants.PROTOCOL_KEY_USER_NAME2, str);
            jSONObject.put("password", str2);
            jSONObject.put("openid", str3);
            jSONObject.put("code", str4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new u_3(this));
    }

    public void a(String str) {
        this.q = "https://graph.qq.com/oauth2.0/me?access_token=";
        q.b("===传入===" + str);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device", this.o.toJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, this.q + str + "&unionid=1", jSONObject.toString());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r7, java.lang.String r8) {
        /*
        r6 = this;
        r0 = 0;
        r2 = 1;
        super.a(r7, r8);
        r1 = "https://graph.qq.com/oauth2.0/me?access_token=";
        r1 = r7.equals(r1);	 Catch:{ JSONException -> 0x0169 }
        if (r1 == 0) goto L_0x009c;
    L_0x000d:
        r0 = "( ";
        r1 = "\":";
        r0 = r8.replace(r0, r1);	 Catch:{ JSONException -> 0x0169 }
        r1 = " );";
        r2 = "}";
        r0 = r0.replace(r1, r2);	 Catch:{ JSONException -> 0x0169 }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0169 }
        r1.<init>();	 Catch:{ JSONException -> 0x0169 }
        r2 = "{\"";
        r1 = r1.append(r2);	 Catch:{ JSONException -> 0x0169 }
        r1 = r1.append(r0);	 Catch:{ JSONException -> 0x0169 }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x0169 }
        r2 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0169 }
        r2.<init>();	 Catch:{ JSONException -> 0x0169 }
        r3 = "===";
        r2 = r2.append(r3);	 Catch:{ JSONException -> 0x0169 }
        r2 = r2.append(r7);	 Catch:{ JSONException -> 0x0169 }
        r3 = "返回1111===";
        r2 = r2.append(r3);	 Catch:{ JSONException -> 0x0169 }
        r0 = r2.append(r0);	 Catch:{ JSONException -> 0x0169 }
        r0 = r0.toString();	 Catch:{ JSONException -> 0x0169 }
        com.ecjia.a.q.a(r0);	 Catch:{ JSONException -> 0x0169 }
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0169 }
        r0.<init>(r1);	 Catch:{ JSONException -> 0x0169 }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0169 }
        r1.<init>();	 Catch:{ JSONException -> 0x0169 }
        r2 = "===";
        r1 = r1.append(r2);	 Catch:{ JSONException -> 0x0169 }
        r1 = r1.append(r7);	 Catch:{ JSONException -> 0x0169 }
        r2 = "返回1112===";
        r1 = r1.append(r2);	 Catch:{ JSONException -> 0x0169 }
        r1 = r1.append(r0);	 Catch:{ JSONException -> 0x0169 }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x0169 }
        com.ecjia.a.q.a(r1);	 Catch:{ JSONException -> 0x0169 }
        r1 = "callback";
        r0 = r0.optJSONObject(r1);	 Catch:{ JSONException -> 0x0169 }
        r1 = "client_id";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x0169 }
        r6.b = r1;	 Catch:{ JSONException -> 0x0169 }
        r1 = "openid";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x0169 }
        r6.c = r1;	 Catch:{ JSONException -> 0x0169 }
        r1 = "unionid";
        r0 = r0.optString(r1);	 Catch:{ JSONException -> 0x0169 }
        r6.d = r0;	 Catch:{ JSONException -> 0x0169 }
    L_0x0093:
        r6.g();	 Catch:{ JSONException -> 0x0169 }
        r0 = r6.e;	 Catch:{ JSONException -> 0x0169 }
        r6.a(r7, r8, r0);	 Catch:{ JSONException -> 0x0169 }
    L_0x009b:
        return;
    L_0x009c:
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0169 }
        r3.<init>(r8);	 Catch:{ JSONException -> 0x0169 }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0169 }
        r1.<init>();	 Catch:{ JSONException -> 0x0169 }
        r4 = "===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x0169 }
        r1 = r1.append(r7);	 Catch:{ JSONException -> 0x0169 }
        r4 = "返回1===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x0169 }
        r4 = r3.toString();	 Catch:{ JSONException -> 0x0169 }
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x0169 }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x0169 }
        com.ecjia.a.q.a(r1);	 Catch:{ JSONException -> 0x0169 }
        r1 = "status";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x0169 }
        r1 = com.ecjia.hamster.model.ax.b(r1);	 Catch:{ JSONException -> 0x0169 }
        r6.e = r1;	 Catch:{ JSONException -> 0x0169 }
        r1 = -1;
        r4 = r7.hashCode();	 Catch:{ JSONException -> 0x0169 }
        switch(r4) {
            case 188979814: goto L_0x018f;
            case 1445059687: goto L_0x0199;
            case 1869151810: goto L_0x01a4;
            default: goto L_0x00d9;
        };	 Catch:{ JSONException -> 0x0169 }
    L_0x00d9:
        r0 = r1;
    L_0x00da:
        switch(r0) {
            case 0: goto L_0x00de;
            case 1: goto L_0x01af;
            case 2: goto L_0x027d;
            default: goto L_0x00dd;
        };	 Catch:{ JSONException -> 0x0169 }
    L_0x00dd:
        goto L_0x0093;
    L_0x00de:
        r0 = r6.e;	 Catch:{ JSONException -> 0x0169 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x0169 }
        if (r0 != r2) goto L_0x0093;
    L_0x00e6:
        r0 = com.ecjia.a.u.a();	 Catch:{ JSONException -> 0x0169 }
        r0.b();	 Catch:{ JSONException -> 0x0169 }
        r0 = "data";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x0169 }
        r1 = "session";
        r1 = r0.optJSONObject(r1);	 Catch:{ JSONException -> 0x0169 }
        r1 = com.ecjia.hamster.model.ap.a(r1);	 Catch:{ JSONException -> 0x0169 }
        r2 = "user";
        r0 = r0.optJSONObject(r2);	 Catch:{ JSONException -> 0x0169 }
        r0 = com.ecjia.hamster.model.bf.a(r0);	 Catch:{ JSONException -> 0x0169 }
        r2 = r6.m;	 Catch:{ JSONException -> 0x0169 }
        r2.a(r0);	 Catch:{ JSONException -> 0x0169 }
        r2 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r3 = "uid";
        r4 = r1.a;	 Catch:{ JSONException -> 0x0169 }
        r2.putString(r3, r4);	 Catch:{ JSONException -> 0x0169 }
        r2 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r3 = "sid";
        r4 = r1.b;	 Catch:{ JSONException -> 0x0169 }
        r2.putString(r3, r4);	 Catch:{ JSONException -> 0x0169 }
        r2 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r3 = "local_uid";
        r1 = r1.a;	 Catch:{ JSONException -> 0x0169 }
        r2.putString(r3, r1);	 Catch:{ JSONException -> 0x0169 }
        r1 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r2 = "uname";
        r3 = r6.a;	 Catch:{ JSONException -> 0x0169 }
        r1.putString(r2, r3);	 Catch:{ JSONException -> 0x0169 }
        r1 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r2 = "level";
        r3 = r0.o();	 Catch:{ JSONException -> 0x0169 }
        r1.putString(r2, r3);	 Catch:{ JSONException -> 0x0169 }
        r1 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r2 = "email";
        r0 = r0.h();	 Catch:{ JSONException -> 0x0169 }
        r1.putString(r2, r0);	 Catch:{ JSONException -> 0x0169 }
        r0 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r0.commit();	 Catch:{ JSONException -> 0x0169 }
        r0 = de.greenrobot.event.c.a();	 Catch:{ JSONException -> 0x0169 }
        r1 = new com.ecjia.a.a.b;	 Catch:{ JSONException -> 0x0169 }
        r2 = "CART_UPDATE";
        r1.<init>(r2);	 Catch:{ JSONException -> 0x0169 }
        r0.c(r1);	 Catch:{ JSONException -> 0x0169 }
        r0 = de.greenrobot.event.c.a();	 Catch:{ JSONException -> 0x0169 }
        r1 = new com.ecjia.a.a.b;	 Catch:{ JSONException -> 0x0169 }
        r2 = "USER_LOGIN_SUCCESS";
        r1.<init>(r2);	 Catch:{ JSONException -> 0x0169 }
        r0.c(r1);	 Catch:{ JSONException -> 0x0169 }
        goto L_0x0093;
    L_0x0169:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "===";
        r0 = r0.append(r1);
        r0 = r0.append(r7);
        r1 = "返回2===";
        r0 = r0.append(r1);
        r0 = r0.append(r8);
        r0 = r0.toString();
        com.ecjia.a.q.a(r0);
        goto L_0x009b;
    L_0x018f:
        r4 = "user/signin";
        r4 = r7.equals(r4);	 Catch:{ JSONException -> 0x0169 }
        if (r4 == 0) goto L_0x00d9;
    L_0x0197:
        goto L_0x00da;
    L_0x0199:
        r0 = "connect/signin";
        r0 = r7.equals(r0);	 Catch:{ JSONException -> 0x0169 }
        if (r0 == 0) goto L_0x00d9;
    L_0x01a1:
        r0 = r2;
        goto L_0x00da;
    L_0x01a4:
        r0 = "connect/bind";
        r0 = r7.equals(r0);	 Catch:{ JSONException -> 0x0169 }
        if (r0 == 0) goto L_0x00d9;
    L_0x01ac:
        r0 = 2;
        goto L_0x00da;
    L_0x01af:
        r0 = r6.e;	 Catch:{ JSONException -> 0x0169 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x0169 }
        if (r0 != r2) goto L_0x0093;
    L_0x01b7:
        r0 = "data";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x0169 }
        r1 = "token";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x0169 }
        r2 = "user";
        r0 = r0.optJSONObject(r2);	 Catch:{ JSONException -> 0x0169 }
        r0 = com.ecjia.hamster.model.bf.a(r0);	 Catch:{ JSONException -> 0x0169 }
        r2 = r0.q();	 Catch:{ JSONException -> 0x0169 }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ JSONException -> 0x0169 }
        if (r2 == 0) goto L_0x01f6;
    L_0x01d7:
        r2 = "sns_qq";
        r3 = r6.f;	 Catch:{ JSONException -> 0x0169 }
        r4 = "thirdWay";
        r5 = "";
        r3 = r3.getString(r4, r5);	 Catch:{ JSONException -> 0x0169 }
        r2 = r2.equals(r3);	 Catch:{ JSONException -> 0x0169 }
        if (r2 == 0) goto L_0x025c;
    L_0x01e9:
        r2 = r6.f;	 Catch:{ JSONException -> 0x0169 }
        r3 = "qq_log_img";
        r4 = "";
        r2 = r2.getString(r3, r4);	 Catch:{ JSONException -> 0x0169 }
        r0.b(r2);	 Catch:{ JSONException -> 0x0169 }
    L_0x01f6:
        r2 = r6.m;	 Catch:{ JSONException -> 0x0169 }
        r2.a(r0);	 Catch:{ JSONException -> 0x0169 }
        r2 = com.ecjia.hamster.model.ap.c();	 Catch:{ JSONException -> 0x0169 }
        r3 = r0.m();	 Catch:{ JSONException -> 0x0169 }
        r2.a(r3);	 Catch:{ JSONException -> 0x0169 }
        r2.b(r1);	 Catch:{ JSONException -> 0x0169 }
        r2 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r3 = "uid";
        r4 = r0.m();	 Catch:{ JSONException -> 0x0169 }
        r2.putString(r3, r4);	 Catch:{ JSONException -> 0x0169 }
        r2 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r3 = "sid";
        r2.putString(r3, r1);	 Catch:{ JSONException -> 0x0169 }
        r1 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r2 = "local_uid";
        r3 = r0.m();	 Catch:{ JSONException -> 0x0169 }
        r1.putString(r2, r3);	 Catch:{ JSONException -> 0x0169 }
        r1 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r2 = "uname";
        r3 = r0.p();	 Catch:{ JSONException -> 0x0169 }
        r1.putString(r2, r3);	 Catch:{ JSONException -> 0x0169 }
        r1 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r2 = "level";
        r3 = r0.o();	 Catch:{ JSONException -> 0x0169 }
        r1.putString(r2, r3);	 Catch:{ JSONException -> 0x0169 }
        r1 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r2 = "email";
        r0 = r0.h();	 Catch:{ JSONException -> 0x0169 }
        r1.putString(r2, r0);	 Catch:{ JSONException -> 0x0169 }
        r0 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r0.commit();	 Catch:{ JSONException -> 0x0169 }
        r0 = de.greenrobot.event.c.a();	 Catch:{ JSONException -> 0x0169 }
        r1 = new com.ecjia.a.a.b;	 Catch:{ JSONException -> 0x0169 }
        r2 = "USER_LOGIN_SUCCESS";
        r1.<init>(r2);	 Catch:{ JSONException -> 0x0169 }
        r0.c(r1);	 Catch:{ JSONException -> 0x0169 }
        goto L_0x0093;
    L_0x025c:
        r2 = "sns_wechat";
        r3 = r6.f;	 Catch:{ JSONException -> 0x0169 }
        r4 = "thirdWay";
        r5 = "";
        r3 = r3.getString(r4, r5);	 Catch:{ JSONException -> 0x0169 }
        r2 = r2.equals(r3);	 Catch:{ JSONException -> 0x0169 }
        if (r2 == 0) goto L_0x01f6;
    L_0x026e:
        r2 = r6.f;	 Catch:{ JSONException -> 0x0169 }
        r3 = "wx_log_img";
        r4 = "";
        r2 = r2.getString(r3, r4);	 Catch:{ JSONException -> 0x0169 }
        r0.b(r2);	 Catch:{ JSONException -> 0x0169 }
        goto L_0x01f6;
    L_0x027d:
        r0 = r6.e;	 Catch:{ JSONException -> 0x0169 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x0169 }
        if (r0 != r2) goto L_0x0093;
    L_0x0285:
        r0 = "data";
        r0 = r3.optJSONObject(r0);	 Catch:{ JSONException -> 0x0169 }
        r1 = "token";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x0169 }
        r2 = "user";
        r0 = r0.optJSONObject(r2);	 Catch:{ JSONException -> 0x0169 }
        r0 = com.ecjia.hamster.model.bf.a(r0);	 Catch:{ JSONException -> 0x0169 }
        r2 = r6.f;	 Catch:{ JSONException -> 0x0169 }
        r3 = "thirdLog";
        r4 = 0;
        r2 = r2.getBoolean(r3, r4);	 Catch:{ JSONException -> 0x0169 }
        if (r2 == 0) goto L_0x02cf;
    L_0x02a6:
        r2 = r0.q();	 Catch:{ JSONException -> 0x0169 }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ JSONException -> 0x0169 }
        if (r2 == 0) goto L_0x02cf;
    L_0x02b0:
        r2 = "sns_qq";
        r3 = r6.f;	 Catch:{ JSONException -> 0x0169 }
        r4 = "thirdWay";
        r5 = "";
        r3 = r3.getString(r4, r5);	 Catch:{ JSONException -> 0x0169 }
        r2 = r2.equals(r3);	 Catch:{ JSONException -> 0x0169 }
        if (r2 == 0) goto L_0x0327;
    L_0x02c2:
        r2 = r6.f;	 Catch:{ JSONException -> 0x0169 }
        r3 = "qq_log_img";
        r4 = "";
        r2 = r2.getString(r3, r4);	 Catch:{ JSONException -> 0x0169 }
        r0.b(r2);	 Catch:{ JSONException -> 0x0169 }
    L_0x02cf:
        r2 = r6.m;	 Catch:{ JSONException -> 0x0169 }
        r2.a(r0);	 Catch:{ JSONException -> 0x0169 }
        r2 = com.ecjia.hamster.model.ap.c();	 Catch:{ JSONException -> 0x0169 }
        r3 = r0.m();	 Catch:{ JSONException -> 0x0169 }
        r2.a(r3);	 Catch:{ JSONException -> 0x0169 }
        r2.b(r1);	 Catch:{ JSONException -> 0x0169 }
        r2 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r3 = "uid";
        r4 = r0.m();	 Catch:{ JSONException -> 0x0169 }
        r2.putString(r3, r4);	 Catch:{ JSONException -> 0x0169 }
        r2 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r3 = "sid";
        r2.putString(r3, r1);	 Catch:{ JSONException -> 0x0169 }
        r1 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r2 = "local_uid";
        r3 = r0.m();	 Catch:{ JSONException -> 0x0169 }
        r1.putString(r2, r3);	 Catch:{ JSONException -> 0x0169 }
        r1 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r2 = "uname";
        r3 = r0.p();	 Catch:{ JSONException -> 0x0169 }
        r1.putString(r2, r3);	 Catch:{ JSONException -> 0x0169 }
        r1 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r2 = "level";
        r3 = r0.o();	 Catch:{ JSONException -> 0x0169 }
        r1.putString(r2, r3);	 Catch:{ JSONException -> 0x0169 }
        r1 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r2 = "email";
        r0 = r0.h();	 Catch:{ JSONException -> 0x0169 }
        r1.putString(r2, r0);	 Catch:{ JSONException -> 0x0169 }
        r0 = r6.g;	 Catch:{ JSONException -> 0x0169 }
        r0.commit();	 Catch:{ JSONException -> 0x0169 }
        goto L_0x0093;
    L_0x0327:
        r2 = "sns_wechat";
        r3 = r6.f;	 Catch:{ JSONException -> 0x0169 }
        r4 = "thirdWay";
        r5 = "";
        r3 = r3.getString(r4, r5);	 Catch:{ JSONException -> 0x0169 }
        r2 = r2.equals(r3);	 Catch:{ JSONException -> 0x0169 }
        if (r2 == 0) goto L_0x02cf;
    L_0x0339:
        r2 = r6.f;	 Catch:{ JSONException -> 0x0169 }
        r3 = "wx_log_img";
        r4 = "";
        r2 = r2.getString(r3, r4);	 Catch:{ JSONException -> 0x0169 }
        r0.b(r2);	 Catch:{ JSONException -> 0x0169 }
        goto L_0x02cf;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.u.a(java.lang.String, java.lang.String):void");
    }
}
