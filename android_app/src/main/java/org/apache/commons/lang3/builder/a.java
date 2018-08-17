package org.apache.commons.lang3.builder;

import java.util.Comparator;

/* compiled from: CompareToBuilder */
public class a {
    private int a = 0;

    public a a(Object obj, Object obj2) {
        return a(obj, obj2, null);
    }

    public a a(Object obj, Object obj2, Comparator<?> comparator) {
        if (this.a == 0 && obj != obj2) {
            if (obj == null) {
                this.a = -1;
            } else if (obj2 == null) {
                this.a = 1;
            } else if (obj.getClass().isArray()) {
                if (obj instanceof long[]) {
                    a((long[]) obj, (long[]) obj2);
                } else if (obj instanceof int[]) {
                    a((int[]) obj, (int[]) obj2);
                } else if (obj instanceof short[]) {
                    a((short[]) obj, (short[]) obj2);
                } else if (obj instanceof char[]) {
                    a((char[]) obj, (char[]) obj2);
                } else if (obj instanceof byte[]) {
                    a((byte[]) obj, (byte[]) obj2);
                } else if (obj instanceof double[]) {
                    a((double[]) obj, (double[]) obj2);
                } else if (obj instanceof float[]) {
                    a((float[]) obj, (float[]) obj2);
                } else if (obj instanceof boolean[]) {
                    a((boolean[]) obj, (boolean[]) obj2);
                } else {
                    a((Object[]) obj, (Object[]) obj2, (Comparator) comparator);
                }
            } else if (comparator == null) {
                this.a = ((Comparable) obj).compareTo(obj2);
            } else {
                this.a = comparator.compare(obj, obj2);
            }
        }
        return this;
    }

    public a a(long j, long j2) {
        if (this.a == 0) {
            int i = j < j2 ? -1 : j > j2 ? 1 : 0;
            this.a = i;
        }
        return this;
    }

    public a a(int i, int i2) {
        if (this.a == 0) {
            int i3 = i < i2 ? -1 : i > i2 ? 1 : 0;
            this.a = i3;
        }
        return this;
    }

    public a a(short s, short s2) {
        if (this.a == 0) {
            int i = s < s2 ? -1 : s > s2 ? 1 : 0;
            this.a = i;
        }
        return this;
    }

    public a a(char c, char c2) {
        if (this.a == 0) {
            int i = c < c2 ? -1 : c > c2 ? 1 : 0;
            this.a = i;
        }
        return this;
    }

    public a a(byte b, byte b2) {
        if (this.a == 0) {
            int i = b < b2 ? -1 : b > b2 ? 1 : 0;
            this.a = i;
        }
        return this;
    }

    public a a(double d, double d2) {
        if (this.a == 0) {
            this.a = Double.compare(d, d2);
        }
        return this;
    }

    public a a(float f, float f2) {
        if (this.a == 0) {
            this.a = Float.compare(f, f2);
        }
        return this;
    }

    public a a(boolean z, boolean z2) {
        if (this.a == 0 && z != z2) {
            if (z) {
                this.a = 1;
            } else {
                this.a = -1;
            }
        }
        return this;
    }

    public a a(Object[] objArr, Object[] objArr2, Comparator<?> comparator) {
        int i = -1;
        if (this.a == 0 && objArr != objArr2) {
            if (objArr == null) {
                this.a = -1;
            } else if (objArr2 == null) {
                this.a = 1;
            } else if (objArr.length != objArr2.length) {
                if (objArr.length >= objArr2.length) {
                    i = 1;
                }
                this.a = i;
            } else {
                for (i = 0; i < objArr.length && this.a == 0; i++) {
                    a(objArr[i], objArr2[i], (Comparator) comparator);
                }
            }
        }
        return this;
    }

    public a a(long[] jArr, long[] jArr2) {
        int i = -1;
        if (this.a == 0 && jArr != jArr2) {
            if (jArr == null) {
                this.a = -1;
            } else if (jArr2 == null) {
                this.a = 1;
            } else if (jArr.length != jArr2.length) {
                if (jArr.length >= jArr2.length) {
                    i = 1;
                }
                this.a = i;
            } else {
                for (i = 0; i < jArr.length && this.a == 0; i++) {
                    a(jArr[i], jArr2[i]);
                }
            }
        }
        return this;
    }

    public a a(int[] iArr, int[] iArr2) {
        int i = -1;
        if (this.a == 0 && iArr != iArr2) {
            if (iArr == null) {
                this.a = -1;
            } else if (iArr2 == null) {
                this.a = 1;
            } else if (iArr.length != iArr2.length) {
                if (iArr.length >= iArr2.length) {
                    i = 1;
                }
                this.a = i;
            } else {
                for (i = 0; i < iArr.length && this.a == 0; i++) {
                    a(iArr[i], iArr2[i]);
                }
            }
        }
        return this;
    }

    public a a(short[] sArr, short[] sArr2) {
        int i = -1;
        if (this.a == 0 && sArr != sArr2) {
            if (sArr == null) {
                this.a = -1;
            } else if (sArr2 == null) {
                this.a = 1;
            } else if (sArr.length != sArr2.length) {
                if (sArr.length >= sArr2.length) {
                    i = 1;
                }
                this.a = i;
            } else {
                for (i = 0; i < sArr.length && this.a == 0; i++) {
                    a(sArr[i], sArr2[i]);
                }
            }
        }
        return this;
    }

    public a a(char[] cArr, char[] cArr2) {
        int i = -1;
        if (this.a == 0 && cArr != cArr2) {
            if (cArr == null) {
                this.a = -1;
            } else if (cArr2 == null) {
                this.a = 1;
            } else if (cArr.length != cArr2.length) {
                if (cArr.length >= cArr2.length) {
                    i = 1;
                }
                this.a = i;
            } else {
                for (i = 0; i < cArr.length && this.a == 0; i++) {
                    a(cArr[i], cArr2[i]);
                }
            }
        }
        return this;
    }

    public a a(byte[] bArr, byte[] bArr2) {
        int i = -1;
        if (this.a == 0 && bArr != bArr2) {
            if (bArr == null) {
                this.a = -1;
            } else if (bArr2 == null) {
                this.a = 1;
            } else if (bArr.length != bArr2.length) {
                if (bArr.length >= bArr2.length) {
                    i = 1;
                }
                this.a = i;
            } else {
                for (i = 0; i < bArr.length && this.a == 0; i++) {
                    a(bArr[i], bArr2[i]);
                }
            }
        }
        return this;
    }

    public a a(double[] dArr, double[] dArr2) {
        int i = -1;
        if (this.a == 0 && dArr != dArr2) {
            if (dArr == null) {
                this.a = -1;
            } else if (dArr2 == null) {
                this.a = 1;
            } else if (dArr.length != dArr2.length) {
                if (dArr.length >= dArr2.length) {
                    i = 1;
                }
                this.a = i;
            } else {
                for (i = 0; i < dArr.length && this.a == 0; i++) {
                    a(dArr[i], dArr2[i]);
                }
            }
        }
        return this;
    }

    public a a(float[] fArr, float[] fArr2) {
        int i = -1;
        if (this.a == 0 && fArr != fArr2) {
            if (fArr == null) {
                this.a = -1;
            } else if (fArr2 == null) {
                this.a = 1;
            } else if (fArr.length != fArr2.length) {
                if (fArr.length >= fArr2.length) {
                    i = 1;
                }
                this.a = i;
            } else {
                for (i = 0; i < fArr.length && this.a == 0; i++) {
                    a(fArr[i], fArr2[i]);
                }
            }
        }
        return this;
    }

    public a a(boolean[] zArr, boolean[] zArr2) {
        int i = -1;
        if (this.a == 0 && zArr != zArr2) {
            if (zArr == null) {
                this.a = -1;
            } else if (zArr2 == null) {
                this.a = 1;
            } else if (zArr.length != zArr2.length) {
                if (zArr.length >= zArr2.length) {
                    i = 1;
                }
                this.a = i;
            } else {
                for (i = 0; i < zArr.length && this.a == 0; i++) {
                    a(zArr[i], zArr2[i]);
                }
            }
        }
        return this;
    }

    public int a() {
        return this.a;
    }
}
