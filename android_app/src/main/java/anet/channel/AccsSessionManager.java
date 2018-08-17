package anet.channel;

import android.text.TextUtils;
import anet.channel.entity.ConnType.TypeLevel;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.StrategyCenter;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.StringUtils;
import java.util.Arrays;

/* compiled from: Taobao */
public class AccsSessionManager {
    public static final Callback DISABLE_AUTO_CONNTION = new a();
    private static final String TAG = "awcn.AccsSessionManager";
    volatile Callback cb = null;
    SessionCenter sessionCenter = null;
    String[] sessionKeyArray = new String[0];

    /* compiled from: Taobao */
    public interface Callback {
        int getSessionCount();

        String getSessionKey(int i);
    }

    /* compiled from: Taobao */
    protected static class a implements Callback {
        protected Config a = null;

        protected a(Config config) {
            this.a = config;
        }

        public int getSessionCount() {
            return this.a.isUnitEnable() ? 2 : 1;
        }

        public String getSessionKey(int i) {
            if (i >= getSessionCount()) {
                throw new IllegalArgumentException("index exceeds count!");
            }
            String accsHost;
            if (i == 0) {
                accsHost = this.a.getAccsHost();
            } else {
                if (i == 1) {
                    Object unitPrefix = StrategyCenter.getInstance().getUnitPrefix(GlobalAppRuntimeInfo.getUserId(), GlobalAppRuntimeInfo.getUtdid());
                    if (!TextUtils.isEmpty(unitPrefix)) {
                        accsHost = StringUtils.concatString(unitPrefix, this.a.getAccsHost());
                    }
                }
                accsHost = null;
            }
            if (accsHost == null) {
                return null;
            }
            String schemeByHost = StrategyCenter.getInstance().getSchemeByHost(accsHost, null);
            if (TextUtils.isEmpty(schemeByHost)) {
                schemeByHost = HttpConstant.HTTPS;
            }
            return StringUtils.buildKey(schemeByHost, accsHost);
        }
    }

    AccsSessionManager(SessionCenter sessionCenter) {
        this.sessionCenter = sessionCenter;
        this.cb = sessionCenter.config.getAccsSessionCb();
        if (GlobalAppRuntimeInfo.isTargetProcess() && this.cb == null) {
            this.cb = new a(sessionCenter.config);
        }
    }

    public void setAccsSessionCb(Callback callback) {
        if (callback == null) {
            this.cb = DISABLE_AUTO_CONNTION;
        } else {
            this.cb = callback;
        }
    }

    public synchronized void checkAndStartAccsSession() {
        if (this.cb == null) {
            ALog.w(TAG, "accs session cb is null.", null, new Object[0]);
        } else {
            int sessionCount = this.cb.getSessionCount();
            if (this.sessionKeyArray.length != sessionCount) {
                this.sessionKeyArray = (String[]) Arrays.copyOf(this.sessionKeyArray, sessionCount);
            }
            boolean isNeedCheckSession = isNeedCheckSession();
            for (sessionCount = 0; sessionCount < this.sessionKeyArray.length; sessionCount++) {
                String str = this.sessionKeyArray[sessionCount];
                String sessionKey = this.cb.getSessionKey(sessionCount);
                if ((sessionKey == null && str != null) || !(sessionKey == null || sessionKey.equalsIgnoreCase(str))) {
                    closeSessions(str);
                    this.sessionKeyArray[sessionCount] = sessionKey;
                }
                if (isNeedCheckSession) {
                    try {
                        if (!TextUtils.isEmpty(sessionKey)) {
                            this.sessionCenter.get(sessionKey, TypeLevel.SPDY, 0);
                        }
                    } catch (Exception e) {
                        ALog.e("start unit session failed", null, "host", sessionKey);
                    }
                }
            }
        }
    }

    public synchronized void forceReCreateSession() {
        forceCloseSession(true);
    }

    public synchronized void forceCloseSession(boolean z) {
        int i = 0;
        synchronized (this) {
            if (ALog.isPrintLog(1)) {
                ALog.d(TAG, "forceCloseSession", this.sessionCenter.seqNum, "reCreate", Boolean.valueOf(z));
            }
            while (i < this.sessionKeyArray.length) {
                closeSessions(this.sessionKeyArray[i]);
                this.sessionKeyArray[i] = null;
                i++;
            }
            if (z) {
                checkAndStartAccsSession();
            }
        }
    }

    private boolean isNeedCheckSession() {
        if (GlobalAppRuntimeInfo.isAppBackground()) {
            ALog.d(TAG, "app is background not need check accs session, return", this.sessionCenter.seqNum, "bg", Boolean.valueOf(true));
            return false;
        } else if (NetworkStatusHelper.f()) {
            return true;
        } else {
            ALog.d(TAG, "network is not available, not need check accs session, return", this.sessionCenter.seqNum, "network", Boolean.valueOf(NetworkStatusHelper.f()));
            return false;
        }
    }

    private void closeSessions(String str) {
        if (!TextUtils.isEmpty(str)) {
            ALog.d(TAG, "closeSessions", this.sessionCenter.seqNum, "host", str);
            this.sessionCenter.getSessionRequest(str).b(false);
        }
    }
}
