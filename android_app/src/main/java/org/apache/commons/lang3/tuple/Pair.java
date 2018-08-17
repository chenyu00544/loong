package org.apache.commons.lang3.tuple;

import java.io.Serializable;
import java.util.Map.Entry;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.a;

public abstract class Pair<L, R> implements Serializable, Comparable<Pair<L, R>>, Entry<L, R> {
    public abstract L getLeft();

    public abstract R getRight();

    public static <L, R> Pair<L, R> of(L l, R r) {
        return new ImmutablePair(l, r);
    }

    public final L getKey() {
        return getLeft();
    }

    public R getValue() {
        return getRight();
    }

    public int compareTo(Pair<L, R> pair) {
        return new a().a(getLeft(), pair.getLeft()).a(getRight(), pair.getRight()).a();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        if (ObjectUtils.a(getKey(), entry.getKey()) && ObjectUtils.a(getValue(), entry.getValue())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = getKey() == null ? 0 : getKey().hashCode();
        if (getValue() != null) {
            i = getValue().hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        return '(' + getLeft() + ',' + getRight() + ')';
    }

    public String toString(String str) {
        return String.format(str, new Object[]{getLeft(), getRight()});
    }
}
