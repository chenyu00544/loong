package org.apache.commons.lang3.mutable;

public class MutableFloat extends Number implements Comparable<MutableFloat> {
    private float a;

    public MutableFloat(float f) {
        this.a = f;
    }

    public MutableFloat(Number number) {
        this.a = number.floatValue();
    }

    public MutableFloat(String str) throws NumberFormatException {
        this.a = Float.parseFloat(str);
    }

    public Float getValue() {
        return Float.valueOf(this.a);
    }

    public void setValue(float f) {
        this.a = f;
    }

    public void setValue(Number number) {
        this.a = number.floatValue();
    }

    public boolean isNaN() {
        return Float.isNaN(this.a);
    }

    public boolean isInfinite() {
        return Float.isInfinite(this.a);
    }

    public void increment() {
        this.a += 1.0f;
    }

    public void decrement() {
        this.a -= 1.0f;
    }

    public void add(float f) {
        this.a += f;
    }

    public void add(Number number) {
        this.a += number.floatValue();
    }

    public void subtract(float f) {
        this.a -= f;
    }

    public void subtract(Number number) {
        this.a -= number.floatValue();
    }

    public int intValue() {
        return (int) this.a;
    }

    public long longValue() {
        return (long) this.a;
    }

    public float floatValue() {
        return this.a;
    }

    public double doubleValue() {
        return (double) this.a;
    }

    public Float toFloat() {
        return Float.valueOf(floatValue());
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableFloat) && Float.floatToIntBits(((MutableFloat) obj).a) == Float.floatToIntBits(this.a);
    }

    public int hashCode() {
        return Float.floatToIntBits(this.a);
    }

    public int compareTo(MutableFloat mutableFloat) {
        return Float.compare(this.a, mutableFloat.a);
    }

    public String toString() {
        return String.valueOf(this.a);
    }
}
