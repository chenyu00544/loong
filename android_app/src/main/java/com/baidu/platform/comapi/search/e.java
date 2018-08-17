package com.baidu.platform.comapi.search;

import android.os.Handler;
import android.os.Message;

class e extends Handler {
    final /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    public void handleMessage(Message message) {
        if (this.a.c != 0 && this.a.c == ((Long) message.obj).longValue() && this.a.d != null) {
            this.a.d.a(message);
        }
    }
}
