package com.alipay.sdk.util;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.alipay.sdk.app.a.a;
import com.alipay.sdk.c.e;

public final class g {
    private static String a = null;

    public static void a(Context context, String str) {
        try {
            PreferenceManager.getDefaultSharedPreferences(context).edit().remove(str).commit();
        } catch (Throwable th) {
        }
    }

    public static void a(Context context, String str, String str2) {
        try {
            Object a = e.a(a(context), str2);
            if (!TextUtils.isEmpty(str2) && TextUtils.isEmpty(a)) {
                a.a("cp", "TriDesDecryptError", String.format("%s,%s", new Object[]{str, str2}));
            }
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(str, a).commit();
        } catch (Throwable th) {
        }
    }

    public static String b(Context context, String str, String str2) {
        String str3 = null;
        try {
            String string = PreferenceManager.getDefaultSharedPreferences(context).getString(str, str2);
            if (!TextUtils.isEmpty(string)) {
                str3 = e.b(a(context), string);
            }
            if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(r0)) {
                a.a("cp", "TriDesEncryptError", String.format("%s,%s", new Object[]{str, string}));
            }
        } catch (Exception e) {
        }
        return str3;
    }

    private static String a(Context context) {
        if (TextUtils.isEmpty(a)) {
            String str = "";
            try {
                str = context.getApplicationContext().getPackageName();
            } catch (Throwable th) {
            }
            a = (str + "0000000000000000000000000000").substring(0, 24);
        }
        return a;
    }
}
