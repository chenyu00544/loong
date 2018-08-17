package org.apache.commons.lang3.mutable;

public class MutableByte extends Number implements Comparable<MutableByte> {
    private byte a;

    public MutableByte(byte b) {
        this.a = b;
    }

    public MutableByte(Number number) {
        this.a = number.byteValue();
    }

    public MutableByte(String str) throws NumberFormatException {
        this.a = Byte.parseByte(str);
    }

    public Byte getValue() {
        return Byte.valueOf(this.a);
    }

    public void setValue(byte b) {
        this.a = b;
    }

    public void setValue(Number number) {
        this.a = number.byteValue();
    }

    public void increment() {
        this.a = (byte) (this.a + 1);
    }

    public void decrement() {
        this.a = (byte) (this.a - 1);
    }

    public void add(byte b) {
        this.a = (byte) (this.a + b);
    }

    public void add(Number number) {
        this.a = (byte) (this.a + number.byteValue());
    }

    public void subtract(byte b) {
        this.a = (byte) (this.a - b);
    }

    public void subtract(Number number) {
        this.a = (byte) (this.a - number.byteValue());
    }

    public byte byteValue() {
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

    public Byte toByte() {
        return Byte.valueOf(byteValue());
    }

    public boolean equals(Object obj) {
        if ((obj instanceof MutableByte) && this.a == ((MutableByte) obj).byteValue()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.a;
    }

    public int compareTo(MutableByte mutableByte) {
        byte b = mutableByte.a;
        if (this.a < b) {
            return -1;
        }
        return this.a == b ? 0 : 1;
    }

    public String toString() {
        return String.valueOf(this.a);
    }
}
