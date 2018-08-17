package com.alipay.android.phone.mrpc.core;

import java.lang.reflect.Proxy;

public final class ab {
    private k a;
    private ad b = new ad(this);

    public ab(k kVar) {
        this.a = kVar;
    }

    public final k a() {
        return this.a;
    }

    public final <T> T a(Class<T> cls) {
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new ac(this.a, cls, this.b));
    }
}
