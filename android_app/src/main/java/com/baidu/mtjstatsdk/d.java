package com.baidu.mtjstatsdk;

import android.content.Context;
import com.baidu.mtjstatsdk.b.a;

class d implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ long c;
    final /* synthetic */ String d;
    final /* synthetic */ String e;
    final /* synthetic */ b f;

    d(b bVar, Context context, String str, long j, String str2, String str3) {
        this.f = bVar;
        this.a = context;
        this.b = str;
        this.c = j;
        this.d = str2;
        this.e = str3;
    }

    public void run() {
        LoadCacheAnalysis.checkLoadCacheFinished(this.a, this.b);
        g gVar = new g(this.f);
        gVar.c = this.c;
        gVar.a = this.d;
        gVar.b = this.e;
        String a = this.f.a(this.d, this.e);
        if (this.f.a.get(a) != null && a.a(this.b)) {
            com.baidu.mtjstatsdk.b.d.b("statsdk", "EventStat: event_id[" + this.d + "] with label[" + this.e + "] is duplicated, older is removed");
        }
        this.f.a.put(a, gVar);
        if (a.a(this.b)) {
            com.baidu.mtjstatsdk.b.d.a("statsdk", "put a keyword[" + a + "] into durationlist");
        }
    }
}
