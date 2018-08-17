package com.baidu.mtjstatsdk;

import android.content.Context;

class o implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ n c;

    o(n nVar, Context context, String str) {
        this.c = nVar;
        this.a = context;
        this.b = str;
    }

    public void run() {
        LoadCacheAnalysis.checkLoadCacheFinished(this.a, this.b);
    }
}
