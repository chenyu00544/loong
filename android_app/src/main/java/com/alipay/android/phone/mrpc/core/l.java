package com.alipay.android.phone.mrpc.core;

import android.content.Context;

public final class l extends aa {
    private Context a;

    public l(Context context) {
        this.a = context;
    }

    public final <T> T a(Class<T> cls, b bVar) {
        return new ab(new m(this, bVar)).a(cls);
    }
}
