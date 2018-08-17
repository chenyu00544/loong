package org.apache.commons.lang3.mutable;

import com.baidu.mapapi.map.WeightedLatLng;

public class MutableDouble extends Number implements Comparable<MutableDouble> {
    private double a;

    public MutableDouble(double d) {
        this.a = d;
    }

    public MutableDouble(Number number) {
        this.a = number.doubleValue();
    }

    public MutableDouble(String str) throws NumberFormatException {
        this.a = Double.parseDouble(str);
    }

    public Double getValue() {
        return Double.valueOf(this.a);
    }

    public void setValue(double d) {
        this.a = d;
    }

    public void setValue(Number number) {
        this.a = number.doubleValue();
    }

    public boolean isNaN() {
        return Double.isNaN(this.a);
    }

    public boolean isInfinite() {
        return Double.isInfinite(this.a);
    }

    public void increment() {
        this.a += WeightedLatLng.DEFAULT_INTENSITY;
    }

    public void decrement() {
        this.a -= WeightedLatLng.DEFAULT_INTENSITY;
    }

    public void add(double d) {
        this.a += d;
    }

    public void add(Number number) {
        this.a += number.doubleValue();
    }

    public void subtract(double d) {
        this.a -= d;
    }

    public void subtract(Number number) {
        this.a -= number.doubleValue();
    }

    public int intValue() {
        return (int) this.a;
    }

    public long longValue() {
        return (long) this.a;
    }

    public float floatValue() {
        return (float) this.a;
    }

    public double doubleValue() {
        return this.a;
    }

    public Double toDouble() {
        return Double.valueOf(doubleValue());
    }

    public boolean equals(Object obj) {
        return (obj instanceof MutableDouble) && Double.doubleToLongBits(((MutableDouble) obj).a) == Double.doubleToLongBits(this.a);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.a);
        return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    }

    public int compareTo(MutableDouble mutableDouble) {
        return Double.compare(this.a, mutableDouble.a);
    }

    public String toString() {
        return String.valueOf(this.a);
    }
}
