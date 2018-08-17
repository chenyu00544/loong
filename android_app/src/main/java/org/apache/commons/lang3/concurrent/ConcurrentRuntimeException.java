package org.apache.commons.lang3.concurrent;

public class ConcurrentRuntimeException extends RuntimeException {
    protected ConcurrentRuntimeException() {
    }

    public ConcurrentRuntimeException(Throwable th) {
        super(a.a(th));
    }

    public ConcurrentRuntimeException(String str, Throwable th) {
        super(str, a.a(th));
    }
}
