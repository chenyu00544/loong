package com.baidu.mtjstatsdk;

import android.content.Context;

class c implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ int e;
    final /* synthetic */ long f;
    final /* synthetic */ b g;

    c(b bVar, Context context, String str, String str2, String str3, int i, long j) {
        this.g = bVar;
        this.a = context;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = i;
        this.f = j;
    }

    public void run() {
        LoadCacheAnalysis.checkLoadCacheFinished(this.a, this.b);
        a.a(this.b).a(this.c, this.d, this.e, this.f, 0, this.b);
        a.a(this.b).a(this.a, this.b);
    }
}
