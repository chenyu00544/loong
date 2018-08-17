package org.apache.commons.lang3.mutable;

import java.io.Serializable;

public class MutableBoolean implements Serializable, Comparable<MutableBoolean> {
    private boolean a;

    public MutableBoolean(boolean z) {
        this.a = z;
    }

    public MutableBoolean(Boolean bool) {
        this.a = bool.booleanValue();
    }

    public Boolean getValue() {
        return Boolean.valueOf(this.a);
    }

    public void setValue(boolean z) {
        this.a = z;
    }

    public void setValue(Boolean bool) {
        this.a = bool.booleanValue();
    }

    public boolean isTrue() {
        return this.a;
    }

    public boolean isFalse() {
        return !this.a;
    }

    public boolean booleanValue() {
        return this.a;
    }

    public Boolean toBoolean() {
        return Boolean.valueOf(booleanValue());
    }

    public boolean equals(Object obj) {
        if ((obj instanceof MutableBoolean) && this.a == ((MutableBoolean) obj).booleanValue()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.a ? Boolean.TRUE.hashCode() : Boolean.FALSE.hashCode();
    }

    public int compareTo(MutableBoolean mutableBoolean) {
        if (this.a == mutableBoolean.a) {
            return 0;
        }
        return this.a ? 1 : -1;
    }

    public String toString() {
        return String.valueOf(this.a);
    }
}
