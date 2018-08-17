package com.umeng.analytics.pro;

import java.lang.reflect.InvocationTargetException;

/* compiled from: TEnumHelper */
public class cj {
    public static ci a(Class<? extends ci> cls, int i) {
        try {
            return (ci) cls.getMethod("findByValue", new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(i)});
        } catch (NoSuchMethodException e) {
            return null;
        } catch (IllegalAccessException e2) {
            return null;
        } catch (InvocationTargetException e3) {
            return null;
        }
    }
}
