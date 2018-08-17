package org.apache.commons.lang3.concurrent;

/* compiled from: ConcurrentUtils */
public class a {
    static Throwable a(Throwable th) {
        if (th != null && !(th instanceof RuntimeException) && !(th instanceof Error)) {
            return th;
        }
        throw new IllegalArgumentException("Not a checked exception: " + th);
    }
}
