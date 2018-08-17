package org.android.agoo.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.taobao.accs.utl.ALog;

/* compiled from: Taobao */
public class b {
    public static final String AGOO_CLEAR_TIME = "agoo_clear_time";
    public static final String AGOO_ENABLE_DAEMONSERVER = "agoo_enable_daemonserver";
    public static final String AGOO_UNREPORT_TIMES = "agoo_UnReport_times";
    public static final String KEY_DEVICE_TOKEN = "deviceId";
    public static final String PREFERENCES = "Agoo_AppStore";
    public static final String PROPERTY_AGOO_SERVICE_MODE = "agoo_service_mode";
    public static final String PROPERTY_APP_KEY = "agoo_app_key";
    public static final String PROPERTY_APP_VERSION = "app_version";
    public static final String PROPERTY_DEVICE_TOKEN = "app_device_token";
    public static final String PROPERTY_PUSH_USER_TOKEN = "app_push_user_token";
    public static final String PROPERTY_TT_ID = "app_tt_id";
    public static final String TAG = "Config";
    private static String a;
    private static String b = null;

    public static void a(Context context, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                ALog.e(TAG, "setAgooAppKey appkey null", new Object[0]);
                return;
            }
            a = str;
            Editor edit = context.getSharedPreferences(PREFERENCES, 4).edit();
            edit.putString(PROPERTY_APP_KEY, str);
            edit.apply();
            ALog.i(TAG, "setAgooAppKey", "appkey", str);
        } catch (Throwable th) {
            ALog.e(TAG, "setAgooAppKey", th, new Object[0]);
        }
    }

    public static String a(Context context) {
        Object obj = a;
        try {
            obj = context.getSharedPreferences(PREFERENCES, 4).getString(PROPERTY_APP_KEY, a);
        } catch (Throwable th) {
            ALog.e(TAG, "getAgooAppKey", th, new Object[0]);
        }
        if (TextUtils.isEmpty(obj)) {
            ALog.e(TAG, "getAgooAppKey null!!", new Object[0]);
        }
        ALog.i(TAG, "getAgooAppKey", "appkey", obj);
        return obj;
    }

    public static void a(Context context, int i) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCES, 4);
            Editor edit = sharedPreferences.edit();
            edit.putInt(AGOO_UNREPORT_TIMES, sharedPreferences.getInt(AGOO_UNREPORT_TIMES, 0) + i);
            edit.apply();
        } catch (Throwable th) {
        }
    }

    public static boolean b(Context context) {
        try {
            if (context.getSharedPreferences(PREFERENCES, 4).getInt(AGOO_UNREPORT_TIMES, 0) > 0) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    public static void c(Context context) {
        try {
            Editor edit = context.getSharedPreferences(PREFERENCES, 4).edit();
            edit.putInt(AGOO_UNREPORT_TIMES, 0);
            edit.apply();
        } catch (Throwable th) {
        }
    }

    public static int d(Context context) {
        int i = 0;
        try {
            i = context.getSharedPreferences(PREFERENCES, 4).getInt(AGOO_UNREPORT_TIMES, 0);
        } catch (Throwable th) {
        }
        return i;
    }

    public static void a(Context context, long j) {
        try {
            Editor edit = context.getSharedPreferences(PREFERENCES, 4).edit();
            edit.putLong(AGOO_CLEAR_TIME, j);
            edit.apply();
        } catch (Throwable th) {
        }
    }

    public static boolean b(Context context, long j) {
        try {
            long j2 = context.getSharedPreferences(PREFERENCES, 4).getLong(AGOO_CLEAR_TIME, 0);
            ALog.d("isClearTime", "now=" + j + ",now - lastTime=" + (j - j2) + ",istrue=" + (j - j2 > 86400000), new Object[0]);
            if (j == 0 || j - j2 <= 86400000) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    public static void a(Context context, boolean z) {
        try {
            Editor edit = context.getSharedPreferences(PREFERENCES, 4).edit();
            edit.putBoolean(AGOO_ENABLE_DAEMONSERVER, z);
            edit.apply();
        } catch (Throwable th) {
        }
    }

    public static boolean e(Context context) {
        boolean z = false;
        try {
            z = context.getSharedPreferences(PREFERENCES, 4).getBoolean(AGOO_ENABLE_DAEMONSERVER, true);
        } catch (Throwable th) {
        }
        return z;
    }

    public static void b(Context context, String str) {
        ALog.i(TAG, "setDeviceToken", "token", str);
        if (!TextUtils.isEmpty(str)) {
            b = str;
            try {
                Editor edit = context.getSharedPreferences(PREFERENCES, 4).edit();
                edit.putString("deviceId", str);
                edit.apply();
            } catch (Throwable th) {
                ALog.e(TAG, "setDeviceToken", th, new Object[0]);
            }
        }
    }

    public static String f(Context context) {
        String str = b;
        try {
            str = context.getSharedPreferences(PREFERENCES, 4).getString("deviceId", b);
        } catch (Throwable th) {
            ALog.e(TAG, "getDeviceToken", th, new Object[0]);
        }
        ALog.i(TAG, "getDeviceToken", "token", str);
        return str;
    }

    public static String g(Context context) {
        String str = null;
        try {
            str = context.getSharedPreferences(PREFERENCES, 4).getString(PROPERTY_PUSH_USER_TOKEN, "");
        } catch (Throwable th) {
        }
        return str;
    }

    public static void c(Context context, String str) {
        try {
            Editor edit = context.getSharedPreferences(PREFERENCES, 4).edit();
            if (!TextUtils.isEmpty(str)) {
                edit.putString(PROPERTY_PUSH_USER_TOKEN, str);
            }
            edit.apply();
        } catch (Throwable th) {
        }
    }
}
