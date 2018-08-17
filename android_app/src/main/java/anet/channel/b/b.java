package anet.channel.b;

import anet.channel.util.ALog;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: Taobao */
public class b {
    private static final Class<? extends a>[] c = new Class[]{c.class, d.class};
    Map<Class, a> a;
    CopyOnWriteArrayList<a> b;

    /* compiled from: Taobao */
    private static class a {
        static b a = new b();

        private a() {
        }
    }

    private b() {
        this.a = new HashMap();
        this.b = new CopyOnWriteArrayList();
        b();
    }

    private void b() {
        for (int i = 0; i < c.length; i++) {
            try {
                this.a.put(c[i], c[i].newInstance());
            } catch (Throwable e) {
                ALog.e("awcn.EventHandlerManager", "instantiate plugin failed.", null, e, new Object[0]);
            }
        }
        this.b.addAll(this.a.values());
    }

    public synchronized void a(Class<? extends a> cls) {
        if (cls == null) {
            throw new IllegalArgumentException("plugin class is null");
        }
        try {
            if (!this.a.containsKey(cls)) {
                a aVar = (a) cls.newInstance();
                this.a.put(cls, aVar);
                this.b.add(aVar);
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void b(Class<? extends a> cls) {
        a aVar = (a) this.a.remove(cls);
        if (aVar != null) {
            this.b.remove(aVar);
        }
    }

    public <T> T a(int i, Object... objArr) {
        Iterator it = this.b.iterator();
        T t = null;
        while (it.hasNext()) {
            t = ((a) it.next()).a(i, objArr);
            if (t != a.a) {
                break;
            }
        }
        if (t == a.a || t == a.b) {
            return null;
        }
        return t;
    }

    public static b a() {
        return a.a;
    }
}
