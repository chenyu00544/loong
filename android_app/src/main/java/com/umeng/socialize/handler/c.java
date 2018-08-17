package com.umeng.socialize.handler;

/* compiled from: UMAPIShareHandler */
class c implements Runnable {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public void run() {
        this.a.d.doShare(this.a.a, this.a.b, this.a.c);
    }
}
