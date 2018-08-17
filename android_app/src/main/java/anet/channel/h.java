package anet.channel;

import anet.channel.util.i;

/* compiled from: Taobao */
class h implements Runnable {
    final /* synthetic */ Session a;
    final /* synthetic */ a b;

    h(a aVar, Session session) {
        this.b = aVar;
        this.a = session;
    }

    public void run() {
        try {
            this.b.b.a(this.b.c, this.a.getConnType().getTypeLevel(), i.a(this.b.b.b.seqNum));
        } catch (Exception e) {
        }
    }
}
