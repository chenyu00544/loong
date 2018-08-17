package com.umeng.socialize.view.a;

import java.util.TimerTask;

/* compiled from: ACProgressCustom */
class e extends TimerTask {
    final /* synthetic */ c a;

    e(c cVar) {
        this.a = cVar;
    }

    public void run() {
        int c = this.a.g % this.a.h;
        this.a.e.a(c);
        if (c == 0) {
            this.a.g = 1;
        } else {
            this.a.g = this.a.g + 1;
        }
    }
}
