package org.apache.commons.lang3.mutable;

public class MutableInt extends Number implements Comparable<MutableInt> {
    private int a;

    public MutableInt(int i) {
        this.a = i;
    }

    public MutableInt(Number number) {
        this.a = number.intValue();
    }

    public MutableInt(String str) throws NumberFormatException {
        this.a = Integer.parseInt(str);
    }

    public Integer getValue() {
        return Integer.valueOf(this.a);
    }

    public void setValue(int i) {
        this.a = i;
    }

    public void setValue(Number number) {
        this.a = number.intValue();
    }

    public void increment() {
        this.a++;
    }

    public void decrement() {
        this.a--;
    }

    public void add(int i) {
        this.a += i;
    }

    public void add(Number number) {
        this.a += number.intValue();
    }

    public void subtract(int i) {
        this.a -= i;
    }

    public void subtract(Number number) {
        this.a -= number.intValue();
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

    public Integer toInteger() {
        return Integer.valueOf(intValue());
    }

    public boolean equals(Object obj) {
        if ((obj instanceof MutableInt) && this.a == ((MutableInt) obj).intValue()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.a;
    }

    public int compareTo(MutableInt mutableInt) {
        int i = mutableInt.a;
        if (this.a < i) {
            return -1;
        }
        return this.a == i ? 0 : 1;
    }

    public String toString() {
        return String.valueOf(this.a);
    }
}
