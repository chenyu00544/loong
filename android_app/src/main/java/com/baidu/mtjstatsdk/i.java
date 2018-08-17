package com.baidu.mtjstatsdk;

import android.content.Context;

final class i {
    Context a;
    boolean b = false;
    boolean c = false;
    String d = null;
    h e;
    final /* synthetic */ LoadCacheAnalysis f;

    i(LoadCacheAnalysis loadCacheAnalysis, Context context, String str) {
        this.f = loadCacheAnalysis;
        this.d = str;
        this.a = context;
        this.b = false;
        this.c = false;
        this.e = new h(loadCacheAnalysis, context, str);
    }
}
