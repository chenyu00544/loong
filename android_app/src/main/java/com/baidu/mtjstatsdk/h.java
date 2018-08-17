package com.baidu.mtjstatsdk;

import android.content.Context;
import android.os.Process;
import com.baidu.mtjstatsdk.b.a;
import com.baidu.mtjstatsdk.b.d;

class h extends Thread {
    final /* synthetic */ LoadCacheAnalysis a;
    private String b;
    private Context c;

    public h(LoadCacheAnalysis loadCacheAnalysis, Context context, String str) {
        this.a = loadCacheAnalysis;
        if (context != null && !loadCacheAnalysis.isStart(str)) {
            this.b = str;
            this.c = context;
            if (a.a(this.b)) {
                d.a("**************load caceh**start********");
            }
        }
    }

    public void run() {
        Process.setThreadPriority(19);
        a.a(this.b).c(this.c, this.b);
        a.a(this.b).b(this.c, this.b);
        this.a.b(this.b);
        synchronized (this) {
            try {
                notifyAll();
            } catch (Throwable e) {
                if (a.a(this.b)) {
                    d.a("statsdk", e);
                }
            }
        }
        j.a().a(this.c, this.b);
        if (a.a(this.b)) {
            d.a("**************load caceh**end********appKey=" + this.b);
        }
    }
}
