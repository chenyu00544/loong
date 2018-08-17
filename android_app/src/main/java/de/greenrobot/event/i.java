package de.greenrobot.event;

import java.lang.reflect.Method;

/* compiled from: SubscriberMethod */
final class i {
    final Method a;
    final ThreadMode b;
    final Class<?> c;
    String d;

    i(Method method, ThreadMode threadMode, Class<?> cls) {
        this.a = method;
        this.b = threadMode;
        this.c = cls;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof i)) {
            return false;
        }
        a();
        return this.d.equals(((i) obj).d);
    }

    private synchronized void a() {
        if (this.d == null) {
            StringBuilder stringBuilder = new StringBuilder(64);
            stringBuilder.append(this.a.getDeclaringClass().getName());
            stringBuilder.append('#').append(this.a.getName());
            stringBuilder.append('(').append(this.c.getName());
            this.d = stringBuilder.toString();
        }
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
