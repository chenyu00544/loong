package org.apache.commons.lang3;

/* compiled from: Validate */
public class e {
    public static void a(boolean z, String str, long j) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, new Object[]{Long.valueOf(j)}));
        }
    }

    public static void a(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    public static void a(boolean z) {
        if (!z) {
            throw new IllegalArgumentException("The validated expression is false");
        }
    }

    public static <T> T a(T t, String str, Object... objArr) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.format(str, objArr));
    }
}
