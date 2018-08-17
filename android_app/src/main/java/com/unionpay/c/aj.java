package com.unionpay.c;

import android.content.Context;

public class aj {
    public static void a(Context context, String str, String str2, long j) {
        try {
            context.getSharedPreferences(str, 0).edit().putLong(str2, j).commit();
        } catch (Throwable th) {
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        try {
            context.getSharedPreferences(str, 0).edit().putString(str2, str3).commit();
        } catch (Throwable th) {
        }
    }

    public static long b(Context context, String str, String str2, long j) {
        try {
            j = context.getSharedPreferences(str, 0).getLong(str2, j);
        } catch (Throwable th) {
        }
        return j;
    }

    public static String b(Context context, String str, String str2, String str3) {
        try {
            str3 = context.getSharedPreferences(str, 0).getString(str2, str3);
        } catch (Throwable th) {
        }
        return str3;
    }
}
