package com.umeng.socialize.view.a;

import java.util.TimerTask;

/* compiled from: ACProgressFlower */
class h extends TimerTask {
    final /* synthetic */ f a;

    h(f fVar) {
        this.a = fVar;
    }

    public void run() {
        int b = this.a.c % this.a.a.h;
        if (this.a.a.m == 100) {
            this.a.b.a(b);
        } else {
            this.a.b.a((this.a.a.h - 1) - b);
        }
        if (b == 0) {
            this.a.c = 1;
        } else {
            this.a.c = this.a.c + 1;
        }
    }
}
