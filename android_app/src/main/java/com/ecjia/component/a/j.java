package com.ecjia.component.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import anet.channel.util.HttpConstant;
import com.ecjia.a.q;
import com.ecjia.component.a.a.b;
import com.ecjia.hamster.model.ak;
import com.ecjia.hamster.model.ap;
import com.ecjia.hamster.model.k;
import com.umeng.message.util.HttpRequest;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJiaConfigModel */
public class j extends e {
    private static j f;
    public k a;
    public Drawable b = null;
    public ArrayList<ak> c = new ArrayList();
    private SharedPreferences d;
    private Editor e;

    /* compiled from: ECJiaConfigModel */
    class j_1 implements OnCancelListener {
        final /* synthetic */ j a;

        j_1(j jVar) {
            this.a = jVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    /* compiled from: ECJiaConfigModel */
    class j_2 implements OnCancelListener {
        final /* synthetic */ j a;

        j_2(j jVar) {
            this.a = jVar;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.a.g();
            this.a.s.b(this.a.q);
        }
    }

    public static j a() {
        return f;
    }

    public j(Context context) {
        super(context);
        this.d = context.getSharedPreferences("spd_shopconfig", 0);
        this.e = this.d.edit();
        f = this;
        this.s.a((b) this);
    }

    public void b() {
        ap c = ap.c();
        this.q = "shop/config";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new j_1(this));
    }

    public int a(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept-Encoding", "identity");
        httpURLConnection.setRequestProperty(HttpRequest.HEADER_REFERER, str);
        httpURLConnection.setRequestProperty("Charset", "UTF-8");
        httpURLConnection.setRequestProperty(HttpConstant.CONNECTION, "Keep-Alive");
        httpURLConnection.connect();
        if (httpURLConnection.getResponseCode() != 200) {
            return 0;
        }
        int contentLength = httpURLConnection.getContentLength();
        q.a("图片大小==" + contentLength);
        return contentLength;
    }

    public void c() {
        ap c = ap.c();
        this.l.show();
        this.q = "shop/payment";
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("token", d());
            jSONObject.put("session", c.d());
            jSONObject.put("device", this.o.toJson());
        } catch (JSONException e) {
        }
        this.s.b(this.q, jSONObject.toString());
        this.l.setOnCancelListener(new j_2(this));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.lang.String r7, java.lang.String r8) {
        /*
        r6 = this;
        r0 = 0;
        r2 = 1;
        super.a(r7, r8);
        r3 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x00e8 }
        r3.<init>(r8);	 Catch:{ JSONException -> 0x00e8 }
        r1 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x00e8 }
        r1.<init>();	 Catch:{ JSONException -> 0x00e8 }
        r4 = "===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00e8 }
        r1 = r1.append(r7);	 Catch:{ JSONException -> 0x00e8 }
        r4 = "返回===";
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00e8 }
        r4 = r3.toString();	 Catch:{ JSONException -> 0x00e8 }
        r1 = r1.append(r4);	 Catch:{ JSONException -> 0x00e8 }
        r1 = r1.toString();	 Catch:{ JSONException -> 0x00e8 }
        com.ecjia.a.q.a(r1);	 Catch:{ JSONException -> 0x00e8 }
        r1 = "status";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00e8 }
        r1 = com.ecjia.hamster.model.ax.a(r1);	 Catch:{ JSONException -> 0x00e8 }
        r6.r = r1;	 Catch:{ JSONException -> 0x00e8 }
        r1 = -1;
        r4 = r7.hashCode();	 Catch:{ JSONException -> 0x00e8 }
        switch(r4) {
            case -1628310547: goto L_0x005b;
            case 1111910523: goto L_0x0051;
            default: goto L_0x0042;
        };	 Catch:{ JSONException -> 0x00e8 }
    L_0x0042:
        switch(r1) {
            case 0: goto L_0x0065;
            case 1: goto L_0x010e;
            default: goto L_0x0045;
        };	 Catch:{ JSONException -> 0x00e8 }
    L_0x0045:
        r6.g();	 Catch:{ JSONException -> 0x00e8 }
        r0 = r6.r;	 Catch:{ JSONException -> 0x00e8 }
        r6.a(r7, r8, r0);	 Catch:{ JSONException -> 0x00e8 }
    L_0x004d:
        r6.e(r8);
        return;
    L_0x0051:
        r4 = "shop/config";
        r4 = r7.equals(r4);	 Catch:{ JSONException -> 0x00e8 }
        if (r4 == 0) goto L_0x0042;
    L_0x0059:
        r1 = r0;
        goto L_0x0042;
    L_0x005b:
        r4 = "shop/payment";
        r4 = r7.equals(r4);	 Catch:{ JSONException -> 0x00e8 }
        if (r4 == 0) goto L_0x0042;
    L_0x0063:
        r1 = r2;
        goto L_0x0042;
    L_0x0065:
        r1 = r6.r;	 Catch:{ JSONException -> 0x00e8 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00e8 }
        if (r1 != r2) goto L_0x0045;
    L_0x006d:
        r1 = "data";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00e8 }
        r3 = "sdcard/android/data/com.ecmoban.android.missmall/shop_config";
        r4 = "shopconfig";
        r5 = r1.toString();	 Catch:{ JSONException -> 0x00e8 }
        com.ecjia.a.j.a(r3, r4, r5);	 Catch:{ JSONException -> 0x00e8 }
        r3 = com.ecjia.hamster.model.k.a(r1);	 Catch:{ JSONException -> 0x00e8 }
        r6.a = r3;	 Catch:{ JSONException -> 0x00e8 }
        r3 = "recommend_city";
        r1 = r1.optJSONArray(r3);	 Catch:{ JSONException -> 0x00e8 }
        r3 = r6.c;	 Catch:{ JSONException -> 0x00e8 }
        r3.clear();	 Catch:{ JSONException -> 0x00e8 }
        if (r1 == 0) goto L_0x00ad;
    L_0x0091:
        r3 = r1.length();	 Catch:{ JSONException -> 0x00e8 }
        if (r3 <= 0) goto L_0x00ad;
    L_0x0097:
        r3 = r1.length();	 Catch:{ JSONException -> 0x00e8 }
        if (r0 >= r3) goto L_0x00ad;
    L_0x009d:
        r3 = r6.c;	 Catch:{ JSONException -> 0x00e8 }
        r4 = r1.optJSONObject(r0);	 Catch:{ JSONException -> 0x00e8 }
        r4 = com.ecjia.hamster.model.ak.a(r4);	 Catch:{ JSONException -> 0x00e8 }
        r3.add(r4);	 Catch:{ JSONException -> 0x00e8 }
        r0 = r0 + 1;
        goto L_0x0097;
    L_0x00ad:
        r0 = r6.a;	 Catch:{ JSONException -> 0x00e8 }
        r0 = r0.f();	 Catch:{ JSONException -> 0x00e8 }
        if (r0 != r2) goto L_0x00c9;
    L_0x00b5:
        r0 = new android.content.Intent;	 Catch:{ JSONException -> 0x00e8 }
        r1 = r6.n;	 Catch:{ JSONException -> 0x00e8 }
        r2 = com.ecjia.hamster.activity.ECJiaAppOutActivity.class;
        r0.<init>(r1, r2);	 Catch:{ JSONException -> 0x00e8 }
        r1 = "flag";
        r2 = 1;
        r0.putExtra(r1, r2);	 Catch:{ JSONException -> 0x00e8 }
        r1 = r6.n;	 Catch:{ JSONException -> 0x00e8 }
        r1.startActivity(r0);	 Catch:{ JSONException -> 0x00e8 }
    L_0x00c9:
        r0 = r6.d;	 Catch:{ JSONException -> 0x00e8 }
        r1 = "login_bg";
        r2 = 0;
        r0 = r0.getInt(r1, r2);	 Catch:{ JSONException -> 0x00e8 }
        r1 = r6.a;	 Catch:{ JSONException -> 0x00e8 }
        r1 = r1.a();	 Catch:{ JSONException -> 0x00e8 }
        r1 = android.text.TextUtils.isEmpty(r1);	 Catch:{ JSONException -> 0x00e8 }
        if (r1 != 0) goto L_0x0045;
    L_0x00de:
        r1 = new com.ecjia.component.a.j$3;	 Catch:{ JSONException -> 0x00e8 }
        r1.<init>(r6, r0);	 Catch:{ JSONException -> 0x00e8 }
        r1.start();	 Catch:{ JSONException -> 0x00e8 }
        goto L_0x0045;
    L_0x00e8:
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
    L_0x010e:
        r1 = r6.r;	 Catch:{ JSONException -> 0x00e8 }
        r1 = r1.b();	 Catch:{ JSONException -> 0x00e8 }
        if (r1 != r2) goto L_0x0045;
    L_0x0116:
        r1 = "data";
        r1 = r3.optJSONObject(r1);	 Catch:{ JSONException -> 0x00e8 }
        r2 = "payment";
        r1 = r1.optJSONArray(r2);	 Catch:{ JSONException -> 0x00e8 }
        r2 = r6.m;	 Catch:{ JSONException -> 0x00e8 }
        r2 = r2.g;	 Catch:{ JSONException -> 0x00e8 }
        r2.clear();	 Catch:{ JSONException -> 0x00e8 }
        r2 = r6.m;	 Catch:{ JSONException -> 0x00e8 }
        r2 = r2.e;	 Catch:{ JSONException -> 0x00e8 }
        r2.clear();	 Catch:{ JSONException -> 0x00e8 }
        r2 = r6.m;	 Catch:{ JSONException -> 0x00e8 }
        r2 = r2.f;	 Catch:{ JSONException -> 0x00e8 }
        r2.clear();	 Catch:{ JSONException -> 0x00e8 }
        r2 = r6.m;	 Catch:{ JSONException -> 0x00e8 }
        r2 = r2.d;	 Catch:{ JSONException -> 0x00e8 }
        r2.clear();	 Catch:{ JSONException -> 0x00e8 }
        if (r1 == 0) goto L_0x0045;
    L_0x0140:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00e8 }
        if (r2 <= 0) goto L_0x0045;
    L_0x0146:
        r2 = r1.length();	 Catch:{ JSONException -> 0x00e8 }
        if (r0 >= r2) goto L_0x0045;
    L_0x014c:
        r2 = r1.optJSONObject(r0);	 Catch:{ JSONException -> 0x00e8 }
        r2 = com.ecjia.hamster.model.ECJia_PAYMENT.fromJson(r2);	 Catch:{ JSONException -> 0x00e8 }
        r3 = r6.m;	 Catch:{ JSONException -> 0x00e8 }
        r3 = r3.d;	 Catch:{ JSONException -> 0x00e8 }
        r3.add(r2);	 Catch:{ JSONException -> 0x00e8 }
        r3 = "1";
        r4 = r2.getIs_online();	 Catch:{ JSONException -> 0x00e8 }
        r3 = r3.equals(r4);	 Catch:{ JSONException -> 0x00e8 }
        if (r3 == 0) goto L_0x0197;
    L_0x0167:
        r3 = r6.m;	 Catch:{ JSONException -> 0x00e8 }
        r3 = r3.e;	 Catch:{ JSONException -> 0x00e8 }
        r3.add(r2);	 Catch:{ JSONException -> 0x00e8 }
    L_0x016e:
        r3 = "1";
        r4 = r2.getIs_online();	 Catch:{ JSONException -> 0x00e8 }
        r3 = r3.equals(r4);	 Catch:{ JSONException -> 0x00e8 }
        if (r3 == 0) goto L_0x0194;
    L_0x017a:
        r3 = "pay_balance";
        r4 = r2.getPay_code();	 Catch:{ JSONException -> 0x00e8 }
        r3 = r3.equals(r4);	 Catch:{ JSONException -> 0x00e8 }
        if (r3 != 0) goto L_0x0194;
    L_0x0186:
        r3 = r2.getPay_code();	 Catch:{ JSONException -> 0x00e8 }
        com.ecjia.a.q.a(r3);	 Catch:{ JSONException -> 0x00e8 }
        r3 = r6.m;	 Catch:{ JSONException -> 0x00e8 }
        r3 = r3.g;	 Catch:{ JSONException -> 0x00e8 }
        r3.add(r2);	 Catch:{ JSONException -> 0x00e8 }
    L_0x0194:
        r0 = r0 + 1;
        goto L_0x0146;
    L_0x0197:
        r3 = r6.m;	 Catch:{ JSONException -> 0x00e8 }
        r3 = r3.f;	 Catch:{ JSONException -> 0x00e8 }
        r3.add(r2);	 Catch:{ JSONException -> 0x00e8 }
        goto L_0x016e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ecjia.component.a.j.a(java.lang.String, java.lang.String):void");
    }
}
