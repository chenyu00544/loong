package com.google.zxing;

public final class ChecksumException extends ReaderException {
    private static final ChecksumException a = new ChecksumException();

    private ChecksumException() {
    }

    public static ChecksumException getChecksumInstance() {
        return a;
    }
}
