package com.unionpay.mobile.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

public class PreferenceUtils {
    public static String a(Context context) {
        String str = "uid";
        String str2 = "tag1";
        SharedPreferences sharedPreferences = context.getSharedPreferences("UnionPayPluginEx.pref", 0);
        String string = sharedPreferences.getString(str, "");
        Object string2 = sharedPreferences.getString(str2, "");
        String str3 = "";
        if (TextUtils.isEmpty(string)) {
            return !TextUtils.isEmpty(string2) ? e(context, string2) : str3;
        } else {
            if (string.length() != 32) {
                string = e(context, string);
                if (!(string.length() == 32 && c.a(string))) {
                    string = "";
                }
            } else if (!c.a(string)) {
                string = "";
            }
            Editor edit = sharedPreferences.edit();
            edit.remove(str);
            edit.commit();
            if (TextUtils.isEmpty(string)) {
                return string;
            }
            a(context, string, str2);
            return string;
        }
    }

    public static String a(Context context, String str) {
        return e(context, context.getSharedPreferences("UnionPayPluginEx.pref", 0).getString(str, ""));
    }

    private static String a(String str, String str2) {
        try {
            return b.a(e.a(b.a(str2), str.getBytes("utf-8")));
        } catch (Exception e) {
            return "";
        }
    }

    public static void a(Context context, String str, String str2) {
        String c = f.c(context);
        c = a(str + c, (c + "23456789abcdef12123456786789abcd").substring(0, 32));
        if (c == null) {
            c = "";
        }
        Editor edit = context.getSharedPreferences("UnionPayPluginEx.pref", 0).edit();
        edit.putString(str2, c);
        edit.commit();
    }

    public static String b(Context context) {
        return b(context, "last_pay_method", "tag2");
    }

    private static String b(Context context, String str, String str2) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("UnionPayPluginEx.pref", 0);
        CharSequence string = sharedPreferences.getString(str, "");
        Object string2 = sharedPreferences.getString(str2, "");
        String str3 = "";
        if (TextUtils.isEmpty(string)) {
            return !TextUtils.isEmpty(string2) ? e(context, string2) : str3;
        } else {
            str3 = "";
            Editor edit = sharedPreferences.edit();
            edit.remove(str);
            edit.commit();
            return str3;
        }
    }

    private static String b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return new String(e.b(b.a(str2), b.a(str)), "utf-8").trim();
        } catch (Exception e) {
            return "";
        }
    }

    public static void b(Context context, String str) {
        a(context, str, "tag1");
    }

    public static String c(Context context) {
        return b(context, "last_user", "tag3");
    }

    public static void c(Context context, String str) {
        a(context, str, "tag2");
    }

    public static void d(Context context, String str) {
        a(context, str, "tag3");
    }

    public static native String decPrefData(String str, String str2);

    private static String e(Context context, String str) {
        String c = f.c(context);
        String b = b(str, (c + "23456789abcdef12123456786789abcd").substring(0, 32));
        return b == null ? "" : !b.endsWith(c) ? "" : b.substring(0, b.length() - c.length());
    }

    public static native String forConfig(int i, String str);
}
