package com.unionpay.mobile.android.widgets;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.unionpay.mobile.android.utils.k;

final class r implements OnGlobalLayoutListener {
    final /* synthetic */ UPWidget a;

    r(UPWidget uPWidget) {
        this.a = uPWidget;
    }

    public final void onGlobalLayout() {
        k.a("uppay", "onGlobalLayout() +++");
        int height = this.a.x().getRootView().getHeight() - this.a.x().getHeight();
        if (height <= UPWidget.o && height <= UPWidget.o) {
            this.a.l();
        }
        k.a("uppay", "onGlobalLayout() ---");
    }
}
