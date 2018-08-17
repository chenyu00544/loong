package com.alipay.sdk.util;

import android.app.Activity;

final class k implements Runnable {
    final /* synthetic */ Activity a;

    k(Activity activity) {
        this.a = activity;
    }

    public final void run() {
        this.a.finish();
    }
}
