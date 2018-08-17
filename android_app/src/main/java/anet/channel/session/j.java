package anet.channel.session;

import anet.channel.Session.Status;
import anet.channel.entity.EventType;
import anet.channel.util.ALog;

/* compiled from: Taobao */
class j implements Runnable {
    final /* synthetic */ i a;

    j(i iVar) {
        this.a = iVar;
    }

    public void run() {
        if (this.a.mHasUnrevPing) {
            if (ALog.isPrintLog(1)) {
                ALog.e(i.a, "getRecvTimeOutRunnable", this.a.mSeq, "send msg time out! pingUnRcv:" + this.a.mHasUnrevPing);
            }
            try {
                if (this.a.mStatus == Status.CONNECTED) {
                    this.a.notifyStatus(Status.AUTH_FAIL, null);
                } else {
                    this.a.handleCallbacks(EventType.DATA_TIMEOUT, null);
                }
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
