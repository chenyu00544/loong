package org.apache.commons.lang3.math;

import com.baidu.mapapi.map.WeightedLatLng;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import java.math.BigInteger;

public final class Fraction extends Number implements Comparable<Fraction> {
    public static final Fraction FOUR_FIFTHS = new Fraction(4, 5);
    public static final Fraction ONE = new Fraction(1, 1);
    public static final Fraction ONE_FIFTH = new Fraction(1, 5);
    public static final Fraction ONE_HALF = new Fraction(1, 2);
    public static final Fraction ONE_QUARTER = new Fraction(1, 4);
    public static final Fraction ONE_THIRD = new Fraction(1, 3);
    public static final Fraction THREE_FIFTHS = new Fraction(3, 5);
    public static final Fraction THREE_QUARTERS = new Fraction(3, 4);
    public static final Fraction TWO_FIFTHS = new Fraction(2, 5);
    public static final Fraction TWO_QUARTERS = new Fraction(2, 4);
    public static final Fraction TWO_THIRDS = new Fraction(2, 3);
    public static final Fraction ZERO = new Fraction(0, 1);
    private final int a;
    private final int b;
    private transient int c = 0;
    private transient String d = null;
    private transient String e = null;

    private Fraction(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public static Fraction getFraction(int i, int i2) {
        if (i2 == 0) {
            throw new ArithmeticException("The denominator must not be zero");
        }
        if (i2 < 0) {
            if (i == Integer.MIN_VALUE || i2 == Integer.MIN_VALUE) {
                throw new ArithmeticException("overflow: can't negate");
            }
            i = -i;
            i2 = -i2;
        }
        return new Fraction(i, i2);
    }

    public static Fraction getFraction(int i, int i2, int i3) {
        if (i3 == 0) {
            throw new ArithmeticException("The denominator must not be zero");
        } else if (i3 < 0) {
            throw new ArithmeticException("The denominator must not be negative");
        } else if (i2 < 0) {
            throw new ArithmeticException("The numerator must not be negative");
        } else {
            long j;
            if (i < 0) {
                j = (((long) i) * ((long) i3)) - ((long) i2);
            } else {
                j = (((long) i) * ((long) i3)) + ((long) i2);
            }
            if (j >= -2147483648L && j <= 2147483647L) {
                return new Fraction((int) j, i3);
            }
            throw new ArithmeticException("Numerator too large to represent as an Integer.");
        }
    }

    public static Fraction getReducedFraction(int i, int i2) {
        if (i2 == 0) {
            throw new ArithmeticException("The denominator must not be zero");
        } else if (i == 0) {
            return ZERO;
        } else {
            int i3;
            int i4;
            if (i2 == Integer.MIN_VALUE && (i & 1) == 0) {
                i3 = i2 / 2;
                i4 = i / 2;
            } else {
                i3 = i2;
                i4 = i;
            }
            if (i3 < 0) {
                if (i4 == Integer.MIN_VALUE || i3 == Integer.MIN_VALUE) {
                    throw new ArithmeticException("overflow: can't negate");
                }
                i4 = -i4;
                i3 = -i3;
            }
            int a = a(i4, i3);
            return new Fraction(i4 / a, i3 / a);
        }
    }

    public static Fraction getFraction(double d) {
        int i = d < 0.0d ? -1 : 1;
        double abs = Math.abs(d);
        if (abs > 2.147483647E9d || Double.isNaN(abs)) {
            throw new ArithmeticException("The value must not be greater than Integer.MAX_VALUE or NaN");
        }
        int i2 = (int) abs;
        double d2 = abs - ((double) i2);
        int i3 = (int) d2;
        abs = Double.MAX_VALUE;
        int i4 = 1;
        int i5 = 1;
        int i6 = 0;
        int i7 = 0;
        int i8 = 1;
        int i9 = i3;
        double d3 = d2 - ((double) i3);
        double d4 = WeightedLatLng.DEFAULT_INTENSITY;
        double d5 = d3;
        while (true) {
            int i10 = (int) (d4 / d5);
            double d6 = d4 - (((double) i10) * d5);
            i6 += i9 * i8;
            i9 = (i9 * i7) + i5;
            d4 = Math.abs(d2 - (((double) i6) / ((double) i9)));
            i4++;
            if (abs > d4 && i9 <= 10000 && i9 > 0 && i4 < 25) {
                abs = d4;
                i5 = i7;
                d4 = d5;
                i7 = i9;
                i9 = i10;
                d5 = d6;
                int i11 = i6;
                i6 = i8;
                i8 = i11;
            }
        }
        if (i4 != 25) {
            return getReducedFraction(i * ((i2 * i7) + i8), i7);
        }
        throw new ArithmeticException("Unable to convert double to fraction");
    }

    public static Fraction getFraction(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The string must not be null");
        } else if (str.indexOf(46) >= 0) {
            return getFraction(Double.parseDouble(str));
        } else {
            int indexOf = str.indexOf(32);
            if (indexOf > 0) {
                int parseInt = Integer.parseInt(str.substring(0, indexOf));
                String substring = str.substring(indexOf + 1);
                int indexOf2 = substring.indexOf(47);
                if (indexOf2 >= 0) {
                    return getFraction(parseInt, Integer.parseInt(substring.substring(0, indexOf2)), Integer.parseInt(substring.substring(indexOf2 + 1)));
                }
                throw new NumberFormatException("The fraction could not be parsed as the format X Y/Z");
            }
            indexOf = str.indexOf(47);
            if (indexOf < 0) {
                return getFraction(Integer.parseInt(str), 1);
            }
            return getFraction(Integer.parseInt(str.substring(0, indexOf)), Integer.parseInt(str.substring(indexOf + 1)));
        }
    }

    public int getNumerator() {
        return this.a;
    }

    public int getDenominator() {
        return this.b;
    }

    public int getProperNumerator() {
        return Math.abs(this.a % this.b);
    }

    public int getProperWhole() {
        return this.a / this.b;
    }

    public int intValue() {
        return this.a / this.b;
    }

    public long longValue() {
        return ((long) this.a) / ((long) this.b);
    }

    public float floatValue() {
        return ((float) this.a) / ((float) this.b);
    }

    public double doubleValue() {
        return ((double) this.a) / ((double) this.b);
    }

    public Fraction reduce() {
        if (this.a != 0) {
            int a = a(Math.abs(this.a), this.b);
            return a != 1 ? getFraction(this.a / a, this.b / a) : this;
        } else if (equals(ZERO)) {
            return this;
        } else {
            return ZERO;
        }
    }

    public Fraction invert() {
        if (this.a == 0) {
            throw new ArithmeticException("Unable to invert zero.");
        } else if (this.a == Integer.MIN_VALUE) {
            throw new ArithmeticException("overflow: can't negate numerator");
        } else if (this.a < 0) {
            return new Fraction(-this.b, -this.a);
        } else {
            return new Fraction(this.b, this.a);
        }
    }

    public Fraction negate() {
        if (this.a != Integer.MIN_VALUE) {
            return new Fraction(-this.a, this.b);
        }
        throw new ArithmeticException("overflow: too large to negate");
    }

    public Fraction abs() {
        return this.a >= 0 ? this : negate();
    }

    public Fraction pow(int i) {
        if (i == 1) {
            return this;
        }
        if (i == 0) {
            return ONE;
        }
        if (i >= 0) {
            Fraction multiplyBy = multiplyBy(this);
            if (i % 2 == 0) {
                return multiplyBy.pow(i / 2);
            }
            return multiplyBy.pow(i / 2).multiplyBy(this);
        } else if (i == Integer.MIN_VALUE) {
            return invert().pow(2).pow(-(i / 2));
        } else {
            return invert().pow(-i);
        }
    }

    private static int a(int i, int i2) {
        if (i == 0 || i2 == 0) {
            if (i != Integer.MIN_VALUE && i2 != Integer.MIN_VALUE) {
                return Math.abs(i) + Math.abs(i2);
            }
            throw new ArithmeticException("overflow: gcd is 2^31");
        } else if (Math.abs(i) == 1 || Math.abs(i2) == 1) {
            return 1;
        } else {
            int i3;
            if (i > 0) {
                i3 = -i;
            } else {
                i3 = i;
            }
            if (i2 > 0) {
                i2 = -i2;
            }
            int i4 = 0;
            int i5 = i2;
            while ((i3 & 1) == 0 && (i5 & 1) == 0 && i4 < 31) {
                i3 /= 2;
                i5 /= 2;
                i4++;
            }
            if (i4 == 31) {
                throw new ArithmeticException("overflow: gcd is 2^31");
            }
            int i6 = i5;
            i5 = (i3 & 1) == 1 ? i5 : -(i3 / 2);
            while (true) {
                if ((i5 & 1) == 0) {
                    i5 /= 2;
                } else {
                    if (i5 > 0) {
                        i5 = -i5;
                    } else {
                        i6 = i5;
                        i5 = i3;
                    }
                    i3 = (i6 - i5) / 2;
                    if (i3 == 0) {
                        return (-i5) * (1 << i4);
                    }
                    int i7 = i3;
                    i3 = i5;
                    i5 = i7;
                }
            }
        }
    }

    private static int b(int i, int i2) {
        long j = ((long) i) * ((long) i2);
        if (j >= -2147483648L && j <= 2147483647L) {
            return (int) j;
        }
        throw new ArithmeticException("overflow: mul");
    }

    private static int c(int i, int i2) {
        long j = ((long) i) * ((long) i2);
        if (j <= 2147483647L) {
            return (int) j;
        }
        throw new ArithmeticException("overflow: mulPos");
    }

    private static int d(int i, int i2) {
        long j = ((long) i) + ((long) i2);
        if (j >= -2147483648L && j <= 2147483647L) {
            return (int) j;
        }
        throw new ArithmeticException("overflow: add");
    }

    private static int e(int i, int i2) {
        long j = ((long) i) - ((long) i2);
        if (j >= -2147483648L && j <= 2147483647L) {
            return (int) j;
        }
        throw new ArithmeticException("overflow: add");
    }

    public Fraction add(Fraction fraction) {
        return a(fraction, true);
    }

    public Fraction subtract(Fraction fraction) {
        return a(fraction, false);
    }

    private Fraction a(Fraction fraction, boolean z) {
        if (fraction == null) {
            throw new IllegalArgumentException("The fraction must not be null");
        } else if (this.a == 0) {
            if (z) {
                return fraction;
            }
            return fraction.negate();
        } else if (fraction.a == 0) {
            return this;
        } else {
            int a = a(this.b, fraction.b);
            if (a == 1) {
                int b = b(this.a, fraction.b);
                a = b(fraction.a, this.b);
                return new Fraction(z ? d(b, a) : e(b, a), c(this.b, fraction.b));
            }
            BigInteger multiply = BigInteger.valueOf((long) this.a).multiply(BigInteger.valueOf((long) (fraction.b / a)));
            BigInteger multiply2 = BigInteger.valueOf((long) fraction.a).multiply(BigInteger.valueOf((long) (this.b / a)));
            multiply = z ? multiply.add(multiply2) : multiply.subtract(multiply2);
            int intValue = multiply.mod(BigInteger.valueOf((long) a)).intValue();
            intValue = intValue == 0 ? a : a(intValue, a);
            BigInteger divide = multiply.divide(BigInteger.valueOf((long) intValue));
            if (divide.bitLength() <= 31) {
                return new Fraction(divide.intValue(), c(this.b / a, fraction.b / intValue));
            }
            throw new ArithmeticException("overflow: numerator too large after multiply");
        }
    }

    public Fraction multiplyBy(Fraction fraction) {
        if (fraction == null) {
            throw new IllegalArgumentException("The fraction must not be null");
        } else if (this.a == 0 || fraction.a == 0) {
            return ZERO;
        } else {
            int a = a(this.a, fraction.b);
            int a2 = a(fraction.a, this.b);
            return getReducedFraction(b(this.a / a, fraction.a / a2), c(this.b / a2, fraction.b / a));
        }
    }

    public Fraction divideBy(Fraction fraction) {
        if (fraction == null) {
            throw new IllegalArgumentException("The fraction must not be null");
        } else if (fraction.a != 0) {
            return multiplyBy(fraction.invert());
        } else {
            throw new ArithmeticException("The fraction to divide by must not be zero");
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Fraction)) {
            return false;
        }
        Fraction fraction = (Fraction) obj;
        if (getNumerator() == fraction.getNumerator() && getDenominator() == fraction.getDenominator()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.c == 0) {
            this.c = ((getNumerator() + 629) * 37) + getDenominator();
        }
        return this.c;
    }

    public int compareTo(Fraction fraction) {
        if (this == fraction) {
            return 0;
        }
        if (this.a == fraction.a && this.b == fraction.b) {
            return 0;
        }
        long j = ((long) this.a) * ((long) fraction.b);
        long j2 = ((long) fraction.a) * ((long) this.b);
        if (j == j2) {
            return 0;
        }
        if (j < j2) {
            return -1;
        }
        return 1;
    }

    public String toString() {
        if (this.d == null) {
            this.d = getNumerator() + '/' + getDenominator();
        }
        return this.d;
    }

    public String toProperString() {
        if (this.e == null) {
            if (this.a == 0) {
                this.e = "0";
            } else if (this.a == this.b) {
                this.e = "1";
            } else if (this.a == this.b * -1) {
                this.e = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;
            } else {
                if ((this.a > 0 ? -this.a : this.a) < (-this.b)) {
                    int properNumerator = getProperNumerator();
                    if (properNumerator == 0) {
                        this.e = Integer.toString(getProperWhole());
                    } else {
                        this.e = getProperWhole() + ' ' + properNumerator + '/' + getDenominator();
                    }
                } else {
                    this.e = getNumerator() + '/' + getDenominator();
                }
            }
        }
        return this.e;
    }
}
