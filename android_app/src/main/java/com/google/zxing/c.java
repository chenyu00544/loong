package com.google.zxing;

/* compiled from: LuminanceSource */
public abstract class c {
    private final int a;
    private final int b;

    public abstract byte[] a();

    public abstract byte[] a(int i, byte[] bArr);

    protected c(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public final int b() {
        return this.a;
    }

    public final int c() {
        return this.b;
    }

    public boolean d() {
        return false;
    }

    public c e() {
        throw new UnsupportedOperationException("This luminance source does not support rotation by 90 degrees.");
    }

    public final String toString() {
        byte[] bArr = new byte[this.a];
        StringBuilder stringBuilder = new StringBuilder(this.b * (this.a + 1));
        byte[] bArr2 = bArr;
        for (int i = 0; i < this.b; i++) {
            bArr2 = a(i, bArr2);
            for (int i2 = 0; i2 < this.a; i2++) {
                char c;
                int i3 = bArr2[i2] & 255;
                if (i3 < 64) {
                    c = '#';
                } else if (i3 < 128) {
                    c = '+';
                } else if (i3 < 192) {
                    c = '.';
                } else {
                    c = ' ';
                }
                stringBuilder.append(c);
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
