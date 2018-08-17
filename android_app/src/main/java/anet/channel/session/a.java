package anet.channel.session;

import anet.channel.entity.EventType;
import anet.channel.util.ALog;

/* compiled from: Taobao */
class a implements Runnable {
    final /* synthetic */ AccsSession a;

    a(AccsSession accsSession) {
        this.a = accsSession;
    }

    public void run() {
        if (this.a.mHasUnrevPing) {
            ALog.e("awcn.AccsSession", "send msg time out!", this.a.mSeq, "pingUnRcv:", Boolean.valueOf(this.a.mHasUnrevPing));
            try {
                this.a.handleCallbacks(EventType.DATA_TIMEOUT, null);
                if (this.a.mSessionStat != null) {
                    this.a.mSessionStat.closeReason = "ping time out";
                }
                this.a.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
