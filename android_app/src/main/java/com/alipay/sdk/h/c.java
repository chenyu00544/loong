package com.alipay.sdk.h;

final class c implements Runnable {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        if (this.a.b != null) {
            try {
                this.a.b.dismiss();
            } catch (Exception e) {
            }
        }
    }
}
