package com.taobao.accs.net;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.Config;
import anet.channel.SessionCenter;
import anet.channel.entity.ENV;
import com.baidu.location.h.e;
import com.taobao.accs.AccsClientConfig;
import com.taobao.accs.AccsClientConfig.Builder;
import com.taobao.accs.ErrorCode;
import com.taobao.accs.common.Constants;
import com.taobao.accs.data.Message;
import com.taobao.accs.data.b;
import com.taobao.accs.ut.statistics.c;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UtilityImpl;
import java.util.LinkedHashMap;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: Taobao */
public abstract class a {
    public static final int ACCS_RECEIVE_TIMEOUT = 40000;
    public static final int INAPP = 1;
    public static final int SERVICE = 0;
    public String a;
    public String b = "";
    protected int c;
    protected Context d;
    protected b e;
    protected int f = 0;
    public com.taobao.accs.client.b g;
    public AccsClientConfig h;
    protected String i;
    protected LinkedHashMap<Integer, Message> j = new BaseConnection$1(this);
    private long k = 0;
    private volatile boolean l = false;
    private Runnable m;
    private ScheduledFuture<?> n;

    public abstract void a();

    protected abstract void a(Message message, boolean z);

    protected abstract void a(String str, String str2);

    public abstract void a(boolean z, boolean z2);

    public abstract boolean a(String str);

    public abstract void b();

    public abstract c c();

    protected abstract String d();

    protected a(Context context, int i, String str) {
        this.b = str;
        this.c = i;
        this.d = context.getApplicationContext();
        this.e = new b(context, this);
        this.e.b = this.c;
        AccsClientConfig config = AccsClientConfig.getConfig(this.b);
        if (config == null) {
            ALog.e(d(), "BaseConnection config null!!", new Object[0]);
            try {
                config = new Builder().setAppKey(this.b).build();
            } catch (Throwable e) {
                ALog.e(d(), "BaseConnection build config", e, new Object[0]);
            }
        }
        this.h = config;
        com.taobao.accs.common.a.a().schedule(new b(this), e.kg, TimeUnit.MILLISECONDS);
    }

    protected String a(int i) {
        switch (i) {
            case 1:
                return "CONNECTED";
            case 2:
                return "CONNECTING";
            case 3:
                return "DISCONNECTED";
            case 4:
                return "DISCONNECTING";
            default:
                return "DISCONNECTED";
        }
    }

    public void e() {
    }

    public void b(Message message, boolean z) {
        if (message.isAck || UtilityImpl.isNetworkConnected(this.d)) {
            long a;
            if (message.getType() != 2) {
                a = this.e.d.a(message.serviceId, message.bizId);
            } else {
                a = 0;
            }
            if (a == -1) {
                ALog.e(d(), "servier limit high. dataId:" + message.dataId, new Object[0]);
                this.e.a(message, (int) ErrorCode.SERVIER_HIGH_LIMIT);
                return;
            } else if (a == -1000) {
                ALog.e(d(), "servier limit high for brush. dataId:" + message.dataId, new Object[0]);
                this.e.a(message, (int) ErrorCode.SERVIER_HIGH_LIMIT_BRUSH);
                return;
            } else {
                if (a > 0) {
                    if (System.currentTimeMillis() > this.k) {
                        message.delyTime = a;
                    } else {
                        message.delyTime = (a + this.k) - System.currentTimeMillis();
                    }
                    this.k = System.currentTimeMillis() + message.delyTime;
                    ALog.e(d(), "send message, " + Message.b.b(message.getType()) + " delay:" + message.delyTime + " dataId:" + message.dataId, new Object[0]);
                } else if (ALog.isPrintLog(Level.D)) {
                    ALog.d(d(), "send message, " + Message.b.b(message.getType()) + " delay:" + message.delyTime + " dataId:" + message.dataId, new Object[0]);
                }
                try {
                    if (TextUtils.isEmpty(this.i)) {
                        this.i = UtilityImpl.getDeviceId(this.d);
                    }
                    if (message.isTimeOut()) {
                        this.e.a(message, -9);
                        return;
                    } else {
                        a(message, z);
                        return;
                    }
                } catch (RejectedExecutionException e) {
                    this.e.a(message, (int) ErrorCode.MESSAGE_QUEUE_FULL);
                    ALog.e(d(), "msg queue full", "size", Integer.valueOf(com.taobao.accs.common.a.b().getQueue().size()));
                    return;
                }
            }
        }
        ALog.e(d(), "no network:" + message.dataId, new Object[0]);
        this.e.a(message, -13);
    }

    protected void a(String str, long j) {
        com.taobao.accs.common.a.a().schedule(new c(this, str), j, TimeUnit.MILLISECONDS);
    }

    protected boolean a(Message message, int i) {
        Throwable th;
        boolean z = true;
        try {
            if (message.retryTimes > 3) {
                return false;
            }
            message.retryTimes++;
            message.delyTime = (long) i;
            ALog.e(d(), "reSend dataid:" + message.dataId + " retryTimes:" + message.retryTimes, new Object[0]);
            b(message, true);
            try {
                if (message.getNetPermanceMonitor() == null) {
                    return true;
                }
                message.getNetPermanceMonitor().take_date = 0;
                message.getNetPermanceMonitor().to_tnet_date = 0;
                message.getNetPermanceMonitor().retry_times = message.retryTimes;
                if (message.retryTimes != 1) {
                    return true;
                }
                com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_POINT_RESEND, "total", 0.0d);
                return true;
            } catch (Throwable th2) {
                th = th2;
                this.e.a(message, -8);
                ALog.e(d(), "reSend error", th, new Object[0]);
                return z;
            }
        } catch (Throwable th3) {
            th = th3;
            z = false;
            this.e.a(message, -8);
            ALog.e(d(), "reSend error", th, new Object[0]);
            return z;
        }
    }

    protected void b(int i) {
        if (i < 0) {
            ALog.e(d(), "reSendAck", Constants.KEY_DATA_ID, Integer.valueOf(i));
            Message message = (Message) this.j.get(Integer.valueOf(i));
            if (message != null) {
                a(message, 5000);
                com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_POINT_RESEND, BaseMonitor.COUNT_ACK, 0.0d);
            }
        }
    }

    protected void f() {
        if (this.m == null) {
            this.m = new d(this);
        }
        g();
        this.n = com.taobao.accs.common.a.a().schedule(this.m, 40000, TimeUnit.MILLISECONDS);
    }

    protected void g() {
        if (this.n != null) {
            this.n.cancel(true);
        }
    }

    public String b(String str) {
        String inappHost = this.h.getInappHost();
        String str2 = "https://" + (TextUtils.isEmpty(str) ? "" : str) + inappHost;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            StringBuilder append = stringBuilder.append("https://");
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            append.append(str);
            stringBuilder.append(inappHost);
            str2 = stringBuilder.toString();
        } catch (Throwable th) {
            ALog.e("InAppConnection", "getHost", th, new Object[0]);
        }
        return str2;
    }

    protected boolean h() {
        return true;
    }

    protected void a(Context context) {
        if (!this.l) {
            try {
                ENV env = ENV.ONLINE;
                if (AccsClientConfig.mEnv == 2) {
                    env = ENV.TEST;
                    SessionCenter.switchEnvironment(env);
                } else if (AccsClientConfig.mEnv == 1) {
                    env = ENV.PREPARE;
                    SessionCenter.switchEnvironment(env);
                }
                Config.Builder tag = new Config.Builder().setAppkey(this.b).setAppSecret(this.h.getAppSecret()).setAccsHost(this.h.getInappHost()).setAccsPublicKey(this.h.getInappPubKey()).setAuthCode(this.h.getAuthCode()).setEnv(env).setUnitEnable(this.h.isAutoUnit()).setTag(this.h.getTag());
                if (!(h() && this.h.isKeepalive())) {
                    ALog.i(d(), "close keepalive", new Object[0]);
                    tag.setHeartbeatFactory(null).setAccsSessionAutoRecreate(false).setAccsSessionCallback(null);
                }
                SessionCenter.init(context, tag.build());
                ALog.i(d(), "init awcn success!", new Object[0]);
                this.l = true;
            } catch (Throwable th) {
                ALog.e(d(), "initAwcn", th, new Object[0]);
            }
        }
    }

    public void b(Message message, int i) {
        this.e.a(message, i);
    }

    public String i() {
        return this.b;
    }

    public com.taobao.accs.client.b j() {
        if (this.g == null) {
            this.g = new com.taobao.accs.client.b(this.d, this.b);
        }
        return this.g;
    }

    public void b(Context context) {
        try {
            com.taobao.accs.common.a.a(new e(this, context), 10000, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            ALog.w(d(), "startChannelService", th, new Object[0]);
        }
    }

    public boolean k() {
        return 2 == this.h.getSecurity();
    }
}
