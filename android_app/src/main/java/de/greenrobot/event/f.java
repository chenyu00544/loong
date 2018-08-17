package de.greenrobot.event;

import java.util.ArrayList;
import java.util.List;

/* compiled from: PendingPost */
final class f {
    private static final List<f> d = new ArrayList();
    Object a;
    k b;
    f c;

    private f(Object obj, k kVar) {
        this.a = obj;
        this.b = kVar;
    }

    static f a(k kVar, Object obj) {
        synchronized (d) {
            int size = d.size();
            if (size > 0) {
                f fVar = (f) d.remove(size - 1);
                fVar.a = obj;
                fVar.b = kVar;
                fVar.c = null;
                return fVar;
            }
            return new f(obj, kVar);
        }
    }

    static void a(f fVar) {
        fVar.a = null;
        fVar.b = null;
        fVar.c = null;
        synchronized (d) {
            if (d.size() < 10000) {
                d.add(fVar);
            }
        }
    }
}
