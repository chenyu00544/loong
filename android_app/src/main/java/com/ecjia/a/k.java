package com.ecjia.a;

import android.text.TextUtils;
import java.text.DecimalFormat;

/* compiled from: ECJiaFormatUtil */
public class k {
    public static String a(float f) {
        String str = "";
        return String.format("%.2f", new Object[]{Float.valueOf(f)});
    }

    public static float a(String str) {
        String str2;
        if (str == null || TextUtils.isEmpty(str)) {
            str2 = "0";
        } else {
            str2 = str.replace("元", "").replace("yuan", "").replace("¥", "").replace("￥", "").replace("Yuan", "").replace("$", "").replace(" ", "").trim();
        }
        try {
            return Float.parseFloat(str2);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public static String b(String str) {
        String str2 = "";
        return "¥" + str.replace("元", "").replace("yuan", "").replace("¥", "").replace("￥", "").replace("Yuan", "").replace(" ", "") + "元";
    }

    public static String c(String str) {
        String str2 = "";
        return str.replace("元", "").replace("yuan", "").replace("¥", "").replace("￥", "").replace("Yuan", "").replace(" ", "");
    }

    public static String d(String str) throws Exception {
        return b(Float.valueOf(str).floatValue());
    }

    public static String b(float f) throws Exception {
        return new DecimalFormat("#######0.00").format((double) f);
    }

    public static String e(String str) throws Exception {
        if (a(str) == 0.0f) {
            return "免费";
        }
        return "¥" + d(str);
    }

    public static String c(float f) throws Exception {
        return "¥" + b(f);
    }

    public static int f(String str) {
        String str2;
        if (str == null || TextUtils.isEmpty(str)) {
            str2 = "0";
        } else {
            str2 = str.replace("元", "").replace("yuan", "").replace("¥", "").replace("￥", "").replace("Yuan", "").replace("$", "").replace(" ", "").trim();
        }
        try {
            return Integer.parseInt(str2);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
