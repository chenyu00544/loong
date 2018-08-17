package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public final class ac implements InvocationHandler {
    protected k a;
    protected Class<?> b;
    protected ad c;

    public ac(k kVar, Class<?> cls, ad adVar) {
        this.a = kVar;
        this.b = cls;
        this.c = adVar;
    }

    public final Object invoke(Object obj, Method method, Object[] objArr) {
        ad adVar = this.c;
        Class cls = this.b;
        return adVar.a(method, objArr);
    }
}
