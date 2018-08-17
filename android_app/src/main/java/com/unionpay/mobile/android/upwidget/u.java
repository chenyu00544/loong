package com.unionpay.mobile.android.upwidget;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

final class u implements OnGlobalLayoutListener {
    final /* synthetic */ UPScrollView a;

    u(UPScrollView uPScrollView) {
        this.a = uPScrollView;
    }

    public final void onGlobalLayout() {
        this.a.d.sendMessageDelayed(this.a.d.obtainMessage(), 5);
    }
}
