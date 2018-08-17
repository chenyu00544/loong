package com.umeng.analytics.pro;

import java.util.BitSet;

/* compiled from: TTupleProtocol */
public final class dj extends cx {

    /* compiled from: TTupleProtocol */
    public static class a implements df {
        public dd a(dr drVar) {
            return new dj(drVar);
        }
    }

    public dj(dr drVar) {
        super(drVar);
    }

    public Class<? extends dl> D() {
        return do.class;
    }

    public void a(BitSet bitSet, int i) throws ck {
        for (byte a : b(bitSet, i)) {
            a(a);
        }
    }

    public BitSet b(int i) throws ck {
        int ceil = (int) Math.ceil(((double) i) / 8.0d);
        byte[] bArr = new byte[ceil];
        for (int i2 = 0; i2 < ceil; i2++) {
            bArr[i2] = u();
        }
        return a(bArr);
    }

    public static BitSet a(byte[] bArr) {
        BitSet bitSet = new BitSet();
        for (int i = 0; i < bArr.length * 8; i++) {
            if ((bArr[(bArr.length - (i / 8)) - 1] & (1 << (i % 8))) > 0) {
                bitSet.set(i);
            }
        }
        return bitSet;
    }

    public static byte[] b(BitSet bitSet, int i) {
        byte[] bArr = new byte[((int) Math.ceil(((double) i) / 8.0d))];
        for (int i2 = 0; i2 < bitSet.length(); i2++) {
            if (bitSet.get(i2)) {
                int length = (bArr.length - (i2 / 8)) - 1;
                bArr[length] = (byte) (bArr[length] | (1 << (i2 % 8)));
            }
        }
        return bArr;
    }
}
