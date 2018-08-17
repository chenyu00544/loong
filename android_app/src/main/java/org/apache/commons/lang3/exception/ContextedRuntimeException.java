package org.apache.commons.lang3.exception;

import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

public class ContextedRuntimeException extends RuntimeException implements a {
    private final a a;

    public ContextedRuntimeException() {
        this.a = new DefaultExceptionContext();
    }

    public ContextedRuntimeException(String str) {
        super(str);
        this.a = new DefaultExceptionContext();
    }

    public ContextedRuntimeException(Throwable th) {
        super(th);
        this.a = new DefaultExceptionContext();
    }

    public ContextedRuntimeException(String str, Throwable th) {
        super(str, th);
        this.a = new DefaultExceptionContext();
    }

    public ContextedRuntimeException(String str, Throwable th, a aVar) {
        super(str, th);
        if (aVar == null) {
            aVar = new DefaultExceptionContext();
        }
        this.a = aVar;
    }

    public ContextedRuntimeException addContextValue(String str, Object obj) {
        this.a.addContextValue(str, obj);
        return this;
    }

    public ContextedRuntimeException setContextValue(String str, Object obj) {
        this.a.setContextValue(str, obj);
        return this;
    }

    public List<Object> getContextValues(String str) {
        return this.a.getContextValues(str);
    }

    public Object getFirstContextValue(String str) {
        return this.a.getFirstContextValue(str);
    }

    public List<Pair<String, Object>> getContextEntries() {
        return this.a.getContextEntries();
    }

    public Set<String> getContextLabels() {
        return this.a.getContextLabels();
    }

    public String getMessage() {
        return getFormattedExceptionMessage(super.getMessage());
    }

    public String getRawMessage() {
        return super.getMessage();
    }

    public String getFormattedExceptionMessage(String str) {
        return this.a.getFormattedExceptionMessage(str);
    }
}
