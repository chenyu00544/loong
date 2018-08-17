package com.taobao.accs.net;

/* compiled from: Taobao */
class q implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ o b;

    q(o oVar, String str) {
        this.b = oVar;
        this.a = str;
    }

    public void run() {
        if (this.a != null && this.a.equals(this.b.L) && this.b.p == 2) {
            this.b.H = false;
            this.b.J = true;
            this.b.l();
            this.b.F.setCloseReason("conn timeout");
        }
    }
}
