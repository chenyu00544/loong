package org.apache.commons.lang3.exception;

public class CloneFailedException extends RuntimeException {
    public CloneFailedException(String str) {
        super(str);
    }

    public CloneFailedException(Throwable th) {
        super(th);
    }

    public CloneFailedException(String str, Throwable th) {
        super(str, th);
    }
}
