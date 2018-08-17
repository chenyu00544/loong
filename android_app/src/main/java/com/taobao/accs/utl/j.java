package com.taobao.accs.utl;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.taobao.accs.common.Constants;
import com.taobao.accs.d.a;

/* compiled from: Taobao */
public class j {
    public static final String SP_AGOO_BIND_FILE_NAME = "AGOO_BIND";
    private static final byte[] a = new byte[0];

    public static void a(Context context, int i) {
        try {
            synchronized (a) {
                ALog.i("Utils", "setMode", Constants.KEY_MODE, Integer.valueOf(i));
                Editor edit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
                edit.putInt(Constants.SP_KEY_DEBUG_MODE, i);
                edit.commit();
            }
        } catch (Throwable th) {
            ALog.e("Utils", "setMode", th, new Object[0]);
        }
    }

    public static int a(Context context) {
        int i;
        Throwable th;
        int i2;
        Throwable th2;
        try {
            synchronized (a) {
                try {
                    i = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getInt(Constants.SP_KEY_DEBUG_MODE, 0);
                    try {
                        ALog.i("Utils", "getMode", Constants.KEY_MODE, Integer.valueOf(i));
                    } catch (Throwable th3) {
                        Throwable th4 = th3;
                        i2 = i;
                        th2 = th4;
                        try {
                            throw th2;
                        } catch (Throwable th22) {
                            th4 = th22;
                            i = i2;
                            th3 = th4;
                            ALog.e("Utils", "getMode", th3, new Object[0]);
                            return i;
                        }
                    }
                } catch (Throwable th5) {
                    th22 = th5;
                    i2 = 0;
                    throw th22;
                }
            }
        } catch (Throwable th222) {
            th3 = th222;
            i = 0;
            ALog.e("Utils", "getMode", th3, new Object[0]);
            return i;
        }
    }

    public static void b(Context context) {
        try {
            synchronized (a) {
                Editor edit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
                edit.clear();
                edit.commit();
            }
        } catch (Throwable th) {
            ALog.e("Utils", "clearAllSharePreferences", th, new Object[0]);
        }
    }

    public static void c(Context context) {
        try {
            Class loadClass = a.a().b().loadClass("com.taobao.accs.utl.UtilityImpl");
            loadClass.getMethod("killService", new Class[]{Context.class}).invoke(loadClass, new Object[]{context});
        } catch (Throwable th) {
            ALog.e("Utils", "killservice", th, new Object[0]);
            th.printStackTrace();
        }
    }

    public static void a(Context context, String str) {
        try {
            Class loadClass = a.a().b().loadClass("org.android.agoo.common.Config");
            loadClass.getMethod("setAgooAppKey", new Class[]{Context.class, String.class}).invoke(loadClass, new Object[]{context, str});
        } catch (Throwable th) {
            ALog.e("Utils", "setAgooAppkey", th, new Object[0]);
            th.printStackTrace();
        }
    }

    @Deprecated
    public static void a() {
        try {
            Class loadClass = a.a().b().loadClass("com.taobao.accs.client.AccsConfig");
            loadClass.getMethod("build", new Class[0]).invoke(loadClass, new Object[0]);
        } catch (Throwable th) {
            ALog.e("Utils", "initConfig", th, new Object[0]);
            th.printStackTrace();
        }
    }

    public static void d(Context context) {
        try {
            Editor edit = context.getSharedPreferences("AGOO_BIND", 0).edit();
            edit.clear();
            edit.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
