package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.pro.s.c.a;
import java.lang.reflect.Method;
import org.json.JSONObject;

/* compiled from: SessionTracker */
public class bb {
    private static final String a = "session_start_time";
    private static final String b = "session_end_time";
    private static final String c = "session_id";
    private static String f = null;
    private static Context g = null;
    private final String d = "a_start_time";
    private final String e = "a_end_time";

    public boolean a(Context context) {
        SharedPreferences a = az.a(context);
        String string = a.getString(c, null);
        if (string == null) {
            return false;
        }
        long j = a.getLong(a, 0);
        long j2 = a.getLong(b, 0);
        if (j2 == 0 || Math.abs(j2 - j) > 86400000) {
            try {
            } catch (Throwable th) {
                return false;
            }
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(a.a, string);
        jSONObject.put("__e", j);
        jSONObject.put(a.g, j2);
        double[] location = AnalyticsConfig.getLocation();
        if (location != null) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("lat", location[0]);
            jSONObject2.put("lng", location[1]);
            jSONObject2.put("ts", System.currentTimeMillis());
            jSONObject.put(a.e, jSONObject2);
        }
        Class cls = Class.forName("android.net.TrafficStats");
        Method method = cls.getMethod("getUidRxBytes", new Class[]{Integer.TYPE});
        Method method2 = cls.getMethod("getUidTxBytes", new Class[]{Integer.TYPE});
        if (context.getApplicationInfo().uid == -1) {
            return false;
        }
        long longValue = ((Long) method.invoke(null, new Object[]{Integer.valueOf(context.getApplicationInfo().uid)})).longValue();
        j = ((Long) method2.invoke(null, new Object[]{Integer.valueOf(r6)})).longValue();
        if (longValue > 0 && j > 0) {
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(x.aj, longValue);
            jSONObject3.put(x.ai, j);
            jSONObject.put(a.d, jSONObject3);
        }
        w.a(context).a(string, jSONObject, w.a.NEWSESSION);
        bd.a(g);
        ao.a(g);
        a(a);
        return true;
    }

    private void a(SharedPreferences sharedPreferences) {
        Editor edit = sharedPreferences.edit();
        edit.remove(a);
        edit.remove(b);
        edit.remove("a_start_time");
        edit.remove("a_end_time");
        edit.commit();
    }

    public String b(Context context) {
        String c = bt.c(context);
        String appkey = AnalyticsConfig.getAppkey(context);
        long currentTimeMillis = System.currentTimeMillis();
        if (appkey == null) {
            throw new RuntimeException("Appkey is null or empty, Please check AndroidManifest.xml");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(currentTimeMillis).append(appkey).append(c);
        f = bu.a(stringBuilder.toString());
        return f;
    }

    public void c(Context context) {
        g = context;
        SharedPreferences a = az.a(context);
        if (a != null) {
            Editor edit = a.edit();
            int i = a.getInt(com.umeng.analytics.a.B, 0);
            int parseInt = Integer.parseInt(bt.a(g));
            if (i != 0 && parseInt != i) {
                try {
                    edit.putInt("vers_code", i);
                    edit.putString("vers_name", a.getString(com.umeng.analytics.a.C, ""));
                    edit.commit();
                } catch (Throwable th) {
                }
                if (g(context) == null) {
                    a(context, a);
                }
                e(g);
                aq.b(g).b();
                f(g);
                aq.b(g).a();
            } else if (b(a)) {
                bw.c("Start new session: " + a(context, a));
            } else {
                String string = a.getString(c, null);
                edit.putLong("a_start_time", System.currentTimeMillis());
                edit.putLong("a_end_time", 0);
                edit.commit();
                bw.c("Extend current session: " + string);
            }
        }
    }

    public void d(Context context) {
        SharedPreferences a = az.a(context);
        if (a != null) {
            if (a.getLong("a_start_time", 0) == 0 && AnalyticsConfig.ACTIVITY_DURATION_OPEN) {
                bw.e("onPause called before onResume");
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            Editor edit = a.edit();
            edit.putLong("a_start_time", 0);
            edit.putLong("a_end_time", currentTimeMillis);
            edit.putLong(b, currentTimeMillis);
            edit.commit();
        }
    }

    private boolean b(SharedPreferences sharedPreferences) {
        long j = sharedPreferences.getLong("a_start_time", 0);
        long j2 = sharedPreferences.getLong("a_end_time", 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (j != 0 && currentTimeMillis - j < AnalyticsConfig.kContinueSessionMillis) {
            bw.e("onResume called before onPause");
            return false;
        } else if (currentTimeMillis - j2 <= AnalyticsConfig.kContinueSessionMillis) {
            return false;
        } else {
            String a = a();
            if (!TextUtils.isEmpty(a)) {
                long j3 = sharedPreferences.getLong(b, 0);
                if (j3 == 0) {
                    j3 = System.currentTimeMillis();
                }
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(a.g, j3);
                    w.a(g).a(a, jSONObject, w.a.END);
                } catch (Throwable th) {
                }
            }
            return true;
        }
    }

    private String a(Context context, SharedPreferences sharedPreferences) {
        aq b = aq.b(context);
        String b2 = b(context);
        long currentTimeMillis = System.currentTimeMillis();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("__e", currentTimeMillis);
            w.a(g).a(b2, jSONObject, w.a.BEGIN);
        } catch (Throwable th) {
        }
        a(context);
        Editor edit = sharedPreferences.edit();
        edit.putString(c, b2);
        edit.putLong(a, System.currentTimeMillis());
        edit.putLong(b, 0);
        edit.putLong("a_start_time", currentTimeMillis);
        edit.putLong("a_end_time", 0);
        edit.putInt(com.umeng.analytics.a.B, Integer.parseInt(bt.a(context)));
        edit.putString(com.umeng.analytics.a.C, bt.b(context));
        edit.commit();
        b.a(Boolean.valueOf(true));
        return b2;
    }

    public boolean e(Context context) {
        boolean z = false;
        SharedPreferences a = az.a(context);
        if (!(a == null || a.getString(c, null) == null)) {
            long j = a.getLong("a_start_time", 0);
            long j2 = a.getLong("a_end_time", 0);
            if (j > 0 && j2 == 0) {
                z = true;
                d(context);
            }
            j = a.getLong(b, 0);
            try {
                JSONObject jSONObject = new JSONObject();
                String str = a.g;
                if (j == 0) {
                    j = System.currentTimeMillis();
                }
                jSONObject.put(str, j);
                w.a(g).a(a(), jSONObject, w.a.END);
            } catch (Throwable th) {
            }
            a(context);
        }
        return z;
    }

    public void f(Context context) {
        SharedPreferences a = az.a(context);
        if (a != null) {
            String b = b(context);
            Editor edit = a.edit();
            long currentTimeMillis = System.currentTimeMillis();
            edit.putString(c, b);
            edit.putLong(a, System.currentTimeMillis());
            edit.putLong(b, 0);
            edit.putLong("a_start_time", currentTimeMillis);
            edit.putLong("a_end_time", 0);
            edit.putInt(com.umeng.analytics.a.B, Integer.parseInt(bt.a(context)));
            edit.putString(com.umeng.analytics.a.C, bt.b(context));
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("__e", currentTimeMillis);
                w.a(g).a(b, jSONObject, w.a.BEGIN);
            } catch (Throwable th) {
            }
            edit.commit();
        }
    }

    public static String g(Context context) {
        if (f == null) {
            f = az.a(context).getString(c, null);
        }
        return f;
    }

    public static String a() {
        return g(g);
    }
}