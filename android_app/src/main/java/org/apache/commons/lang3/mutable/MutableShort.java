package org.apache.commons.lang3.mutable;

public class MutableShort extends Number implements Comparable<MutableShort> {
    private short a;

    public MutableShort(short s) {
        this.a = s;
    }

    public MutableShort(Number number) {
        this.a = number.shortValue();
    }

    public MutableShort(String str) throws NumberFormatException {
        this.a = Short.parseShort(str);
    }

    public Short getValue() {
        return Short.valueOf(this.a);
    }

    public void setValue(short s) {
        this.a = s;
    }

    public void setValue(Number number) {
        this.a = number.shortValue();
    }

    public void increment() {
        this.a = (short) (this.a + 1);
    }

    public void decrement() {
        this.a = (short) (this.a - 1);
    }

    public void add(short s) {
        this.a = (short) (this.a + s);
    }

    public void add(Number number) {
        this.a = (short) (this.a + number.shortValue());
    }

    public void subtract(short s) {
        this.a = (short) (this.a - s);
    }

    public void subtract(Number number) {
        this.a = (short) (this.a - number.shortValue());
    }

    public short shortValue() {
        return this.a;
    }

    public int intValue() {
        return this.a;
    }

    public long longValue() {
        return (long) this.a;
    }

    public float floatValue() {
        return (float) this.a;
    }

    public double doubleValue() {
        return (double) this.a;
    }

    public Short toShort() {
        return Short.valueOf(shortValue());
    }

    public boolean equals(Object obj) {
        if ((obj instanceof MutableShort) && this.a == ((MutableShort) obj).shortValue()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.a;
    }

    public int compareTo(MutableShort mutableShort) {
        short s = mutableShort.a;
        if (this.a < s) {
            return -1;
        }
        return this.a == s ? 0 : 1;
    }

    public String toString() {
        return String.valueOf(this.a);
    }
}
