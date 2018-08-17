package com.baidu.mtjstatsdk;

import android.content.Context;
import com.baidu.mtjstatsdk.a.a;

class k implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ j c;

    k(j jVar, Context context, String str) {
        this.c = jVar;
        this.a = context;
        this.b = str;
    }

    public void run() {
        if (this.c.e != null) {
            this.c.e.cancel();
            this.c.e = null;
        }
        this.c.c = a.a()[BasicStoreTools.getInstance().loadSendStrategy(this.a)];
        this.c.d = BasicStoreTools.getInstance().loadSendStrategyTime(this.a);
        this.c.b = BasicStoreTools.getInstance().loadOnlyWifiChannel(this.a);
        if (this.c.c.equals(a.SET_TIME_INTERVAL)) {
            this.c.b(this.a, this.b);
        } else if (this.c.c.equals(a.ONCE_A_DAY)) {
            this.c.b(this.a, this.b);
        }
        j.g.postDelayed(new l(this), (long) (((DataCoreObject) a.a.get(this.b)).a() * 1000));
    }
}
