package de.greenrobot.event;

import android.os.Looper;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* compiled from: EventBus */
public final class c {
    static ExecutorService a = Executors.newCachedThreadPool();
    public static String b = "Event";
    private static volatile c c;
    private static final Map<Class<?>, List<Class<?>>> d = new HashMap();
    private static /* synthetic */ int[] q;
    private final Map<Class<?>, CopyOnWriteArrayList<k>> e = new HashMap();
    private final Map<Object, List<Class<?>>> f = new HashMap();
    private final Map<Class<?>, Object> g = new ConcurrentHashMap();
    private final ThreadLocal<List<Object>> h = new c_1(this);
    private final ThreadLocal<a> i = new c_2(this);
    private String j = "onEvent";
    private final d k = new d(this, Looper.getMainLooper(), 10);
    private final b l = new b(this);
    private final a m = new a(this);
    private final j n = new j();
    private boolean o;
    private boolean p = true;

    /* compiled from: EventBus */
    class c_1 extends ThreadLocal<List<Object>> {
        final /* synthetic */ c a;

        c_1(c cVar) {
            this.a = cVar;
        }

        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected List<Object> a() {
            return new ArrayList();
        }
    }

    /* compiled from: EventBus */
    class c_2 extends ThreadLocal<a> {
        final /* synthetic */ c a;

        c_2(c cVar) {
            this.a = cVar;
        }

        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected a a() {
            return new a();
        }
    }

    /* compiled from: EventBus */
    static final class a {
        boolean a;

        a() {
        }
    }

    static /* synthetic */ int[] b() {
        int[] iArr = q;
        if (iArr == null) {
            iArr = new int[ThreadMode.values().length];
            try {
                iArr[ThreadMode.Async.ordinal()] = 4;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ThreadMode.BackgroundThread.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ThreadMode.MainThread.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ThreadMode.PostThread.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            q = iArr;
        }
        return iArr;
    }

    public static c a() {
        if (c == null) {
            synchronized (c.class) {
                if (c == null) {
                    c = new c();
                }
            }
        }
        return c;
    }

    public void a(Object obj) {
        a(obj, this.j, false);
    }

    private void a(Object obj, String str, boolean z) {
        for (i a : this.n.a(obj.getClass(), str)) {
            a(obj, a, z);
        }
    }

    private void a(Object obj, i iVar, boolean z) {
        this.o = true;
        Class cls = iVar.c;
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.e.get(cls);
        k kVar = new k(obj, iVar);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList();
            this.e.put(cls, copyOnWriteArrayList);
        } else {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                if (((k) it.next()).equals(kVar)) {
                    throw new EventBusException("Subscriber " + obj.getClass() + " already registered to event " + cls);
                }
            }
        }
        iVar.a.setAccessible(true);
        copyOnWriteArrayList.add(kVar);
        List list = (List) this.f.get(obj);
        if (list == null) {
            list = new ArrayList();
            this.f.put(obj, list);
        }
        list.add(cls);
        if (z) {
            Object obj2;
            synchronized (this.g) {
                obj2 = this.g.get(cls);
            }
            if (obj2 != null) {
                a(kVar, obj2, Looper.getMainLooper() == Looper.myLooper());
            }
        }
    }

    private void a(Object obj, Class<?> cls) {
        List list = (List) this.e.get(cls);
        if (list != null) {
            int size = list.size();
            int i = 0;
            while (i < size) {
                int i2;
                if (((k) list.get(i)).a == obj) {
                    list.remove(i);
                    i2 = i - 1;
                    i = size - 1;
                } else {
                    i2 = i;
                    i = size;
                }
                size = i;
                i = i2 + 1;
            }
        }
    }

    public synchronized void b(Object obj) {
        List<Class> list = (List) this.f.get(obj);
        if (list != null) {
            for (Class a : list) {
                a(obj, a);
            }
            this.f.remove(obj);
        } else {
            Log.w(b, "Subscriber to unregister was not registered before: " + obj.getClass());
        }
    }

    public void c(Object obj) {
        List list = (List) this.h.get();
        list.add(obj);
        a aVar = (a) this.i.get();
        if (!aVar.a) {
            boolean z = Looper.getMainLooper() == Looper.myLooper();
            aVar.a = true;
            while (!list.isEmpty()) {
                try {
                    a(list.remove(0), z);
                } finally {
                    aVar.a = false;
                }
            }
        }
    }

    private void a(Object obj, boolean z) throws Error {
        Class cls = obj.getClass();
        List a = a(cls);
        int size = a.size();
        int i = 0;
        Object obj2 = null;
        while (i < size) {
            Object obj3;
            Class cls2 = (Class) a.get(i);
            synchronized (this) {
                CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.e.get(cls2);
            }
            if (copyOnWriteArrayList != null) {
                Iterator it = copyOnWriteArrayList.iterator();
                while (it.hasNext()) {
                    a((k) it.next(), obj, z);
                }
                obj3 = 1;
            } else {
                obj3 = obj2;
            }
            i++;
            obj2 = obj3;
        }
        if (obj2 == null) {
            Log.d(b, "No subscripers registered for event " + cls);
            if (cls != e.class && cls != h.class) {
                c(new e(this, obj));
            }
        }
    }

    private void a(k kVar, Object obj, boolean z) {
        switch (b()[kVar.b.b.ordinal()]) {
            case 1:
                a(kVar, obj);
                return;
            case 2:
                if (z) {
                    a(kVar, obj);
                    return;
                } else {
                    this.k.a(kVar, obj);
                    return;
                }
            case 3:
                if (z) {
                    this.l.a(kVar, obj);
                    return;
                } else {
                    a(kVar, obj);
                    return;
                }
            case 4:
                this.m.a(kVar, obj);
                return;
            default:
                throw new IllegalStateException("Unknown thread mode: " + kVar.b.b);
        }
    }

    private List<Class<?>> a(Class<?> cls) {
        List<Class<?>> list;
        synchronized (d) {
            list = (List) d.get(cls);
            if (list == null) {
                List arrayList = new ArrayList();
                for (Class cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                    arrayList.add(cls2);
                    a(arrayList, cls2.getInterfaces());
                }
                d.put(cls, arrayList);
            }
        }
        return list;
    }

    static void a(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                a((List) list, cls.getInterfaces());
            }
        }
    }

    void a(f fVar) {
        Object obj = fVar.a;
        k kVar = fVar.b;
        f.a(fVar);
        a(kVar, obj);
    }

    void a(k kVar, Object obj) throws Error {
        Throwable cause;
        try {
            kVar.b.a.invoke(kVar.a, new Object[]{obj});
        } catch (InvocationTargetException e) {
            cause = e.getCause();
            if (obj instanceof h) {
                Log.e(b, "SubscriberExceptionEvent subscriber " + kVar.a.getClass() + " threw an exception", cause);
                h hVar = (h) obj;
                Log.e(b, "Initial event " + hVar.c + " caused exception in " + hVar.d, hVar.b);
                return;
            }
            if (this.p) {
                Log.e(b, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + kVar.a.getClass(), cause);
            }
            c(new h(this, cause, obj, kVar.a));
        } catch (Throwable cause2) {
            throw new IllegalStateException("Unexpected exception", cause2);
        }
    }
}
