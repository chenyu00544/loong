package com.baidu.mtjstatsdk;

import android.content.Context;
import com.baidu.mtjstatsdk.b.a;
import com.baidu.mtjstatsdk.b.d;
import java.util.TimerTask;

class m extends TimerTask {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;
    final /* synthetic */ j c;

    m(j jVar, String str, Context context) {
        this.c = jVar;
        this.a = str;
        this.b = context;
    }

    public void run() {
        if (!a.a(this.a).c(this.a)) {
            if (a.a(this.a)) {
                d.a("welcome timer log start");
            }
            this.c.a(this.b, this.c.b, this.a);
        }
    }
}
