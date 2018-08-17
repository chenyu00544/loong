package org.apache.commons.lang3.mutable;

import java.io.Serializable;

public class MutableObject<T> implements Serializable {
    private T a;

    public MutableObject(T t) {
        this.a = t;
    }

    public T getValue() {
        return this.a;
    }

    public void setValue(T t) {
        this.a = t;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((MutableObject) obj).a);
    }

    public int hashCode() {
        return this.a == null ? 0 : this.a.hashCode();
    }

    public String toString() {
        return this.a == null ? "null" : this.a.toString();
    }
}
