package org.apache.commons.lang3.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/* compiled from: ExceptionUtils */
public class b {
    private static final String[] a = new String[]{"getCause", "getNextException", "getTargetException", "getException", "getSourceException", "getRootCause", "getCausedByException", "getNested", "getLinkedException", "getNestedException", "getLinkedCause", "getThrowable"};

    public static String a(Throwable th) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter, true));
        return stringWriter.getBuffer().toString();
    }
}
