package org.apache.commons.lang3;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CharSet implements Serializable {
    public static final CharSet ASCII_ALPHA = new CharSet("a-zA-Z");
    public static final CharSet ASCII_ALPHA_LOWER = new CharSet("a-z");
    public static final CharSet ASCII_ALPHA_UPPER = new CharSet("A-Z");
    public static final CharSet ASCII_NUMERIC = new CharSet("0-9");
    public static final CharSet EMPTY = new CharSet((String) null);
    protected static final Map<String, CharSet> a = Collections.synchronizedMap(new HashMap());
    private final Set<a> b = Collections.synchronizedSet(new HashSet());

    static {
        a.put(null, EMPTY);
        a.put("", EMPTY);
        a.put("a-zA-Z", ASCII_ALPHA);
        a.put("A-Za-z", ASCII_ALPHA);
        a.put("a-z", ASCII_ALPHA_LOWER);
        a.put("A-Z", ASCII_ALPHA_UPPER);
        a.put("0-9", ASCII_NUMERIC);
    }

    public static CharSet getInstance(String... strArr) {
        if (strArr == null) {
            return null;
        }
        if (strArr.length == 1) {
            CharSet charSet = (CharSet) a.get(strArr[0]);
            if (charSet != null) {
                return charSet;
            }
        }
        return new CharSet(strArr);
    }

    protected CharSet(String... strArr) {
        for (String a : strArr) {
            a(a);
        }
    }

    protected void a(String str) {
        if (str != null) {
            int length = str.length();
            int i = 0;
            while (i < length) {
                int i2 = length - i;
                if (i2 >= 4 && str.charAt(i) == '^' && str.charAt(i + 2) == '-') {
                    this.b.add(a.b(str.charAt(i + 1), str.charAt(i + 3)));
                    i += 4;
                } else if (i2 >= 3 && str.charAt(i + 1) == '-') {
                    this.b.add(a.a(str.charAt(i), str.charAt(i + 2)));
                    i += 3;
                } else if (i2 < 2 || str.charAt(i) != '^') {
                    this.b.add(a.a(str.charAt(i)));
                    i++;
                } else {
                    this.b.add(a.b(str.charAt(i + 1)));
                    i += 2;
                }
            }
        }
    }

    public boolean contains(char c) {
        for (a c2 : this.b) {
            if (c2.c(c)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CharSet)) {
            return false;
        }
        return this.b.equals(((CharSet) obj).b);
    }

    public int hashCode() {
        return this.b.hashCode() + 89;
    }

    public String toString() {
        return this.b.toString();
    }
}
