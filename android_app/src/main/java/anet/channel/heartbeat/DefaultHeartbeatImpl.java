package anet.channel.heartbeat;

import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.Session;
import anet.channel.c.c;
import anet.channel.util.ALog;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.util.concurrent.TimeUnit;

/* compiled from: Taobao */
public class DefaultHeartbeatImpl implements IHeartbeat, Runnable {
    private static final String TAG = "awcn.DefaultHeartbeatImpl";
    private int bgHeartbeatCount = 0;
    private volatile long executeTime = 0;
    protected long interval = 0;
    private volatile boolean isCancelled = false;
    private final Session session;

    public DefaultHeartbeatImpl(Session session) {
        this.session = session;
        this.interval = (long) session.getConnStrategy().getHeartbeat();
    }

    public void start() {
        ALog.i(TAG, "heartbeat start", null, "session", this.session);
        this.executeTime = System.currentTimeMillis() + this.interval;
        submit(this.interval);
    }

    public void stop() {
        ALog.i(TAG, "heartbeat stop", null, "session", this.session);
        this.isCancelled = true;
    }

    public void delay() {
        long currentTimeMillis = System.currentTimeMillis() + this.interval;
        if (this.executeTime + 1000 < currentTimeMillis) {
            if (ALog.isPrintLog(1)) {
                ALog.d(TAG, "setNextHeartbeat", null, "session", this.session, ParamKey.OFFSET, Long.valueOf(currentTimeMillis - this.executeTime));
            }
            this.executeTime = currentTimeMillis;
        }
    }

    public void run() {
        int i = 0;
        if (!this.isCancelled) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis < this.executeTime) {
                submit(this.executeTime - currentTimeMillis);
                return;
            }
            boolean isAppBackground = GlobalAppRuntimeInfo.isAppBackground();
            if (isAppBackground) {
                ALog.e(TAG, "close session in background", null, "session", this.session);
                this.session.close(false);
                return;
            }
            if (ALog.isPrintLog(1)) {
                ALog.d(TAG, "heartbeat", null, "session", this.session);
            }
            this.session.ping(true);
            if (isAppBackground) {
                i = this.bgHeartbeatCount + 1;
            }
            this.bgHeartbeatCount = i;
            this.executeTime = this.interval + currentTimeMillis;
            submit(this.interval);
        }
    }

    private void submit(long j) {
        try {
            c.a(this, 50 + j, TimeUnit.MILLISECONDS);
        } catch (Throwable e) {
            ALog.e(TAG, "Submit heartbeat task to thread pool failed.", null, e, new Object[0]);
        }
    }
}
