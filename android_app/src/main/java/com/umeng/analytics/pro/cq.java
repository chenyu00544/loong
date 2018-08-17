package com.umeng.analytics.pro;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* compiled from: FieldMetaData */
public class cq implements Serializable {
    private static Map<Class<? extends ce>, Map<? extends cl, cq>> d = new HashMap();
    public final String a;
    public final byte b;
    public final cr c;

    public cq(String str, byte b, cr crVar) {
        this.a = str;
        this.b = b;
        this.c = crVar;
    }

    public static void a(Class<? extends ce> cls, Map<? extends cl, cq> map) {
        d.put(cls, map);
    }

    public static Map<? extends cl, cq> a(Class<? extends ce> cls) {
        if (!d.containsKey(cls)) {
            try {
                cls.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("InstantiationException for TBase class: " + cls.getName() + ", message: " + e.getMessage());
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("IllegalAccessException for TBase class: " + cls.getName() + ", message: " + e2.getMessage());
            }
        }
        return (Map) d.get(cls);
    }
}
