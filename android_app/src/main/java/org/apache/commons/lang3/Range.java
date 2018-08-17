package org.apache.commons.lang3;

import java.io.Serializable;
import java.util.Comparator;

public final class Range<T> implements Serializable {
    private final Comparator<T> a;
    private final T b;
    private final T c;
    private transient int d;
    private transient String e;

    private enum a implements Comparator {
        INSTANCE;

        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    public static <T extends Comparable<T>> Range<T> is(T t) {
        return between(t, t, null);
    }

    public static <T> Range<T> is(T t, Comparator<T> comparator) {
        return between(t, t, comparator);
    }

    public static <T extends Comparable<T>> Range<T> between(T t, T t2) {
        return between(t, t2, null);
    }

    public static <T> Range<T> between(T t, T t2, Comparator<T> comparator) {
        return new Range(t, t2, comparator);
    }

    private Range(T t, T t2, Comparator<T> comparator) {
        if (t == null || t2 == null) {
            throw new IllegalArgumentException("Elements in a range must not be null: element1=" + t + ", element2=" + t2);
        }
        if (comparator == null) {
            comparator = a.INSTANCE;
        }
        if (comparator.compare(t, t2) < 1) {
            this.b = t;
            this.c = t2;
        } else {
            this.b = t2;
            this.c = t;
        }
        this.a = comparator;
    }

    public T getMinimum() {
        return this.b;
    }

    public T getMaximum() {
        return this.c;
    }

    public Comparator<T> getComparator() {
        return this.a;
    }

    public boolean isNaturalOrdering() {
        return this.a == a.INSTANCE;
    }

    public boolean contains(T t) {
        boolean z = true;
        if (t == null) {
            return false;
        }
        if (this.a.compare(t, this.b) <= -1 || this.a.compare(t, this.c) >= 1) {
            z = false;
        }
        return z;
    }

    public boolean isAfter(T t) {
        if (t != null && this.a.compare(t, this.b) < 0) {
            return true;
        }
        return false;
    }

    public boolean isStartedBy(T t) {
        if (t != null && this.a.compare(t, this.b) == 0) {
            return true;
        }
        return false;
    }

    public boolean isEndedBy(T t) {
        if (t != null && this.a.compare(t, this.c) == 0) {
            return true;
        }
        return false;
    }

    public boolean isBefore(T t) {
        if (t != null && this.a.compare(t, this.c) > 0) {
            return true;
        }
        return false;
    }

    public int elementCompareTo(T t) {
        if (t == null) {
            throw new NullPointerException("Element is null");
        } else if (isAfter(t)) {
            return -1;
        } else {
            if (isBefore(t)) {
                return 1;
            }
            return 0;
        }
    }

    public boolean containsRange(Range<T> range) {
        if (range != null && contains(range.b) && contains(range.c)) {
            return true;
        }
        return false;
    }

    public boolean isAfterRange(Range<T> range) {
        if (range == null) {
            return false;
        }
        return isAfter(range.c);
    }

    public boolean isOverlappedBy(Range<T> range) {
        if (range == null) {
            return false;
        }
        if (range.contains(this.b) || range.contains(this.c) || contains(range.b)) {
            return true;
        }
        return false;
    }

    public boolean isBeforeRange(Range<T> range) {
        if (range == null) {
            return false;
        }
        return isBefore(range.b);
    }

    public Range<T> intersectionWith(Range<T> range) {
        if (!isOverlappedBy(range)) {
            throw new IllegalArgumentException(String.format("Cannot calculate intersection with non-overlapping range %s", new Object[]{range}));
        } else if (equals(range)) {
            return this;
        } else {
            return between(getComparator().compare(this.b, range.b) < 0 ? range.b : this.b, getComparator().compare(this.c, range.c) < 0 ? this.c : range.c, getComparator());
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        Range range = (Range) obj;
        if (this.b.equals(range.b) && this.c.equals(range.c)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = this.d;
        if (this.d != 0) {
            return i;
        }
        i = ((((getClass().hashCode() + 629) * 37) + this.b.hashCode()) * 37) + this.c.hashCode();
        this.d = i;
        return i;
    }

    public String toString() {
        String str = this.e;
        if (str != null) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append('[');
        stringBuilder.append(this.b);
        stringBuilder.append("..");
        stringBuilder.append(this.c);
        stringBuilder.append(']');
        str = stringBuilder.toString();
        this.e = str;
        return str;
    }

    public String toString(String str) {
        return String.format(str, new Object[]{this.b, this.c, this.a});
    }
}
