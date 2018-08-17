package org.apache.commons.lang3.concurrent;

public class ConcurrentException extends Exception {
    protected ConcurrentException() {
    }

    public ConcurrentException(Throwable th) {
        super(a.a(th));
    }

    public ConcurrentException(String str, Throwable th) {
        super(str, a.a(th));
    }
}
