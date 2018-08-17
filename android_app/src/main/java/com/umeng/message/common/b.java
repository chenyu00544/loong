package com.umeng.message.common;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.umeng.analytics.pro.x;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.proguard.c;
import org.json.JSONObject;

/* compiled from: Header */
public class b {
    public static final String E = "Android";
    public static final String F = "Android";
    private static final String G = b.class.getName();
    public String A;
    public String B;
    public String C;
    public String D;
    private final String H = "appkey";
    private final String I = "channel";
    private final String J = "device_id";
    private final String K = "idmd5";
    private final String L = "mc";
    private final String M = "umid";
    private final String N = "din";
    private final String O = x.M;
    private final String P = "android_id";
    private final String Q = "serial_number";
    private final String R = x.v;
    private final String S = "os";
    private final String T = x.q;
    private final String U = x.r;
    private final String V = x.o;
    private final String W = "gpu_vender";
    private final String X = "gpu_renderer";
    private final String Y = "app_version";
    private final String Z = "version_code";
    public String a;
    private final String aa = x.e;
    private final String ab = x.k;
    private final String ac = "sdk_version";
    private final String ad = x.E;
    private final String ae = x.G;
    private final String af = x.F;
    private final String ag = x.I;
    private final String ah = x.J;
    private final String ai = "carrier";
    private final String aj = x.i;
    private final String ak = x.j;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public long h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;
    public String r;
    public String s;
    public String t;
    public String u;
    public String v;
    public int w;
    public String x;
    public String y;
    public String z;

    public b(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    private void d(JSONObject jSONObject) throws Exception {
        this.a = jSONObject.getString("appkey");
        this.c = jSONObject.getString("device_id");
        this.d = jSONObject.getString("idmd5");
        if (jSONObject.has("mc")) {
            this.e = jSONObject.getString("mc");
        }
        if (jSONObject.has("channel")) {
            this.b = jSONObject.getString("channel");
        }
        if (jSONObject.has(x.M)) {
            this.h = jSONObject.getLong(x.M);
        }
    }

    private void e(JSONObject jSONObject) throws Exception {
        String string;
        String str = null;
        this.k = jSONObject.has(x.v) ? jSONObject.getString(x.v) : null;
        if (jSONObject.has("os")) {
            string = jSONObject.getString("os");
        } else {
            string = null;
        }
        this.l = string;
        if (jSONObject.has(x.q)) {
            string = jSONObject.getString(x.q);
        } else {
            string = null;
        }
        this.m = string;
        if (jSONObject.has(x.r)) {
            string = jSONObject.getString(x.r);
        } else {
            string = null;
        }
        this.n = string;
        if (jSONObject.has(x.o)) {
            string = jSONObject.getString(x.o);
        } else {
            string = null;
        }
        this.o = string;
        if (jSONObject.has("gpu_vender")) {
            string = jSONObject.getString("gpu_vender");
        } else {
            string = null;
        }
        this.p = string;
        if (jSONObject.has("gpu_renderer")) {
            string = jSONObject.getString("gpu_renderer");
        } else {
            string = null;
        }
        this.q = string;
        if (jSONObject.has("android_id")) {
            string = jSONObject.getString("android_id");
        } else {
            string = null;
        }
        this.i = string;
        if (jSONObject.has("serial_number")) {
            str = jSONObject.getString("serial_number");
        }
        this.j = str;
    }

    private void f(JSONObject jSONObject) throws Exception {
        String string;
        String str = null;
        this.r = jSONObject.has("app_version") ? jSONObject.getString("app_version") : null;
        if (jSONObject.has("version_code")) {
            string = jSONObject.getString("version_code");
        } else {
            string = null;
        }
        this.s = string;
        if (jSONObject.has(x.e)) {
            str = jSONObject.getString(x.e);
        }
        this.t = str;
    }

    private void g(JSONObject jSONObject) throws Exception {
        this.u = jSONObject.getString(x.k);
        this.v = jSONObject.getString("sdk_version");
    }

    private void h(JSONObject jSONObject) throws Exception {
        String string;
        String str = null;
        this.w = jSONObject.has(x.E) ? jSONObject.getInt(x.E) : 8;
        if (jSONObject.has(x.G)) {
            string = jSONObject.getString(x.G);
        } else {
            string = null;
        }
        this.x = string;
        if (jSONObject.has(x.F)) {
            str = jSONObject.getString(x.F);
        }
        this.y = str;
    }

    private void i(JSONObject jSONObject) throws Exception {
        String string;
        String str = null;
        if (jSONObject.has(x.I)) {
            string = jSONObject.getString(x.I);
        } else {
            string = null;
        }
        this.z = string;
        if (jSONObject.has(x.J)) {
            string = jSONObject.getString(x.J);
        } else {
            string = null;
        }
        this.A = string;
        if (jSONObject.has("carrier")) {
            str = jSONObject.getString("carrier");
        }
        this.B = str;
    }

    private void j(JSONObject jSONObject) throws Exception {
        String string;
        String str = null;
        if (jSONObject.has(x.i)) {
            string = jSONObject.getString(x.i);
        } else {
            string = null;
        }
        this.C = string;
        if (jSONObject.has(x.j)) {
            str = jSONObject.getString(x.j);
        }
        this.D = str;
    }

    public void a(JSONObject jSONObject) throws Exception {
        if (jSONObject != null) {
            d(jSONObject);
            e(jSONObject);
            f(jSONObject);
            g(jSONObject);
            h(jSONObject);
            i(jSONObject);
            j(jSONObject);
        }
    }

    private void k(JSONObject jSONObject) throws Exception {
        jSONObject.put("appkey", this.a);
        if (this.a == null || 24 != this.a.length()) {
            this.c = c.a(this.c, "utf-8");
            this.g = c.a(this.g, "utf-8");
        } else {
            this.c = c.a(this.c, "utf-8", this.a.substring(0, 16));
            this.g = c.a(this.g, "utf-8", this.a.substring(0, 16));
        }
        jSONObject.put("device_id", this.c);
        jSONObject.put("idmd5", this.d);
        if (this.b != null) {
            jSONObject.put("channel", this.b);
        }
        if (this.e != null) {
            jSONObject.put("mc", this.e);
        }
        if (this.h > 0) {
            jSONObject.put(x.M, this.h);
        }
        if (this.i != null) {
            jSONObject.put("android_id", this.i);
        }
        if (this.j != null) {
            jSONObject.put("serial_number", this.j);
        }
        jSONObject.put("umid", this.f);
        jSONObject.put("din", this.g);
    }

    private void l(JSONObject jSONObject) throws Exception {
        jSONObject.put("appkey", this.a);
        if (this.a == null || 24 != this.a.length()) {
            this.g = c.a(this.g, "utf-8");
        } else {
            this.g = c.a(this.g, "utf-8", this.a.substring(0, 16));
        }
        if (this.b != null) {
            jSONObject.put("channel", this.b);
        }
        jSONObject.put("umid", this.f);
        jSONObject.put("din", this.g);
    }

    private void m(JSONObject jSONObject) throws Exception {
        if (this.k != null) {
            jSONObject.put(x.v, this.k);
        }
        if (this.l != null) {
            jSONObject.put("os", this.l);
        }
        if (this.m != null) {
            jSONObject.put(x.q, this.m);
        }
        if (this.n != null) {
            jSONObject.put(x.r, this.n);
        }
        if (this.o != null) {
            jSONObject.put(x.o, this.o);
        }
        if (this.p != null) {
            jSONObject.put("gpu_vender", this.p);
        }
        if (this.q != null) {
            jSONObject.put("gpu_vender", this.q);
        }
    }

    private void n(JSONObject jSONObject) throws Exception {
        if (this.k != null) {
            jSONObject.put(x.v, this.k);
        }
        if (this.l != null) {
            jSONObject.put("os", this.l);
        }
        if (this.m != null) {
            jSONObject.put(x.q, this.m);
        }
    }

    private void o(JSONObject jSONObject) throws Exception {
        if (this.r != null) {
            jSONObject.put("app_version", this.r);
        }
        if (this.s != null) {
            jSONObject.put("version_code", this.s);
        }
        if (this.t != null) {
            jSONObject.put(x.e, this.t);
        }
    }

    private void p(JSONObject jSONObject) throws Exception {
        if (this.r != null) {
            jSONObject.put("app_version", this.r);
        }
        if (this.s != null) {
            jSONObject.put("version_code", this.s);
        }
    }

    private void q(JSONObject jSONObject) throws Exception {
        jSONObject.put(x.k, this.u);
        jSONObject.put("sdk_version", this.v);
    }

    private void r(JSONObject jSONObject) throws Exception {
        jSONObject.put(x.E, this.w);
        if (this.x != null) {
            jSONObject.put(x.G, this.x);
        }
        if (this.y != null) {
            jSONObject.put(x.F, this.y);
        }
    }

    private void s(JSONObject jSONObject) throws Exception {
        if (this.z != null) {
            jSONObject.put(x.I, this.z);
        }
        if (this.A != null) {
            jSONObject.put(x.J, this.A);
        }
        if (this.B != null) {
            jSONObject.put("carrier", this.B);
        }
    }

    private void t(JSONObject jSONObject) throws Exception {
        if (this.C != null) {
            jSONObject.put(x.i, this.C);
        }
        if (this.D != null) {
            jSONObject.put(x.j, this.D);
        }
    }

    public void b(JSONObject jSONObject) throws Exception {
        k(jSONObject);
        m(jSONObject);
        o(jSONObject);
        q(jSONObject);
        r(jSONObject);
        s(jSONObject);
        t(jSONObject);
    }

    public void c(JSONObject jSONObject) throws Exception {
        l(jSONObject);
        n(jSONObject);
        p(jSONObject);
        q(jSONObject);
        s(jSONObject);
    }

    public boolean a() {
        if (this.a == null) {
            UmLog.e(G, "missing appkey ");
            return false;
        } else if (this.c != null && this.d != null) {
            return true;
        } else {
            UmLog.e(G, "missing device id");
            return false;
        }
    }

    public void a(Context context, String... strArr) {
        if (strArr != null && strArr.length == 2) {
            this.a = strArr[0];
            this.b = strArr[1];
        }
        if (this.a == null) {
            this.a = PushAgent.getInstance(context).getMessageAppkey();
        }
        if (this.b == null) {
            this.b = PushAgent.getInstance(context).getMessageChannel();
        }
        this.c = UmengMessageDeviceConfig.getDeviceId(context);
        this.d = UmengMessageDeviceConfig.getDeviceIdMD5(context);
        this.e = UmengMessageDeviceConfig.getMac(context);
        this.g = UmengMessageDeviceConfig.getDIN(context);
        this.f = UmengMessageDeviceConfig.getUmid(context);
    }

    public void a(Context context) {
        this.k = Build.MODEL;
        this.l = "Android";
        this.m = VERSION.RELEASE;
        this.n = UmengMessageDeviceConfig.getResolution(context);
        this.o = UmengMessageDeviceConfig.getCPU();
        this.i = UmengMessageDeviceConfig.getAndroidId(context);
        this.j = UmengMessageDeviceConfig.getSerial_number();
    }

    public void b(Context context) {
        this.r = UmengMessageDeviceConfig.getAppVersionName(context);
        this.s = UmengMessageDeviceConfig.getAppVersionCode(context);
        this.t = UmengMessageDeviceConfig.getPackageName(context);
    }

    public void c(Context context) {
        this.u = "Android";
        this.v = MsgConstant.SDK_VERSION;
    }

    public void d(Context context) {
        this.w = UmengMessageDeviceConfig.getTimeZone(context);
        String[] localeInfo = UmengMessageDeviceConfig.getLocaleInfo(context);
        this.x = localeInfo[0];
        this.y = localeInfo[1];
    }

    public void e(Context context) {
        String[] networkAccessMode = UmengMessageDeviceConfig.getNetworkAccessMode(context);
        this.z = networkAccessMode[0];
        this.A = networkAccessMode[1];
        this.B = UmengMessageDeviceConfig.getOperator(context);
    }

    public void b(Context context, String... strArr) {
        a(context, strArr);
        a(context);
        b(context);
        c(context);
        d(context);
        e(context);
    }

    public void c(Context context, String... strArr) {
        a(context, strArr);
        a(context);
        b(context);
        c(context);
        e(context);
    }

    public boolean b() {
        if (this.a == null || this.c == null) {
            return false;
        }
        return true;
    }
}
