package com.umeng.socialize.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Dummy {

    final class Dummy_1 implements InvocationHandler {
        Dummy_1() {
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            return null;
        }
    }

    public static <T> T get(Class<T> cls, T t) {
        if (t != null) {
            return t;
        }
        if (cls.isInterface()) {
            return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new Dummy_1());
        }
        try {
            return cls.newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
