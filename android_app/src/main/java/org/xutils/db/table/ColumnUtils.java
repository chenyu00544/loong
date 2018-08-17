package org.xutils.db.table;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import org.xutils.common.util.LogUtil;
import org.xutils.db.converter.ColumnConverterFactory;

public final class ColumnUtils {
    private static final HashSet<Class<?>> a = new HashSet(2);
    private static final HashSet<Class<?>> b = new HashSet(2);
    private static final HashSet<Class<?>> c = new HashSet(4);

    private ColumnUtils() {
    }

    static {
        a.add(Boolean.TYPE);
        a.add(Boolean.class);
        b.add(Integer.TYPE);
        b.add(Integer.class);
        c.addAll(b);
        c.add(Long.TYPE);
        c.add(Long.class);
    }

    public static boolean isAutoIdType(Class<?> cls) {
        return c.contains(cls);
    }

    public static boolean isInteger(Class<?> cls) {
        return b.contains(cls);
    }

    public static boolean isBoolean(Class<?> cls) {
        return a.contains(cls);
    }

    public static Object convert2DbValueIfNeeded(Object obj) {
        if (obj != null) {
            return ColumnConverterFactory.getColumnConverter(obj.getClass()).fieldValue2DbValue(obj);
        }
        return obj;
    }

    static Method a(Class<?> cls, Field field) {
        Method method = null;
        if (Object.class.equals(cls)) {
            return null;
        }
        String name = field.getName();
        if (isBoolean(field.getType())) {
            method = a((Class) cls, name);
        }
        if (method == null) {
            name = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
            try {
                method = cls.getDeclaredMethod(name, new Class[0]);
            } catch (NoSuchMethodException e) {
                LogUtil.d(cls.getName() + "#" + name + " not exist");
            }
        }
        if (method == null) {
            return a(cls.getSuperclass(), field);
        }
        return method;
    }

    static Method b(Class<?> cls, Field field) {
        Method method = null;
        if (Object.class.equals(cls)) {
            return null;
        }
        String name = field.getName();
        Class type = field.getType();
        if (isBoolean(type)) {
            method = a(cls, name, type);
        }
        if (method == null) {
            name = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
            try {
                method = cls.getDeclaredMethod(name, new Class[]{type});
            } catch (NoSuchMethodException e) {
                LogUtil.d(cls.getName() + "#" + name + " not exist");
            }
        }
        if (method == null) {
            return b(cls.getSuperclass(), field);
        }
        return method;
    }

    private static Method a(Class<?> cls, String str) {
        if (!str.startsWith("is")) {
            str = "is" + str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        try {
            return cls.getDeclaredMethod(str, new Class[0]);
        } catch (NoSuchMethodException e) {
            LogUtil.d(cls.getName() + "#" + str + " not exist");
            return null;
        }
    }

    private static Method a(Class<?> cls, String str, Class<?> cls2) {
        String str2;
        if (str.startsWith("is")) {
            str2 = "set" + str.substring(2, 3).toUpperCase() + str.substring(3);
        } else {
            str2 = "set" + str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        try {
            return cls.getDeclaredMethod(str2, new Class[]{cls2});
        } catch (NoSuchMethodException e) {
            LogUtil.d(cls.getName() + "#" + str2 + " not exist");
            return null;
        }
    }
}
