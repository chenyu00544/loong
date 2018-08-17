package com.baidu.mtjstatsdk;

import android.content.Context;
import android.support.v4.app.Fragment;
import com.baidu.mtjstatsdk.b.a;
import com.baidu.mtjstatsdk.b.d;
import java.lang.ref.WeakReference;

class t implements Runnable {
    final /* synthetic */ n a;
    private long b;
    private long c;
    private WeakReference<Context> d;
    private WeakReference<Fragment> e;
    private WeakReference<Object> f;
    private int g = 1;
    private String h;

    public t(n nVar, long j, long j2, Context context, Fragment fragment, Object obj, int i, String str) {
        this.a = nVar;
        this.b = j;
        this.c = j2;
        this.d = new WeakReference(context);
        this.e = new WeakReference(fragment);
        this.f = new WeakReference(obj);
        this.g = i;
        this.h = str;
    }

    public void run() {
        if (this.c - this.b >= ((long) ((SessionAnalysisObject) n.a.get(this.h)).getSessionPeriod()) && this.b > 0) {
            if (this.d.get() != null || this.e.get() != null || this.f.get() != null) {
                ((SessionAnalysisObject) n.a.get(this.h)).h.b(this.b);
                String jSONObject = ((SessionAnalysisObject) n.a.get(this.h)).h.c().toString();
                if (a.a(this.h)) {
                    d.a("statsdk", "new session:" + jSONObject);
                }
                a.a(this.h).b(jSONObject, this.h);
                Context context = null;
                Context context2;
                if (this.g == 1) {
                    context2 = (Context) this.d.get();
                    a.a(this.h).a(context2, this.h);
                    context = context2;
                } else if (this.g == 2) {
                    a.a(this.h).a(((Fragment) this.e.get()).getActivity(), this.h);
                } else if (this.g == 3) {
                    context2 = n.a(this.f.get());
                    a.a(this.h).a(context2, this.h);
                    context = context2;
                }
                ((SessionAnalysisObject) n.a.get(this.h)).h.b();
                if (this.g == 1) {
                    this.a.a((Context) this.d.get(), this.h);
                } else if (this.g == 2) {
                    this.a.a(((Fragment) this.e.get()).getActivity(), this.h);
                } else {
                    this.a.a(context, this.h);
                }
                j.a().a(context, this.h);
            }
        }
    }
}
