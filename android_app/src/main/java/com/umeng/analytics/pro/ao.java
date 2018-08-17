package com.umeng.analytics.pro;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import com.umeng.analytics.pro.w.a;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: AutoViewPageTracker */
public class ao {
    public static String a = null;
    private static JSONObject d = new JSONObject();
    ActivityLifecycleCallbacks b = new ao_1(this);
    private final Map<String, Long> c = new HashMap();
    private Application e = null;

    /* compiled from: AutoViewPageTracker */
    class ao_1 implements ActivityLifecycleCallbacks {
        final /* synthetic */ ao a;

        ao_1(ao aoVar) {
            this.a = aoVar;
        }

        public void onActivityStopped(Activity activity) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityResumed(Activity activity) {
            this.a.b(activity);
        }

        public void onActivityPaused(Activity activity) {
            this.a.c(activity);
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
        }
    }

    public ao(Activity activity) {
        if (activity != null) {
            this.e = activity.getApplication();
            a(activity);
        }
    }

    private void a(Activity activity) {
        this.e.registerActivityLifecycleCallbacks(this.b);
        if (a == null) {
            b(activity);
        }
    }

    public void a() {
        if (this.e != null) {
            this.e.unregisterActivityLifecycleCallbacks(this.b);
        }
    }

    public void b() {
        c(null);
        a();
    }

    public static void a(Context context) {
        JSONObject jSONObject = null;
        try {
            synchronized (d) {
                if (d.length() > 0) {
                    jSONObject = new JSONObject(d.toString());
                    d = new JSONObject();
                }
            }
            if (jSONObject == null) {
                return;
            }
            if (jSONObject.length() > 0) {
                w.a(context).a(bb.a(), jSONObject, a.AUTOPAGE);
            }
        } catch (Throwable th) {
        }
    }

    private void b(Activity activity) {
        a = activity.getPackageName() + "." + activity.getLocalClassName();
        synchronized (this.c) {
            this.c.put(a, Long.valueOf(System.currentTimeMillis()));
        }
    }

    private void c(Activity activity) {
        long j = 0;
        try {
            synchronized (this.c) {
                if (this.c.containsKey(a)) {
                    j = System.currentTimeMillis() - ((Long) this.c.get(a)).longValue();
                    this.c.remove(a);
                }
            }
            synchronized (d) {
                try {
                    d = new JSONObject();
                    d.put(x.ab, a);
                    d.put("duration", j);
                } catch (Throwable th) {
                }
            }
        } catch (Throwable th2) {
        }
    }
}
