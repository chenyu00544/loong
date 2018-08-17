package org.apache.commons.lang3;

import java.util.HashMap;
import java.util.Map;

/* compiled from: ClassUtils */
public class b {
    public static final String a = String.valueOf('.');
    public static final String b = String.valueOf('$');
    private static final Map<Class<?>, Class<?>> c = new HashMap();
    private static final Map<Class<?>, Class<?>> d = new HashMap();
    private static final Map<String, String> e = new HashMap();
    private static final Map<String, String> f = new HashMap();

    static {
        c.put(Boolean.TYPE, Boolean.class);
        c.put(Byte.TYPE, Byte.class);
        c.put(Character.TYPE, Character.class);
        c.put(Short.TYPE, Short.class);
        c.put(Integer.TYPE, Integer.class);
        c.put(Long.TYPE, Long.class);
        c.put(Double.TYPE, Double.class);
        c.put(Float.TYPE, Float.class);
        c.put(Void.TYPE, Void.TYPE);
        for (Class cls : c.keySet()) {
            Class cls2 = (Class) c.get(cls);
            if (!cls.equals(cls2)) {
                d.put(cls2, cls);
            }
        }
        a("int", "I");
        a("boolean", "Z");
        a("float", "F");
        a("long", "J");
        a("short", "S");
        a("byte", "B");
        a("double", "D");
        a("char", "C");
    }

    private static void a(String str, String str2) {
        e.put(str, str2);
        f.put(str2, str);
    }

    public static String a(Class<?> cls) {
        if (cls == null) {
            return "";
        }
        return a(cls.getName());
    }

    public static String a(String str) {
        int i = 0;
        if (str == null) {
            return "";
        }
        if (str.length() == 0) {
            return "";
        }
        String str2;
        StringBuilder stringBuilder = new StringBuilder();
        if (str.startsWith("[")) {
            while (str.charAt(0) == '[') {
                str = str.substring(1);
                stringBuilder.append("[]");
            }
            if (str.charAt(0) == 'L' && str.charAt(str.length() - 1) == ';') {
                str = str.substring(1, str.length() - 1);
            }
        }
        if (f.containsKey(str)) {
            str2 = (String) f.get(str);
        } else {
            str2 = str;
        }
        int lastIndexOf = str2.lastIndexOf(46);
        if (lastIndexOf != -1) {
            i = lastIndexOf + 1;
        }
        i = str2.indexOf(36, i);
        str2 = str2.substring(lastIndexOf + 1);
        if (i != -1) {
            str2 = str2.replace('$', '.');
        }
        return str2 + stringBuilder;
    }
}
