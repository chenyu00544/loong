package com.baidu.mtjstatsdk;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import com.baidu.mtjstatsdk.b.a;
import com.baidu.mtjstatsdk.b.d;

public class StatSDKService {
    private static boolean a(Context context, String str) {
        if (context != null) {
            return true;
        }
        d.c("statsdk", str + ":context=null， please check it");
        return false;
    }

    private static boolean a(Class<?> cls, String str) {
        int i = 2;
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        boolean z = false;
        if (stackTrace.length >= 2) {
            while (i < stackTrace.length) {
                StackTraceElement stackTraceElement = stackTrace[i];
                if (stackTraceElement.getMethodName().equals(str)) {
                    try {
                        Class cls2 = Class.forName(stackTraceElement.getClassName());
                        while (cls2.getSuperclass() != null && cls2.getSuperclass() != cls) {
                            cls2 = cls2.getSuperclass();
                        }
                        z = true;
                    } catch (Throwable e) {
                        d.a(e);
                    }
                }
                i++;
            }
        }
        return z;
    }

    private static void b(Context context, String str) {
        LoadCacheAnalysis.checkStartLoadCache(context, str);
    }

    public static String getCuid(Context context) {
        return new DataCoreObject().a(context);
    }

    public static void onEvent(Context context, String str, String str2, int i, String str3) {
        if (a(context, "onEvent(...)") && str != null && !str.equals("")) {
            b(context, str3);
            b.a().a(context.getApplicationContext(), str, str2, i, System.currentTimeMillis(), str3);
        }
    }

    public static void onEvent(Context context, String str, String str2, String str3) {
        onEvent(context, str, str2, 1, str3);
    }

    public static void onEventDuration(Context context, String str, String str2, long j, String str3) {
        if (a(context, "onEventDuration(...)") && str != null && !str.equals("")) {
            if (j > 0) {
                b(context, str3);
                b.a().c(context.getApplicationContext(), str, str2, j, str3);
            } else if (a.a(str3)) {
                d.b("statsdk", "onEventDuration: duration must be greater than zero");
            }
        }
    }

    public static void onEventEnd(Context context, String str, String str2, String str3) {
        if (a(context, "onEventEnd(...)") && str != null && !str.equals("")) {
            b(context, str3);
            b.a().b(context.getApplicationContext(), str, str2, System.currentTimeMillis(), str3);
        }
    }

    public static void onEventStart(Context context, String str, String str2, String str3) {
        if (a(context, "onEventStart(...)") && str != null && !str.equals("")) {
            b(context, str3);
            b.a().a(context.getApplicationContext(), str, str2, System.currentTimeMillis(), str3);
        }
    }

    public static synchronized void onPageEnd(Context context, String str, String str2) {
        synchronized (StatSDKService.class) {
            if (!(context == null || str == null)) {
                if (!str.equals("")) {
                    n.a(str2).b(context, System.currentTimeMillis(), str, str2);
                }
            }
            if (a.a(str2)) {
                d.c("statsdk", "onPageEnd :parame=null || empty");
            }
        }
    }

    public static synchronized void onPageStart(Context context, String str, String str2) {
        synchronized (StatSDKService.class) {
            if (!(context == null || str == null)) {
                if (!str.equals("")) {
                    b(context, str2);
                    n.a(str2).a(context, System.currentTimeMillis(), str, str2);
                }
            }
            if (a.a(str2)) {
                d.c("statsdk", "onPageStart :parame=null || empty");
            }
        }
    }

    public static synchronized void onPause(Context context, String str) {
        synchronized (StatSDKService.class) {
            if (a(context, "onPause(...)")) {
                if (a(Activity.class, "onPause")) {
                    n.a(str).b(context, System.currentTimeMillis(), str);
                } else {
                    throw new SecurityException("onPause(Context context)不在Activity.onPause()中被调用||onPause(Context context)is not called in Activity.onPause().");
                }
            }
        }
    }

    public static synchronized void onPause(Fragment fragment, String str) {
        synchronized (StatSDKService.class) {
            if (fragment == null) {
                if (a.a(str)) {
                    d.c("statsdk", "onResume :parame=null");
                }
            } else if (a(Fragment.class, "onPause")) {
                n.a(str).b(fragment, System.currentTimeMillis(), str);
            } else {
                throw new SecurityException("Fragment onPause(Context context)不在Fragment.onPause()中被调用||onPause(Context context)is not called in Fragment.onPause().");
            }
        }
    }

    public static synchronized void onPause(Object obj, String str) {
        synchronized (StatSDKService.class) {
            if (obj == null) {
                if (a.a(str)) {
                    d.c("statsdk", "android.app.Fragment onResume :parame=null");
                }
            } else if (a(obj.getClass(), "onPause")) {
                n.a(str).b(obj, System.currentTimeMillis(), str);
            } else {
                throw new SecurityException("android.app.Fragment onPause(Context context)不在android.app.Fragment.onPause()中被调用||onPause(Context context)is not called in android.app.Fragment.onPause().");
            }
        }
    }

    public static synchronized void onResume(Context context, String str) {
        synchronized (StatSDKService.class) {
            if (a(context, "onResume(...)")) {
                if (a(Activity.class, "onResume")) {
                    b(context, str);
                    n.a(str).a(context, System.currentTimeMillis(), str);
                } else {
                    throw new SecurityException("onResume(Context context)不在Activity.onResume()中被调用||onResume(Context context)is not called in Activity.onResume().");
                }
            }
        }
    }

    public static synchronized void onResume(Fragment fragment, String str) {
        synchronized (StatSDKService.class) {
            if (fragment == null) {
                if (a.a(str)) {
                    d.c("statsdk", "onResume :parame=null");
                }
            } else if (a(Fragment.class, "onResume")) {
                b(fragment.getActivity(), str);
                n.a(str).a(fragment, System.currentTimeMillis(), str);
            } else {
                throw new SecurityException("onResume(Context context)不在Activity.onResume()中被调用||onResume(Context context)is not called in Activity.onResume().");
            }
        }
    }

    public static synchronized void onResume(Object obj, String str) {
        synchronized (StatSDKService.class) {
            if (obj == null) {
                if (a.a(str)) {
                    d.c("statsdk", "onResume :parame=null");
                }
            } else if (a(obj.getClass(), "onResume")) {
                b(n.a(obj), str);
                n.a(str).a(obj, System.currentTimeMillis(), str);
            } else {
                throw new SecurityException("onResume(Context context)不在Activity.onResume()中被调用||onResume(Context context)is not called in Activity.onResume().");
            }
        }
    }

    public static void setAppChannel(Context context, String str, boolean z, String str2) {
        a.a(str2).a(context, str, z, str2);
    }

    public static void setAppVersionName(String str, String str2) {
        a.a(str2).a(str, str2);
    }

    public static void setDebugOn(boolean z, String str) {
        if (str != null) {
            a.b.put(str, Boolean.valueOf(z));
            a.a = 2;
        }
    }

    public static void setLogSenderDelayed(int i, String str) {
        a.a(str).a(i, str);
    }
}
