package anet.channel.strategy;

import anet.channel.entity.ConnType;
import anet.channel.util.ALog;
import anet.channel.util.c;
import java.net.InetAddress;

/* compiled from: Taobao */
class e implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ Object b;
    final /* synthetic */ d c;

    e(d dVar, String str, Object obj) {
        this.c = dVar;
        this.a = str;
        this.b = obj;
    }

    public void run() {
        try {
            String hostAddress = InetAddress.getByName(this.a).getHostAddress();
            if (c.a(hostAddress)) {
                if (a.d(this.a)) {
                    this.c.a.put(this.a, a.a(hostAddress, a.a()));
                } else {
                    this.c.a.put(this.a, a.a(hostAddress, a.a(80, ConnType.HTTP)));
                }
                if (ALog.isPrintLog(1)) {
                    ALog.d("awcn.LocalDnsStrategyTable", "resolve ip by local dns", null, "host", this.a, "ip", hostAddress);
                }
            } else {
                this.c.a.put(this.a, d.c);
            }
            synchronized (this.c.b) {
                this.c.b.remove(this.a);
            }
            synchronized (this.b) {
                this.b.notifyAll();
            }
        } catch (Exception e) {
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.LocalDnsStrategyTable", "resolve ip by local dns failed", null, "host", this.a);
            }
            synchronized (this.c.b) {
                this.c.b.remove(this.a);
                synchronized (this.b) {
                    this.b.notifyAll();
                }
            }
        } catch (Throwable th) {
            synchronized (this.c.b) {
                this.c.b.remove(this.a);
                synchronized (this.b) {
                    this.b.notifyAll();
                }
            }
        }
    }
}
