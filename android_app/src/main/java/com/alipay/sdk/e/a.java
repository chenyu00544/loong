package com.alipay.sdk.e;

import android.text.TextUtils;

public final class a {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split("&");
        if (split.length == 0) {
            return "";
        }
        Object obj = null;
        Object obj2 = null;
        Object obj3 = null;
        Object obj4 = null;
        for (String str2 : split) {
            if (TextUtils.isEmpty(obj4)) {
                obj4 = !str2.contains("biz_type") ? null : b(str2);
            }
            if (TextUtils.isEmpty(obj3)) {
                obj3 = !str2.contains("biz_no") ? null : b(str2);
            }
            if (TextUtils.isEmpty(obj2)) {
                obj2 = (!str2.contains("trade_no") || str2.startsWith("out_trade_no")) ? null : b(str2);
            }
            if (TextUtils.isEmpty(obj)) {
                if (str2.contains("app_userid")) {
                    obj = b(str2);
                } else {
                    obj = null;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(obj4)) {
            stringBuilder.append("biz_type=" + obj4 + ";");
        }
        if (!TextUtils.isEmpty(obj3)) {
            stringBuilder.append("biz_no=" + obj3 + ";");
        }
        if (!TextUtils.isEmpty(obj2)) {
            stringBuilder.append("trade_no=" + obj2 + ";");
        }
        if (!TextUtils.isEmpty(obj)) {
            stringBuilder.append("app_userid=" + obj + ";");
        }
        String stringBuilder2 = stringBuilder.toString();
        if (stringBuilder2.endsWith(";")) {
            return stringBuilder2.substring(0, stringBuilder2.length() - 1);
        }
        return stringBuilder2;
    }

    private static String b(String str) {
        String[] split = str.split("=");
        if (split.length <= 1) {
            return null;
        }
        String str2 = split[1];
        if (str2.contains("\"")) {
            return str2.replaceAll("\"", "");
        }
        return str2;
    }
}
