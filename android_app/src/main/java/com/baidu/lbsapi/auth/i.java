package com.baidu.lbsapi.auth;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class i extends Handler {
    final /* synthetic */ h a;

    i(h hVar, Looper looper) {
        this.a = hVar;
        super(looper);
    }

    public void handleMessage(Message message) {
        if (a.a) {
            a.a("handleMessage !!");
        }
        m mVar = (m) h.f.get(message.getData().getString("listenerKey"));
        if (a.a) {
            a.a("handleMessage listener = " + mVar);
        }
        if (mVar != null) {
            mVar.a(message.what, message.obj.toString());
        }
    }
}
