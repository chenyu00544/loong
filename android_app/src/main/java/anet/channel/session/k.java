package anet.channel.session;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.Config;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.RequestCb;
import anet.channel.Session;
import anet.channel.Session.Status;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.b.b;
import anet.channel.entity.ConnType;
import anet.channel.entity.EventType;
import anet.channel.entity.d;
import anet.channel.request.Cancelable;
import anet.channel.request.Request;
import anet.channel.request.TnetCancelable;
import anet.channel.security.ISecurity;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.statist.RequestStatistic;
import anet.channel.statist.SessionStatistic;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anet.channel.util.HttpConstant;
import anet.channel.util.Utils;
import anet.channel.util.c;
import java.net.URL;
import java.util.List;
import java.util.Map;
import org.android.spdy.RequestPriority;
import org.android.spdy.SessionCb;
import org.android.spdy.SessionInfo;
import org.android.spdy.SpdyAgent;
import org.android.spdy.SpdyByteArray;
import org.android.spdy.SpdyDataProvider;
import org.android.spdy.SpdyErrorException;
import org.android.spdy.SpdyRequest;
import org.android.spdy.SpdySession;
import org.android.spdy.SpdySessionKind;
import org.android.spdy.SpdyVersion;
import org.android.spdy.SuperviseConnectInfo;
import org.android.spdy.SuperviseData;
import org.android.spdy.TnetStatusCode;

/* compiled from: Taobao */
public abstract class k extends Session implements SessionCb {
    private static final String SSL_TIKET_KEY2 = "accs_ssl_key2_";
    private static final String TAG = "awcn.TnetSpdySession";
    protected ISecurity iSecurity = GlobalAppRuntimeInfo.getSecurity();
    protected SpdyAgent mAgent;
    protected String mAppkey = GlobalAppRuntimeInfo.getAppKey();
    protected long mConnectedTime = 0;
    protected volatile boolean mHasUnrevPing = false;
    protected long mLastPingTime;
    protected SpdySession mSession;
    private int requestTimeoutCount = 0;
    protected int tnetPublicKey = -1;

    /* compiled from: Taobao */
    private class a extends b {
        final /* synthetic */ k a;
        private Request b;
        private RequestCb c;
        private long d = 0;
        private long e = 0;
        private long f = 0;

        public a(k kVar, Request request, RequestCb requestCb) {
            this.a = kVar;
            this.b = request;
            this.c = requestCb;
            this.d = System.currentTimeMillis();
        }

        public void spdyDataChunkRecvCB(SpdySession spdySession, boolean z, long j, SpdyByteArray spdyByteArray, Object obj) {
            ALog.d(k.TAG, "spdyDataChunkRecvCB", this.b.getSeq(), "len", Integer.valueOf(spdyByteArray.getDataLength()), "fin", Boolean.valueOf(z));
            if (this.b.rs.firstDataTime == 0) {
                this.b.rs.firstDataTime = System.currentTimeMillis();
            }
            if (this.c != null) {
                anet.channel.a.a a = a.a.a(spdyByteArray.getByteArray(), spdyByteArray.getDataLength());
                spdyByteArray.recycle();
                this.c.onDataReceive(a, z);
            }
            this.a.handleCallbacks(EventType.DATA_RECEIVE, null);
        }

        public void spdyStreamCloseCallback(SpdySession spdySession, long j, int i, Object obj, SuperviseData superviseData) {
            ALog.d(k.TAG, "spdyStreamCloseCallback", this.b.getSeq(), "streamId", Long.valueOf(j));
            this.e = System.currentTimeMillis();
            a(superviseData);
            String str = HttpConstant.SUCCESS;
            if (i != 0) {
                if (i != TnetStatusCode.EASY_REASON_CANCEL) {
                    str = ErrorConstant.formatMsg(ErrorConstant.ERROR_TNET_EXCEPTION, "statusCode=" + i);
                    AppMonitor.getInstance().commitStat(new ExceptionStatistic(ErrorConstant.ERROR_TNET_EXCEPTION, str, this.b.rs, null));
                }
                ALog.e(k.TAG, "spdyStreamCloseCallback error", this.b.getSeq(), "status code", Integer.valueOf(i));
            }
            if (this.c != null) {
                this.c.onFinish(i, str, this.b.rs);
            }
            if (i == TnetStatusCode.EASY_REASON_SESSION_TIMEOUT && k.access$204(this.a) >= 3) {
                this.a.close(true);
            }
            if (i <= TnetStatusCode.EASY_REASON_HANDSHAKE_ERROR && i > -4000) {
                b.a().a(3, this.b.getHost());
            }
        }

        private void a(SuperviseData superviseData) {
            long j = 0;
            try {
                if (this.e > 0 && this.d > 0) {
                    this.b.rs.serverRT = this.f;
                    RequestStatistic requestStatistic = this.b.rs;
                    if (this.b.rs.firstDataTime != 0) {
                        j = this.e - this.b.rs.firstDataTime;
                    }
                    requestStatistic.recDataTime = j;
                    this.b.rs.oneWayTime = this.e - this.b.rs.start;
                    this.b.rs.waitingTime = this.b.rs.oneWayTime;
                    if (superviseData != null) {
                        this.b.rs.firstDataTime = superviseData.responseStart - superviseData.sendStart;
                        this.b.rs.recDataTime = superviseData.responseEnd - superviseData.responseStart;
                        this.b.rs.sendBeforeTime = superviseData.sendStart - this.d;
                        this.b.rs.sendDataTime = superviseData.sendEnd - superviseData.sendStart;
                        this.b.rs.sendDataSize = (long) (superviseData.bodySize + superviseData.compressSize);
                        this.b.rs.recDataSize = (long) (superviseData.recvBodySize + superviseData.recvCompressSize);
                        SessionStatistic sessionStatistic = this.a.mSessionStat;
                        sessionStatistic.recvSizeCount += (long) (superviseData.recvBodySize + superviseData.recvCompressSize);
                        sessionStatistic = this.a.mSessionStat;
                        sessionStatistic.sendSizeCount += (long) (superviseData.bodySize + superviseData.compressSize);
                    }
                }
            } catch (Exception e) {
            }
        }

        public void spdyOnStreamResponse(SpdySession spdySession, long j, Map<String, List<String>> map, Object obj) {
            List list;
            int i;
            try {
                int i2;
                list = (List) map.get(HttpConstant.STATUS);
                if (list == null || list.isEmpty()) {
                    i2 = 0;
                } else {
                    i2 = Integer.parseInt((String) list.get(0));
                }
                i = i2;
            } catch (NumberFormatException e) {
                i = 0;
            }
            if (i > 0) {
                this.b.rs.ret = true;
                this.a.requestTimeoutCount = 0;
            }
            ALog.i(k.TAG, "", this.b.getSeq(), "httpStatusCode", Integer.valueOf(i));
            ALog.i(k.TAG, "", this.b.getSeq(), "response headers", map);
            if (this.c != null) {
                this.c.onResponseCode(i, c.a((Map) map));
            }
            this.a.handleCallbacks(EventType.HEADER_RECEIVE, null);
            try {
                list = (List) map.get(HttpConstant.SERVER_RT);
                if (!(list == null || list.isEmpty())) {
                    this.f = Long.parseLong((String) list.get(0));
                }
            } catch (NumberFormatException e2) {
            }
            if (anet.channel.strategy.a.d(this.a.mRealHost)) {
                b.a().a(0, this.a.mRealHost, Integer.valueOf(i));
            }
        }
    }

    static /* synthetic */ int access$204(k kVar) {
        int i = kVar.requestTimeoutCount + 1;
        kVar.requestTimeoutCount = i;
        return i;
    }

    public k(Context context, anet.channel.entity.a aVar, ConnType connType) {
        super(context, aVar, connType);
        init();
    }

    public Cancelable request(Request request, RequestCb requestCb) {
        RequestStatistic requestStatistic;
        Cancelable tnetCancelable;
        SpdyErrorException e;
        Exception e2;
        TnetCancelable tnetCancelable2 = TnetCancelable.NULL;
        if (request != null) {
            requestStatistic = request.rs;
        } else {
            requestStatistic = new RequestStatistic(this.mRealHost, null);
        }
        requestStatistic.setConnType(this.mConnType);
        requestStatistic.setIPAndPort(this.mIp, this.mPort);
        if (requestStatistic.start == 0) {
            requestStatistic.start = System.currentTimeMillis();
        }
        if (request == null || requestCb == null) {
            if (requestCb != null) {
                requestCb.onFinish(-102, ErrorConstant.getErrMsg(-102), requestStatistic);
            }
            return tnetCancelable2;
        }
        try {
            if (this.mSession == null || !isAvailable()) {
                requestCb.onFinish(ErrorConstant.ERROR_SESSION_INVALID, "Session不可用", request.rs);
                return tnetCancelable2;
            }
            SpdyRequest spdyRequest;
            if (ALog.isPrintLog(2)) {
                ALog.i(TAG, "", request.getSeq(), "request URL", request.getUrlString());
                ALog.i(TAG, "", request.getSeq(), "request headers", request.getHeaders());
            }
            URL url = request.getUrl();
            if (TextUtils.isEmpty(this.mProxyIp) || this.mProxyPort <= 0) {
                spdyRequest = new SpdyRequest(url, request.getMethod(), RequestPriority.DEFAULT_PRIORITY, request.getReadTimeout(), request.getConnectTimeout());
            } else {
                spdyRequest = new SpdyRequest(url, url.getHost(), url.getPort(), this.mProxyIp, this.mProxyPort, request.getMethod(), RequestPriority.DEFAULT_PRIORITY, request.getReadTimeout(), request.getConnectTimeout(), 0);
            }
            spdyRequest.addHeaders(request.getHeaders());
            spdyRequest.addHeader(":host", request.getHost());
            int submitRequest = this.mSession.submitRequest(spdyRequest, new SpdyDataProvider(request.getBody()), this, new a(this, request, requestCb));
            if (ALog.isPrintLog(1)) {
                ALog.d(TAG, "", request.getSeq(), "streamId", Integer.valueOf(submitRequest));
            }
            tnetCancelable = new TnetCancelable(this.mSession, submitRequest, request.getSeq());
            try {
                SessionStatistic sessionStatistic = this.mSessionStat;
                sessionStatistic.requestCount++;
                sessionStatistic = this.mSessionStat;
                sessionStatistic.stdRCount++;
                this.mLastPingTime = System.currentTimeMillis();
                return tnetCancelable;
            } catch (SpdyErrorException e3) {
                e = e3;
                ALog.e(TAG, "Send request on closed session!!!", this.mSeq, new Object[0]);
                notifyStatus(Status.DISCONNECTED, new anet.channel.entity.c(EventType.DISCONNECTED, false, TnetStatusCode.TNET_JNI_ERR_ASYNC_CLOSE, "Session is closed!"));
                requestCb.onFinish(ErrorConstant.ERROR_TNET_EXCEPTION, ErrorConstant.formatMsg(ErrorConstant.ERROR_TNET_EXCEPTION, e.toString()), requestStatistic);
                return tnetCancelable;
            } catch (Exception e4) {
                e2 = e4;
                requestCb.onFinish(-101, ErrorConstant.formatMsg(-101, e2.toString()), requestStatistic);
                return tnetCancelable;
            }
        } catch (SpdyErrorException e5) {
            e = e5;
            Object obj = tnetCancelable2;
            if (e.SpdyErrorGetCode() == TnetStatusCode.TNET_JNI_ERR_ASYNC_CLOSE || e.SpdyErrorGetCode() == TnetStatusCode.TNET_JNI_ERR_STATUS_ERR) {
                ALog.e(TAG, "Send request on closed session!!!", this.mSeq, new Object[0]);
                notifyStatus(Status.DISCONNECTED, new anet.channel.entity.c(EventType.DISCONNECTED, false, TnetStatusCode.TNET_JNI_ERR_ASYNC_CLOSE, "Session is closed!"));
            }
            requestCb.onFinish(ErrorConstant.ERROR_TNET_EXCEPTION, ErrorConstant.formatMsg(ErrorConstant.ERROR_TNET_EXCEPTION, e.toString()), requestStatistic);
            return tnetCancelable;
        } catch (Exception e6) {
            e2 = e6;
            tnetCancelable = tnetCancelable2;
            requestCb.onFinish(-101, ErrorConstant.formatMsg(-101, e2.toString()), requestStatistic);
            return tnetCancelable;
        }
    }

    public void sendCustomFrame(int i, byte[] bArr, int i2) {
    }

    protected void connect() {
        if (this.mStatus != Status.CONNECTING && this.mStatus != Status.CONNECTED && this.mStatus != Status.AUTH_SUCC) {
            try {
                if (this.mAgent != null) {
                    String valueOf = String.valueOf(System.currentTimeMillis());
                    ALog.e(TAG, "[connect]", this.mSeq, "host", this.mHost, "connect ", this.mIp + ":" + this.mPort, "sessionId", valueOf, "SpdyProtocol,", this.mConnType.toProtocol(), "proxyIp,", this.mProxyIp, "proxyPort,", Integer.valueOf(this.mProxyPort));
                    SessionInfo sessionInfo = new SessionInfo(this.mIp, this.mPort, this.mHost + "_" + this.mAppkey, this.mProxyIp, this.mProxyPort, valueOf, this, this.mConnType.getTnetConType());
                    sessionInfo.setPubKeySeqNum(Utils.getPublicKey(this.mConnType, this.iSecurity.isSecOff(), this.tnetPublicKey));
                    this.mSession = this.mAgent.createSession(sessionInfo);
                    if (this.mSession.getRefCount() > 1) {
                        ALog.e(TAG, "get session ref count > 1!!!", this.mSeq, new Object[0]);
                        notifyStatus(Status.CONNECTED, new anet.channel.entity.b(EventType.CONNECTED));
                        auth();
                        return;
                    }
                    boolean z;
                    notifyStatus(Status.CONNECTING, null);
                    this.mLastPingTime = System.currentTimeMillis();
                    SessionStatistic sessionStatistic = this.mSessionStat;
                    StringBuilder stringBuilder = new StringBuilder();
                    if (TextUtils.isEmpty(this.mProxyIp)) {
                        z = false;
                    } else {
                        z = true;
                    }
                    sessionStatistic.isProxy = stringBuilder.append(z).append("").toString();
                    this.mSessionStat.isTunnel = "false";
                    this.mSessionStat.isBackground = GlobalAppRuntimeInfo.isAppBackground();
                    this.mConnectedTime = 0;
                }
            } catch (Throwable th) {
                notifyStatus(Status.CONNETFAIL, null);
                ALog.e(TAG, "connect exception ", this.mSeq, th, new Object[0]);
            }
        }
    }

    public void close() {
        ALog.e(TAG, "force close!", this.mSeq, "session", this);
        notifyStatus(Status.DISCONNECTING, null);
        try {
            if (this.mSession != null) {
                this.mSession.closeSession();
            }
        } catch (Exception e) {
        }
    }

    protected void onDisconnect() {
        this.mHasUnrevPing = false;
    }

    public void ping(boolean z) {
        if (ALog.isPrintLog(1)) {
            ALog.d(TAG, "ping", this.mSeq, "host", this.mHost, "thread", Thread.currentThread().getName());
        }
        if (z) {
            try {
                if (this.mSession == null) {
                    if (this.mSessionStat != null) {
                        this.mSessionStat.closeReason = "session null";
                    }
                    ALog.e(TAG, this.mHost + " session null", this.mSeq, new Object[0]);
                    close();
                } else if (this.mStatus == Status.CONNECTED || this.mStatus == Status.AUTH_SUCC) {
                    handleCallbacks(EventType.PING_SEND, null);
                    this.mHasUnrevPing = true;
                    SessionStatistic sessionStatistic = this.mSessionStat;
                    sessionStatistic.ppkgCount++;
                    this.mSession.submitPing();
                    if (ALog.isPrintLog(1)) {
                        ALog.d(TAG, this.mHost + " submit ping ms:" + (System.currentTimeMillis() - this.mLastPingTime) + " force:" + z, this.mSeq, new Object[0]);
                    }
                    setPingTimeout();
                    this.mLastPingTime = System.currentTimeMillis();
                }
            } catch (Throwable e) {
                if (e.SpdyErrorGetCode() == TnetStatusCode.TNET_JNI_ERR_ASYNC_CLOSE || e.SpdyErrorGetCode() == TnetStatusCode.TNET_JNI_ERR_STATUS_ERR) {
                    ALog.e(TAG, "Send request on closed session!!!", this.mSeq, new Object[0]);
                    notifyStatus(Status.DISCONNECTED, new anet.channel.entity.c(EventType.DISCONNECTED, false, TnetStatusCode.TNET_JNI_ERR_ASYNC_CLOSE, "Session is closed!"));
                }
                ALog.e(TAG, "ping", this.mSeq, e, new Object[0]);
            } catch (Throwable e2) {
                ALog.e(TAG, "ping", this.mSeq, e2, new Object[0]);
            }
        }
    }

    protected void auth() {
    }

    public boolean isAvailable() {
        return this.mStatus == Status.AUTH_SUCC;
    }

    public void setConfig(Config config) {
        this.mAppkey = config.getAppkey();
        this.iSecurity = config.getSecurity();
        this.tnetPublicKey = config.getAccsPublicKey();
    }

    private void init() {
        try {
            SpdyAgent.enableDebug = false;
            this.mAgent = SpdyAgent.getInstance(this.mContext, SpdyVersion.SPDY3, SpdySessionKind.NONE_SESSION);
            if (!this.iSecurity.isSecOff()) {
                this.mAgent.setAccsSslCallback(new l(this));
            }
        } catch (Throwable e) {
            ALog.e(TAG, "Init failed.", null, e, new Object[0]);
        }
    }

    public void spdySessionConnectCB(SpdySession spdySession, SuperviseConnectInfo superviseConnectInfo) {
        d bVar = new anet.channel.entity.b(EventType.CONNECTED);
        bVar.a = (long) superviseConnectInfo.connectTime;
        bVar.b = (long) superviseConnectInfo.handshakeTime;
        this.mSessionStat.connectionTime = (long) superviseConnectInfo.connectTime;
        this.mSessionStat.sslTime = (long) superviseConnectInfo.handshakeTime;
        this.mSessionStat.sslCalTime = (long) superviseConnectInfo.doHandshakeTime;
        this.mSessionStat.netType = NetworkStatusHelper.b();
        this.mConnectedTime = System.currentTimeMillis();
        notifyStatus(Status.CONNECTED, bVar);
        auth();
        ALog.e(TAG, "spdySessionConnectCB connect", this.mSeq, "connectTime", Integer.valueOf(superviseConnectInfo.connectTime), "sslTime:", Integer.valueOf(superviseConnectInfo.handshakeTime));
    }

    public void spdyPingRecvCallback(SpdySession spdySession, long j, Object obj) {
        if (ALog.isPrintLog(2)) {
            ALog.i(TAG, "ping receive", this.mSeq, HttpConstant.HOST, this.mHost, "id", Long.valueOf(j));
        }
        if (j >= 0) {
            this.mHasUnrevPing = false;
            handleCallbacks(EventType.PIND_RECEIVE, null);
        }
    }

    public void bioPingRecvCallback(SpdySession spdySession, int i) {
        if (ALog.isPrintLog(2)) {
            ALog.i(TAG, this.mHost + " ping receive " + i, this.mSeq, new Object[0]);
        }
    }

    public void spdyCustomControlFrameRecvCallback(SpdySession spdySession, Object obj, int i, int i2, int i3, int i4, byte[] bArr) {
    }

    public void spdySessionFailedError(SpdySession spdySession, int i, Object obj) {
        if (spdySession != null) {
            try {
                spdySession.cleanUp();
            } catch (Throwable e) {
                ALog.e(TAG, "[spdySessionFailedError]session clean up failed!", null, e, new Object[0]);
            }
        }
        notifyStatus(Status.CONNETFAIL, new d(EventType.CONNECT_FAIL, i, "tnet connect fail"));
        ALog.e(TAG, null, this.mSeq, " errorId:", Integer.valueOf(i));
        this.mSessionStat.errorCode = (long) i;
        this.mSessionStat.ret = 0;
        this.mSessionStat.netType = NetworkStatusHelper.b();
        if (!this.isHorseRide) {
            AppMonitor.getInstance().commitStat(this.mSessionStat);
            AppMonitor.getInstance().commitAlarm(this.mSessionStat.getAlarmObject());
        }
    }

    public void spdySessionCloseCallback(SpdySession spdySession, Object obj, SuperviseConnectInfo superviseConnectInfo, int i) {
        ALog.e(TAG, "spdySessionCloseCallback", this.mSeq, " errorCode:", Integer.valueOf(i));
        if (spdySession != null) {
            try {
                spdySession.cleanUp();
            } catch (Throwable e) {
                ALog.e(TAG, "[spdySessionCloseCallback]session clean up failed!", null, e, new Object[0]);
            }
        }
        notifyStatus(Status.DISCONNECTED, new anet.channel.entity.c(EventType.DISCONNECTED, false, i, TextUtils.isEmpty(this.mSessionStat.closeReason) ? "tnet close error:" + i : this.mSessionStat.closeReason + ":" + this.mSessionStat.errorCode));
        if (superviseConnectInfo != null) {
            this.mSessionStat.requestCount = (long) superviseConnectInfo.reused_counter;
            this.mSessionStat.liveTime = (long) superviseConnectInfo.keepalive_period_second;
        }
        if (this.mSessionStat.errorCode == 0) {
            this.mSessionStat.errorCode = (long) i;
        }
        this.mSessionStat.lastPingInterval = (int) (System.currentTimeMillis() - this.mLastPingTime);
        if (!this.isHorseRide) {
            AppMonitor.getInstance().commitStat(this.mSessionStat);
            AppMonitor.getInstance().commitAlarm(this.mSessionStat.getAlarmObject());
        }
    }

    public void spdyCustomControlFrameFailCallback(SpdySession spdySession, Object obj, int i, int i2) {
    }

    public byte[] getSSLMeta(SpdySession spdySession) {
        byte[] bArr = null;
        Object domain = spdySession.getDomain();
        if (TextUtils.isEmpty(domain)) {
            ALog.i(TAG, "get sslticket host is null", bArr, new Object[0]);
        } else {
            try {
                bArr = this.iSecurity.dynamicGetBytes(this.mContext, SSL_TIKET_KEY2 + domain);
            } catch (Throwable th) {
                ALog.e(TAG, "getSSLMeta", bArr, th, new Object[0]);
            }
        }
        return bArr;
    }

    public int putSSLMeta(SpdySession spdySession, byte[] bArr) {
        int i = 0;
        Object domain = spdySession.getDomain();
        if (TextUtils.isEmpty(domain)) {
            return -1;
        }
        try {
            if (!this.iSecurity.dynamicPutBytes(this.mContext, SSL_TIKET_KEY2 + domain, bArr)) {
                i = -1;
            }
        } catch (Throwable th) {
            ALog.e(TAG, "putSSLMeta", null, th, new Object[0]);
            i = -1;
        }
        return i;
    }
}
