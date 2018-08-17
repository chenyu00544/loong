package com.ecjia.component.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ax;
import com.taobao.accs.common.Constants;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaRegisterModel */
public class ac extends e {
    public ax a;
    private SharedPreferences b;
    private Editor c = this.b.edit();
    private String d;
    private String e;

    /* compiled from: ECJiaRegisterModel */
    class ac_1 implements OnCancelListener {
        final /* synthetic */ ac a;

        ac_1(ac acVar) {
            this.a = acVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaRegisterModel */
    class ac_2 implements OnCancelListener {
        final /* synthetic */ ac a;

        ac_2(ac acVar) {
            this.a = acVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    @SuppressLint({"CommitPrefEdits"})
    public ac(Context context) {
        super(context);
        this.s.a((b) this);
        this.b = context.getSharedPreferences(Constants.KEY_USER_ID, 0);
    }

    public void a(String str, String str2, String str3, JSONArray jSONArray, String str4, String str5) {
        this.q = "user/signup";
        this.l.show();
        this.e = str;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("name", str);
            jSONObject.put("password", str2);
            jSONObject.put("email", str3);
            jSONObject.put("mobile", str4);
            jSONObject.put("invite_code", str5);
            jSONObject.put("field", jSONArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new ac_1(this));
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.q = "connect/signup";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", this.d);
            jSONObject.put("device", this.o.toJson());
            jSONObject.put(SocializeProtocolConstants.PROTOCOL_KEY_USER_NAME2, str);
            jSONObject.put("password", str2);
            jSONObject.put("mobile", str3);
            jSONObject.put("invite_code", str4);
            jSONObject.put("openid", str5);
            jSONObject.put("code", str6);
            jSONObject.put("validate_code", str7);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new ac_2(this));
    }

    public void a(String str) {
        this.q = "user/userbind";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("token", this.d);
            jSONObject.put("type", "mobile");
            jSONObject.put("value", str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void a() {
        this.q = "shop/token";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("device", this.o.toJson());
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
        r3 = 2;
        r0 = 0;
        r2 = 1;
        super.a(r7, r8);
        r4 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00f0 }
        r4.<init>(r8);	 Catch:{ JSONException -> 0x00f0 }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00f0 }
        r1.<init>();	 Catch:{ JSONException -> 0x00f0 }
        r5 = "===";
        r1 = r1.append(r5);	 Catch:{ JSONException -> 0x00f0 }
        r1 = r1.append(r7);	 Catch:{ JSONException -> 0x00f0 }
        r5 = "返回===";
        r1 = r1.append(r5);	 Catch:{ JSONException -> 0x00f0 }
        r5 = r4.toString();	 Catch:{ JSONException -> 0x00f0 }
        r1 = r1.append(r5);	 Catch:{ JSONException -> 0x00f0 }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x00f0 }
        com.ecjia.a.q.a(r1);	 Catch:{ JSONException -> 0x00f0 }
        r1 = "status";
        r1 = r4.optJSONObject(r1);	 Catch:{ JSONException -> 0x00f0 }
        r1 = com.ecjia.hamster.model.ax.a(r1);	 Catch:{ JSONException -> 0x00f0 }
        r6.a = r1;	 Catch:{ JSONException -> 0x00f0 }
        r1 = -1;
        r5 = r7.hashCode();	 Catch:{ JSONException -> 0x00f0 }
        switch(r5) {
            case 188980188: goto L_0x0053;
            case 642956364: goto L_0x0066;
            case 1445060061: goto L_0x005c;
            case 2129775008: goto L_0x0070;
            default: goto L_0x0043;
        };	 Catch:{ JSONException -> 0x00f0 }
    L_0x0043:
        r0 = r1;
    L_0x0044:
        switch(r0) {
            case 0: goto L_0x007a;
            case 1: goto L_0x0116;
            case 2: goto L_0x01e4;
            case 3: goto L_0x0221;
            default: goto L_0x0047;
        };	 Catch:{ JSONException -> 0x00f0 }
    L_0x0047:
        r6.g();	 Catch:{ JSONException -> 0x00f0 }
        r0 = r6.a;	 Catch:{ JSONException -> 0x00f0 }
        r6.a(r7, r8, r0);	 Catch:{ JSONException -> 0x00f0 }
    L_0x004f:
        r6.e(r8);
        return;
    L_0x0053:
        r3 = "user/signup";
        r3 = r7.equals(r3);	 Catch:{ JSONException -> 0x00f0 }
        if (r3 == 0) goto L_0x0043;
    L_0x005b:
        goto L_0x0044;
    L_0x005c:
        r0 = "connect/signup";
        r0 = r7.equals(r0);	 Catch:{ JSONException -> 0x00f0 }
        if (r0 == 0) goto L_0x0043;
    L_0x0064:
        r0 = r2;
        goto L_0x0044;
    L_0x0066:
        r0 = "user/userbind";
        r0 = r7.equals(r0);	 Catch:{ JSONException -> 0x00f0 }
        if (r0 == 0) goto L_0x0043;
    L_0x006e:
        r0 = r3;
        goto L_0x0044;
    L_0x0070:
        r0 = "shop/token";
        r0 = r7.equals(r0);	 Catch:{ JSONException -> 0x00f0 }
        if (r0 == 0) goto L_0x0043;
    L_0x0078:
        r0 = 3;
        goto L_0x0044;
    L_0x007a:
        r0 = r6.a;	 Catch:{ JSONException -> 0x00f0 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x00f0 }
        if (r0 != r2) goto L_0x0047;
    L_0x0082:
        r0 = "data";
        r0 = r4.optJSONObject(r0);	 Catch:{ JSONException -> 0x00f0 }
        r1 = "session";
        r1 = r0.optJSONObject(r1);	 Catch:{ JSONException -> 0x00f0 }
        r1 = com.ecjia.hamster.model.ap.a(r1);	 Catch:{ JSONException -> 0x00f0 }
        r2 = "user";
        r0 = r0.optJSONObject(r2);	 Catch:{ JSONException -> 0x00f0 }
        r0 = com.ecjia.hamster.model.bf.a(r0);	 Catch:{ JSONException -> 0x00f0 }
        r2 = r6.m;	 Catch:{ JSONException -> 0x00f0 }
        r2.a(r0);	 Catch:{ JSONException -> 0x00f0 }
        r2 = r6.c;	 Catch:{ JSONException -> 0x00f0 }
        r3 = "uid";
        r4 = r1.a;	 Catch:{ JSONException -> 0x00f0 }
        r2.putString(r3, r4);	 Catch:{ JSONException -> 0x00f0 }
        r2 = r6.c;	 Catch:{ JSONException -> 0x00f0 }
        r3 = "sid";
        r4 = r1.b;	 Catch:{ JSONException -> 0x00f0 }
        r2.putString(r3, r4);	 Catch:{ JSONException -> 0x00f0 }
        r2 = r6.c;	 Catch:{ JSONException -> 0x00f0 }
        r3 = "local_uid";
        r1 = r1.a;	 Catch:{ JSONException -> 0x00f0 }
        r2.putString(r3, r1);	 Catch:{ JSONException -> 0x00f0 }
        r1 = r6.c;	 Catch:{ JSONException -> 0x00f0 }
        r2 = "uname";
        r3 = r6.e;	 Catch:{ JSONException -> 0x00f0 }
        r1.putString(r2, r3);	 Catch:{ JSONException -> 0x00f0 }
        r1 = r6.c;	 Catch:{ JSONException -> 0x00f0 }
        r2 = "level";
        r3 = r0.o();	 Catch:{ JSONException -> 0x00f0 }
        r1.putString(r2, r3);	 Catch:{ JSONException -> 0x00f0 }
        r1 = r6.c;	 Catch:{ JSONException -> 0x00f0 }
        r2 = "email";
        r0 = r0.h();	 Catch:{ JSONException -> 0x00f0 }
        r1.putString(r2, r0);	 Catch:{ JSONException -> 0x00f0 }
        r0 = r6.c;	 Catch:{ JSONException -> 0x00f0 }
        r0.commit();	 Catch:{ JSONException -> 0x00f0 }
        r0 = de.greenrobot.event.c.a();	 Catch:{ JSONException -> 0x00f0 }
        r1 = new com.ecjia.a.a.b;	 Catch:{ JSONException -> 0x00f0 }
        r2 = "USER_LOGIN_SUCCESS";
        r1.<init>(r2);	 Catch:{ JSONException -> 0x00f0 }
        r0.c(r1);	 Catch:{ JSONException -> 0x00f0 }
        goto L_0x0047;
    L_0x00f0:
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
    L_0x0116:
        r0 = r6.a;	 Catch:{ JSONException -> 0x00f0 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x00f0 }
        if (r0 != r2) goto L_0x0047;
    L_0x011e:
        r0 = "data";
        r0 = r4.optJSONObject(r0);	 Catch:{ JSONException -> 0x00f0 }
        r1 = "token";
        r1 = r0.optString(r1);	 Catch:{ JSONException -> 0x00f0 }
        r2 = "user";
        r0 = r0.optJSONObject(r2);	 Catch:{ JSONException -> 0x00f0 }
        r0 = com.ecjia.hamster.model.bf.a(r0);	 Catch:{ JSONException -> 0x00f0 }
        r2 = r0.q();	 Catch:{ JSONException -> 0x00f0 }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ JSONException -> 0x00f0 }
        if (r2 == 0) goto L_0x015d;
    L_0x013e:
        r2 = "sns_qq";
        r3 = r6.b;	 Catch:{ JSONException -> 0x00f0 }
        r4 = "thirdWay";
        r5 = "";
        r3 = r3.getString(r4, r5);	 Catch:{ JSONException -> 0x00f0 }
        r2 = r2.equals(r3);	 Catch:{ JSONException -> 0x00f0 }
        if (r2 == 0) goto L_0x01c3;
    L_0x0150:
        r2 = r6.b;	 Catch:{ JSONException -> 0x00f0 }
        r3 = "qq_log_img";
        r4 = "";
        r2 = r2.getString(r3, r4);	 Catch:{ JSONException -> 0x00f0 }
        r0.b(r2);	 Catch:{ JSONException -> 0x00f0 }
    L_0x015d:
        r2 = r6.m;	 Catch:{ JSONException -> 0x00f0 }
        r2.a(r0);	 Catch:{ JSONException -> 0x00f0 }
        r2 = com.ecjia.hamster.model.ap.c();	 Catch:{ JSONException -> 0x00f0 }
        r3 = r0.m();	 Catch:{ JSONException -> 0x00f0 }
        r2.a(r3);	 Catch:{ JSONException -> 0x00f0 }
        r2.b(r1);	 Catch:{ JSONException -> 0x00f0 }
        r2 = r6.c;	 Catch:{ JSONException -> 0x00f0 }
        r3 = "uid";
        r4 = r0.m();	 Catch:{ JSONException -> 0x00f0 }
        r2.putString(r3, r4);	 Catch:{ JSONException -> 0x00f0 }
        r2 = r6.c;	 Catch:{ JSONException -> 0x00f0 }
        r3 = "sid";
        r2.putString(r3, r1);	 Catch:{ JSONException -> 0x00f0 }
        r1 = r6.c;	 Catch:{ JSONException -> 0x00f0 }
        r2 = "local_uid";
        r3 = r0.m();	 Catch:{ JSONException -> 0x00f0 }
        r1.putString(r2, r3);	 Catch:{ JSONException -> 0x00f0 }
        r1 = r6.c;	 Catch:{ JSONException -> 0x00f0 }
        r2 = "uname";
        r3 = r0.p();	 Catch:{ JSONException -> 0x00f0 }
        r1.putString(r2, r3);	 Catch:{ JSONException -> 0x00f0 }
        r1 = r6.c;	 Catch:{ JSONException -> 0x00f0 }
        r2 = "level";
        r3 = r0.o();	 Catch:{ JSONException -> 0x00f0 }
        r1.putString(r2, r3);	 Catch:{ JSONException -> 0x00f0 }
        r1 = r6.c;	 Catch:{ JSONException -> 0x00f0 }
        r2 = "email";
        r0 = r0.h();	 Catch:{ JSONException -> 0x00f0 }
        r1.putString(r2, r0);	 Catch:{ JSONException -> 0x00f0 }
        r0 = r6.c;	 Catch:{ JSONException -> 0x00f0 }
        r0.commit();	 Catch:{ JSONException -> 0x00f0 }
        r0 = de.greenrobot.event.c.a();	 Catch:{ JSONException -> 0x00f0 }
        r1 = new com.ecjia.a.a.b;	 Catch:{ JSONException -> 0x00f0 }
        r2 = "USER_LOGIN_SUCCESS";
        r1.<init>(r2);	 Catch:{ JSONException -> 0x00f0 }
        r0.c(r1);	 Catch:{ JSONException -> 0x00f0 }
        goto L_0x0047;
    L_0x01c3:
        r2 = "sns_wechat";
        r3 = r6.b;	 Catch:{ JSONException -> 0x00f0 }
        r4 = "thirdWay";
        r5 = "";
        r3 = r3.getString(r4, r5);	 Catch:{ JSONException -> 0x00f0 }
        r2 = r2.equals(r3);	 Catch:{ JSONException -> 0x00f0 }
        if (r2 == 0) goto L_0x015d;
    L_0x01d5:
        r2 = r6.b;	 Catch:{ JSONException -> 0x00f0 }
        r3 = "wx_log_img";
        r4 = "";
        r2 = r2.getString(r3, r4);	 Catch:{ JSONException -> 0x00f0 }
        r0.b(r2);	 Catch:{ JSONException -> 0x00f0 }
        goto L_0x015d;
    L_0x01e4:
        r0 = r6.a;	 Catch:{ JSONException -> 0x00f0 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x00f0 }
        if (r0 != r2) goto L_0x0219;
    L_0x01ec:
        r0 = "data";
        r0 = r4.optJSONObject(r0);	 Catch:{ JSONException -> 0x00f0 }
        r1 = "data";
        r1 = r4.optJSONObject(r1);	 Catch:{ JSONException -> 0x00f0 }
        if (r1 != 0) goto L_0x0201;
    L_0x01fa:
        r0 = "注册信息返回值错误";
        com.ecjia.a.q.b(r0);	 Catch:{ JSONException -> 0x00f0 }
        goto L_0x0047;
    L_0x0201:
        r1 = "registered";
        r0 = r0.getInt(r1);	 Catch:{ JSONException -> 0x00f0 }
        if (r0 != r2) goto L_0x0211;
    L_0x0209:
        r0 = r6.a;	 Catch:{ JSONException -> 0x00f0 }
        r1 = 1;
        r0.a(r1);	 Catch:{ JSONException -> 0x00f0 }
        goto L_0x0047;
    L_0x0211:
        r0 = r6.a;	 Catch:{ JSONException -> 0x00f0 }
        r1 = 0;
        r0.a(r1);	 Catch:{ JSONException -> 0x00f0 }
        goto L_0x0047;
    L_0x0219:
        r0 = r6.a;	 Catch:{ JSONException -> 0x00f0 }
        r1 = 2;
        r0.a(r1);	 Catch:{ JSONException -> 0x00f0 }
        goto L_0x0047;
    L_0x0221:
        r0 = r6.a;	 Catch:{ JSONException -> 0x00f0 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x00f0 }
        if (r0 != r2) goto L_0x0047;
    L_0x0229:
        r0 = "data";
        r0 = r4.optJSONObject(r0);	 Catch:{ JSONException -> 0x00f0 }
        r0 = com.ecjia.hamster.model.a.a(r0);	 Catch:{ JSONException -> 0x00f0 }
        r0 = r0.a();	 Catch:{ JSONException -> 0x00f0 }
        r6.d = r0;	 Catch:{ JSONException -> 0x00f0 }
        goto L_0x0047;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.ac.a(java.lang.String, java.lang.String):void");
    }
}
