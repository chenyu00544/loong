package anet.channel.strategy;

/* compiled from: Taobao */
class k implements Runnable {
    final /* synthetic */ j a;

    k(j jVar) {
        this.a = jVar;
    }

    public void run() {
        if (!this.a.a()) {
            this.a.a.b();
        }
    }
}
