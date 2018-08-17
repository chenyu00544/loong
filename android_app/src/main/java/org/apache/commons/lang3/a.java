package org.apache.commons.lang3;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: CharRange */
final class a implements Serializable, Iterable<Character> {
    private final char a;
    private final char b;
    private final boolean c;
    private transient String d;

    /* compiled from: CharRange */
    private static class a implements Iterator<Character> {
        private char a;
        private final a b;
        private boolean c;

        public /* synthetic */ Object next() {
            return a();
        }

        private a(a aVar) {
            this.b = aVar;
            this.c = true;
            if (!this.b.c) {
                this.a = this.b.a;
            } else if (this.b.a != '\u0000') {
                this.a = '\u0000';
            } else if (this.b.b == '￿') {
                this.c = false;
            } else {
                this.a = (char) (this.b.b + 1);
            }
        }

        private void b() {
            if (this.b.c) {
                if (this.a == '￿') {
                    this.c = false;
                } else if (this.a + 1 != this.b.a) {
                    this.a = (char) (this.a + 1);
                } else if (this.b.b == '￿') {
                    this.c = false;
                } else {
                    this.a = (char) (this.b.b + 1);
                }
            } else if (this.a < this.b.b) {
                this.a = (char) (this.a + 1);
            } else {
                this.c = false;
            }
        }

        public boolean hasNext() {
            return this.c;
        }

        public Character a() {
            if (this.c) {
                char c = this.a;
                b();
                return Character.valueOf(c);
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private a(char c, char c2, boolean z) {
        if (c <= c2) {
            char c3 = c2;
            c2 = c;
            c = c3;
        }
        this.a = c2;
        this.b = c;
        this.c = z;
    }

    public static a a(char c) {
        return new a(c, c, false);
    }

    public static a b(char c) {
        return new a(c, c, true);
    }

    public static a a(char c, char c2) {
        return new a(c, c2, false);
    }

    public static a b(char c, char c2) {
        return new a(c, c2, true);
    }

    public boolean a() {
        return this.c;
    }

    public boolean c(char c) {
        boolean z = c >= this.a && c <= this.b;
        return z != this.c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        if (this.a == aVar.a && this.b == aVar.b && this.c == aVar.c) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.c ? 1 : 0) + ((this.b * 7) + (this.a + 83));
    }

    public String toString() {
        if (this.d == null) {
            StringBuilder stringBuilder = new StringBuilder(4);
            if (a()) {
                stringBuilder.append('^');
            }
            stringBuilder.append(this.a);
            if (this.a != this.b) {
                stringBuilder.append('-');
                stringBuilder.append(this.b);
            }
            this.d = stringBuilder.toString();
        }
        return this.d;
    }

    public Iterator<Character> iterator() {
        return new a();
    }
}
