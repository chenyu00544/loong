package org.apache.commons.lang3.text;

import java.util.Arrays;

/* compiled from: StrMatcher */
public abstract class b {
    private static final b a = new a(',');
    private static final b b = new a('\t');
    private static final b c = new a(' ');
    private static final b d = new b(" \t\n\r\f".toCharArray());
    private static final b e = new d();
    private static final b f = new a('\'');
    private static final b g = new a('\"');
    private static final b h = new b("'\"".toCharArray());
    private static final b i = new c();

    /* compiled from: StrMatcher */
    static final class a extends b {
        private final char a;

        a(char c) {
            this.a = c;
        }

        public int a(char[] cArr, int i, int i2, int i3) {
            return this.a == cArr[i] ? 1 : 0;
        }
    }

    /* compiled from: StrMatcher */
    static final class b extends b {
        private final char[] a;

        b(char[] cArr) {
            this.a = (char[]) cArr.clone();
            Arrays.sort(this.a);
        }

        public int a(char[] cArr, int i, int i2, int i3) {
            return Arrays.binarySearch(this.a, cArr[i]) >= 0 ? 1 : 0;
        }
    }

    /* compiled from: StrMatcher */
    static final class c extends b {
        c() {
        }

        public int a(char[] cArr, int i, int i2, int i3) {
            return 0;
        }
    }

    /* compiled from: StrMatcher */
    static final class d extends b {
        d() {
        }

        public int a(char[] cArr, int i, int i2, int i3) {
            return cArr[i] <= ' ' ? 1 : 0;
        }
    }

    public abstract int a(char[] cArr, int i, int i2, int i3);

    public static b a() {
        return d;
    }

    protected b() {
    }

    public int a(char[] cArr, int i) {
        return a(cArr, i, 0, cArr.length);
    }
}
