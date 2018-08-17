package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Base64;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.pro.by.b;
import com.umeng.analytics.pro.by.c;
import com.umeng.analytics.pro.by.d;
import com.umeng.analytics.pro.by.e;
import com.umeng.analytics.pro.by.f;
import com.umeng.analytics.pro.by.g;
import com.umeng.analytics.pro.by.h;
import com.umeng.analytics.pro.by.i;
import com.umeng.analytics.pro.by.j;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CacheImpl */
public final class ap implements at, ay {
    private static Context j = null;
    private static final String q = "thtstart";
    private static final String r = "gkvc";
    private static final String s = "ekvc";
    String a = null;
    private ca b = null;
    private bc c = null;
    private bf d = null;
    private be e = null;
    private bg f = null;
    private a g = null;
    private com.umeng.analytics.pro.af.a h = null;
    private long i = 0;
    private int k = 10;
    private JSONArray l = new JSONArray();
    private final int m = 5000;
    private int n = 0;
    private int o = 0;
    private long p = 0;
    private final long t = 28800000;

    /* compiled from: CacheImpl */
    class ap_1 extends bz {
        final /* synthetic */ ap a;

        ap_1(ap apVar) {
            this.a = apVar;
        }

        public void a() {
            this.a.a();
        }
    }

    /* compiled from: CacheImpl */
    public class a {
        final /* synthetic */ ap a;
        private h b;
        private int c = -1;
        private int d = -1;
        private int e = -1;
        private int f = -1;

        public a(ap apVar) {
            this.a = apVar;
            int[] a = apVar.h.a(-1, -1);
            this.c = a[0];
            this.d = a[1];
        }

        protected void a(boolean z) {
            int i = 1;
            int i2 = 0;
            if (this.a.d.d()) {
                h hVar;
                if (!((this.b instanceof b) && this.b.a())) {
                    i = 0;
                }
                if (i != 0) {
                    hVar = this.b;
                } else {
                    hVar = new b(this.a.c, this.a.d);
                }
                this.b = hVar;
                return;
            }
            if (!((this.b instanceof c) && this.b.a())) {
                i = 0;
            }
            if (i != 0) {
                return;
            }
            if (z && this.a.f.a()) {
                this.b = new c((int) this.a.f.b());
                this.a.b((int) this.a.f.b());
            } else if (bw.a && this.a.h.b()) {
                this.b = new com.umeng.analytics.pro.by.a(this.a.c);
            } else if (this.a.e.a() && "RPT".equals(this.a.e.f())) {
                if (this.a.e.b() == 6) {
                    if (this.a.h.a()) {
                        i2 = this.a.h.d(90000);
                    } else if (this.d > 0) {
                        i2 = this.d;
                    } else {
                        i2 = this.f;
                    }
                }
                this.b = b(this.a.e.b(), i2);
            } else {
                i = this.e;
                i2 = this.f;
                if (this.c != -1) {
                    i = this.c;
                    i2 = this.d;
                }
                this.b = b(i, i2);
            }
        }

        public h b(boolean z) {
            a(z);
            return this.b;
        }

        private h b(int i, int i2) {
            switch (i) {
                case 0:
                    return this.b instanceof g ? this.b : new g();
                case 1:
                    return this.b instanceof d ? this.b : new d();
                case 4:
                    if (this.b instanceof f) {
                        return this.b;
                    }
                    return new f(this.a.c);
                case 5:
                    if (this.b instanceof i) {
                        return this.b;
                    }
                    return new i(ap.j);
                case 6:
                    if (!(this.b instanceof e)) {
                        return new e(this.a.c, (long) i2);
                    }
                    h hVar = this.b;
                    ((e) hVar).a((long) i2);
                    return hVar;
                case 8:
                    if (this.b instanceof j) {
                        return this.b;
                    }
                    return new j(this.a.c);
                default:
                    if (this.b instanceof d) {
                        return this.b;
                    }
                    return new d();
            }
        }

        public void a(int i, int i2) {
            this.e = i;
            this.f = i2;
        }

        public void a(com.umeng.analytics.pro.af.a aVar) {
            int[] a = aVar.a(-1, -1);
            this.c = a[0];
            this.d = a[1];
        }
    }

    public ap(Context context) {
        j = context;
        this.c = new bc(context);
        this.b = ca.a(context);
        this.h = af.a(context).b();
        this.g = new a(this);
        this.e = be.a(j);
        this.d = bf.a(j);
        this.f = bg.a(j, this.c);
        SharedPreferences a = az.a(j);
        this.p = a.getLong(q, 0);
        this.n = a.getInt(r, 0);
        this.o = a.getInt(s, 0);
        this.a = this.h.b(null);
    }

    public void a() {
        if (bt.l(j)) {
            d();
        } else {
            bw.b("network is unavailable");
        }
    }

    public void a(Object obj) {
        if (this.c.f()) {
            this.i = this.c.l();
        }
        boolean z = true;
        if (obj instanceof JSONObject) {
            z = false;
            try {
                b((JSONObject) obj);
            } catch (Throwable th) {
            }
        }
        if (a(z)) {
            d();
        }
    }

    private void b(JSONObject jSONObject) {
        try {
            if (w.b == jSONObject.getInt("__t")) {
                if (c(this.n)) {
                    this.n++;
                } else {
                    return;
                }
            } else if (w.a == jSONObject.getInt("__t")) {
                if (c(this.o)) {
                    this.o++;
                } else {
                    return;
                }
            }
            if (this.l.length() > this.k) {
                w.a(j).a(this.l);
                this.l = new JSONArray();
            }
            if (this.p == 0) {
                this.p = System.currentTimeMillis();
            }
            this.l.put(jSONObject);
        } catch (Throwable th) {
        }
    }

    public void b() {
        c(a(new int[0]));
    }

    private void a(int i) {
        int currentTimeMillis = (int) (System.currentTimeMillis() - this.c.m());
        c(a(i, currentTimeMillis));
        bx.a(new ap_1(this), (long) i);
    }

    private void c(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                ad a = ad.a(j);
                a.a();
                try {
                    CharSequence encodeToString = Base64.encodeToString(new cn().a(a.b()), 0);
                    if (!TextUtils.isEmpty(encodeToString)) {
                        JSONObject jSONObject2 = jSONObject.getJSONObject("header");
                        jSONObject2.put(x.O, encodeToString);
                        jSONObject.put("header", jSONObject2);
                    }
                } catch (Exception e) {
                }
                byte[] bytes = String.valueOf(jSONObject).getBytes();
                if (bytes != null && !br.a(j, bytes)) {
                    aa b;
                    if (e()) {
                        b = aa.b(j, AnalyticsConfig.getAppkey(j), bytes);
                    } else {
                        b = aa.a(j, AnalyticsConfig.getAppkey(j), bytes);
                    }
                    bytes = b.c();
                    ca a2 = ca.a(j);
                    a2.g();
                    a2.a(bytes);
                    a.d();
                }
            } catch (Exception e2) {
            }
        }
    }

    protected JSONObject a(int... iArr) {
        JSONObject a;
        try {
            if (TextUtils.isEmpty(AnalyticsConfig.getAppkey(j))) {
                bw.e("Appkey is missing ,Please check AndroidManifest.xml");
                return new JSONObject();
            }
            JSONObject jSONObject;
            CharSequence string;
            JSONObject jSONObject2;
            a(j);
            a = w.a(j).a();
            if (a == null) {
                a = new JSONObject();
            }
            try {
                jSONObject = a.getJSONObject("body");
            } catch (Throwable th) {
                jSONObject = new JSONObject();
            }
            JSONObject jSONObject3 = new JSONObject(jSONObject.toString());
            SharedPreferences a2 = az.a(j);
            if (a2 != null) {
                string = a2.getString("userlevel", "");
                if (!TextUtils.isEmpty(string)) {
                    jSONObject.put("userlevel", string);
                }
            }
            if (this.c.f() && this.i != 0) {
                jSONObject2 = new JSONObject();
                jSONObject2.put("ts", this.i);
                jSONObject.put(x.ak, jSONObject2);
                jSONObject3.put(x.ak, jSONObject2);
            }
            jSONObject2 = new JSONObject();
            JSONObject b = m.a(j).b();
            if (b != null && b.length() > 0) {
                jSONObject2.put(x.av, b);
            }
            b = m.a(j).c();
            if (b != null && b.length() > 0) {
                jSONObject2.put(x.aB, b);
            }
            if (jSONObject2.length() > 0) {
                jSONObject.put(x.au, jSONObject2);
                jSONObject3.put(x.au, jSONObject2);
            }
            String[] a3 = com.umeng.analytics.c.a(j);
            if (!(a3 == null || TextUtils.isEmpty(a3[0]) || TextUtils.isEmpty(a3[1]))) {
                b = new JSONObject();
                b.put(x.as, a3[0]);
                b.put(x.at, a3[1]);
                if (b.length() > 0) {
                    jSONObject.put(x.ar, b);
                    jSONObject3.put(x.ar, b);
                }
            }
            if (be.a(j).a()) {
                d(jSONObject);
            }
            this.d.a(jSONObject, j);
            if (iArr != null && iArr.length == 2) {
                jSONObject2 = new JSONObject();
                b = new JSONObject();
                b.put("interval", iArr[0] / 1000);
                b.put(x.ao, iArr[1]);
                jSONObject2.put(x.an, b);
                jSONObject.put(x.am, jSONObject2);
            }
            if (jSONObject.length() > 0) {
                a.put("body", jSONObject);
            } else {
                try {
                    a.remove("body");
                } catch (Throwable th2) {
                }
            }
            jSONObject = new JSONObject();
            jSONObject.put("appkey", AnalyticsConfig.getAppkey(j));
            jSONObject.put("channel", AnalyticsConfig.getChannel(j));
            string = bu.a(AnalyticsConfig.getSecretKey(j));
            if (!TextUtils.isEmpty(string)) {
                jSONObject.put(x.c, string);
            }
            jSONObject.put(x.g, bt.w(j));
            jSONObject.put(x.e, bt.t(j));
            jSONObject.put(x.f, bt.u(j));
            if (a2 == null) {
                try {
                    a2 = az.a(j);
                } catch (Throwable th3) {
                    jSONObject.put("app_version", bt.b(j));
                    jSONObject.put("version_code", Integer.parseInt(bt.a(j)));
                }
            }
            if (a2 != null) {
                string = a2.getString("vers_name", "");
                if (TextUtils.isEmpty(string)) {
                    jSONObject.put("app_version", bt.b(j));
                    jSONObject.put("version_code", Integer.parseInt(bt.a(j)));
                } else {
                    jSONObject.put("app_version", string);
                    jSONObject.put("version_code", a2.getInt("vers_code", 0));
                }
            }
            if (!(AnalyticsConfig.mWrapperType == null || AnalyticsConfig.mWrapperVersion == null)) {
                jSONObject.put(x.i, AnalyticsConfig.mWrapperType);
                jSONObject.put(x.j, AnalyticsConfig.mWrapperVersion);
            }
            jSONObject.put(x.k, "Android");
            jSONObject.put("sdk_version", AnalyticsConfig.getSDKVersion(j));
            jSONObject.put(x.m, AnalyticsConfig.getVerticalType(j));
            jSONObject.put("idmd5", bt.d(j));
            jSONObject.put(x.o, bt.a());
            jSONObject.put("os", "Android");
            jSONObject.put(x.q, VERSION.RELEASE);
            int[] r = bt.r(j);
            if (r != null) {
                jSONObject.put(x.r, r[1] + "*" + r[0]);
            }
            jSONObject.put("mc", bt.q(j));
            jSONObject.put("device_id", bt.c(j));
            jSONObject.put(x.v, Build.MODEL);
            jSONObject.put(x.w, Build.BOARD);
            jSONObject.put(x.x, Build.BRAND);
            jSONObject.put(x.y, Build.TIME);
            jSONObject.put(x.z, Build.MANUFACTURER);
            jSONObject.put(x.A, Build.ID);
            jSONObject.put(x.B, Build.DEVICE);
            string = bt.x(j);
            if (!TextUtils.isEmpty(string)) {
                jSONObject.put(x.C, string);
            }
            string = bt.y(j);
            if (!TextUtils.isEmpty(string)) {
                jSONObject.put(x.D, string);
            }
            a3 = bt.j(j);
            if ("Wi-Fi".equals(a3[0])) {
                jSONObject.put(x.I, UtilityImpl.NET_TYPE_WIFI);
            } else if (bt.c.equals(a3[0])) {
                jSONObject.put(x.I, bt.c);
            } else {
                jSONObject.put(x.I, "unknow");
            }
            if (!"".equals(a3[1])) {
                jSONObject.put(x.J, a3[1]);
            }
            string = bt.e(j);
            if (TextUtils.isEmpty(string)) {
                jSONObject.put(x.t, "");
            } else {
                jSONObject.put(x.t, string);
            }
            a3 = bt.o(j);
            jSONObject.put(x.G, a3[0]);
            jSONObject.put(x.F, a3[1]);
            jSONObject.put(x.E, bt.m(j));
            jSONObject.put("carrier", bt.h(j));
            try {
                jSONObject.put(x.K, a2.getInt("successful_request", 0));
                jSONObject.put(x.L, a2.getInt(x.L, 0));
                jSONObject.put(x.M, a2.getInt("last_request_spent_ms", 0));
            } catch (Exception e) {
            }
            try {
                ce a4 = af.a(j).a();
                if (a4 != null) {
                    jSONObject.put(x.N, Base64.encodeToString(new cn().a(a4), 0));
                }
            } catch (Exception e2) {
            }
            a.put("header", jSONObject);
            if (jSONObject.has("sdk_version")) {
                jSONObject3.put("sdk_version", jSONObject.getString("sdk_version"));
            }
            if (jSONObject.has("device_id")) {
                jSONObject3.put("device_id", jSONObject.getString("device_id"));
            }
            if (jSONObject.has(x.v)) {
                jSONObject3.put(x.v, jSONObject.getString(x.v));
            }
            if (jSONObject.has("version")) {
                jSONObject3.put("version", jSONObject.getInt("version_code"));
            }
            if (jSONObject.has("appkey")) {
                jSONObject3.put("appkey", jSONObject.getString("appkey"));
            }
            if (jSONObject.has("channel")) {
                jSONObject3.put("channel", jSONObject.getString("channel"));
            }
            if (!a(jSONObject)) {
                a = null;
            }
            if (bw.a && jSONObject3.length() > 0) {
                bw.b(String.valueOf(jSONObject3));
            }
            if (a2 == null) {
                return a;
            }
            Editor edit = a2.edit();
            edit.remove("vers_name");
            edit.remove("vers_code");
            edit.remove("vers_date");
            edit.remove("vers_pre_version");
            edit.commit();
            return a;
        } catch (Throwable th4) {
            ca.a(j).g();
            return new JSONObject();
        }
    }

    private void d(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put(be.a(j).f(), be.a(j).e());
        jSONObject.put(x.aq, jSONObject2);
    }

    private String[] a(Editor editor, SharedPreferences sharedPreferences, String str, String str2) {
        String string = sharedPreferences.getString("pre_version", "");
        String string2 = sharedPreferences.getString("pre_date", "");
        String string3 = sharedPreferences.getString("cur_version", "");
        String b = bt.b(j);
        if (string3.equals(b)) {
            string3 = string;
        } else {
            string2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
            editor.putString("pre_version", string3);
            editor.putString("pre_date", string2);
            editor.putString("cur_version", b);
            editor.commit();
        }
        return new String[]{string3, string2};
    }

    private void a(JSONObject jSONObject, String str, String str2) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        jSONObject.put(x.R, str);
        if (TextUtils.isEmpty(str2)) {
            str2 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
        }
        jSONObject.put(x.S, str2);
    }

    public boolean a(JSONObject jSONObject) {
        if (TextUtils.isEmpty("device_id") || TextUtils.isEmpty("mc") || TextUtils.isEmpty(x.r) || TextUtils.isEmpty("appkey") || TextUtils.isEmpty("channel") || TextUtils.isEmpty(x.f) || TextUtils.isEmpty(x.e) || TextUtils.isEmpty("app_version")) {
            return false;
        }
        return true;
    }

    private boolean a(boolean z) {
        if (!bt.l(j)) {
            bw.e("network is unavailable");
            return false;
        } else if (this.c.f()) {
            return true;
        } else {
            return this.g.b(z).a(z);
        }
    }

    private void d() {
        try {
            if (this.b.h()) {
                ba baVar = new ba(j, this.c);
                baVar.a((ay) this);
                if (this.d.d()) {
                    baVar.b(true);
                }
                baVar.a();
                return;
            }
            JSONObject a = a(new int[0]);
            if (a != null && a.length() > 0) {
                ba baVar2 = new ba(j, this.c);
                baVar2.a((ay) this);
                if (this.d.d()) {
                    baVar2.b(true);
                }
                baVar2.a(a);
                baVar2.a(e());
                baVar2.a();
            }
        } catch (Throwable th) {
            if (th != null) {
                th.printStackTrace();
            }
        }
    }

    private boolean e() {
        switch (this.h.c(-1)) {
            case -1:
                return AnalyticsConfig.sEncrypt;
            case 1:
                return true;
            default:
                return false;
        }
    }

    private void b(int i) {
        a(i);
    }

    public void a(com.umeng.analytics.pro.af.a aVar) {
        this.e.a(aVar);
        this.d.a(aVar);
        this.f.a(aVar);
        this.g.a(aVar);
        this.a = af.a(j).b().b(null);
    }

    private boolean c(int i) {
        if (this.p == 0) {
            return true;
        }
        if (System.currentTimeMillis() - this.p > 28800000) {
            f();
            return true;
        } else if (i > 5000) {
            return false;
        } else {
            return true;
        }
    }

    public void a(Context context) {
        try {
            if (j == null) {
                j = context;
            }
            if (this.l.length() > 0) {
                w.a(j).a(this.l);
                this.l = new JSONArray();
            }
            az.a(j).edit().putLong(q, this.p).putInt(r, this.n).putInt(s, this.o).commit();
        } catch (Throwable th) {
        }
    }

    private void f() {
        this.n = 0;
        this.o = 0;
        this.p = System.currentTimeMillis();
    }
}
