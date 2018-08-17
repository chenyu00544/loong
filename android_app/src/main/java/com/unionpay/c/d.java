package com.unionpay.c;

import android.content.Context;
import android.os.Handler;
import com.umeng.socialize.common.SocializeConstants;
import java.nio.channels.FileChannel;
import java.util.Map;
import java.util.TreeMap;

public class d {
    static final Map a = new TreeMap();
    static boolean b = false;
    public static Context c = null;
    static Handler d = null;
    static FileChannel e;
    static long f = 0;
    static boolean g = false;
    static boolean h = true;
    static boolean i = true;
    static String j = "";
    static String k = "Default";
    static long l = StatisticConfig.MIN_UPLOAD_INTERVAL;

    static String a(Context context) {
        if (am.b(j) && context != null) {
            j = aj.b(context, "UP_app_pefercen_profile", "UP_appId", "");
        }
        return j;
    }

    public static void a(String str) {
        aj.a(c, "UP_app_pefercen_profile", "UP_analytics_appId", str);
    }

    static void a(String str, String str2) {
        if (c != null) {
            d = new Handler(c.getMainLooper());
        }
        if (str == null || str.trim().isEmpty() || !str.contains(SocializeConstants.OP_DIVIDER_MINUS)) {
            j = str;
        } else {
            String str3 = null;
            try {
                str3 = str.split(SocializeConstants.OP_DIVIDER_MINUS)[1];
            } catch (Throwable th) {
            }
            j = str3;
        }
        if (!(str2 == null || str2.trim().isEmpty())) {
            k = str2;
        }
        am.a(new h());
    }
}
