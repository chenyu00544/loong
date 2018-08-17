package com.unionpay.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import java.security.MessageDigest;

public class UPUtils {
    public static String a(Context context, String str) {
        String string = context.getSharedPreferences("UnionPayPluginEx.pref", 0).getString(str, "");
        String a = d.a(context);
        string = b(string, (a + "23456789abcdef12123456786789abcd").substring(0, 32));
        return string == null ? "" : !string.endsWith(a) ? "" : string.substring(0, string.length() - a.length());
    }

    public static String a(String str) {
        try {
            byte[] bytes = str.getBytes();
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.reset();
            instance.update(bytes);
            return a.a(instance.digest());
        } catch (Exception e) {
            return null;
        }
    }

    private static String a(String str, String str2) {
        try {
            return a.a(c.a(a.a(str2), str.getBytes("utf-8")));
        } catch (Exception e) {
            return "";
        }
    }

    public static void a(Context context, long j, String str) {
        Editor edit = context.getSharedPreferences("UnionPayPluginEx.pref", 0).edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public static void a(Context context, String str, String str2) {
        String a = d.a(context);
        a = a(str + a, (a + "23456789abcdef12123456786789abcd").substring(0, 32));
        if (a == null) {
            a = "";
        }
        Editor edit = context.getSharedPreferences("UnionPayPluginEx.pref", 0).edit();
        edit.putString(str2, a);
        edit.commit();
    }

    private static String b(String str, String str2) {
        try {
            return new String(c.b(a.a(str2), a.a(str)), "utf-8").trim();
        } catch (Exception e) {
            return "";
        }
    }

    public static void b(Context context, String str) {
        Editor edit = context.getSharedPreferences("UnionPayPluginEx.pref", 3).edit();
        edit.remove(str);
        edit.commit();
    }

    public static long c(Context context, String str) {
        return context.getSharedPreferences("UnionPayPluginEx.pref", 0).getLong(str, 0);
    }

    public static native String forConfig(int i, String str);

    public static native String forWap(int i, String str);
}
