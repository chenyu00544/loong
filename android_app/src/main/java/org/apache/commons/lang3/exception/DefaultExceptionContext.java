package org.apache.commons.lang3.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.c;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class DefaultExceptionContext implements Serializable, a {
    private final List<Pair<String, Object>> a = new ArrayList();

    public DefaultExceptionContext addContextValue(String str, Object obj) {
        this.a.add(new ImmutablePair(str, obj));
        return this;
    }

    public DefaultExceptionContext setContextValue(String str, Object obj) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            if (c.a(str, (CharSequence) ((Pair) it.next()).getKey())) {
                it.remove();
            }
        }
        addContextValue(str, obj);
        return this;
    }

    public List<Object> getContextValues(String str) {
        List<Object> arrayList = new ArrayList();
        for (Pair pair : this.a) {
            if (c.a(str, (CharSequence) pair.getKey())) {
                arrayList.add(pair.getValue());
            }
        }
        return arrayList;
    }

    public Object getFirstContextValue(String str) {
        for (Pair pair : this.a) {
            if (c.a(str, (CharSequence) pair.getKey())) {
                return pair.getValue();
            }
        }
        return null;
    }

    public Set<String> getContextLabels() {
        Set<String> hashSet = new HashSet();
        for (Pair key : this.a) {
            hashSet.add(key.getKey());
        }
        return hashSet;
    }

    public List<Pair<String, Object>> getContextEntries() {
        return this.a;
    }

    public String getFormattedExceptionMessage(String str) {
        StringBuilder stringBuilder = new StringBuilder(256);
        if (str != null) {
            stringBuilder.append(str);
        }
        if (this.a.size() > 0) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append('\n');
            }
            stringBuilder.append("Exception Context:\n");
            int i = 0;
            for (Pair pair : this.a) {
                stringBuilder.append("\t[");
                int i2 = i + 1;
                stringBuilder.append(i2);
                stringBuilder.append(':');
                stringBuilder.append((String) pair.getKey());
                stringBuilder.append("=");
                Object value = pair.getValue();
                if (value == null) {
                    stringBuilder.append("null");
                } else {
                    String obj;
                    try {
                        obj = value.toString();
                    } catch (Throwable e) {
                        obj = "Exception thrown on toString(): " + b.a(e);
                    }
                    stringBuilder.append(obj);
                }
                stringBuilder.append("]\n");
                i = i2;
            }
            stringBuilder.append("---------------------------------");
        }
        return stringBuilder.toString();
    }
}
