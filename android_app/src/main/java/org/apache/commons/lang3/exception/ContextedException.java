package org.apache.commons.lang3.exception;

import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.tuple.Pair;

public class ContextedException extends Exception implements a {
    private final a a;

    public ContextedException() {
        this.a = new DefaultExceptionContext();
    }

    public ContextedException(String str) {
        super(str);
        this.a = new DefaultExceptionContext();
    }

    public ContextedException(Throwable th) {
        super(th);
        this.a = new DefaultExceptionContext();
    }

    public ContextedException(String str, Throwable th) {
        super(str, th);
        this.a = new DefaultExceptionContext();
    }

    public ContextedException(String str, Throwable th, a aVar) {
        super(str, th);
        if (aVar == null) {
            aVar = new DefaultExceptionContext();
        }
        this.a = aVar;
    }

    public ContextedException addContextValue(String str, Object obj) {
        this.a.addContextValue(str, obj);
        return this;
    }

    public ContextedException setContextValue(String str, Object obj) {
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
