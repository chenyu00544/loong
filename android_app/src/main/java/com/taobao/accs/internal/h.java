package com.taobao.accs.internal;

import android.content.Intent;
import android.os.Process;
import com.taobao.accs.internal.ServiceImpl.ServiceImpl_2;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.a;

/* compiled from: Taobao */
class h implements Runnable {
    final /* synthetic */ ServiceImpl_2 a;

    h(ServiceImpl_2 serviceImpl_2) {
        this.a = serviceImpl_2;
    }

    public void run() {
        try {
            if (this.a.this$0.c == null || !UtilityImpl.getServiceEnabled(this.a.this$0.c)) {
                Process.killProcess(Process.myPid());
            } else {
                Intent intent = new Intent();
                intent.setAction("org.agoo.android.intent.action.PING_V4");
                intent.setClassName(this.a.this$0.c.getPackageName(), a.channelService);
                this.a.this$0.c.startService(intent);
                UTMini.getInstance().commitEvent(66001, "probeServiceEnabled", UtilityImpl.getDeviceId(this.a.this$0.c));
                ALog.d("ServiceImpl", "ReceiverImpl probeTaoBao........mContext.startService(intent) [probe][successfully]", new Object[0]);
            }
            ALog.d("ServiceImpl", "ReceiverImpl probeTaoBao........messageServiceBinder [probe][end]", new Object[0]);
        } catch (Throwable th) {
            ALog.d("ServiceImpl", "ReceiverImpl probeTaoBao error........e=" + th, new Object[0]);
        }
    }
}
