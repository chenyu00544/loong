package com.unionpay.c;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

final class q implements InvocationHandler {
    final /* synthetic */ ak a;
    final /* synthetic */ Object b;

    q(ak akVar, Object obj) {
        this.a = akVar;
        this.b = obj;
    }

    public final Object invoke(Object obj, Method method, Object[] objArr) {
        this.a.a(obj, method, objArr);
        Object invoke = method.invoke(this.b, objArr);
        this.a.a(obj, method, objArr, invoke);
        return invoke;
    }
}
