package org.apache.commons.lang3;

import java.io.Serializable;

public class ObjectUtils {
    public static final Null a = new Null();

    public static class Null implements Serializable {
        Null() {
        }
    }

    public static boolean a(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    public static boolean b(Object obj, Object obj2) {
        return !a(obj, obj2);
    }

    public static int a(Object obj) {
        return obj == null ? 0 : obj.hashCode();
    }

    public static void a(StringBuffer stringBuffer, Object obj) {
        if (obj == null) {
            throw new NullPointerException("Cannot get the toString of a null identity");
        }
        stringBuffer.append(obj.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(obj)));
    }
}
