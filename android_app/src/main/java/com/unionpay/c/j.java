package com.unionpay.c;

import java.util.concurrent.ConcurrentLinkedQueue;

final class j extends ThreadLocal {
    final /* synthetic */ i a;

    j(i iVar) {
        this.a = iVar;
    }

    protected final /* synthetic */ Object initialValue() {
        return new ConcurrentLinkedQueue();
    }
}
