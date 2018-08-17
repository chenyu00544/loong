package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ECJia_BONUS;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.bf;
import com.taobao.accs.common.Constants;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaUserInfoModel */
public class al extends e {
    public static al b;
    public bf a;
    public ArrayList<ECJia_BONUS> c = new ArrayList();
    public String d;
    public String e;
    public String f;
    private Editor g;
    private SharedPreferences h;

    /* compiled from: ECJiaUserInfoModel */
    class al_1 implements OnCancelListener {
        final /* synthetic */ al a;

        al_1(al alVar) {
            this.a = alVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaUserInfoModel */
    class al_2 implements OnCancelListener {
        final /* synthetic */ al a;

        al_2(al alVar) {
            this.a = alVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaUserInfoModel */
    class al_3 implements OnCancelListener {
        final /* synthetic */ al a;

        al_3(al alVar) {
            this.a = alVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public al(Context context) {
        super(context);
        this.s.a((b) this);
        this.h = context.getSharedPreferences(Constants.KEY_USER_ID, 0);
        this.g = this.h.edit();
        b = this;
    }

    public void a() {
        a(false);
    }

    public void a(boolean z) {
        if (z) {
            this.l.show();
        }
        this.q = "user/info";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new al_1(this));
    }

    public void b(String str, String str2) {
        this.q = "user/password";
        this.l.show();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("password", str);
            jSONObject.put("new_password", str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new al_2(this));
    }

    public void a(String str) {
        this.q = "user/update";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void b(String str) {
        this.q = "user/update";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
            jSONObject.put("nick_name", str);
            jSONObject.put("avatar_img", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
    }

    public void b() {
        this.q = "user/signout";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", ap.c().d());
            jSONObject.put("device", this.o.toJson());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new al_3(this));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r7, java.lang.String r8) {
        /*
        r6 = this;
        r3 = 2;
        r2 = 1;
        r0 = 0;
        super.a(r7, r8);
        r4 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0136 }
        r4.<init>(r8);	 Catch:{ JSONException -> 0x0136 }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0136 }
        r1.<init>();	 Catch:{ JSONException -> 0x0136 }
        r5 = "===";
        r1 = r1.append(r5);	 Catch:{ JSONException -> 0x0136 }
        r1 = r1.append(r7);	 Catch:{ JSONException -> 0x0136 }
        r5 = "返回===";
        r1 = r1.append(r5);	 Catch:{ JSONException -> 0x0136 }
        r5 = r4.toString();	 Catch:{ JSONException -> 0x0136 }
        r1 = r1.append(r5);	 Catch:{ JSONException -> 0x0136 }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x0136 }
        com.ecjia.a.q.a(r1);	 Catch:{ JSONException -> 0x0136 }
        r1 = "status";
        r1 = r4.optJSONObject(r1);	 Catch:{ JSONException -> 0x0136 }
        r1 = com.ecjia.hamster.model.ax.a(r1);	 Catch:{ JSONException -> 0x0136 }
        r6.r = r1;	 Catch:{ JSONException -> 0x0136 }
        r1 = -1;
        r5 = r7.hashCode();	 Catch:{ JSONException -> 0x0136 }
        switch(r5) {
            case 252601229: goto L_0x0066;
            case 294875250: goto L_0x0052;
            case 1563413037: goto L_0x0070;
            case 2126005343: goto L_0x005c;
            default: goto L_0x0043;
        };	 Catch:{ JSONException -> 0x0136 }
    L_0x0043:
        switch(r1) {
            case 0: goto L_0x007a;
            case 1: goto L_0x015c;
            case 2: goto L_0x0176;
            case 3: goto L_0x01c2;
            default: goto L_0x0046;
        };	 Catch:{ JSONException -> 0x0136 }
    L_0x0046:
        r6.g();	 Catch:{ JSONException -> 0x0136 }
        r0 = r6.r;	 Catch:{ JSONException -> 0x0136 }
        r6.a(r7, r8, r0);	 Catch:{ JSONException -> 0x0136 }
    L_0x004e:
        r6.e(r8);
        return;
    L_0x0052:
        r3 = "user/info";
        r3 = r7.equals(r3);	 Catch:{ JSONException -> 0x0136 }
        if (r3 == 0) goto L_0x0043;
    L_0x005a:
        r1 = r0;
        goto L_0x0043;
    L_0x005c:
        r3 = "user/password";
        r3 = r7.equals(r3);	 Catch:{ JSONException -> 0x0136 }
        if (r3 == 0) goto L_0x0043;
    L_0x0064:
        r1 = r2;
        goto L_0x0043;
    L_0x0066:
        r5 = "user/update";
        r5 = r7.equals(r5);	 Catch:{ JSONException -> 0x0136 }
        if (r5 == 0) goto L_0x0043;
    L_0x006e:
        r1 = r3;
        goto L_0x0043;
    L_0x0070:
        r3 = "user/signout";
        r3 = r7.equals(r3);	 Catch:{ JSONException -> 0x0136 }
        if (r3 == 0) goto L_0x0043;
    L_0x0078:
        r1 = 3;
        goto L_0x0043;
    L_0x007a:
        r1 = r6.r;	 Catch:{ JSONException -> 0x0136 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x0136 }
        if (r1 != r2) goto L_0x0046;
    L_0x0082:
        r1 = "data";
        r1 = r4.optJSONObject(r1);	 Catch:{ JSONException -> 0x0136 }
        r1 = com.ecjia.hamster.model.bf.a(r1);	 Catch:{ JSONException -> 0x0136 }
        r6.a = r1;	 Catch:{ JSONException -> 0x0136 }
        r1 = r6.a;	 Catch:{ JSONException -> 0x0136 }
        r1 = r1.g();	 Catch:{ JSONException -> 0x0136 }
        r6.d = r1;	 Catch:{ JSONException -> 0x0136 }
        r1 = r6.a;	 Catch:{ JSONException -> 0x0136 }
        r1 = r1.h();	 Catch:{ JSONException -> 0x0136 }
        r6.e = r1;	 Catch:{ JSONException -> 0x0136 }
        r1 = r6.a;	 Catch:{ JSONException -> 0x0136 }
        r1 = r1.o();	 Catch:{ JSONException -> 0x0136 }
        r6.f = r1;	 Catch:{ JSONException -> 0x0136 }
        r1 = r6.m;	 Catch:{ JSONException -> 0x0136 }
        r2 = r6.a;	 Catch:{ JSONException -> 0x0136 }
        r1.a(r2);	 Catch:{ JSONException -> 0x0136 }
        r1 = r6.h;	 Catch:{ JSONException -> 0x0136 }
        r2 = "thirdLog";
        r3 = 0;
        r1 = r1.getBoolean(r2, r3);	 Catch:{ JSONException -> 0x0136 }
        if (r1 == 0) goto L_0x00e5;
    L_0x00b8:
        r1 = r6.a;	 Catch:{ JSONException -> 0x0136 }
        r1 = r1.q();	 Catch:{ JSONException -> 0x0136 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ JSONException -> 0x0136 }
        if (r1 == 0) goto L_0x00e5;
    L_0x00c4:
        r1 = "sns_qq";
        r2 = r6.h;	 Catch:{ JSONException -> 0x0136 }
        r3 = "thirdWay";
        r5 = "";
        r2 = r2.getString(r3, r5);	 Catch:{ JSONException -> 0x0136 }
        r1 = r1.equals(r2);	 Catch:{ JSONException -> 0x0136 }
        if (r1 == 0) goto L_0x0114;
    L_0x00d6:
        r1 = r6.a;	 Catch:{ JSONException -> 0x0136 }
        r2 = r6.h;	 Catch:{ JSONException -> 0x0136 }
        r3 = "qq_log_img";
        r5 = "";
        r2 = r2.getString(r3, r5);	 Catch:{ JSONException -> 0x0136 }
        r1.b(r2);	 Catch:{ JSONException -> 0x0136 }
    L_0x00e5:
        r1 = "data";
        r1 = r4.optJSONObject(r1);	 Catch:{ JSONException -> 0x0136 }
        r2 = "bonus_list";
        r1 = r1.optJSONArray(r2);	 Catch:{ JSONException -> 0x0136 }
        r2 = r6.c;	 Catch:{ JSONException -> 0x0136 }
        r2.clear();	 Catch:{ JSONException -> 0x0136 }
        if (r1 == 0) goto L_0x0046;
    L_0x00f8:
        r2 = r1.length();	 Catch:{ JSONException -> 0x0136 }
        if (r2 <= 0) goto L_0x0046;
    L_0x00fe:
        r2 = r1.length();	 Catch:{ JSONException -> 0x0136 }
        if (r0 >= r2) goto L_0x0046;
    L_0x0104:
        r2 = r6.c;	 Catch:{ JSONException -> 0x0136 }
        r3 = r1.optJSONObject(r0);	 Catch:{ JSONException -> 0x0136 }
        r3 = com.ecjia.hamster.model.ECJia_BONUS.fromJson(r3);	 Catch:{ JSONException -> 0x0136 }
        r2.add(r3);	 Catch:{ JSONException -> 0x0136 }
        r0 = r0 + 1;
        goto L_0x00fe;
    L_0x0114:
        r1 = "sns_wechat";
        r2 = r6.h;	 Catch:{ JSONException -> 0x0136 }
        r3 = "thirdWay";
        r5 = "";
        r2 = r2.getString(r3, r5);	 Catch:{ JSONException -> 0x0136 }
        r1 = r1.equals(r2);	 Catch:{ JSONException -> 0x0136 }
        if (r1 == 0) goto L_0x00e5;
    L_0x0126:
        r1 = r6.a;	 Catch:{ JSONException -> 0x0136 }
        r2 = r6.h;	 Catch:{ JSONException -> 0x0136 }
        r3 = "wx_log_img";
        r5 = "";
        r2 = r2.getString(r3, r5);	 Catch:{ JSONException -> 0x0136 }
        r1.b(r2);	 Catch:{ JSONException -> 0x0136 }
        goto L_0x00e5;
    L_0x0136:
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
        goto L_0x004e;
    L_0x015c:
        r0 = r6.r;	 Catch:{ JSONException -> 0x0136 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x0136 }
        if (r0 != 0) goto L_0x0046;
    L_0x0164:
        r0 = new com.ecjia.component.view.k;	 Catch:{ JSONException -> 0x0136 }
        r1 = r6.n;	 Catch:{ JSONException -> 0x0136 }
        r2 = r6.r;	 Catch:{ JSONException -> 0x0136 }
        r2 = r2.d();	 Catch:{ JSONException -> 0x0136 }
        r0.<init>(r1, r2);	 Catch:{ JSONException -> 0x0136 }
        r0.a();	 Catch:{ JSONException -> 0x0136 }
        goto L_0x0046;
    L_0x0176:
        r1 = r6.r;	 Catch:{ JSONException -> 0x0136 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x0136 }
        if (r1 != r2) goto L_0x01ba;
    L_0x017e:
        r1 = "data";
        r1 = r4.optJSONObject(r1);	 Catch:{ JSONException -> 0x0136 }
        r2 = com.ecjia.hamster.model.bf.a(r1);	 Catch:{ JSONException -> 0x0136 }
        r6.a = r2;	 Catch:{ JSONException -> 0x0136 }
        r2 = r6.m;	 Catch:{ JSONException -> 0x0136 }
        r3 = r6.a;	 Catch:{ JSONException -> 0x0136 }
        r2.a(r3);	 Catch:{ JSONException -> 0x0136 }
        r2 = "bonus_list";
        r1 = r1.optJSONArray(r2);	 Catch:{ JSONException -> 0x0136 }
        r2 = r6.c;	 Catch:{ JSONException -> 0x0136 }
        r2.clear();	 Catch:{ JSONException -> 0x0136 }
        if (r1 == 0) goto L_0x0046;
    L_0x019e:
        r2 = r1.length();	 Catch:{ JSONException -> 0x0136 }
        if (r2 <= 0) goto L_0x0046;
    L_0x01a4:
        r2 = r1.length();	 Catch:{ JSONException -> 0x0136 }
        if (r0 >= r2) goto L_0x0046;
    L_0x01aa:
        r2 = r6.c;	 Catch:{ JSONException -> 0x0136 }
        r3 = r1.optJSONObject(r0);	 Catch:{ JSONException -> 0x0136 }
        r3 = com.ecjia.hamster.model.ECJia_BONUS.fromJson(r3);	 Catch:{ JSONException -> 0x0136 }
        r2.add(r3);	 Catch:{ JSONException -> 0x0136 }
        r0 = r0 + 1;
        goto L_0x01a4;
    L_0x01ba:
        r0 = r6.r;	 Catch:{ JSONException -> 0x0136 }
        r1 = 2;
        r0.b(r1);	 Catch:{ JSONException -> 0x0136 }
        goto L_0x0046;
    L_0x01c2:
        r0 = r6.r;	 Catch:{ JSONException -> 0x0136 }
        r0 = r0.b();	 Catch:{ JSONException -> 0x0136 }
        if (r0 != r2) goto L_0x0046;
    L_0x01ca:
        r0 = r6.m;	 Catch:{ JSONException -> 0x0136 }
        r1 = 0;
        r0.a(r1);	 Catch:{ JSONException -> 0x0136 }
        r0 = com.ecjia.hamster.model.ap.c();	 Catch:{ JSONException -> 0x0136 }
        r1 = "";
        r0.a = r1;	 Catch:{ JSONException -> 0x0136 }
        r0 = com.ecjia.hamster.model.ap.c();	 Catch:{ JSONException -> 0x0136 }
        r1 = "";
        r0.b = r1;	 Catch:{ JSONException -> 0x0136 }
        r0 = 0;
        r6.a = r0;	 Catch:{ JSONException -> 0x0136 }
        r0 = r6.g;	 Catch:{ JSONException -> 0x0136 }
        r1 = "uid";
        r2 = "";
        r0.putString(r1, r2);	 Catch:{ JSONException -> 0x0136 }
        r0 = r6.g;	 Catch:{ JSONException -> 0x0136 }
        r1 = "sid";
        r2 = "";
        r0.putString(r1, r2);	 Catch:{ JSONException -> 0x0136 }
        r0 = r6.g;	 Catch:{ JSONException -> 0x0136 }
        r0.commit();	 Catch:{ JSONException -> 0x0136 }
        r0 = de.greenrobot.event.c.a();	 Catch:{ JSONException -> 0x0136 }
        r1 = new com.ecjia.a.a.b;	 Catch:{ JSONException -> 0x0136 }
        r2 = "exsit";
        r1.<init>(r2);	 Catch:{ JSONException -> 0x0136 }
        r0.c(r1);	 Catch:{ JSONException -> 0x0136 }
        goto L_0x0046;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.al.a(java.lang.String, java.lang.String):void");
    }
}
