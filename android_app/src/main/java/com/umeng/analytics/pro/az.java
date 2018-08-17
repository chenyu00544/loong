package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;

/* compiled from: PreferenceWrapper */
public class az {
    private static final String a = "umeng_general_config";

    private az() {
    }

    public static SharedPreferences a(Context context, String str) {
        return context.getSharedPreferences(str, 0);
    }

    public static SharedPreferences a(Context context) {
        return context.getSharedPreferences(a, 0);
    }
}
