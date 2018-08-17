package com.baidu.mtjstatsdk;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import com.baidu.mtjstatsdk.b.a;
import com.baidu.mtjstatsdk.b.d;
import java.lang.ref.WeakReference;

class s implements Runnable {
    final /* synthetic */ n a;
    private long b;
    private WeakReference<Context> c;
    private WeakReference<Fragment> d;
    private WeakReference<Object> e;
    private long f;
    private WeakReference<Context> g;
    private WeakReference<Fragment> h;
    private WeakReference<Object> i;
    private int j;
    private String k = null;
    private String l = null;

    public s(n nVar, long j, Context context, Fragment fragment, long j2, Context context2, Fragment fragment2, int i, String str, Object obj, Object obj2, String str2) {
        this.a = nVar;
        this.b = j;
        if (context != null) {
            this.c = new WeakReference(context);
        }
        this.f = j2;
        if (context2 != null) {
            this.g = new WeakReference(context2);
        }
        if (fragment != null) {
            this.d = new WeakReference(fragment);
        }
        if (fragment2 != null) {
            this.h = new WeakReference(fragment2);
        }
        if (obj != null) {
            this.i = new WeakReference(obj);
        }
        if (obj2 != null) {
            this.e = new WeakReference(obj2);
        }
        this.j = i;
        this.k = str;
        this.l = str2;
    }

    public void run() {
        SessionAnalysisObject sessionAnalysisObject = (SessionAnalysisObject) n.a.get(this.l);
        long j;
        if (this.j == 1) {
            if (this.c.get() == this.g.get()) {
                j = this.b - this.f;
                Activity activity = (Activity) this.c.get();
                if (activity != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    if (this.k != null) {
                        stringBuilder.append(this.k);
                    } else {
                        stringBuilder.append(activity.getComponentName().getShortClassName());
                        if (stringBuilder.charAt(0) == '.') {
                            stringBuilder.deleteCharAt(0);
                        }
                    }
                    if (a.a(this.l)) {
                        d.a("statsdk", "new page view, page name = " + stringBuilder.toString() + ",stay time = " + j + "(ms)");
                    }
                    sessionAnalysisObject.h.a(stringBuilder.toString(), j, this.f);
                    ((SessionAnalysisObject) n.a.get(this.l)).flushSession((Context) this.c.get(), this.b, this.l);
                } else if (a.a(this.l)) {
                    d.c("statsdk", "onPause,WeakReference is already been released");
                }
            } else if (this.k != null) {
                if (a.a(this.l)) {
                    d.c("statsdk", "onPageStart() 或 onPageEnd()安放错误  || onPageStart() or onPageEnd() install error.");
                }
            } else if (a.a(this.l)) {
                d.c("statsdk", "onPause() 或 onResume()安放错误  ||  onPause() or onResume() install error.");
            }
        } else if (this.j == 2) {
            if (this.d.get() == this.h.get()) {
                j = this.b - this.f;
                Fragment fragment = (Fragment) this.d.get();
                if (fragment != null) {
                    r4 = fragment.getClass().getName().toString();
                    r1 = r4.substring(r4.lastIndexOf(".") + 1);
                    if (a.a(this.l)) {
                        d.a("statsdk", "Fragment new page view, page name = " + r4.toString() + ",stay time = " + j + "(ms)");
                    }
                    sessionAnalysisObject.h.a(r1, j, this.f);
                    ((SessionAnalysisObject) n.a.get(this.l)).flushSession(((Fragment) this.d.get()).getActivity(), this.b, this.l);
                } else if (a.a(this.l)) {
                    d.c("statsdk", "onPause,WeakReference is already been released");
                }
            } else if (a.a(this.l)) {
                d.c("statsdk", " Fragment onPause() 或 onResume()安放错误||onPause() or onResume() install error.");
            }
        } else if (this.j != 3) {
        } else {
            if (this.e.get() == this.i.get()) {
                j = this.b - this.f;
                Object obj = this.e.get();
                if (obj != null) {
                    Context a = n.a(obj);
                    r4 = obj.getClass().getName().toString();
                    r1 = r4.substring(r4.lastIndexOf(".") + 1);
                    if (a.a(this.l)) {
                        d.a("statsdk", "android.app.Fragment new page view, page name = " + r4.toString() + ",stay time = " + j + "(ms)");
                    }
                    sessionAnalysisObject.h.a(r1, j, this.f);
                    ((SessionAnalysisObject) n.a.get(this.l)).flushSession(a, this.b, this.l);
                } else if (a.a(this.l)) {
                    d.c("statsdk", "onPause,WeakReference is already been released");
                }
            } else if (a.a(this.l)) {
                d.c("statsdk", " Fragment onPause() 或 onResume()安放错误||onPause() or onResume() install error.");
            }
        }
    }
}
