package de.greenrobot.event;

/* compiled from: AsyncPoster */
class a implements Runnable {
    private final g a = new g();
    private final c b;

    a(c cVar) {
        this.b = cVar;
    }

    public void a(k kVar, Object obj) {
        this.a.a(f.a(kVar, obj));
        c.a.execute(this);
    }

    public void run() {
        f a = this.a.a();
        if (a == null) {
            throw new IllegalStateException("No pending post available");
        }
        this.b.a(a);
    }
}
