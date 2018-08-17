package com.unionpay.c;

import com.umeng.analytics.pro.j;
import java.io.OutputStream;

final class aq {
    protected byte[] a = new byte[9];
    protected OutputStream b;

    aq(OutputStream outputStream) {
        this.b = outputStream;
    }

    static int b(long j) {
        return j < -32 ? j < -32768 ? j < -2147483648L ? 9 : 5 : j < -128 ? 3 : 2 : j < 128 ? 1 : j < 65536 ? j < 256 ? 2 : 3 : j < 4294967296L ? 5 : 9;
    }

    static int b(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            return bytes.length + c(bytes.length);
        } catch (Exception e) {
            return 0;
        }
    }

    static int c(int i) {
        return i < 16 ? 1 : i < 65536 ? 3 : 5;
    }

    private aq c(String str) {
        byte[] bytes = str.getBytes("UTF-8");
        e(bytes.length);
        return a(bytes);
    }

    final aq a() {
        this.b.write(-64);
        return this;
    }

    final aq a(double d) {
        this.a[0] = (byte) -53;
        long doubleToRawLongBits = Double.doubleToRawLongBits(d);
        this.a[1] = (byte) ((int) (doubleToRawLongBits >> 56));
        this.a[2] = (byte) ((int) (doubleToRawLongBits >> 48));
        this.a[3] = (byte) ((int) (doubleToRawLongBits >> 40));
        this.a[4] = (byte) ((int) (doubleToRawLongBits >> 32));
        this.a[5] = (byte) ((int) (doubleToRawLongBits >> 24));
        this.a[6] = (byte) ((int) (doubleToRawLongBits >> 16));
        this.a[7] = (byte) ((int) (doubleToRawLongBits >> 8));
        this.a[8] = (byte) ((int) (doubleToRawLongBits >> 0));
        this.b.write(this.a, 0, 9);
        return this;
    }

    final aq a(float f) {
        this.a[0] = (byte) -54;
        int floatToRawIntBits = Float.floatToRawIntBits(f);
        this.a[1] = (byte) (floatToRawIntBits >> 24);
        this.a[2] = (byte) (floatToRawIntBits >> 16);
        this.a[3] = (byte) (floatToRawIntBits >> 8);
        this.a[4] = (byte) (floatToRawIntBits >> 0);
        this.b.write(this.a, 0, 5);
        return this;
    }

    final aq a(int i) {
        if (i < -32) {
            if (i < -32768) {
                this.a[0] = (byte) -46;
                this.a[1] = (byte) (i >> 24);
                this.a[2] = (byte) (i >> 16);
                this.a[3] = (byte) (i >> 8);
                this.a[4] = (byte) (i >> 0);
                this.b.write(this.a, 0, 5);
            } else if (i < -128) {
                this.a[0] = (byte) -47;
                this.a[1] = (byte) (i >> 8);
                this.a[2] = (byte) (i >> 0);
                this.b.write(this.a, 0, 3);
            } else {
                this.a[0] = (byte) -48;
                this.a[1] = (byte) i;
                this.b.write(this.a, 0, 2);
            }
        } else if (i < 128) {
            this.b.write((byte) i);
        } else if (i < 256) {
            this.a[0] = (byte) -52;
            this.a[1] = (byte) i;
            this.b.write(this.a, 0, 2);
        } else if (i < 65536) {
            this.a[0] = (byte) -51;
            this.a[1] = (byte) (i >> 8);
            this.a[2] = (byte) (i >> 0);
            this.b.write(this.a, 0, 3);
        } else {
            this.a[0] = (byte) -50;
            this.a[1] = (byte) (i >> 24);
            this.a[2] = (byte) (i >> 16);
            this.a[3] = (byte) (i >> 8);
            this.a[4] = (byte) (i >> 0);
            this.b.write(this.a, 0, 5);
        }
        return this;
    }

    final aq a(long j) {
        if (j < -32) {
            if (j < -32768) {
                if (j < -2147483648L) {
                    this.a[0] = (byte) -45;
                    this.a[1] = (byte) ((int) (j >> 56));
                    this.a[2] = (byte) ((int) (j >> 48));
                    this.a[3] = (byte) ((int) (j >> 40));
                    this.a[4] = (byte) ((int) (j >> 32));
                    this.a[5] = (byte) ((int) (j >> 24));
                    this.a[6] = (byte) ((int) (j >> 16));
                    this.a[7] = (byte) ((int) (j >> 8));
                    this.a[8] = (byte) ((int) (j >> 0));
                    this.b.write(this.a, 0, 9);
                } else {
                    this.a[0] = (byte) -46;
                    this.a[1] = (byte) ((int) (j >> 24));
                    this.a[2] = (byte) ((int) (j >> 16));
                    this.a[3] = (byte) ((int) (j >> 8));
                    this.a[4] = (byte) ((int) (j >> 0));
                    this.b.write(this.a, 0, 5);
                }
            } else if (j < -128) {
                this.a[0] = (byte) -47;
                this.a[1] = (byte) ((int) (j >> 8));
                this.a[2] = (byte) ((int) (j >> 0));
                this.b.write(this.a, 0, 3);
            } else {
                this.a[0] = (byte) -48;
                this.a[1] = (byte) ((int) j);
                this.b.write(this.a, 0, 2);
            }
        } else if (j < 128) {
            this.b.write((byte) ((int) j));
        } else if (j < 65536) {
            if (j < 256) {
                this.a[0] = (byte) -52;
                this.a[1] = (byte) ((int) j);
                this.b.write(this.a, 0, 2);
            } else {
                this.a[0] = (byte) -51;
                this.a[1] = (byte) ((int) ((65280 & j) >> 8));
                this.a[2] = (byte) ((int) ((255 & j) >> 0));
                this.b.write(this.a, 0, 3);
            }
        } else if (j < 4294967296L) {
            this.a[0] = (byte) -50;
            this.a[1] = (byte) ((int) ((-16777216 & j) >> 24));
            this.a[2] = (byte) ((int) ((16711680 & j) >> 16));
            this.a[3] = (byte) ((int) ((65280 & j) >> 8));
            this.a[4] = (byte) ((int) ((255 & j) >> 0));
            this.b.write(this.a, 0, 5);
        } else {
            this.a[0] = (byte) -49;
            this.a[1] = (byte) ((int) (j >> 56));
            this.a[2] = (byte) ((int) (j >> 48));
            this.a[3] = (byte) ((int) (j >> 40));
            this.a[4] = (byte) ((int) (j >> 32));
            this.a[5] = (byte) ((int) (j >> 24));
            this.a[6] = (byte) ((int) (j >> 16));
            this.a[7] = (byte) ((int) (j >> 8));
            this.a[8] = (byte) ((int) (j >> 0));
            this.b.write(this.a, 0, 9);
        }
        return this;
    }

    final aq a(ap apVar) {
        if (apVar == null) {
            return a();
        }
        apVar.a(this);
        return this;
    }

    final aq a(String str) {
        return str == null ? c("") : c(str);
    }

    final aq a(boolean z) {
        if (z) {
            this.b.write(-61);
        } else {
            this.b.write(-62);
        }
        return this;
    }

    final aq a(byte[] bArr) {
        this.b.write(bArr);
        return this;
    }

    final aq b(int i) {
        if (i < 16) {
            this.b.write((byte) (i | 144));
        } else if (i < 65536) {
            this.a[0] = (byte) -36;
            this.a[1] = (byte) (i >> 8);
            this.a[2] = (byte) (i >> 0);
            this.b.write(this.a, 0, 3);
        } else {
            this.a[0] = (byte) -35;
            this.a[1] = (byte) (i >> 24);
            this.a[2] = (byte) (i >> 16);
            this.a[3] = (byte) (i >> 8);
            this.a[4] = (byte) (i >> 0);
            this.b.write(this.a, 0, 5);
        }
        return this;
    }

    final aq d(int i) {
        if (i < 16) {
            this.b.write((byte) (i | 128));
        } else if (i < 65536) {
            this.a[0] = (byte) -34;
            this.a[1] = (byte) (i >> 8);
            this.a[2] = (byte) (i >> 0);
            this.b.write(this.a, 0, 3);
        } else {
            this.a[0] = (byte) -33;
            this.a[1] = (byte) (i >> 24);
            this.a[2] = (byte) (i >> 16);
            this.a[3] = (byte) (i >> 8);
            this.a[4] = (byte) (i >> 0);
            this.b.write(this.a, 0, 5);
        }
        return this;
    }

    final aq e(int i) {
        if (i < 32) {
            this.b.write((byte) (i | j.b));
        } else if (i < 65536) {
            this.a[0] = (byte) -38;
            this.a[1] = (byte) (i >> 8);
            this.a[2] = (byte) (i >> 0);
            this.b.write(this.a, 0, 3);
        } else {
            this.a[0] = (byte) -37;
            this.a[1] = (byte) (i >> 24);
            this.a[2] = (byte) (i >> 16);
            this.a[3] = (byte) (i >> 8);
            this.a[4] = (byte) (i >> 0);
            this.b.write(this.a, 0, 5);
        }
        return this;
    }
}
