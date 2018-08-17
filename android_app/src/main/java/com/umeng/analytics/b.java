package com.umeng.analytics;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.umeng.analytics.MobclickAgent.EScenarioType;
import com.umeng.analytics.MobclickAgent.UMAnalyticsConfig;
import com.umeng.analytics.pro.ao;
import com.umeng.analytics.pro.aq;
import com.umeng.analytics.pro.ar;
import com.umeng.analytics.pro.as;
import com.umeng.analytics.pro.ax;
import com.umeng.analytics.pro.az;
import com.umeng.analytics.pro.bb;
import com.umeng.analytics.pro.bd;
import com.umeng.analytics.pro.br;
import com.umeng.analytics.pro.bt;
import com.umeng.analytics.pro.bv;
import com.umeng.analytics.pro.bw;
import com.umeng.analytics.pro.bx;
import com.umeng.analytics.pro.bz;
import com.umeng.analytics.pro.w;
import com.umeng.analytics.pro.x;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONObject;

/* compiled from: InternalAgent */
public class b implements ax {
    private static final String j = "sp";
    private Context a;
    private bv b;
    private ar c;
    private bd d;
    private bb e;
    private as f;
    private Object g;
    private aq h;
    private ao i;
    private boolean k;
    private JSONObject l;
    private boolean m;

    /* compiled from: InternalAgent */
    class b_5 extends bz {
        final /* synthetic */ b a;

        b_5(b bVar) {
            this.a = bVar;
        }

        public void a() {
            String[] a = c.a(this.a.a);
            if (a != null && !TextUtils.isEmpty(a[0]) && !TextUtils.isEmpty(a[1])) {
                if (this.a.h != null) {
                    this.a.h.a(this.a.a).a(this.a.a);
                }
                boolean e = this.a.b().e(this.a.a);
                aq.b(this.a.a).b();
                if (e) {
                    this.a.b().f(this.a.a);
                }
                c.b(this.a.a);
            }
        }
    }

    /* compiled from: InternalAgent */
    private static class a {
        private static final b a = new b();

        private a() {
        }
    }

    private b() {
        this.a = null;
        this.c = new ar();
        this.d = new bd();
        this.e = new bb();
        this.f = null;
        this.g = new Object();
        this.h = null;
        this.i = null;
        this.k = false;
        this.l = null;
        this.m = false;
        this.c.a((ax) this);
    }

    public static b a() {
        return a.a;
    }

    private synchronized void g(final Context context) {
        if (context != null) {
            try {
                if (VERSION.SDK_INT > 13 && !this.m) {
                    this.m = true;
                    bx.b(new bz(this) {
                        final /* synthetic */ b b;

                        public void a() {
                            if (context instanceof Activity) {
                                this.b.i = new ao((Activity) context);
                            }
                        }
                    });
                }
                if (!this.k) {
                    this.a = context.getApplicationContext();
                    this.k = true;
                    if (this.f == null) {
                        synchronized (this.g) {
                            this.f = new as(this.a);
                        }
                    }
                    this.h = aq.b(this.a);
                }
            } catch (Throwable th) {
            }
        }
    }

    void a(String str) {
        if (!AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            try {
                if (this.d != null) {
                    this.d.a(str);
                }
            } catch (Throwable th) {
            }
        }
    }

    void b(String str) {
        if (!AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
            try {
                if (this.d != null) {
                    this.d.b(str);
                }
            } catch (Throwable th) {
            }
        }
    }

    public void a(bv bvVar) {
        this.b = bvVar;
    }

    public void a(Context context, int i) {
        AnalyticsConfig.a(context, i);
    }

    void a(final Context context) {
        if (context == null) {
            try {
                bw.e("unexpected null context in onResume");
                return;
            } catch (Throwable th) {
                bw.e("Exception occurred in Mobclick.onResume(). ", th);
                return;
            }
        }
        if (AnalyticsConfig.ACTIVITY_DURATION_OPEN && this.d != null) {
            this.d.a(context.getClass().getName());
        }
        if (!(this.k && this.m)) {
            g(context);
        }
        bx.a(new bz(this) {
            final /* synthetic */ b b;

            public void a() {
                this.b.h(context.getApplicationContext());
            }
        });
    }

    void b(final Context context) {
        if (context == null) {
            try {
                bw.e("unexpected null context in onPause");
                return;
            } catch (Throwable th) {
                if (bw.a) {
                    bw.e("Exception occurred in Mobclick.onRause(). ", th);
                    return;
                }
                return;
            }
        }
        if (AnalyticsConfig.ACTIVITY_DURATION_OPEN && this.d != null) {
            this.d.b(context.getClass().getName());
        }
        if (!(this.k && this.m)) {
            g(context);
        }
        bx.a(new bz(this) {
            final /* synthetic */ b b;

            public void a() {
                this.b.i(context.getApplicationContext());
            }
        });
    }

    public bb b() {
        return this.e;
    }

    public void a(Context context, String str, HashMap<String, Object> hashMap) {
        try {
            if (!(this.k && this.m)) {
                g(context);
            }
            if (this.f != null) {
                this.f.b(str, hashMap);
            }
        } catch (Throwable th) {
            if (bw.a) {
                bw.e(th);
            }
        }
    }

    void a(Context context, String str) {
        if (!TextUtils.isEmpty(str)) {
            if (context == null) {
                bw.e("unexpected null context in reportError");
                return;
            }
            try {
                if (!(this.k && this.m)) {
                    g(context);
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ts", System.currentTimeMillis());
                jSONObject.put(x.aH, 2);
                jSONObject.put(x.aI, str);
                w.a(this.a).a(bb.a(), jSONObject.toString(), 2);
            } catch (Throwable th) {
                if (bw.a) {
                    bw.e(th);
                }
            }
        }
    }

    void a(Context context, Throwable th) {
        if (context != null && th != null) {
            try {
                a(this.a, br.a(th));
            } catch (Throwable th2) {
                if (bw.a) {
                    bw.e(th2);
                }
            }
        }
    }

    private void h(Context context) {
        try {
            if (this.a == null && context != null) {
                this.a = context.getApplicationContext();
            }
            if (this.e != null) {
                this.e.c(this.a == null ? context.getApplicationContext() : this.a);
            }
            if (this.b != null) {
                this.b.a();
            }
        } catch (Throwable th) {
        }
    }

    private void i(Context context) {
        try {
            if (this.a == null && context != null) {
                this.a = context.getApplicationContext();
            }
            if (this.a != null) {
                if (this.e != null) {
                    this.e.d(this.a);
                }
                bd.a(this.a);
                ao.a(this.a);
                if (this.h != null) {
                    this.h.a(this.a).a(this.a);
                }
            }
            if (this.b != null) {
                this.b.b();
            }
        } catch (Throwable th) {
        }
    }

    void c(Context context) {
        try {
            if (!(this.k && this.m)) {
                g(context);
            }
            if (this.h != null) {
                this.h.a();
            }
        } catch (Throwable th) {
        }
    }

    public void a(Context context, List<String> list, int i, String str) {
    }

    public void a(Context context, String str, String str2, long j, int i) {
        try {
            if (!(this.k && this.m)) {
                g(context);
            }
            synchronized (this.g) {
                if (this.f != null) {
                    this.f.a(str, str2, j, i);
                }
            }
        } catch (Throwable th) {
            if (bw.a) {
                bw.e(th);
            }
        }
    }

    void a(Context context, String str, Map<String, Object> map, long j) {
        try {
            if (!(this.k && this.m)) {
                g(context);
            }
            if (this.f != null) {
                this.f.a(str, (Map) map, j);
            }
        } catch (Throwable th) {
            if (bw.a) {
                bw.e(th);
            }
        }
    }

    public void a(Context context, String str, Map<String, Object> map) {
    }

    void d(Context context) {
        try {
            if (this.i != null) {
                this.i.b();
            }
            if (this.d != null) {
                this.d.a();
            }
            if (context != null) {
                i(context);
                az.a(context).edit().commit();
            } else if (this.a != null) {
                i(this.a);
                az.a(this.a).edit().commit();
            }
            bx.a();
        } catch (Throwable th) {
            if (bw.a) {
                th.printStackTrace();
            }
        }
    }

    public void a(Throwable th) {
        try {
            if (this.d != null) {
                this.d.a();
            }
            if (this.i != null) {
                this.i.b();
            }
            if (this.a != null) {
                if (!(th == null || this.h == null)) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("ts", System.currentTimeMillis());
                    jSONObject.put(x.aH, 1);
                    jSONObject.put(x.aI, br.a(th));
                    w.a(this.a).a(bb.a(), jSONObject.toString(), 1);
                }
                i(this.a);
                az.a(this.a).edit().commit();
            }
            bx.a();
        } catch (Throwable th2) {
            if (bw.a) {
                bw.e("Exception in onAppCrash", th2);
            }
        }
    }

    void a(final String str, final String str2) {
        try {
            bx.a(new bz(this) {
                final /* synthetic */ b c;

                public void a() {
                    String[] a = c.a(this.c.a);
                    if (a == null || !str.equals(a[0]) || !str2.equals(a[1])) {
                        if (this.c.h != null) {
                            this.c.h.a(this.c.a).a(this.c.a);
                        }
                        boolean e = this.c.b().e(this.c.a);
                        aq.b(this.c.a).b();
                        if (e) {
                            this.c.b().f(this.c.a);
                        }
                        c.a(this.c.a, str, str2);
                    }
                }
            });
        } catch (Throwable th) {
            if (bw.a) {
                bw.e(" Excepthon  in  onProfileSignIn", th);
            }
        }
    }

    void c() {
        try {
            bx.a(new b_5(this));
        } catch (Throwable th) {
            if (bw.a) {
                bw.e(" Excepthon  in  onProfileSignOff", th);
            }
        }
    }

    void a(boolean z) {
        AnalyticsConfig.CATCH_EXCEPTION = z;
    }

    void a(GL10 gl10) {
        String[] a = bt.a(gl10);
        if (a.length == 2) {
            AnalyticsConfig.GPU_VENDER = a[0];
            AnalyticsConfig.GPU_RENDERER = a[1];
        }
    }

    void b(boolean z) {
        AnalyticsConfig.ACTIVITY_DURATION_OPEN = z;
    }

    void c(boolean z) {
        a.d = z;
    }

    void d(boolean z) {
        bw.a = z;
    }

    void e(boolean z) {
        AnalyticsConfig.a(z);
    }

    void a(double d, double d2) {
        if (AnalyticsConfig.a == null) {
            AnalyticsConfig.a = new double[2];
        }
        AnalyticsConfig.a[0] = d;
        AnalyticsConfig.a[1] = d2;
    }

    void a(long j) {
        AnalyticsConfig.sLatentWindow = ((int) j) * 1000;
    }

    void a(Context context, EScenarioType eScenarioType) {
        if (context != null) {
            this.a = context.getApplicationContext();
        }
        if (eScenarioType != null) {
            a(this.a, eScenarioType.toValue());
        }
    }

    void b(Context context, String str) {
        if (context != null) {
            this.a = context.getApplicationContext();
        }
        AnalyticsConfig.b(this.a, str);
    }

    void a(UMAnalyticsConfig uMAnalyticsConfig) {
        if (uMAnalyticsConfig.mContext != null) {
            this.a = uMAnalyticsConfig.mContext.getApplicationContext();
        }
        if (TextUtils.isEmpty(uMAnalyticsConfig.mAppkey)) {
            bw.e("the appkey is null!");
            return;
        }
        AnalyticsConfig.a(uMAnalyticsConfig.mContext, uMAnalyticsConfig.mAppkey);
        if (!TextUtils.isEmpty(uMAnalyticsConfig.mChannelId)) {
            AnalyticsConfig.a(uMAnalyticsConfig.mChannelId);
        }
        AnalyticsConfig.CATCH_EXCEPTION = uMAnalyticsConfig.mIsCrashEnable;
        a(this.a, uMAnalyticsConfig.mType);
    }

    void b(long j) {
        AnalyticsConfig.kContinueSessionMillis = j;
    }

    public void a(Context context, String str, Object obj) {
    }

    public void c(Context context, String str) {
    }

    public Object d(Context context, String str) {
        if (this.a == null && context != null) {
            this.a = context.getApplicationContext();
        }
        return null;
    }

    public String e(Context context) {
        if (this.a == null && context != null) {
            this.a = context.getApplicationContext();
        }
        return null;
    }

    private JSONObject j(Context context) {
        if (this.a == null && context != null) {
            this.a = context.getApplicationContext();
        }
        try {
            Object string = az.a(this.a).getString(j, null);
            if (!TextUtils.isEmpty(string)) {
                return new JSONObject(string);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }

    public void f(Context context) {
        if (this.a == null && context != null) {
            this.a = context.getApplicationContext();
        }
    }

    public void a(Context context, List<String> list) {
        try {
            if (!(this.k && this.m)) {
                g(context);
            }
            if (this.f != null) {
                this.f.a(context, (List) list);
            }
        } catch (Throwable th) {
            bw.e(th);
        }
    }
}
