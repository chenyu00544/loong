package com.taobao.accs.data;

import android.content.Context;
import android.content.Intent;

/* compiled from: Taobao */
final class f implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ Intent b;

    f(Context context, Intent intent) {
        this.a = context;
        this.b = intent;
    }

    public void run() {
        e.c().b(this.a, this.b);
    }
}
