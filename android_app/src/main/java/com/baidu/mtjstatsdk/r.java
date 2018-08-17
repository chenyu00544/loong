package com.baidu.mtjstatsdk;

class r implements Runnable {
    final /* synthetic */ Object a;
    final /* synthetic */ String b;
    final /* synthetic */ n c;

    r(n nVar, Object obj, String str) {
        this.c = nVar;
        this.a = obj;
        this.b = str;
    }

    public void run() {
        LoadCacheAnalysis.checkLoadCacheFinished(n.a(this.a), this.b);
    }
}
