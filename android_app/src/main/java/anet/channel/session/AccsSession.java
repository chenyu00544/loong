package anet.channel.session;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import anet.channel.AccsFrameCb;
import anet.channel.Config;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.RequestCb;
import anet.channel.Session.Status;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.entity.ENV;
import anet.channel.heartbeat.IHeartbeat;
import anet.channel.heartbeat.IHeartbeatFactory;
import anet.channel.request.Cancelable;
import anet.channel.request.Request;
import anet.channel.security.ISecurity;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.statist.SessionStatistic;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anet.channel.util.HttpConstant;
import anet.channel.util.Utils;
import anet.channel.util.c;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.BaseMonitor;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import org.android.spdy.RequestPriority;
import org.android.spdy.SpdyDataProvider;
import org.android.spdy.SpdyRequest;
import org.android.spdy.SpdySession;
import org.android.spdy.SuperviseConnectInfo;
import org.android.spdy.SuperviseData;

/* compiled from: Taobao */
public class AccsSession extends k {
    public static final int CONN_TYPE_INAPP = 1;
    private static final String TAG = "awcn.AccsSession";
    private IHeartbeat heartbeat = null;
    private AccsFrameCb mFrameCb;

    /* compiled from: Taobao */
    class a extends b {
        final /* synthetic */ AccsSession a;

        a(AccsSession accsSession) {
            this.a = accsSession;
        }

        public void spdyOnStreamResponse(SpdySession spdySession, long j, Map<String, List<String>> map, Object obj) {
            int parseInt;
            try {
                parseInt = Integer.parseInt(c.b(map, HttpConstant.STATUS));
            } catch (NumberFormatException e) {
                parseInt = 0;
            }
            try {
                ALog.e(AccsSession.TAG, "AUTH httpStatusCode: " + parseInt, this.a.mSeq, new Object[0]);
                if (parseInt == 200) {
                    this.a.notifyStatus(Status.AUTH_SUCC, null);
                    if (this.a.heartbeat != null) {
                        this.a.mLastPingTime = System.currentTimeMillis();
                        this.a.heartbeat.start();
                    }
                    this.a.mSessionStat.ret = 1;
                    ALog.d(AccsSession.TAG, "spdyOnStreamResponse", this.a.mSeq, "authTime", Long.valueOf(this.a.mSessionStat.authTime));
                } else {
                    this.a.onAuthFail(parseInt);
                }
                if (this.a.mConnectedTime > 0) {
                    this.a.mSessionStat.authTime = System.currentTimeMillis() - this.a.mConnectedTime;
                }
                Object b = c.b(map, "x-at");
                if (!TextUtils.isEmpty(b)) {
                    GlobalAppRuntimeInfo.mConnToken = b;
                }
            } catch (Throwable e2) {
                ALog.e(AccsSession.TAG, "spdyOnStreamResponse", this.a.mSeq, e2, new Object[0]);
                this.a.close();
            }
        }

        public void spdyStreamCloseCallback(SpdySession spdySession, long j, int i, Object obj, SuperviseData superviseData) {
            if (i != 0) {
                ALog.e(AccsSession.TAG, "AUTH spdyStreamCloseCallback: " + i, this.a.mSeq, new Object[0]);
                this.a.onAuthFail(i);
            }
        }
    }

    public AccsSession(Context context, anet.channel.entity.a aVar) {
        super(context, aVar, aVar.c());
    }

    public void setFrameCb(AccsFrameCb accsFrameCb) {
        ALog.e(TAG, "setFrameCb", this.mSeq, "AccsFrameCb", accsFrameCb);
        this.mFrameCb = accsFrameCb;
    }

    public void setConfig(Config config) {
        super.setConfig(config);
        this.autoReCreate = config.isAccsSessionAutoCreate();
        IHeartbeatFactory heartbeatFactory = config.getHeartbeatFactory();
        if (heartbeatFactory != null) {
            this.heartbeat = heartbeatFactory.createHeartbeat(this);
        }
    }

    public void close() {
        if (this.heartbeat != null) {
            this.heartbeat.stop();
            this.heartbeat = null;
        }
        super.close();
    }

    public Cancelable request(Request request, RequestCb requestCb) {
        if (this.heartbeat != null) {
            this.mLastPingTime = System.currentTimeMillis();
            this.heartbeat.delay();
        }
        return super.request(request, requestCb);
    }

    private String buildAuthUrl() {
        String encode;
        String sign;
        String deviceId = Utils.getDeviceId(this.mContext);
        try {
            encode = URLEncoder.encode(deviceId);
        } catch (Throwable th) {
            encode = deviceId;
        }
        try {
            sign = this.iSecurity.sign(this.mContext, ISecurity.SIGN_ALGORITHM_HMAC_SHA1, this.mAppkey, !this.iSecurity.isSecOff() ? deviceId + this.mAppkey : this.mAppkey + deviceId);
        } catch (Throwable th2) {
            ALog.e(TAG, "getAppSign", null, th2, new Object[0]);
            sign = null;
        }
        StringBuilder stringBuilder = new StringBuilder(256);
        stringBuilder.append("https://").append(this.mIp).append(":").append(this.mPort).append("/accs/");
        stringBuilder.append("auth?1=").append(encode).append("&2=").append(sign).append("&3=").append(this.mAppkey);
        if (GlobalAppRuntimeInfo.mConnToken != null) {
            stringBuilder.append("&4=").append(GlobalAppRuntimeInfo.mConnToken);
        }
        stringBuilder.append("&5=").append(1).append("&6=").append(NetworkStatusHelper.b()).append("&7=").append(Utils.getOperator(this.mContext)).append("&8=").append("1.1.2").append("&9=").append(System.currentTimeMillis()).append("&10=").append(1).append("&11=").append(VERSION.SDK_INT).append("&12=").append(this.mContext.getPackageName()).append("&13=").append(Utils.getAppVersion(this.mContext)).append("&14=").append(GlobalAppRuntimeInfo.getTtid()).append("&15=").append(Build.MODEL).append("&16=").append(Build.BRAND).append("&17=").append(Utils.getAccsVersion());
        if (this.isHorseRide) {
            stringBuilder.append("&18=").append("ign-loc");
        }
        stringBuilder.append("&19=").append(this.iSecurity.isSecOff() ? 0 : 1);
        ALog.e(TAG, BaseMonitor.ALARM_POINT_AUTH, this.mSeq, "auth url", stringBuilder.toString());
        if (checkParam(deviceId, this.mAppkey, sign)) {
            return encode;
        }
        ALog.e(TAG, "connect param error!", this.mSeq, new Object[0]);
        onAuthFail(-104);
        return null;
    }

    protected void auth() {
        if (this.mSession == null) {
            notifyStatus(Status.CONNETFAIL, null);
            return;
        }
        String buildAuthUrl = buildAuthUrl();
        if (buildAuthUrl != null) {
            try {
                SpdyRequest spdyRequest;
                URL url = new URL(buildAuthUrl);
                if (TextUtils.isEmpty(this.mProxyIp) || this.mProxyPort <= 0) {
                    spdyRequest = new SpdyRequest(url, "GET", RequestPriority.DEFAULT_PRIORITY, this.mReqTimeout, this.mConnTimeout);
                } else {
                    spdyRequest = new SpdyRequest(url, url.getHost(), url.getPort(), this.mProxyIp, this.mProxyPort, "GET", RequestPriority.DEFAULT_PRIORITY, this.mReqTimeout, this.mConnTimeout, 0);
                }
                spdyRequest.setDomain(this.mHost);
                this.mSession.submitRequest(spdyRequest, new SpdyDataProvider((byte[]) null), this.mHost, new a(this));
            } catch (Throwable th) {
                ALog.e(TAG, "auth exception ", this.mSeq, th, new Object[0]);
                onAuthFail(ErrorConstant.ERROR_AUTH_EXCEPTION);
            }
        }
    }

    private void onAuthFail(int i) {
        notifyStatus(Status.AUTH_FAIL, null);
        if (this.mSessionStat != null) {
            this.mSessionStat.closeReason = "Accs_Auth_Fail";
            this.mSessionStat.errorCode = (long) i;
        }
        close();
    }

    private boolean checkParam(String str, String str2, String str3) {
        int i = 1;
        if (GlobalAppRuntimeInfo.getEnv() == ENV.TEST) {
            return true;
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            return true;
        }
        if (!TextUtils.isEmpty(str)) {
            if (TextUtils.isEmpty(str2)) {
                i = 2;
            } else if (TextUtils.isEmpty(str3)) {
                i = 3;
            }
        }
        AppMonitor.getInstance().commitStat(new ExceptionStatistic(-104, ErrorConstant.formatMsg(-104, "1.1.2 errorCode=" + i), "rt"));
        return false;
    }

    public void sendCustomFrame(int i, byte[] bArr, int i2) {
        try {
            ALog.e(TAG, "sendCustomFrame", this.mSeq, Constants.KEY_DATA_ID, Integer.valueOf(i), "type", Integer.valueOf(i2));
            if (this.mStatus != Status.AUTH_SUCC || this.mSession == null) {
                ALog.e(TAG, "sendCustomFrame", this.mSeq, "sendCustomFrame con invalid mStatus:" + this.mStatus);
                onException(i, ErrorConstant.ERROR_SESSION_INVALID, true, "session invalid");
            } else if (bArr == null || bArr.length <= 16384) {
                this.mSession.sendCustomControlFrame(i, i2, 0, bArr == null ? 0 : bArr.length, bArr);
                SessionStatistic sessionStatistic = this.mSessionStat;
                sessionStatistic.requestCount++;
                sessionStatistic = this.mSessionStat;
                sessionStatistic.cfRCount++;
                this.mLastPingTime = System.currentTimeMillis();
                if (this.heartbeat != null) {
                    this.heartbeat.delay();
                }
            } else {
                onException(i, ErrorConstant.ERROR_DATA_TOO_LARGE, false, null);
            }
        } catch (Throwable e) {
            ALog.e(TAG, "sendCustomFrame error", this.mSeq, e, new Object[0]);
            onException(i, ErrorConstant.ERROR_TNET_EXCEPTION, true, "SpdyErrorException: " + e.toString());
        } catch (Throwable e2) {
            ALog.e(TAG, "sendCustomFrame error", this.mSeq, e2, new Object[0]);
            onException(i, -101, true, e2.toString());
        }
    }

    private void onException(int i, int i2, boolean z, String str) {
        if (this.mFrameCb != null) {
            this.mFrameCb.onException(i, i2, z, str);
        }
    }

    protected Runnable getRecvTimeOutRunnable() {
        return new a(this);
    }

    public void spdySessionCloseCallback(SpdySession spdySession, Object obj, SuperviseConnectInfo superviseConnectInfo, int i) {
        if (this.heartbeat != null) {
            this.heartbeat.stop();
        }
        super.spdySessionCloseCallback(spdySession, obj, superviseConnectInfo, i);
    }

    public void spdyCustomControlFrameRecvCallback(SpdySession spdySession, Object obj, int i, int i2, int i3, int i4, byte[] bArr) {
        ALog.e(TAG, "[spdyCustomControlFrameRecvCallback]", this.mSeq, "len", Integer.valueOf(i4), "frameCb", this.mFrameCb);
        if (ALog.isPrintLog(1)) {
            String str = "";
            if (i4 < 512) {
                for (byte b : bArr) {
                    str = str + Integer.toHexString(b & 255) + " ";
                }
                ALog.e(TAG, null, this.mSeq, "str", str);
            }
        }
        if (this.mFrameCb != null) {
            this.mFrameCb.onDataReceive(this, bArr, i, i2);
        } else {
            ALog.e(TAG, "AccsFrameCb is null", this.mSeq, new Object[0]);
            AppMonitor.getInstance().commitStat(new ExceptionStatistic(-105, null, "rt"));
        }
        SessionStatistic sessionStatistic = this.mSessionStat;
        sessionStatistic.inceptCount++;
    }

    public void spdyCustomControlFrameFailCallback(SpdySession spdySession, Object obj, int i, int i2) {
        ALog.e(TAG, "spdyCustomControlFrameFailCallback", this.mSeq, Constants.KEY_DATA_ID, Integer.valueOf(i));
        onException(i, i2, true, "tnet error");
    }
}
