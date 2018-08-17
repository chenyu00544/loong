package org.apache.commons.lang3;

import java.util.regex.Pattern;

/* compiled from: StringUtils */
public class c {
    private static final Pattern a = Pattern.compile("\\s+");

    public static boolean a(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean b(CharSequence charSequence) {
        return !a(charSequence);
    }

    public static boolean a(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null) {
            return charSequence2 == null;
        } else {
            return charSequence.equals(charSequence2);
        }
    }
}
