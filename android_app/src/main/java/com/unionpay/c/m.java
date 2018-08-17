package com.unionpay.c;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

final class m {
    private final Object a;
    private final Method b;
    private final int c;
    private boolean d = true;

    m(Object obj, Method method) {
        if (obj == null) {
            throw new NullPointerException("EventHandler target cannot be null.");
        } else if (method == null) {
            throw new NullPointerException("EventHandler method cannot be null.");
        } else {
            this.a = obj;
            this.b = method;
            method.setAccessible(true);
            this.c = ((method.hashCode() + 31) * 31) + obj.hashCode();
        }
    }

    public final void a(Object obj) {
        if (this.d) {
            try {
                this.b.invoke(this.a, new Object[]{obj});
                return;
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (InvocationTargetException e2) {
                if (e2.getCause() instanceof Error) {
                    throw ((Error) e2.getCause());
                }
                throw e2;
            }
        }
        throw new IllegalStateException(toString() + " has been invalidated and can no longer handle events.");
    }

    public final boolean a() {
        return this.d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        m mVar = (m) obj;
        return this.b.equals(mVar.b) && this.a == mVar.a;
    }

    public final int hashCode() {
        return this.c;
    }

    public final String toString() {
        return "[EventHandler " + this.b + "]";
    }
}
