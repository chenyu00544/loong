package com.unionpay.mobile.android.upwidget;

import android.os.Handler;
import android.os.Message;

final class t extends Handler {
    final /* synthetic */ UPRadiationView a;

    t(UPRadiationView uPRadiationView) {
        this.a = uPRadiationView;
    }

    public final void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case 0:
                UPRadiationView.a(this.a);
                this.a.invalidate();
                if (this.a.a != null && this.a.a.size() > 0) {
                    this.a.e.sendEmptyMessageDelayed(0, 50);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
