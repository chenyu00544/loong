package com.taobao.accs.net;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.strategy.IConnStrategy;
import anet.channel.util.HttpConstant;
import com.baidu.location.h.e;
import com.taobao.accs.AccsClientConfig;
import com.taobao.accs.ErrorCode;
import com.taobao.accs.common.Constants;
import com.taobao.accs.data.Message;
import com.taobao.accs.data.Message.b;
import com.taobao.accs.ut.monitor.SessionMonitor;
import com.taobao.accs.ut.statistics.c;
import com.taobao.accs.ut.statistics.d;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.h;
import com.taobao.accs.utl.j;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.android.spdy.RequestPriority;
import org.android.spdy.SessionCb;
import org.android.spdy.SessionInfo;
import org.android.spdy.SpdyAgent;
import org.android.spdy.SpdyByteArray;
import org.android.spdy.SpdyDataProvider;
import org.android.spdy.SpdyRequest;
import org.android.spdy.SpdySession;
import org.android.spdy.SpdySessionKind;
import org.android.spdy.SpdyVersion;
import org.android.spdy.Spdycb;
import org.android.spdy.SuperviseConnectInfo;
import org.android.spdy.SuperviseData;

/* compiled from: Taobao */
public class o extends a implements SessionCb, Spdycb {
    private long A;
    private long B;
    private long C;
    private int D = -1;
    private String E = null;
    private SessionMonitor F;
    private c G;
    private boolean H = false;
    private String I = "";
    private boolean J = false;
    private g K = new g(m());
    private String L;
    protected ScheduledFuture<?> k;
    protected String l;
    protected int m;
    protected String n;
    protected int o;
    private int p = 3;
    private LinkedList<Message> q = new LinkedList();
    private a r;
    private boolean s = true;
    private String t;
    private String u;
    private String v = null;
    private SpdyAgent w = null;
    private SpdySession x = null;
    private Object y = new Object();
    private long z;

    /* compiled from: Taobao */
    private class a extends Thread {
        public int a = 0;
        long b;
        final /* synthetic */ o c;
        private final String d = getName();

        public a(o oVar, String str) {
            this.c = oVar;
            super(str);
        }

        private void a(boolean z) {
            if (this.c.p != 1) {
                if (UtilityImpl.isNetworkConnected(this.c.d)) {
                    if (z) {
                        this.a = 0;
                    }
                    ALog.e(this.d, this.c.c + " try connect, force = " + z + " failTimes = " + this.a, new Object[0]);
                    if (this.c.p != 1 && this.a >= 4) {
                        this.c.H = true;
                        ALog.e(this.d, this.c.c + " try connect fail " + 4 + " times", new Object[0]);
                        return;
                    } else if (this.c.p != 1) {
                        if (this.c.c == 1 && this.a == 0) {
                            ALog.i(this.d, this.c.c + " try connect in app, no sleep", new Object[0]);
                        } else {
                            ALog.i(this.d, this.c.c + " try connect, need sleep", new Object[0]);
                            try {
                                sleep(e.kg);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        this.c.I = "";
                        if (this.a == 3) {
                            this.c.K.b(this.c.m());
                        }
                        this.c.c(null);
                        this.c.F.setRetryTimes(this.a);
                        if (this.c.p != 1) {
                            this.a++;
                            ALog.e(this.d, this.c.c + " try connect fail, ready for reconnect", new Object[0]);
                            a(false);
                            return;
                        }
                        this.b = System.currentTimeMillis();
                        return;
                    } else {
                        return;
                    }
                }
                ALog.e(this.d, this.c.c + " Network not available", new Object[0]);
            } else if (this.c.p == 1 && System.currentTimeMillis() - this.b > e.kg) {
                this.a = 0;
            }
        }

        public void run() {
            Throwable th;
            int i;
            Throwable th2;
            int i2 = 1;
            ALog.i(this.d, this.c.c + " NetworkThread run", new Object[0]);
            Message message = null;
            this.a = 0;
            while (this.c.s) {
                ALog.d(this.d, "ready to get message", new Object[0]);
                synchronized (this.c.q) {
                    if (this.c.q.size() == 0) {
                        try {
                            ALog.d(this.d, "no message, wait", new Object[0]);
                            this.c.q.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    ALog.d(this.d, "try get message", new Object[0]);
                    if (this.c.q.size() != 0) {
                        message = (Message) this.c.q.getFirst();
                        if (message.getNetPermanceMonitor() != null) {
                            message.getNetPermanceMonitor().onTakeFromQueue();
                        }
                    }
                    Message message2 = message;
                }
                if (!this.c.s) {
                    break;
                } else if (message2 != null) {
                    ALog.d(this.d, "send message not null", new Object[0]);
                    try {
                        int type = message2.getType();
                        ALog.i(this.d, this.c.c + " send:" + b.b(type) + " status:" + this.c.p, new Object[0]);
                        if (type == 2) {
                            if (this.c.c == 1) {
                                ALog.d(this.d, "INAPP ping, skip", new Object[0]);
                                try {
                                    ALog.d(this.d, "send succ, remove it", new Object[0]);
                                    synchronized (this.c.q) {
                                        this.c.q.remove(message2);
                                    }
                                    message = message2;
                                } catch (Throwable th3) {
                                    ALog.e(this.d, " run finally error", th3, new Object[0]);
                                    message = message2;
                                }
                            } else if (System.currentTimeMillis() - this.c.z >= ((long) ((f.a(this.c.d).b() - 1) * 1000)) || message2.force) {
                                ALog.d(this.d, "ms:" + (System.currentTimeMillis() - this.c.z) + " force:" + message2.force, new Object[0]);
                                a(true);
                                if (this.c.x == null || this.c.p != 1) {
                                    i = 0;
                                } else {
                                    if (System.currentTimeMillis() - this.c.z >= ((long) ((f.a(this.c.d).b() - 1) * 1000))) {
                                        ALog.i(this.d, this.c.c + " onSendPing", new Object[0]);
                                        this.c.e.a();
                                        this.c.x.submitPing();
                                        this.c.F.onSendPing();
                                        this.c.z = System.currentTimeMillis();
                                        this.c.A = System.nanoTime();
                                        this.c.f();
                                        i = 1;
                                    }
                                    i = 1;
                                }
                            } else {
                                a(false);
                                i = 1;
                            }
                        } else if (type == 1) {
                            a(true);
                            if (this.c.p != 1 || this.c.x == null) {
                                i = 0;
                            } else {
                                byte[] build = message2.build(this.c.d, this.c.c);
                                message2.setSendTime(System.currentTimeMillis());
                                if (build.length <= 16384 || message2.command.intValue() == 102) {
                                    this.c.x.sendCustomControlFrame(message2.getIntDataId(), 200, 0, build == null ? 0 : build.length, build);
                                    String str = this.d;
                                    String str2 = this.c.c + " send data len";
                                    Object[] objArr = new Object[5];
                                    objArr[0] = Integer.valueOf(build == null ? 0 : build.length);
                                    objArr[1] = Constants.KEY_DATA_ID;
                                    objArr[2] = message2.getDataId();
                                    objArr[3] = "utdid";
                                    objArr[4] = this.c.i;
                                    ALog.e(str, str2, objArr);
                                    this.c.e.a(message2);
                                    if (message2.isAck) {
                                        ALog.e(this.d, this.c.c + " sendCFrame end ack", Constants.KEY_DATA_ID, Integer.valueOf(message2.getIntDataId()));
                                        this.c.j.put(Integer.valueOf(message2.getIntDataId()), message2);
                                    }
                                    if (message2.getNetPermanceMonitor() != null) {
                                        message2.getNetPermanceMonitor().onSendData();
                                    }
                                    this.c.a(message2.getDataId(), (long) message2.timeout);
                                    this.c.e.a(new com.taobao.accs.ut.monitor.TrafficsMonitor.a(message2.serviceId, GlobalAppRuntimeInfo.isAppBackground(), this.c.m(), (long) build.length));
                                } else {
                                    this.c.e.a(message2, -4);
                                }
                                i = 1;
                            }
                        } else {
                            a(false);
                            ALog.e(this.d, this.c.c + " skip msg " + type, new Object[0]);
                            i = 1;
                        }
                        try {
                            this.c.q();
                            if (i == 0) {
                                try {
                                    this.c.l();
                                    if (this.c.F != null) {
                                        this.c.F.setCloseReason("send fail");
                                    }
                                    synchronized (this.c.q) {
                                        for (i = this.c.q.size() - 1; i >= 0; i--) {
                                            message = (Message) this.c.q.get(i);
                                            if (!(message == null || message.command == null || (message.command.intValue() != 100 && message.command.intValue() != 201))) {
                                                this.c.e.a(message, -1);
                                                this.c.q.remove(i);
                                            }
                                        }
                                        ALog.e(this.d, this.c.c + " network disconnected, wait", new Object[0]);
                                        this.c.q.wait();
                                    }
                                } catch (Throwable th32) {
                                    ALog.e(this.d, " run finally error", th32, new Object[0]);
                                }
                                message = message2;
                            } else {
                                ALog.d(this.d, "send succ, remove it", new Object[0]);
                                synchronized (this.c.q) {
                                    this.c.q.remove(message2);
                                }
                                message = message2;
                            }
                        } catch (Throwable th4) {
                            th32 = th4;
                            try {
                                com.taobao.accs.utl.b.a("accs", BaseMonitor.ALARM_POINT_REQ_ERROR, message2.serviceId, "1", this.c.c + th32.toString());
                                th32.printStackTrace();
                                ALog.e(this.d, "service connection run", th32, new Object[0]);
                                if (i != 0) {
                                    ALog.d(this.d, "send succ, remove it", new Object[0]);
                                    synchronized (this.c.q) {
                                        this.c.q.remove(message2);
                                    }
                                    message = message2;
                                } else {
                                    try {
                                        this.c.l();
                                        if (this.c.F != null) {
                                            this.c.F.setCloseReason("send fail");
                                        }
                                        synchronized (this.c.q) {
                                            for (i = this.c.q.size() - 1; i >= 0; i--) {
                                                message = (Message) this.c.q.get(i);
                                                this.c.e.a(message, -1);
                                                this.c.q.remove(i);
                                            }
                                            ALog.e(this.d, this.c.c + " network disconnected, wait", new Object[0]);
                                            this.c.q.wait();
                                        }
                                    } catch (Throwable th322) {
                                        ALog.e(this.d, " run finally error", th322, new Object[0]);
                                    }
                                    message = message2;
                                }
                            } catch (Throwable th3222) {
                                i2 = i;
                                th2 = th3222;
                            }
                        }
                    } catch (Throwable th32222) {
                        th2 = th32222;
                    }
                } else {
                    message = message2;
                }
            }
            this.c.l();
            return;
            if (i2 == 0) {
                try {
                    this.c.l();
                    if (this.c.F != null) {
                        this.c.F.setCloseReason("send fail");
                    }
                    synchronized (this.c.q) {
                        for (int size = this.c.q.size() - 1; size >= 0; size--) {
                            message = (Message) this.c.q.get(size);
                            if (!(message == null || message.command == null || (message.command.intValue() != 100 && message.command.intValue() != 201))) {
                                this.c.e.a(message, -1);
                                this.c.q.remove(size);
                            }
                        }
                        ALog.e(this.d, this.c.c + " network disconnected, wait", new Object[0]);
                        this.c.q.wait();
                    }
                } catch (Throwable th322222) {
                    ALog.e(this.d, " run finally error", th322222, new Object[0]);
                }
            } else {
                ALog.d(this.d, "send succ, remove it", new Object[0]);
                synchronized (this.c.q) {
                    this.c.q.remove(message2);
                }
            }
            throw th2;
            throw th2;
        }
    }

    public o(Context context, int i, String str) {
        super(context, i, str);
        r();
    }

    public synchronized void a() {
        this.s = true;
        a(this.d);
        if (this.r == null) {
            ALog.i(d(), this.c + " start thread", new Object[0]);
            this.r = new a(this, "NetworkThread" + this.b);
            this.r.setPriority(2);
            this.r.start();
        }
        a(false, false);
    }

    protected void a(Message message, boolean z) {
        if (!this.s || message == null) {
            ALog.e(d(), "not running or msg null! " + this.s, new Object[0]);
            return;
        }
        try {
            if (com.taobao.accs.common.a.a().getQueue().size() > 1000) {
                throw new RejectedExecutionException("accs");
            }
            ScheduledFuture schedule = com.taobao.accs.common.a.a().schedule(new p(this, message, z), message.delyTime, TimeUnit.MILLISECONDS);
            if (message.getType() == 1 && message.cunstomDataId != null) {
                if (message.isControlFrame()) {
                    a(message.cunstomDataId);
                }
                this.e.a.put(message.cunstomDataId, schedule);
            }
            if (message.getNetPermanceMonitor() != null) {
                message.getNetPermanceMonitor().setDeviceId(UtilityImpl.getDeviceId(this.d));
                message.getNetPermanceMonitor().setConnType(this.c);
                message.getNetPermanceMonitor().onEnterQueueData();
            }
        } catch (RejectedExecutionException e) {
            this.e.a(message, (int) ErrorCode.MESSAGE_QUEUE_FULL);
            ALog.e(d(), this.c + "send queue full count:" + com.taobao.accs.common.a.a().getQueue().size(), new Object[0]);
        } catch (Throwable th) {
            this.e.a(message, -8);
            ALog.e(d(), this.c + "send error", th, new Object[0]);
        }
    }

    public void e() {
        super.e();
        this.s = false;
        l();
        if (this.F != null) {
            this.F.setCloseReason("shut down");
        }
        synchronized (this.q) {
            try {
                this.q.notifyAll();
            } catch (Exception e) {
            }
        }
        ALog.e(d(), this.c + "shut down", new Object[0]);
    }

    public void a(boolean z, boolean z2) {
        ALog.d(d(), "try ping, force:" + z, new Object[0]);
        if (this.c == 1) {
            ALog.d(d(), "INAPP, skip", new Object[0]);
        } else {
            b(Message.BuildPing(z, (int) (z2 ? (Math.random() * 10.0d) * 1000.0d : 0.0d)), z);
        }
    }

    public void l() {
        ALog.e(d(), this.c + " force close!", new Object[0]);
        try {
            this.x.closeSession();
            this.F.setCloseType(1);
        } catch (Exception e) {
        }
        c(3);
    }

    public c c() {
        int i = 0;
        if (this.G == null) {
            this.G = new c();
        }
        this.G.b = this.c;
        this.G.d = this.q.size();
        this.G.i = UtilityImpl.isNetworkConnected(this.d);
        this.G.f = this.I;
        this.G.a = this.p;
        this.G.c = this.F == null ? false : this.F.getRet();
        this.G.j = n();
        c cVar = this.G;
        if (this.e != null) {
            i = this.e.d();
        }
        cVar.e = i;
        this.G.g = this.u;
        return this.G;
    }

    private void a(Message message) {
        if (message.command != null && this.q.size() != 0) {
            for (int size = this.q.size() - 1; size >= 0; size--) {
                Message message2 = (Message) this.q.get(size);
                if (!(message2 == null || message2.command == null || !message2.getPackageName().equals(message.getPackageName()))) {
                    switch (message.command.intValue()) {
                        case 1:
                        case 2:
                            if (message2.command.intValue() == 1 || message2.command.intValue() == 2) {
                                this.q.remove(size);
                                break;
                            }
                        case 3:
                        case 4:
                            if (message2.command.intValue() == 3 || message2.command.intValue() == 4) {
                                this.q.remove(size);
                                break;
                            }
                        case 5:
                        case 6:
                            if (message2.command.intValue() == 5 || message2.command.intValue() == 6) {
                                this.q.remove(size);
                                break;
                            }
                    }
                    ALog.d(d(), "clearRepeatControlCommand message:" + message2.command + "/" + message2.getPackageName(), new Object[0]);
                }
            }
            if (this.e != null) {
                this.e.b(message);
            }
        }
    }

    private void c(String str) {
        int i = Constants.PORT;
        if (this.p != 2 && this.p != 1) {
            if (this.K == null) {
                this.K = new g(m());
            }
            List<IConnStrategy> a = this.K.a(m());
            if (a == null || a.size() <= 0) {
                if (str != null) {
                    this.l = str;
                } else {
                    this.l = m();
                }
                if (System.currentTimeMillis() % 2 == 0) {
                    i = 80;
                }
                this.m = i;
                com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_POINT_DNS, "localdns", 0.0d);
                ALog.i(d(), this.c + " get ip from amdc fail!!", new Object[0]);
            } else {
                for (IConnStrategy iConnStrategy : a) {
                    if (iConnStrategy != null) {
                        ALog.e(d(), this.c + " connect strategys ip:" + iConnStrategy.getIp() + " port:" + iConnStrategy.getPort(), new Object[0]);
                    }
                }
                if (this.J) {
                    this.K.b();
                    this.J = false;
                }
                IConnStrategy a2 = this.K.a();
                this.l = a2 == null ? m() : a2.getIp();
                this.m = a2 == null ? Constants.PORT : a2.getPort();
                com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_POINT_DNS, "httpdns", 0.0d);
                ALog.e(d(), this.c + " get ip from amdc succ:" + this.l + ":" + this.m + " originPos:" + this.K.c(), new Object[0]);
            }
            this.t = "https://" + this.l + ":" + this.m + "/accs/";
            ALog.e(d(), this.c + " connect URL:" + this.t, new Object[0]);
            this.L = String.valueOf(System.currentTimeMillis());
            if (this.F != null) {
                AppMonitor.getInstance().commitStat(this.F);
            }
            this.F = new SessionMonitor();
            this.F.setConnectType(this.c == 0 ? "service" : "inapp");
            if (this.w != null) {
                try {
                    this.B = System.currentTimeMillis();
                    this.C = System.nanoTime();
                    this.n = UtilityImpl.getProxyHost(this.d);
                    this.o = UtilityImpl.getProxyPort(this.d);
                    this.z = System.currentTimeMillis();
                    this.F.onStartConnect();
                    synchronized (this.y) {
                        try {
                            SessionInfo sessionInfo;
                            if (TextUtils.isEmpty(this.n) || this.o < 0 || !this.H) {
                                ALog.e(d(), this.c + " connect normal", new Object[0]);
                                sessionInfo = new SessionInfo(this.l, this.m, m() + "_" + this.b, null, 0, this.L, this, 4226);
                                this.I = "";
                            } else {
                                ALog.e(d(), this.c + " connect with proxy:" + this.n + ":" + this.o, new Object[0]);
                                sessionInfo = new SessionInfo(this.l, this.m, m() + "_" + this.b, this.n, this.o, this.L, this, 4226);
                                this.I = this.n + ":" + this.o;
                            }
                            sessionInfo.setPubKeySeqNum(o());
                            sessionInfo.setConnectionTimeoutMs(40000);
                            this.x = this.w.createSession(sessionInfo);
                            c(2);
                            this.F.connection_stop_date = 0;
                            this.y.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (Exception e2) {
                            e2.printStackTrace();
                            this.H = false;
                        }
                    }
                } catch (Exception e22) {
                    e22.printStackTrace();
                }
            }
        }
    }

    private int o() {
        boolean k = k();
        if (AccsClientConfig.mEnv == 2) {
            return k ? 0 : 0;
        } else {
            int channelPubKey = this.h.getChannelPubKey();
            if (channelPubKey > 0) {
                ALog.i(d(), "use custom pub key", "pubKey", Integer.valueOf(channelPubKey));
                return channelPubKey;
            } else if (k) {
                return 4;
            } else {
                return 3;
            }
        }
    }

    private void p() {
        if (this.x == null) {
            c(3);
            return;
        }
        try {
            String str;
            String imsi = UtilityImpl.getImsi(this.d);
            String str2 = "null";
            if (imsi == null || imsi.length() <= 5) {
                str = str2;
            } else {
                str = imsi.substring(0, 5);
            }
            String encode = URLEncoder.encode(UtilityImpl.getDeviceId(this.d));
            imsi = UtilityImpl.getAppsign(this.d, i(), this.h.getAppSecret(), UtilityImpl.getDeviceId(this.d), this.v, this.c + "");
            String str3 = (this.t + "auth?1=" + encode + "&2=" + imsi + "&3=" + i() + (this.v == null ? "" : "&4=" + this.v) + "&5=" + this.c + "&6=" + UtilityImpl.getNetworkType(this.d) + "&7=" + str + "&8=" + Constants.SDK_VERSION_CODE + "&9=" + System.currentTimeMillis() + "&10=" + 1 + "&11=" + VERSION.SDK_INT + "&12=" + this.d.getPackageName() + "&13=" + UtilityImpl.getAppVersion(this.d) + "&14=" + this.a + "&15=" + Build.MODEL + "&16=" + Build.BRAND + "&17=" + Constants.SDK_VERSION_CODE) + "&19=" + (k() ? 0 : 1);
            ALog.e(d(), this.c + " auth URL:" + str3, new Object[0]);
            this.u = str3;
            if (a(encode, i(), imsi)) {
                URL url = new URL(str3);
                SpdyRequest spdyRequest = new SpdyRequest(new URL(str3), "GET", RequestPriority.DEFAULT_PRIORITY, 80000, 40000);
                spdyRequest.setDomain(m());
                this.x.submitRequest(spdyRequest, new SpdyDataProvider((byte[]) null), m(), this);
                return;
            }
            ALog.e(d(), this.c + " auth param error!", new Object[0]);
            d(-6);
        } catch (Throwable th) {
            ALog.e(d(), this.c + " auth exception ", th, new Object[0]);
            d(-7);
        }
    }

    private boolean a(String str, String str2, String str3) {
        if (j.a(this.d) == 2) {
            return true;
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            return true;
        }
        int i;
        int i2;
        c(3);
        if (TextUtils.isEmpty(str)) {
            i = 1;
        } else if (TextUtils.isEmpty(str2)) {
            i = 2;
        } else if (TextUtils.isEmpty(str3)) {
            i = 3;
        } else {
            i = 1;
        }
        this.F.setFailReason(i);
        this.F.onConnectStop();
        String str4 = this.c == 0 ? "service" : "inapp";
        if (this.r != null) {
            i2 = this.r.a;
        } else {
            i2 = 0;
        }
        UTMini.getInstance().commitEvent(66001, "DISCONNECT " + str4, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(Constants.SDK_VERSION_CODE), this.u, this.I);
        com.taobao.accs.utl.b.a("accs", BaseMonitor.ALARM_POINT_CONNECT, "retrytimes:" + i2, i + "", "");
        return false;
    }

    private final Map<String, String> a(Map<String, List<String>> map) {
        Map<String, String> hashMap = new HashMap();
        try {
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (!TextUtils.isEmpty(str)) {
                    Object a = a((List) entry.getValue());
                    if (!TextUtils.isEmpty(a)) {
                        if (!str.startsWith(":")) {
                            str = str.toLowerCase(Locale.US);
                        }
                        hashMap.put(str, a);
                        ALog.i(d(), "\theader:" + str + " value:" + a, new Object[0]);
                    }
                }
            }
        } catch (Throwable th) {
        }
        return hashMap;
    }

    private final String a(List<String> list) {
        StringBuffer stringBuffer = new StringBuffer();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            stringBuffer.append((String) list.get(i));
            if (i < size - 1) {
                stringBuffer.append(",");
            }
        }
        return stringBuffer.toString();
    }

    private synchronized void q() {
        if (this.c != 1) {
            this.z = System.currentTimeMillis();
            this.A = System.nanoTime();
            f.a(this.d).a();
        }
    }

    private synchronized void c(int i) {
        ALog.e(d(), this.c + " notifyStatus:" + a(i), new Object[0]);
        if (i == this.p) {
            ALog.i(d(), this.c + " ignore notifyStatus", new Object[0]);
        } else {
            this.p = i;
            switch (i) {
                case 1:
                    f.a(this.d).f();
                    q();
                    if (this.k != null) {
                        this.k.cancel(true);
                    }
                    synchronized (this.y) {
                        try {
                            this.y.notifyAll();
                        } catch (Exception e) {
                        }
                    }
                    synchronized (this.q) {
                        try {
                            this.q.notifyAll();
                        } catch (Exception e2) {
                        }
                    }
                    break;
                case 2:
                    if (this.k != null) {
                        this.k.cancel(true);
                    }
                    com.taobao.accs.common.a.a().schedule(new q(this, this.L), 120000, TimeUnit.MILLISECONDS);
                    break;
                case 3:
                    q();
                    f.a(this.d).d();
                    synchronized (this.y) {
                        try {
                            this.y.notifyAll();
                        } catch (Exception e3) {
                        }
                    }
                    this.e.a(-10);
                    a(false, true);
                    break;
            }
            ALog.i(d(), this.c + " notifyStatus:" + a(i) + " handled", new Object[0]);
        }
    }

    public String m() {
        String channelHost = this.h.getChannelHost();
        ALog.i(d(), this.c + " getChannelHost:" + channelHost, new Object[0]);
        return channelHost == null ? "" : channelHost;
    }

    private void r() {
        try {
            SpdyAgent.enableDebug = true;
            this.w = SpdyAgent.getInstance(this.d, SpdyVersion.SPDY3, SpdySessionKind.NONE_SESSION);
            if (SpdyAgent.checkLoadSucc()) {
                com.taobao.accs.utl.e.a();
                if (!k()) {
                    this.w.setAccsSslCallback(new r(this));
                }
                if (!h.a(false)) {
                    String str = this.c == 0 ? "service" : "inapp";
                    ALog.d(d(), "into--[setTnetLogPath]", new Object[0]);
                    Object tnetLogFilePath = UtilityImpl.getTnetLogFilePath(this.d, str);
                    ALog.d(d(), "config tnet log path:" + tnetLogFilePath, new Object[0]);
                    if (!TextUtils.isEmpty(tnetLogFilePath)) {
                        this.w.configLogFile(tnetLogFilePath, UtilityImpl.TNET_FILE_SIZE, 5);
                        return;
                    }
                    return;
                }
                return;
            }
            ALog.e(d(), "loadSoFail", new Object[0]);
            com.taobao.accs.utl.e.b();
        } catch (Throwable th) {
            ALog.e(d(), "loadSoFail", th, new Object[0]);
        }
    }

    public boolean n() {
        return this.s;
    }

    public void spdySessionFailedError(SpdySession spdySession, int i, Object obj) {
        int i2;
        if (spdySession != null) {
            try {
                spdySession.cleanUp();
            } catch (Exception e) {
                ALog.e("SilenceConn", "session cleanUp has exception: " + e, new Object[0]);
            }
        }
        if (this.r != null) {
            i2 = this.r.a;
        } else {
            i2 = 0;
        }
        ALog.e("SilenceConn", this.c + " spdySessionFailedError, retryTimes:" + i2 + " errorId:" + i, new Object[0]);
        this.H = false;
        this.J = true;
        c(3);
        this.F.setFailReason(i);
        this.F.onConnectStop();
        UTMini.getInstance().commitEvent(66001, "DISCONNECT " + (this.c == 0 ? "service" : "inapp"), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(Constants.SDK_VERSION_CODE), this.u, this.I);
        com.taobao.accs.utl.b.a("accs", BaseMonitor.ALARM_POINT_CONNECT, "retrytimes:" + i2, i + "", "");
    }

    public void spdySessionConnectCB(SpdySession spdySession, SuperviseConnectInfo superviseConnectInfo) {
        this.D = superviseConnectInfo.connectTime;
        int i = superviseConnectInfo.handshakeTime;
        ALog.e("SilenceConn", this.c + " spdySessionConnectCB sessionConnectInterval:" + this.D + " sslTime:" + i + " reuse:" + superviseConnectInfo.sessionTicketReused, new Object[0]);
        p();
        if (this.r != null) {
            int i2 = this.r.a;
        }
        this.F.setRet(true);
        this.F.onConnectStop();
        this.F.tcp_time = (long) this.D;
        this.F.ssl_time = (long) i;
        UTMini.getInstance().commitEvent(66001, "CONNECTED " + (this.c == 0 ? "service" : "inapp") + " " + superviseConnectInfo.sessionTicketReused, String.valueOf(this.D), String.valueOf(i), Integer.valueOf(Constants.SDK_VERSION_CODE), String.valueOf(superviseConnectInfo.sessionTicketReused), this.u, this.I);
        com.taobao.accs.utl.b.a("accs", BaseMonitor.ALARM_POINT_CONNECT, "");
    }

    public void spdySessionCloseCallback(SpdySession spdySession, Object obj, SuperviseConnectInfo superviseConnectInfo, int i) {
        ALog.e("SilenceConn", this.c + " spdySessionCloseCallback, errorCode:" + i, new Object[0]);
        if (spdySession != null) {
            try {
                spdySession.cleanUp();
            } catch (Exception e) {
                ALog.e("SilenceConn", "session cleanUp has exception: " + e, new Object[0]);
            }
        }
        c(3);
        this.F.onCloseConnect();
        if (this.F.getConCloseDate() > 0 && this.F.getConStopDate() > 0) {
            this.F.getConCloseDate();
            this.F.getConStopDate();
        }
        this.F.setCloseReason(this.F.getCloseReason() + "tnet error:" + i);
        if (superviseConnectInfo != null) {
            this.F.live_time = (long) superviseConnectInfo.keepalive_period_second;
        }
        AppMonitor.getInstance().commitStat(this.F);
        for (Message message : this.e.e()) {
            if (message.getNetPermanceMonitor() != null) {
                message.getNetPermanceMonitor().setFailReason("session close");
                AppMonitor.getInstance().commitStat(message.getNetPermanceMonitor());
            }
        }
        String str = this.c == 0 ? "service" : "inapp";
        ALog.d("SilenceConn", "spdySessionCloseCallback, conKeepTime:" + this.F.live_time + " connectType:" + str, new Object[0]);
        UTMini.getInstance().commitEvent(66001, "DISCONNECT CLOSE " + str, Integer.valueOf(i), Long.valueOf(this.F.live_time), Integer.valueOf(Constants.SDK_VERSION_CODE), this.u, this.I);
    }

    public void spdyPingRecvCallback(SpdySession spdySession, long j, Object obj) {
        ALog.d("SilenceConn", "spdyPingRecvCallback uniId:" + j, new Object[0]);
        if (j >= 0) {
            this.e.b();
            f.a(this.d).e();
            f.a(this.d).a();
            this.F.onPingCBReceive();
            if (this.F.ping_rec_times % 2 == 0) {
                UtilityImpl.setServiceTime(this.d, Constants.SP_KEY_SERVICE_END, System.currentTimeMillis());
            }
        }
    }

    public void spdyCustomControlFrameRecvCallback(SpdySession spdySession, Object obj, int i, int i2, int i3, int i4, byte[] bArr) {
        q();
        ALog.e("SilenceConn", this.c + " onFrame, type:" + i2 + " len:" + bArr.length, new Object[0]);
        String str = "";
        if (ALog.isPrintLog(Level.D) && bArr.length < 512) {
            long currentTimeMillis = System.currentTimeMillis();
            String str2 = str;
            for (byte b : bArr) {
                str2 = str2 + Integer.toHexString(b & 255) + " ";
            }
            ALog.d("SilenceConn", str2 + " log time:" + (System.currentTimeMillis() - currentTimeMillis), new Object[0]);
        }
        if (i2 == 200) {
            try {
                long currentTimeMillis2 = System.currentTimeMillis();
                this.e.a(bArr);
                d f = this.e.f();
                if (f != null) {
                    f.c = String.valueOf(currentTimeMillis2);
                    f.g = this.c == 0 ? "service" : "inapp";
                    f.commitUT();
                }
            } catch (Throwable th) {
                ALog.e("SilenceConn", "onDataReceive ", th, new Object[0]);
                UTMini.getInstance().commitEvent(66001, "SERVICE_DATA_RECEIVE", UtilityImpl.getStackMsg(th));
            }
            ALog.d("SilenceConn", "try handle msg", new Object[0]);
            g();
        } else {
            ALog.e("SilenceConn", this.c + " drop frame" + " len:" + bArr.length, new Object[0]);
        }
        ALog.d("SilenceConn", "spdyCustomControlFrameRecvCallback", new Object[0]);
    }

    public void spdyStreamCloseCallback(SpdySession spdySession, long j, int i, Object obj, SuperviseData superviseData) {
        ALog.d("SilenceConn", "spdyStreamCloseCallback", new Object[0]);
        if (i != 0) {
            ALog.e("SilenceConn", "spdyStreamCloseCallback", "statusCode", Integer.valueOf(i));
            d(i);
        }
    }

    public void spdyRequestRecvCallback(SpdySession spdySession, long j, Object obj) {
        ALog.d("SilenceConn", "spdyRequestRecvCallback", new Object[0]);
    }

    public void spdyOnStreamResponse(SpdySession spdySession, long j, Map<String, List<String>> map, Object obj) {
        this.z = System.currentTimeMillis();
        this.A = System.nanoTime();
        try {
            Map a = a((Map) map);
            int parseInt = Integer.parseInt((String) a.get(HttpConstant.STATUS));
            ALog.e("SilenceConn", this.c + " spdyOnStreamResponse httpStatusCode: " + parseInt, new Object[0]);
            if (parseInt == 200) {
                c(1);
                if (!TextUtils.isEmpty((String) a.get("x-at"))) {
                    this.v = (String) a.get("x-at");
                }
                this.F.auth_time = this.F.connection_stop_date > 0 ? System.currentTimeMillis() - this.F.connection_stop_date : 0;
                UTMini.getInstance().commitEvent(66001, "CONNECTED 200 " + (this.c == 0 ? "service" : "inapp"), this.u, this.I, Integer.valueOf(Constants.SDK_VERSION_CODE), "0");
                com.taobao.accs.utl.b.a("accs", BaseMonitor.ALARM_POINT_AUTH, "");
            } else {
                d(parseInt);
            }
        } catch (Exception e) {
            ALog.e("SilenceConn", "SilenceConn" + e.toString(), new Object[0]);
            l();
            this.F.setCloseReason("exception");
        }
        ALog.d("SilenceConn", "spdyOnStreamResponse", new Object[0]);
    }

    private void d(int i) {
        int i2;
        this.v = null;
        l();
        if (this.r != null) {
            i2 = this.r.a;
        } else {
            i2 = 0;
        }
        this.F.setCloseReason("code not 200 is" + i);
        this.J = true;
        UTMini.getInstance().commitEvent(66001, "CONNECTED NO 200 " + (this.c == 0 ? "service" : "inapp"), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(Constants.SDK_VERSION_CODE), this.u, this.I);
        com.taobao.accs.utl.b.a("accs", BaseMonitor.ALARM_POINT_AUTH, "", i + "", "");
    }

    public void spdyDataSendCallback(SpdySession spdySession, boolean z, long j, int i, Object obj) {
        ALog.d("SilenceConn", "spdyDataSendCallback", new Object[0]);
    }

    public void spdyDataRecvCallback(SpdySession spdySession, boolean z, long j, int i, Object obj) {
        ALog.d("SilenceConn", "spdyDataRecvCallback", new Object[0]);
    }

    public void b() {
        this.H = false;
        this.f = 0;
    }

    public void bioPingRecvCallback(SpdySession spdySession, int i) {
        ALog.w(d(), "bioPingRecvCallback uniId:" + i, new Object[0]);
    }

    protected void a(String str, String str2) {
        try {
            c(4);
            l();
            this.F.setCloseReason(str2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean a(String str) {
        boolean z;
        synchronized (this.q) {
            for (int size = this.q.size() - 1; size >= 0; size--) {
                Message message = (Message) this.q.get(size);
                if (message != null && message.getType() == 1 && message.cunstomDataId != null && message.cunstomDataId.equals(str)) {
                    this.q.remove(size);
                    z = true;
                    break;
                }
            }
            z = false;
        }
        return z;
    }

    public byte[] getSSLMeta(SpdySession spdySession) {
        return UtilityImpl.SecurityGuardGetSslTicket2(this.d, this.b, spdySession.getDomain());
    }

    public int putSSLMeta(SpdySession spdySession, byte[] bArr) {
        return UtilityImpl.SecurityGuardPutSslTicket2(this.d, this.b, spdySession.getDomain(), bArr);
    }

    public void spdyDataChunkRecvCB(SpdySession spdySession, boolean z, long j, SpdyByteArray spdyByteArray, Object obj) {
        ALog.d(d(), "spdyDataChunkRecvCB", new Object[0]);
    }

    protected String d() {
        return "SilenceConn" + this.b;
    }

    public void spdyCustomControlFrameFailCallback(SpdySession spdySession, Object obj, int i, int i2) {
        b(i);
    }

    protected boolean h() {
        return false;
    }

    protected void a(Context context) {
        super.a(context);
        GlobalAppRuntimeInfo.setBackground(false);
    }
}
