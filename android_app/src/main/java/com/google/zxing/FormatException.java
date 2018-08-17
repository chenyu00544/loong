package com.google.zxing;

public final class FormatException extends ReaderException {
    private static final FormatException a = new FormatException();

    private FormatException() {
    }

    public static FormatException getFormatInstance() {
        return a;
    }
}
