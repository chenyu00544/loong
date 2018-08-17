package org.apache.commons.lang3.mutable;

public class MutableLong extends Number implements Comparable<MutableLong> {
    private long a;

    public MutableLong(long j) {
        this.a = j;
    }

    public MutableLong(Number number) {
        this.a = number.longValue();
    }

    public MutableLong(String str) throws NumberFormatException {
        this.a = Long.parseLong(str);
    }

    public Long getValue() {
        return Long.valueOf(this.a);
    }

    public void setValue(long j) {
        this.a = j;
    }

    public void setValue(Number number) {
        this.a = number.longValue();
    }

    public void increment() {
        this.a++;
    }

    public void decrement() {
        this.a--;
    }

    public void add(long j) {
        this.a += j;
    }

    public void add(Number number) {
        this.a += number.longValue();
    }

    public void subtract(long j) {
        this.a -= j;
    }

    public void subtract(Number number) {
        this.a -= number.longValue();
    }

    public int intValue() {
        return (int) this.a;
    }

    public long longValue() {
        return this.a;
    }

    public float floatValue() {
        return (float) this.a;
    }

    public double doubleValue() {
        return (double) this.a;
    }

    public Long toLong() {
        return Long.valueOf(longValue());
    }

    public boolean equals(Object obj) {
        if ((obj instanceof MutableLong) && this.a == ((MutableLong) obj).longValue()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) (this.a ^ (this.a >>> 32));
    }

    public int compareTo(MutableLong mutableLong) {
        long j = mutableLong.a;
        if (this.a < j) {
            return -1;
        }
        return this.a == j ? 0 : 1;
    }

    public String toString() {
        return String.valueOf(this.a);
    }
}
