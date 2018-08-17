package com.taobao.accs.net;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.AccsFrameCb;
import anet.channel.Session;
import anet.channel.SessionCenter;
import anet.channel.session.AccsSession;
import com.taobao.accs.ErrorCode;
import com.taobao.accs.common.a;
import com.taobao.accs.data.Message;
import com.taobao.accs.ut.monitor.NetPerformanceMonitor;
import com.taobao.accs.ut.statistics.c;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.h;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* compiled from: Taobao */
public class j extends a implements AccsFrameCb {
    private boolean k = true;
    private ScheduledFuture<?> l = null;
    private Runnable m = new n(this);

    public j(Context context, int i, String str) {
        super(context, i, str);
        if (!h.a(true)) {
            Object tnetLogFilePath = UtilityImpl.getTnetLogFilePath(this.d, "inapp");
            ALog.d(d(), "config tnet log path:" + tnetLogFilePath, new Object[0]);
            if (!TextUtils.isEmpty(tnetLogFilePath)) {
                Session.configTnetALog(context, tnetLogFilePath, UtilityImpl.TNET_FILE_SIZE, 5);
            }
        }
        this.l = a.a().schedule(this.m, 120000, TimeUnit.MILLISECONDS);
    }

    public synchronized void a() {
        this.k = true;
        a(this.d);
        ALog.d(d(), this.c + " start", new Object[0]);
    }

    protected void a(Message message, boolean z) {
        if (!this.k || message == null) {
            ALog.e(d(), "not running or msg null! " + this.k, new Object[0]);
            return;
        }
        try {
            if (a.b().getQueue().size() > 1000) {
                throw new RejectedExecutionException("accs");
            }
            ScheduledFuture schedule = a.b().schedule(new k(this, message), message.delyTime, TimeUnit.MILLISECONDS);
            if (message.getType() == 1 && message.cunstomDataId != null) {
                if (message.isControlFrame() && a(message.cunstomDataId)) {
                    this.e.b(message);
                }
                this.e.a.put(message.cunstomDataId, schedule);
            }
            NetPerformanceMonitor netPermanceMonitor = message.getNetPermanceMonitor();
            if (netPermanceMonitor != null) {
                netPermanceMonitor.setDeviceId(UtilityImpl.getDeviceId(this.d));
                netPermanceMonitor.setConnType(this.c);
                netPermanceMonitor.onEnterQueueData();
            }
        } catch (RejectedExecutionException e) {
            this.e.a(message, (int) ErrorCode.MESSAGE_QUEUE_FULL);
            ALog.e(d(), "send queue full count:" + a.b().getQueue().size(), new Object[0]);
        } catch (Throwable th) {
            this.e.a(message, -8);
            ALog.e(d(), "send error", th, new Object[0]);
        }
    }

    public void e() {
        ALog.e(d(), this.c + "shut down", new Object[0]);
        this.k = false;
    }

    public void b() {
        this.f = 0;
    }

    public void a(boolean z, boolean z2) {
    }

    public c c() {
        return null;
    }

    protected void a(String str, String str2) {
        try {
            Message a = this.e.a(str);
            if (a != null && a.host != null) {
                Session session = SessionCenter.getInstance(this.h.getTag()).get(a.host.toString(), 0);
                if (session != null) {
                    session.checkAvailable();
                }
            }
        } catch (Throwable e) {
            ALog.e(d(), "onTimeOut", e, new Object[0]);
        }
    }

    public void onDataReceive(AccsSession accsSession, byte[] bArr, int i, int i2) {
        if (ALog.isPrintLog(Level.I)) {
            ALog.i(d(), "onDataReceive, type:" + i2 + " len:" + bArr.length, new Object[0]);
        }
        a.a().execute(new l(this, i2, bArr, accsSession));
        if (ALog.isPrintLog(Level.E)) {
            ALog.e(d(), "onDataReceive, end:", new Object[0]);
        }
    }

    public void onException(int i, int i2, boolean z, String str) {
        ALog.e(d(), "errorId:" + i2 + "detail:" + str + " dataId:" + i + " needRetry:" + z, new Object[0]);
        a.a().execute(new m(this, i, z, i2));
    }

    public boolean a(String str) {
        if (str == null) {
            return false;
        }
        boolean cancel;
        ScheduledFuture scheduledFuture = (ScheduledFuture) this.e.a.get(str);
        if (scheduledFuture != null) {
            cancel = scheduledFuture.cancel(false);
        } else {
            cancel = false;
        }
        if (!cancel) {
            return cancel;
        }
        ALog.e(d(), "cancel", "customDataId", str);
        return cancel;
    }

    protected String d() {
        return "InAppConn" + this.b;
    }

    protected void a(Context context) {
        super.a(context);
        SessionCenter.getInstance(this.h.getTag()).setDataReceiveCb(this);
    }
}
