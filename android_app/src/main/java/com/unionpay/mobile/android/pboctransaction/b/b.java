package com.unionpay.mobile.android.pboctransaction.b;

import android.nfc.tech.IsoDep;
import android.util.Log;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class b {
    public static final byte[] a = new byte[]{(byte) 0};
    protected byte[] b;

    public static final class a extends b {
        public static final byte[] c = new byte[0];
        public static final byte[] d = new byte[]{(byte) 111, (byte) 0};

        public a(byte[] bArr) {
            if (bArr == null || bArr.length < 2) {
                bArr = d;
            }
            super(bArr);
        }

        public final byte[] a() {
            return b() ? Arrays.copyOfRange(this.b, 0, this.b.length - 2) : c;
        }

        public final boolean b() {
            byte[] bArr = this.b;
            int length = bArr.length;
            return ((short) ((bArr[length + -1] & 255) | (bArr[length + -2] << 8))) == (short) -28672;
        }
    }

    public static final class b {
        private final IsoDep a;

        public b(IsoDep isoDep) {
            this.a = isoDep;
        }

        public static String c(byte[] bArr) {
            String str = "";
            for (byte b : bArr) {
                String toHexString = Integer.toHexString(b & 255);
                if (toHexString.length() == 1) {
                    toHexString = "0" + toHexString;
                }
                str = str + toHexString.toUpperCase() + " ";
            }
            return str;
        }

        public final a a(byte... bArr) {
            ByteBuffer allocate = ByteBuffer.allocate(bArr.length + 6);
            allocate.put((byte) 0).put((byte) -92).put((byte) 4).put((byte) 0).put((byte) bArr.length).put(bArr).put((byte) 0);
            Log.e("PBOC Transceive", c(allocate.array()));
            a aVar = new a(b(allocate.array()));
            Log.e("PBOC receive", c(aVar.a()));
            return aVar;
        }

        public final void a() {
            try {
                this.a.connect();
            } catch (Exception e) {
            }
        }

        public final byte[] b(byte[] bArr) {
            try {
                return this.a.transceive(bArr);
            } catch (Exception e) {
                return a.d;
            }
        }
    }

    protected b(byte[] bArr) {
        if (bArr == null) {
            bArr = a;
        }
        this.b = bArr;
    }

    public byte[] a() {
        return this.b;
    }

    public String toString() {
        return c.a(this.b, this.b.length);
    }
}
