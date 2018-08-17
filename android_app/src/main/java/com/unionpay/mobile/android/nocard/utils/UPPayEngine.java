package com.unionpay.mobile.android.nocard.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import com.umeng.analytics.pro.x;
import com.unionpay.mobile.android.h.c;
import com.unionpay.mobile.android.h.d;
import com.unionpay.mobile.android.nocard.views.ah;
import com.unionpay.mobile.android.utils.f;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.mobile.android.utils.k;
import java.lang.ref.WeakReference;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class UPPayEngine implements Callback, com.unionpay.mobile.android.c.a, Runnable {
    protected c a = null;
    private d b = null;
    private String c = null;
    private String d = null;
    private Context e = null;
    private Handler f = null;
    private WeakReference<a> g = null;
    private com.unionpay.mobile.android.g.b h = null;
    private long i = 0;

    public interface a {
        void a(int i, String str);
    }

    class b {
        public int a;
        public String b;
        final /* synthetic */ UPPayEngine c;

        public b(UPPayEngine uPPayEngine, int i, String str) {
            this.c = uPPayEngine;
            this.a = i;
            this.b = str;
        }
    }

    public UPPayEngine(Context context) {
        this.e = context;
        this.f = new Handler(this);
    }

    private native String commonMessage(long j, String str, String str2, String str3);

    private native String decryptResponse(long j, String str);

    private native String desEncryptMessage(long j, String str, String str2);

    private native String encryptMessage(long j, String str);

    private native String followRulesMessage(long j, String str, String str2);

    private native String getServerUrl(int i, int i2, int i3);

    private native String getTalkingDataId(int i);

    private native String getUserInfo(long j, String str, String str2);

    protected static String i() {
        return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date(System.currentTimeMillis()));
    }

    private native String initMessage(long j, String str, String str2);

    private void o(String str) {
        new Thread(this, str).start();
    }

    private native String openupgradeMessage(long j, String str, String str2);

    private native String payingMessage(long j, String str, String str2, String str3, String str4, String str5);

    private native String retrieveInitializeKey(long j);

    private native String rsaEncryptMessageForHFT(long j, String str);

    private native String rsaPrivateEncryptMessage(long j, String str);

    private native String ruleMessage(long j, String str, String str2);

    private native void setSessionKey(long j, String str);

    private native String unBoundMessage(long j, String str, String str2);

    public String a(String str) {
        String jSONObject;
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            jSONObject2.put("reqtm", i());
            jSONObject = jSONObject2.toString();
        } catch (JSONException e) {
            jSONObject = str;
        }
        k.c("uppay", "post message = " + str);
        this.b.a(encryptMessage(this.i, jSONObject));
        HashMap hashMap = new HashMap(1);
        hashMap.put("sid", this.c);
        this.b.a(hashMap);
        g();
        if (this.a == null) {
            this.a = new c(this.b, this.e);
        }
        int a = this.a.a();
        String c = this.a.c();
        if (a == 0) {
            jSONObject = decryptResponse(this.i, c);
            k.a("uppay", "[ response msg ] " + jSONObject);
            return jSONObject;
        }
        Message obtainMessage = this.f.obtainMessage(2);
        obtainMessage.arg1 = a;
        this.f.sendMessage(obtainMessage);
        return null;
    }

    public final String a(String str, String str2) {
        return desEncryptMessage(this.i, str, str2);
    }

    public final void a() {
        String serverUrl;
        int i = 2;
        if (TextUtils.isEmpty(this.h.bk)) {
            int i2 = this.h.I.c.equalsIgnoreCase("01") ? 1 : this.h.I.c.equalsIgnoreCase("02") ? 2 : this.h.I.c.equalsIgnoreCase("98") ? 98 : this.h.I.c.equalsIgnoreCase("99") ? 99 : "95".equalsIgnoreCase(this.h.I.c) ? 95 : 0;
            k.a("uppay", "idx  is : " + i2 + ", isNewTypeTn :" + this.h.c);
            if (!this.h.f) {
                i = this.h.c ? 1 : 0;
            }
            serverUrl = getServerUrl(i, i2, this.h.aO);
        } else {
            serverUrl = this.h.f ? this.h.bk + "/app/mobile/hft" : this.h.c ? this.h.bk + "/app/mobile/json" : this.h.bk + "/gateway/mobile/json";
        }
        k.a("uppay", "url  is : " + serverUrl);
        this.b = new d(serverUrl);
    }

    public final void a(long j) {
        this.i = j;
    }

    public final void a(com.unionpay.mobile.android.g.b bVar) {
        if (this.h == null || this.h != bVar) {
            this.h = bVar;
        }
    }

    public final void a(a aVar) {
        this.g = new WeakReference(aVar);
    }

    public final void a(String str, String str2, int i) {
        this.b.a(commonMessage(this.i, str, str2, i()));
        HashMap hashMap = new HashMap(1);
        hashMap.put("sid", this.c);
        this.b.a(hashMap);
        if (i <= 0) {
            o(str);
            return;
        }
        this.f.sendMessageDelayed(this.f.obtainMessage(1, str), (long) (i * 1000));
    }

    public final void a(String str, String str2, String str3, String str4) {
        this.b.a(payingMessage(this.i, str, str2, str3, str4, i()));
        HashMap hashMap = new HashMap(1);
        hashMap.put("sid", this.c);
        this.b.a(hashMap);
        o("pay");
    }

    public final String b() {
        return this.d;
    }

    public final String b(String str) {
        String str2 = "00";
        if (!i.c(str)) {
            str = str2;
        }
        return getTalkingDataId(Integer.decode(str).intValue());
    }

    public final void b(String str, String str2) {
        String b;
        if (this.h.f) {
            b = ah.b(this.e, str, anet.channel.strategy.dispatch.c.ANDROID, this.h.a(), this.h.g, this.h.d);
        } else {
            b = ah.a(this.e, str, anet.channel.strategy.dispatch.c.ANDROID, this.h.a(), this.h.g, str2);
        }
        this.b.a(initMessage(this.i, b, i()));
        HashMap hashMap = new HashMap(1);
        hashMap.put(x.c, retrieveInitializeKey(this.i));
        this.b.a(hashMap);
        o("init");
    }

    public final long c() {
        return this.i;
    }

    public final void c(String str) {
        this.c = str;
    }

    public final void c(String str, String str2) {
        a(str, str2, 0);
    }

    public final d d() {
        return this.b;
    }

    public final void d(String str) {
        this.d = str;
    }

    public final Handler e() {
        return this.f;
    }

    public final boolean e(String str) {
        setSessionKey(this.i, str);
        return true;
    }

    public final String f() {
        return this.c;
    }

    public final String f(String str) {
        return encryptMessage(this.i, str);
    }

    public final String g(String str) {
        return decryptResponse(this.i, str);
    }

    protected final void g() {
        Object c = this.b.c();
        if (!TextUtils.isEmpty(c)) {
            try {
                JSONObject jSONObject = new JSONObject(g(c));
                this.b.a(this.e, jSONObject.getString(com.taobao.agoo.a.a.b.JSON_CMD), this.h.b + jSONObject.getString("reqtm") + f.d(this.e));
            } catch (JSONException e) {
                this.b.a(this.e, "uppay", "1234567890");
            }
        }
    }

    public final String h(String str) {
        return rsaPrivateEncryptMessage(this.i, str);
    }

    public final void h() {
        this.e = null;
        this.f.removeCallbacksAndMessages(null);
        this.f = null;
        this.b = null;
        this.h = null;
        this.a = null;
    }

    public boolean handleMessage(Message message) {
        if (message.what == 0) {
            String str;
            b bVar = (b) message.obj;
            if (bVar.a == 0) {
                String decryptResponse = decryptResponse(this.i, bVar.b);
                k.a("uppay", "resp is:" + decryptResponse);
                str = decryptResponse;
            } else {
                str = null;
            }
            if (!(this.g == null || this.g.get() == null)) {
                ((a) this.g.get()).a(bVar.a, str);
                k.b("uppayEx", "UPPayEngine:" + this.g.toString());
            }
        } else if (message.what == 1) {
            o((String) message.obj);
        } else if (!(message.what != 2 || this.g == null || this.g.get() == null)) {
            ((a) this.g.get()).a(message.arg1, null);
        }
        return true;
    }

    public final String i(String str) {
        return rsaEncryptMessageForHFT(this.i, str);
    }

    public native long initJNIEnv(Activity activity, int i, int i2, boolean z, String str, int i3, String str2);

    public final void j(String str) {
        this.b.a(ruleMessage(this.i, str, i()));
        HashMap hashMap = new HashMap(1);
        hashMap.put("sid", this.c);
        this.b.a(hashMap);
        o("rule");
    }

    public final void k(String str) {
        this.b.a(followRulesMessage(this.i, str, i()));
        HashMap hashMap = new HashMap(1);
        hashMap.put("sid", this.c);
        this.b.a(hashMap);
        o("followRule");
    }

    public final void l(String str) {
        this.b.a(openupgradeMessage(this.i, str, i()));
        HashMap hashMap = new HashMap(1);
        hashMap.put("sid", this.c);
        this.b.a(hashMap);
        o("openupgrade");
    }

    public final void m(String str) {
        this.b.a(unBoundMessage(this.i, str, i()));
        HashMap hashMap = new HashMap(1);
        hashMap.put("sid", this.c);
        this.b.a(hashMap);
        o("unbindcard");
    }

    public final void n(String str) {
        String userInfo = getUserInfo(this.i, str, i());
        k.a("uppay", "actEntrust msg:" + userInfo);
        this.b.a(userInfo);
        HashMap hashMap = new HashMap(1);
        hashMap.put("sid", this.c);
        this.b.a(hashMap);
        o("getuserinfo");
    }

    public void run() {
        try {
            if (this.h == null || this.h.aO <= 0 || this.h.aO > 5) {
                this.b.d().put("magic_number", "20131120");
            } else {
                this.b.d().put("magic_number", "20150423");
            }
            g();
            if (this.a == null) {
                this.a = new c(this.b, this.e);
            }
            b bVar = new b(this, this.a.a(), this.a.c());
            if (this.f != null) {
                Message obtainMessage = this.f.obtainMessage();
                obtainMessage.what = 0;
                obtainMessage.obj = bVar;
                this.f.sendMessage(obtainMessage);
            }
        } catch (NullPointerException e) {
        } catch (Exception e2) {
        }
    }
}
