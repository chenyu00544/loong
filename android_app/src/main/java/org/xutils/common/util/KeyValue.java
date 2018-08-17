package org.xutils.common.util;

public class KeyValue {
    public final String key;
    public final Object value;

    public KeyValue(String str, Object obj) {
        this.key = str;
        this.value = obj;
    }

    public String getValueStr() {
        return this.value == null ? null : this.value.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        KeyValue keyValue = (KeyValue) obj;
        if (this.key != null) {
            return this.key.equals(keyValue.key);
        }
        if (keyValue.key != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.key != null ? this.key.hashCode() : 0;
    }

    public String toString() {
        return "KeyValue{key='" + this.key + '\'' + ", value=" + this.value + '}';
    }
}
