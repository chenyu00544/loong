package com.unionpay.mobile.android.upwidget;

import android.os.Handler;
import android.os.Message;
import com.unionpay.mobile.android.upwidget.UPScrollView.a;

final class v extends Handler {
    final /* synthetic */ UPScrollView a;

    v(UPScrollView uPScrollView) {
        this.a = uPScrollView;
    }

    public final void handleMessage(Message message) {
        int scrollY = this.a.getScrollY();
        if (this.a.b != scrollY) {
            this.a.b = scrollY;
            this.a.d.sendMessageDelayed(this.a.d.obtainMessage(), 5);
        }
        if (this.a.a != null && this.a.a.get() != null) {
            ((a) this.a.a.get()).e(scrollY);
        }
    }
}
