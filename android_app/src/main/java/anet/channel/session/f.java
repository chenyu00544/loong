package anet.channel.session;

import anet.channel.Session.Status;
import anet.channel.entity.EventType;
import anet.channel.entity.b;
import anet.channel.entity.d;
import anet.channel.request.Request;
import anet.channel.session.c.a;
import anet.channel.util.ErrorConstant;
import anet.channel.util.StringUtils;

/* compiled from: Taobao */
class f implements Runnable {
    final /* synthetic */ Request a;
    final /* synthetic */ e b;

    f(e eVar, Request request) {
        this.b = eVar;
        this.a = request;
    }

    public void run() {
        long currentTimeMillis = System.currentTimeMillis();
        a a = c.a(this.a, null);
        if (a.a > 0) {
            d bVar = new b(EventType.CONNECTED);
            bVar.a = System.currentTimeMillis() - currentTimeMillis;
            this.b.notifyStatus(Status.AUTH_SUCC, bVar);
            return;
        }
        if (a.a == ErrorConstant.ERROR_SSL_ERROR || a.a == ErrorConstant.ERROR_HOST_NOT_VERIFY_ERROR) {
            e.a.put(this.b.mHost, StringUtils.concatString(this.b.mIp, ":", String.valueOf(this.b.mPort)));
        }
        this.b.handleCallbacks(EventType.CONNECT_FAIL, new d(EventType.CONNECT_FAIL, a.a, "Http connect fail"));
    }
}
