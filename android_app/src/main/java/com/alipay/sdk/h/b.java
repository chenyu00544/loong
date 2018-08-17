package com.alipay.sdk.h;

import com.alipay.sdk.h.a.a;

final class b implements Runnable {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        if (this.a.b == null) {
            this.a.b = new a(this.a, this.a.c);
        }
        try {
            if (!this.a.b.isShowing()) {
                this.a.b.show();
            }
        } catch (Exception e) {
        }
    }
}
