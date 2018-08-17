package com.unionpay.mobile.android.utils;

import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import java.util.regex.Pattern;

public final class i {
    public static SpannableString a(String str, String str2, String str3, String str4) {
        Object obj = "";
        if (!b(str)) {
            obj = obj + str;
        }
        if (!b(str3)) {
            obj = obj + str3;
        }
        if (!b(str4)) {
            obj = obj + str4;
        }
        SpannableString spannableString = new SpannableString(obj);
        int length = b(str) ? 0 : str.length();
        int length2 = (b(str3) ? 0 : str3.length()) + length;
        spannableString.setSpan(new ForegroundColorSpan(ViewCompat.MEASURED_STATE_MASK), 0, obj.length(), 33);
        if (!b(str2)) {
            if (str2.length() == 6 || !d(str2)) {
                str2 = "#" + str2;
            }
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor(str2)), length, length2, 33);
        }
        return spannableString;
    }

    public static final String a(String str) {
        return (str != null || str.length() > 2) ? str.substring(1, str.length() - 1) : "";
    }

    public static final boolean b(String str) {
        return str == null || str.length() == 0;
    }

    public static final boolean c(String str) {
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    private static final boolean d(String str) {
        if (!b(str)) {
            for (String equalsIgnoreCase : new String[]{"black", "darkgray", "gray", "lightgray", "white", "red", "green", "blue", "yellow", "cyan", "magenta"}) {
                if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                    return true;
                }
            }
        }
        return false;
    }
}
