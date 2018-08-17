package com.baidu.mtjstatsdk;

import android.content.Context;
import com.baidu.mtjstatsdk.b.a;
import com.baidu.mtjstatsdk.b.d;

class e implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ long e;
    final /* synthetic */ b f;

    e(b bVar, Context context, String str, String str2, String str3, long j) {
        this.f = bVar;
        this.a = context;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = j;
    }

    public void run() {
        LoadCacheAnalysis.checkLoadCacheFinished(this.a, this.b);
        String a = this.f.a(this.c, this.d);
        g gVar = (g) this.f.a.get(a);
        if (gVar == null) {
            if (a.a(this.b)) {
                d.b("statsdk", "EventStat: event_id[" + this.c + "] with label[" + this.d + "] is not started or alread done.");
            }
        } else if (this.c.equals(gVar.a) && this.d.equals(gVar.b)) {
            this.f.a.remove(a);
            long j = this.e - gVar.c;
            if (j > 0) {
                a.a(this.b).a(this.c, this.d, 1, gVar.c, j, this.b);
                a.a(this.b).a(this.a, this.b);
            } else if (a.a(this.b)) {
                d.a("statsdk", "EventStat: Wrong Case, Duration must be positive");
            }
        } else if (a.a(this.b)) {
            d.a("statsdk", "EventStat: Wrong Case, eventId/label pair not match");
        }
    }
}
