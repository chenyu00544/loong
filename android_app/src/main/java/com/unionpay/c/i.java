package com.unionpay.c;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

final class i {
    private static volatile i a = null;
    private final ConcurrentMap b = new ConcurrentHashMap();
    private final ThreadLocal c = new j(this);
    private final ThreadLocal d = new k(this);
    private final Map e = new HashMap();

    static class a {
        final Object a;
        final m b;

        public a(Object obj, m mVar) {
            this.a = obj;
            this.b = mVar;
        }
    }

    private i() {
    }

    public static i a() {
        if (a == null) {
            synchronized (i.class) {
                if (a == null) {
                    a = new i();
                }
            }
        }
        return a;
    }

    private Set a(Class cls) {
        return (Set) this.b.get(cls);
    }

    private static Set b(Class cls) {
        List linkedList = new LinkedList();
        Set hashSet = new HashSet();
        linkedList.add(cls);
        while (!linkedList.isEmpty()) {
            Class cls2 = (Class) linkedList.remove(0);
            hashSet.add(cls2);
            cls2 = cls2.getSuperclass();
            if (cls2 != null) {
                linkedList.add(cls2);
            }
        }
        return hashSet;
    }

    private void b() {
        Object obj;
        m mVar;
        if (!((Boolean) this.d.get()).booleanValue()) {
            this.d.set(Boolean.valueOf(true));
            while (true) {
                try {
                    a aVar = (a) ((ConcurrentLinkedQueue) this.c.get()).poll();
                    if (aVar == null) {
                        this.d.set(Boolean.valueOf(false));
                        return;
                    } else if (aVar.b.a()) {
                        obj = aVar.a;
                        mVar = aVar.b;
                        mVar.a(obj);
                    }
                } catch (Throwable e) {
                    String str = "Could not dispatch event: " + obj.getClass() + " to handler " + mVar;
                    Throwable cause = e.getCause();
                    if (cause != null) {
                        throw new RuntimeException(str + ": " + cause.getMessage(), cause);
                    }
                    throw new RuntimeException(str + ": " + e.getMessage(), e);
                } catch (Throwable th) {
                    this.d.set(Boolean.valueOf(false));
                }
            }
        }
    }

    public final void a(Object obj) {
        if (obj != null) {
            Map a = l.a(obj);
            for (Class cls : a.keySet()) {
                Set set = (Set) this.b.get(cls);
                if (set == null) {
                    CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();
                    set = (Set) this.b.putIfAbsent(cls, copyOnWriteArraySet);
                    if (set == null) {
                        set = copyOnWriteArraySet;
                    }
                }
                if (!set.addAll((Set) a.get(cls))) {
                    throw new IllegalArgumentException("Object already registered.");
                }
            }
        }
    }

    public final void b(Object obj) {
        if (obj == null) {
            throw new NullPointerException("Event to post must not be null.");
        }
        Class cls = obj.getClass();
        Set set = (Set) this.e.get(cls);
        if (set == null) {
            set = b(cls);
            this.e.put(cls, set);
        }
        Object obj2 = null;
        for (Class a : r0) {
            Object obj3;
            Set<m> a2 = a(a);
            if (a2 == null || a2.isEmpty()) {
                obj3 = obj2;
            } else {
                for (m aVar : a2) {
                    ((ConcurrentLinkedQueue) this.c.get()).offer(new a(obj, aVar));
                }
                int i = 1;
            }
            obj2 = obj3;
        }
        if (obj2 == null && !(obj instanceof n)) {
            b(new n(this, obj));
        }
        b();
    }
}
