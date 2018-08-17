package anet.channel.session;

import android.content.Context;
import anet.channel.RequestCb;
import anet.channel.Session;
import anet.channel.Session.Status;
import anet.channel.c.c;
import anet.channel.entity.ConnType;
import anet.channel.entity.a;
import anet.channel.request.Cancelable;
import anet.channel.request.FutureCancelable;
import anet.channel.request.Request;
import anet.channel.request.Request.Builder;
import anet.channel.statist.RequestStatistic;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anet.channel.util.HttpConstant;
import anet.channel.util.g;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: Taobao */
public class e extends Session {
    public static Map<String, String> a = new ConcurrentHashMap();

    public e(Context context, a aVar) {
        super(context, aVar, aVar.c());
        if (this.mConnStrategy == null) {
            ConnType connType = (this.mHost == null || !this.mHost.startsWith(HttpConstant.HTTPS)) ? ConnType.HTTP : ConnType.HTTPS;
            this.mConnType = connType;
        }
    }

    public static boolean a(String str, String str2, int i) {
        String str3 = (String) a.get(str);
        if (str3 != null && str3.startsWith(str2) && str3.endsWith(String.valueOf(i))) {
            return true;
        }
        return false;
    }

    public boolean isAvailable() {
        return this.mStatus == Status.AUTH_SUCC;
    }

    protected void connect() {
        try {
            ALog.i("awcn.HttpSession", "HttpSession connect", null, "host", this.mHost);
            Request build = new Builder().setUrl(this.mHost).build();
            build.setDnsOptimize(this.mIp, this.mPort);
            c.a(new f(this, build), 7);
        } catch (Throwable th) {
            ALog.e("awcn.HttpSession", "HTTP connect fail.", null, th, new Object[0]);
        }
    }

    public void close() {
        notifyStatus(Status.DISCONNECTED, null);
    }

    public void close(boolean z) {
        this.autoReCreate = false;
        close();
    }

    protected Runnable getRecvTimeOutRunnable() {
        return null;
    }

    public void ping(boolean z) {
    }

    public Cancelable request(Request request, RequestCb requestCb) {
        RequestStatistic requestStatistic;
        FutureCancelable futureCancelable = FutureCancelable.NULL;
        if (request != null) {
            requestStatistic = request.rs;
        } else {
            requestStatistic = new RequestStatistic(this.mRealHost, null);
        }
        requestStatistic.setConnType(this.mConnType);
        if (requestStatistic.start == 0) {
            requestStatistic.start = System.currentTimeMillis();
        }
        if (request == null || requestCb == null) {
            if (requestCb != null) {
                requestCb.onFinish(-102, ErrorConstant.getErrMsg(-102), requestStatistic);
            }
            return futureCancelable;
        }
        try {
            request.setDnsOptimize(this.mIp, this.mPort);
            return new FutureCancelable(c.a(new g(this, request, requestCb), g.a(request.getUrl())), request.getSeq());
        } catch (Throwable th) {
            if (requestCb != null) {
                requestCb.onFinish(-101, ErrorConstant.formatMsg(-101, th.toString()), requestStatistic);
            }
            return futureCancelable;
        }
    }
}
