package com.unionpay.mobile.android.b;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import com.unionpay.mobile.android.utils.i;
import java.util.HashMap;

public final class a {
    public static final HashMap<String, Integer> a = new HashMap();

    public static SpannableString a(String str, String str2) {
        int i = -10066330;
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ForegroundColorSpan(-10066330), 0, str.length(), 33);
        if (str2 == null) {
            return spannableString;
        }
        for (String str3 : str2.split(";")) {
            if (a(str3)) {
                break;
            }
            try {
                i = Color.parseColor("#" + str3);
            } catch (Exception e) {
            }
        }
        spannableString.setSpan(new ForegroundColorSpan(i), 0, str.length(), 33);
        return spannableString;
    }

    public static final boolean a(String str) {
        if (!i.b(str)) {
            for (String equalsIgnoreCase : new String[]{"black", "darkgray", "gray", "lightgray", "white", "red", "green", "blue", "yellow", "cyan", "magenta"}) {
                if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                    return true;
                }
            }
        }
        return false;
    }
}
