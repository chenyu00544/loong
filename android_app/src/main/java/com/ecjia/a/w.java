package com.ecjia.a;

import android.text.TextUtils;
import java.util.regex.Pattern;

/* compiled from: ECJiaRegexUtil */
public class w {
    public static boolean a(String str) {
        return Pattern.compile("^[A-Za-z0-9_\\P{Cn}-]+$").matcher(str).matches();
    }

    public static boolean b(String str) {
        if (str == null || new o(str).f() != 0) {
            return false;
        }
        return true;
    }

    public static boolean c(String str) {
        return !TextUtils.isEmpty(str) && a.a(str);
    }
}
