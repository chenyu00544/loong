package com.baidu.mtjstatsdk;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.Fragment;
import com.baidu.mtjstatsdk.b.a;
import com.baidu.mtjstatsdk.b.b;
import com.baidu.mtjstatsdk.b.d;
import java.lang.ref.WeakReference;
import java.util.HashMap;

class n {
    static HashMap<String, SessionAnalysisObject> a = new HashMap();
    private static HandlerThread b = new HandlerThread("SessionAnalysisThread");
    private static Handler c;
    private static n d = new n();

    private n() {
        b.start();
        b.setPriority(10);
        c = new Handler(b.getLooper());
    }

    static Context a(Object obj) {
        try {
            return (Context) obj.getClass().getMethod("getActivity", new Class[0]).invoke(obj, new Object[0]);
        } catch (Throwable th) {
            d.a(th.getMessage());
            return null;
        }
    }

    public static n a(String str) {
        b(str);
        return d;
    }

    private void a(Context context, String str) {
        if (context != null) {
            b.a(false, context, str + "__local_last_session.json", "{}", false);
        } else if (a.a(str)) {
            d.a("statsdk", "clearLastSession(Context context):context=null");
        }
    }

    private static void b(String str) {
        if ((str == null || str.equals("")) && a.a(str)) {
            d.c("statsdk", "AppKey can not be null");
        }
        if (!a.containsKey(str)) {
            a.put(str, new SessionAnalysisObject());
        }
    }

    public HashMap<String, SessionAnalysisObject> a() {
        return a;
    }

    public void a(Context context, long j, String str) {
        if (a.a(str)) {
            d.a("statsdk", "AnalysisResume job");
        }
        if (((SessionAnalysisObject) a.get(str)).k && a.a(str)) {
            d.c("statsdk", "遗漏StatService.onPause() || missing StatService.onPause()");
        }
        ((SessionAnalysisObject) a.get(str)).k = true;
        if (((SessionAnalysisObject) a.get(str)).isFirstResume()) {
            if (a.a(str)) {
                d.a("is_first_resume=true");
            }
            ((SessionAnalysisObject) a.get(str)).setFirstResume(false);
            c.post(new o(this, context, str));
        } else if (a.a(str)) {
            d.a("statsdk", " is_first_resume=false");
        }
        c.post(new t(this, ((SessionAnalysisObject) a.get(str)).a, j, context, null, null, 1, str));
        ((SessionAnalysisObject) a.get(str)).e = new WeakReference(context);
        ((SessionAnalysisObject) a.get(str)).b = j;
    }

    public void a(Context context, long j, String str, String str2) {
        if (a.a(str2)) {
            d.a("statsdk", "AnalysisPageStart");
        }
        if (((SessionAnalysisObject) a.get(str2)).n && a.a(str2)) {
            d.c("statsdk", "遗漏StatService.onPageEnd() || missing StatService.onPageEnd()");
        }
        ((SessionAnalysisObject) a.get(str2)).n = true;
        if (((SessionAnalysisObject) a.get(str2)).isFirstResume()) {
            if (a.a(str2)) {
                d.b("PPPPPPPPPPPPP is_first_resume=true");
            }
            ((SessionAnalysisObject) a.get(str2)).setFirstResume(false);
            c.post(new p(this, context, str2));
        } else if (a.a(str2)) {
            d.a("statsdk", " is_first_resume=false");
        }
        c.post(new t(this, ((SessionAnalysisObject) a.get(str2)).a, j, context, null, null, 1, str2));
        ((SessionAnalysisObject) a.get(str2)).o = str;
        ((SessionAnalysisObject) a.get(str2)).e = new WeakReference(context);
        ((SessionAnalysisObject) a.get(str2)).b = j;
    }

    public void a(Fragment fragment, long j, String str) {
        if (a.a(str)) {
            d.a("statsdk", "post resume job");
        }
        if (((SessionAnalysisObject) a.get(str)).l && a.a(str)) {
            d.c("statsdk", "遗漏StatService.onPause() || missing StatService.onPause()");
        }
        ((SessionAnalysisObject) a.get(str)).l = true;
        if (((SessionAnalysisObject) a.get(str)).isFirstResume()) {
            if (a.a(str)) {
                d.a("statsdk", "is_first_resume=true");
            }
            ((SessionAnalysisObject) a.get(str)).setFirstResume(false);
            c.post(new q(this, fragment, str));
        } else if (a.a(str)) {
            d.a("statsdk", "is_first_resume=false");
        }
        c.post(new t(this, ((SessionAnalysisObject) a.get(str)).a, j, null, fragment, null, 2, str));
        ((SessionAnalysisObject) a.get(str)).f = new WeakReference(fragment);
        ((SessionAnalysisObject) a.get(str)).c = j;
    }

    public void a(Object obj, long j, String str) {
        if (a.a(str)) {
            d.a("statsdk", "post resume job");
        }
        if (((SessionAnalysisObject) a.get(str)).m && a.a(str)) {
            d.c("statsdk", "遗漏StatService.onPause() || missing StatService.onPause()");
        }
        ((SessionAnalysisObject) a.get(str)).m = true;
        if (((SessionAnalysisObject) a.get(str)).isFirstResume()) {
            if (a.a(str)) {
                d.a("statsdk", "is_first_resume=true");
            }
            ((SessionAnalysisObject) a.get(str)).setFirstResume(false);
            c.post(new r(this, obj, str));
        } else if (a.a(str)) {
            d.a("statsdk", "is_first_resume=false");
        }
        c.post(new t(this, ((SessionAnalysisObject) a.get(str)).a, j, null, null, obj, 3, str));
        ((SessionAnalysisObject) a.get(str)).g = new WeakReference(obj);
        ((SessionAnalysisObject) a.get(str)).d = j;
    }

    public void b(Context context, long j, String str) {
        if (a.a(str)) {
            d.a("statsdk", "post pause job");
        }
        if (((SessionAnalysisObject) a.get(str)).k) {
            ((SessionAnalysisObject) a.get(str)).k = false;
            c.post(new s(this, j, context, null, ((SessionAnalysisObject) a.get(str)).b, (Context) ((SessionAnalysisObject) a.get(str)).e.get(), null, 1, null, null, null, str));
            ((SessionAnalysisObject) a.get(str)).a = j;
        } else if (a.a(str)) {
            d.c("statsdk", "遗漏StatService.onResume() || missing StatService.onResume()");
        }
    }

    public void b(Context context, long j, String str, String str2) {
        if (a.a(str2)) {
            d.a("statsdk", "post pause job");
        }
        SessionAnalysisObject sessionAnalysisObject = (SessionAnalysisObject) a.get(str2);
        if (sessionAnalysisObject.n) {
            sessionAnalysisObject.n = false;
            if (sessionAnalysisObject.o != null && sessionAnalysisObject.o.equals(str)) {
                c.post(new s(this, j, context, null, sessionAnalysisObject.b, (Context) sessionAnalysisObject.e.get(), null, 1, str, null, null, str2));
                sessionAnalysisObject.a = j;
            } else if (a.a(str2)) {
                d.c("statsdk", "Please check the reason : (1)遗漏StatService.onPageStart() || missing StatService.onPageStart() || (2)页面的起始和结束不是同一页面 || The page " + str + " name is not equal to the page end " + sessionAnalysisObject.o + "");
            }
        } else if (a.a(str2)) {
            d.c("statsdk", "Please check (1)遗漏StatService.onPageStart() || missing StatService.onPageStart()");
        }
    }

    public void b(Fragment fragment, long j, String str) {
        SessionAnalysisObject sessionAnalysisObject = (SessionAnalysisObject) a.get(str);
        if (a.a(str)) {
            d.a("statsdk", "post pause job");
        }
        if (sessionAnalysisObject.l) {
            sessionAnalysisObject.l = false;
            c.post(new s(this, j, null, fragment, sessionAnalysisObject.c, null, (Fragment) sessionAnalysisObject.f.get(), 2, null, null, null, str));
            sessionAnalysisObject.a = j;
        } else if (a.a(str)) {
            d.c("statsdk", "遗漏android.support.v4.app.Fragment StatService.onResume() || android.support.v4.app.Fragment missing StatService.onResume()");
        }
    }

    public void b(Object obj, long j, String str) {
        SessionAnalysisObject sessionAnalysisObject = (SessionAnalysisObject) a.get(str);
        if (a.a(str)) {
            d.a("statsdk", "post pause job");
        }
        if (sessionAnalysisObject.m) {
            sessionAnalysisObject.m = false;
            c.post(new s(this, j, null, null, sessionAnalysisObject.d, null, null, 3, null, sessionAnalysisObject.g.get(), obj, str));
            sessionAnalysisObject.a = j;
        } else if (a.a(str)) {
            d.c("statsdk", "遗漏android.app.Fragment StatService.onResume() || android.app.Fragment missing StatService.onResume()");
        }
    }
}
