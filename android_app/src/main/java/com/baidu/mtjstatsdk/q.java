package com.baidu.mtjstatsdk;

import android.support.v4.app.Fragment;

class q implements Runnable {
    final /* synthetic */ Fragment a;
    final /* synthetic */ String b;
    final /* synthetic */ n c;

    q(n nVar, Fragment fragment, String str) {
        this.c = nVar;
        this.a = fragment;
        this.b = str;
    }

    public void run() {
        LoadCacheAnalysis.checkLoadCacheFinished(this.a.getActivity(), this.b);
    }
}
