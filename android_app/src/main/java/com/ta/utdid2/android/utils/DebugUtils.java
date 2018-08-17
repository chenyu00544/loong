package com.ta.utdid2.android.utils;

import java.lang.reflect.Method;

public class DebugUtils {
    public static boolean DBG = false;
    private static final String PROPERTY_DEBUG = "alidebug";
    private static Class<?> mClassType = null;
    private static Method mGetIntMethod = null;
    private static Method mGetMethod = null;

    public static String get(String str) {
        init();
        try {
            return (String) mGetMethod.invoke(mClassType, new Object[]{str});
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getInt(String str, int i) {
        init();
        try {
            i = ((Integer) mGetIntMethod.invoke(mClassType, new Object[]{str, Integer.valueOf(i)})).intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    static {
        boolean z = true;
        if (getInt(PROPERTY_DEBUG, 0) != 1) {
            z = false;
        }
        DBG = z;
    }

    private static void init() {
        try {
            if (mClassType == null) {
                mClassType = Class.forName("android.os.SystemProperties");
                mGetMethod = mClassType.getDeclaredMethod("get", new Class[]{String.class});
                mGetIntMethod = mClassType.getDeclaredMethod("getInt", new Class[]{String.class, Integer.TYPE});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
