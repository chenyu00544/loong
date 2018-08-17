package org.apache.commons.lang3.event;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.commons.lang3.e;

public class EventListenerSupport<L> implements Serializable {
    private List<L> a;
    private transient L b;
    private transient L[] c;

    protected class a implements InvocationHandler {
        final /* synthetic */ EventListenerSupport a;

        protected a(EventListenerSupport eventListenerSupport) {
            this.a = eventListenerSupport;
        }

        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            for (Object invoke : this.a.a) {
                method.invoke(invoke, objArr);
            }
            return null;
        }
    }

    public static <T> EventListenerSupport<T> create(Class<T> cls) {
        return new EventListenerSupport(cls);
    }

    public EventListenerSupport(Class<L> cls) {
        this(cls, Thread.currentThread().getContextClassLoader());
    }

    public EventListenerSupport(Class<L> cls, ClassLoader classLoader) {
        this();
        e.a((Object) cls, "Listener interface cannot be null.", new Object[0]);
        e.a((Object) classLoader, "ClassLoader cannot be null.", new Object[0]);
        e.a(cls.isInterface(), "Class {0} is not an interface", cls.getName());
        a(cls, classLoader);
    }

    private EventListenerSupport() {
        this.a = new CopyOnWriteArrayList();
    }

    public L fire() {
        return this.b;
    }

    public void addListener(L l) {
        e.a((Object) l, "Listener object cannot be null.", new Object[0]);
        this.a.add(l);
    }

    public void removeListener(L l) {
        e.a((Object) l, "Listener object cannot be null.", new Object[0]);
        this.a.remove(l);
    }

    public L[] getListeners() {
        return this.a.toArray(this.c);
    }

    private void a(Class<L> cls, ClassLoader classLoader) {
        this.c = (Object[]) Array.newInstance(cls, 0);
        b(cls, classLoader);
    }

    private void b(Class<L> cls, ClassLoader classLoader) {
        this.b = cls.cast(Proxy.newProxyInstance(classLoader, new Class[]{cls}, a()));
    }

    protected InvocationHandler a() {
        return new a(this);
    }
}
