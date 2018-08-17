package com.unionpay.mobile.android.nocard.views;

import android.view.ViewTreeObserver.OnPreDrawListener;

final class an implements OnPreDrawListener {
    final /* synthetic */ b a;

    an(b bVar) {
        this.a = bVar;
    }

    public final boolean onPreDraw() {
        this.a.r.getViewTreeObserver().removeOnPreDrawListener(this);
        this.a.u = this.a.r.getMeasuredHeight();
        this.a.v = this.a.r.getTop();
        return true;
    }
}
