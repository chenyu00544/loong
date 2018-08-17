package com.baidu.mtjstatsdk;

import android.content.Context;
import com.baidu.mtjstatsdk.b.a;
import com.baidu.mtjstatsdk.b.d;

class f implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ long c;
    final /* synthetic */ String d;
    final /* synthetic */ String e;
    final /* synthetic */ b f;

    f(b bVar, Context context, String str, long j, String str2, String str3) {
        this.f = bVar;
        this.a = context;
        this.b = str;
        this.c = j;
        this.d = str2;
        this.e = str3;
    }

    public void run() {
        LoadCacheAnalysis.checkLoadCacheFinished(this.a, this.b);
        if (this.c > 0) {
            a.a(this.b).a(this.d, this.e, 1, System.currentTimeMillis(), this.c, this.b);
            a.a(this.b).a(this.a, this.b);
        } else if (a.a(this.b)) {
            d.a("statsdk", "EventStat: Wrong Case, Duration must be positive");
        }
    }
}
