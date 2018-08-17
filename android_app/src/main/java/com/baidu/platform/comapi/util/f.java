package com.baidu.platform.comapi.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.LocationManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.baidu.android.bbalbs.common.util.CommonParam;
import com.baidu.mapapi.VersionInfo;
import com.baidu.mtjstatsdk.BasicStoreTools;
import com.baidu.platform.comjni.map.commonmemcache.a;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.tencent.open.GameAppOperation;
import com.umeng.analytics.pro.j;
import com.umeng.analytics.pro.x;

public class f {
    public static String A;
    private static final String B = f.class.getSimpleName();
    private static boolean C = true;
    private static int D = 0;
    private static int E = 0;
    static a a = new a();
    static String b = "02";
    static String c;
    static String d;
    static String e;
    static String f;
    static int g;
    static int h;
    static int i;
    static int j;
    static int k;
    static int l;
    static String m;
    static String n;
    static String o = "baidu";
    static String p = "baidu";
    static String q = "";
    static String r = "";
    static String s = "";
    static String t;
    static String u;
    static String v = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
    static String w = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
    public static Context x;
    public static final int y = Integer.parseInt(VERSION.SDK);
    public static float z = 1.0f;

    public static Bundle a() {
        Bundle bundle = new Bundle();
        bundle.putString(x.o, q);
        bundle.putString("resid", b);
        bundle.putString("channel", o);
        bundle.putString("glr", r);
        bundle.putString("glv", s);
        bundle.putString("mb", f());
        bundle.putString("sv", h());
        bundle.putString("os", j());
        bundle.putInt("dpi_x", k());
        bundle.putInt("dpi_y", k());
        bundle.putString("net", m);
        bundle.putString(BasicStoreTools.DEVICE_CUID, m());
        bundle.putByteArray(GameAppOperation.GAME_SIGNATURE, a(x));
        bundle.putString("pcn", x.getPackageName());
        bundle.putInt("screen_x", g());
        bundle.putInt("screen_y", i());
        return bundle;
    }

    public static void a(String str) {
        m = str;
        e();
    }

    public static void a(String str, String str2) {
        v = str2;
        w = str;
        e();
    }

    public static byte[] a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0].toByteArray();
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void b() {
        if (a != null) {
            a.a();
        }
    }

    public static void b(Context context) {
        x = context;
        t = context.getFilesDir().getAbsolutePath();
        u = context.getCacheDir().getAbsolutePath();
        d = Build.MODEL;
        e = "Android" + VERSION.SDK;
        c = context.getPackageName();
        c(context);
        d(context);
        e(context);
        f(context);
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            D = locationManager.isProviderEnabled("gps") ? 1 : 0;
            E = locationManager.isProviderEnabled("network") ? 1 : 0;
        } catch (Exception e) {
            Log.w("baidumapsdk", "LocationManager error");
        }
    }

    public static String c() {
        return a != null ? a.b() : null;
    }

    private static void c(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            f = VersionInfo.getApiVersion();
            if (!(f == null || f.equals(""))) {
                f = f.replace('_', '.');
            }
            g = packageInfo.versionCode;
        } catch (NameNotFoundException e) {
            f = "1.0.0";
            g = 1;
        }
    }

    public static String d() {
        return m;
    }

    private static void d(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = windowManager != null ? windowManager.getDefaultDisplay() : null;
        if (defaultDisplay != null) {
            h = defaultDisplay.getWidth();
            i = defaultDisplay.getHeight();
            defaultDisplay.getMetrics(displayMetrics);
        }
        z = displayMetrics.density;
        j = (int) displayMetrics.xdpi;
        k = (int) displayMetrics.ydpi;
        if (y > 3) {
            l = displayMetrics.densityDpi;
        } else {
            l = j.b;
        }
        if (l == 0) {
            l = j.b;
        }
    }

    public static void e() {
        Bundle bundle = new Bundle();
        bundle.putString(x.o, q);
        bundle.putString("resid", b);
        bundle.putString("channel", o);
        bundle.putString("glr", r);
        bundle.putString("glv", s);
        bundle.putString("mb", f());
        bundle.putString("sv", h());
        bundle.putString("os", j());
        bundle.putInt("dpi_x", k());
        bundle.putInt("dpi_y", k());
        bundle.putString("net", m);
        bundle.putString(BasicStoreTools.DEVICE_CUID, m());
        bundle.putString("pcn", x.getPackageName());
        bundle.putInt("screen_x", g());
        bundle.putInt("screen_y", i());
        bundle.putString("appid", v);
        bundle.putString("duid", w);
        if (!TextUtils.isEmpty(A)) {
            bundle.putString("token", A);
        }
        if (a != null) {
            a.a(bundle);
        }
    }

    private static void e(Context context) {
        n = Secure.getString(context.getContentResolver(), "android_id");
    }

    public static String f() {
        return d;
    }

    private static void f(Context context) {
        m = "0";
    }

    public static int g() {
        return h;
    }

    public static String h() {
        return f;
    }

    public static int i() {
        return i;
    }

    public static String j() {
        return e;
    }

    public static int k() {
        return l;
    }

    public static String l() {
        return t;
    }

    public static String m() {
        String a;
        try {
            a = CommonParam.a(x);
        } catch (Exception e) {
            a = "";
        }
        return a == null ? "" : a;
    }
}
