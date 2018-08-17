package com.taobao.accs.net;

import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.Session;
import anet.channel.SessionCenter;
import anet.channel.entity.ConnType.TypeLevel;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.taobao.accs.common.Constants;
import com.taobao.accs.data.Message;
import com.taobao.accs.data.Message.b;
import com.taobao.accs.ut.monitor.TrafficsMonitor.a;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.ALog.Level;
import com.taobao.accs.utl.BaseMonitor;

/* compiled from: Taobao */
class k implements Runnable {
    final /* synthetic */ Message a;
    final /* synthetic */ j b;

    k(j jVar, Message message) {
        this.b = jVar;
        this.a = message;
    }

    public void run() {
        if (this.a != null) {
            if (this.a.getNetPermanceMonitor() != null) {
                this.a.getNetPermanceMonitor().onTakeFromQueue();
            }
            int type = this.a.getType();
            try {
                boolean z;
                if (ALog.isPrintLog(Level.I)) {
                    ALog.i(this.b.d(), "try send:" + b.b(type) + " dataId:" + this.a.dataId, "appkey", this.b.b);
                }
                if (type != 1) {
                    ALog.e(this.b.d(), "skip msg type" + b.b(type), new Object[0]);
                    z = true;
                } else if (this.a.host == null) {
                    this.b.e.a(this.a, -5);
                    z = true;
                } else {
                    Session session = SessionCenter.getInstance(this.b.h.getTag()).get(this.a.host.toString(), TypeLevel.SPDY, 60000);
                    SessionCenter.getInstance(this.b.h.getTag()).setDataReceiveCb(this.b);
                    if (session != null) {
                        byte[] build = this.a.build(this.b.d, this.b.c);
                        if (ALog.isPrintLog(Level.I)) {
                            String d = this.b.d();
                            String str = "send data ";
                            Object[] objArr = new Object[10];
                            objArr[0] = "len";
                            objArr[1] = Integer.valueOf(build == null ? 0 : build.length);
                            objArr[2] = Constants.KEY_DATA_ID;
                            objArr[3] = this.a.getDataId();
                            objArr[4] = "command";
                            objArr[5] = this.a.command;
                            objArr[6] = "host";
                            objArr[7] = this.a.host;
                            objArr[8] = "utdid";
                            objArr[9] = this.b.i;
                            ALog.i(d, str, objArr);
                        }
                        this.a.setSendTime(System.currentTimeMillis());
                        if (build.length <= 16384 || this.a.command.intValue() == 102) {
                            this.b.e.a(this.a);
                            if (this.a.isAck) {
                                this.b.j.put(Integer.valueOf(this.a.getIntDataId()), this.a);
                            }
                            session.sendCustomFrame(this.a.getIntDataId(), build, 200);
                            if (this.a.getNetPermanceMonitor() != null) {
                                this.a.getNetPermanceMonitor().onSendData();
                            }
                            this.b.a(this.a.getDataId(), (long) this.a.timeout);
                            this.b.e.a(new a(this.a.serviceId, GlobalAppRuntimeInfo.isAppBackground(), this.a.host.toString(), (long) build.length));
                        } else {
                            this.b.e.a(this.a, -4);
                        }
                        z = true;
                    } else {
                        z = false;
                    }
                }
                if (!z) {
                    if (type == 1) {
                        if (this.a.isTimeOut() || !this.b.a(this.a, (int) m_AppUI.MSG_APP_DATA_OK)) {
                            this.b.e.a(this.a, -11);
                        }
                        if (this.a.retryTimes == 1 && this.a.getNetPermanceMonitor() != null) {
                            com.taobao.accs.utl.b.a("accs", BaseMonitor.COUNT_POINT_RESEND, "total_accs", 0.0d);
                        }
                    } else {
                        this.b.e.a(this.a, -11);
                    }
                }
                if (ALog.isPrintLog(Level.D)) {
                    ALog.d(this.b.d(), "sendSucc" + z + " dataId:" + this.a.getDataId(), new Object[0]);
                }
            } catch (Throwable th) {
                if (ALog.isPrintLog(Level.D)) {
                    ALog.d(this.b.d(), "sendSucc" + true + " dataId:" + this.a.getDataId(), new Object[0]);
                }
            }
        }
    }
}
