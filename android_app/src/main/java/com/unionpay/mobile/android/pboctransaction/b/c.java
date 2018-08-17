package com.unionpay.mobile.android.pboctransaction.b;

public final class c {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String a(byte[] bArr, int i) {
        int i2 = 0;
        char[] cArr = new char[(i * 2)];
        int i3 = i + 0;
        int i4 = 0;
        while (i2 < i3) {
            byte b = bArr[i2];
            int i5 = i4 + 1;
            cArr[i4] = a[(b >> 4) & 15];
            i4 = i5 + 1;
            cArr[i5] = a[b & 15];
            i2++;
        }
        return new String(cArr);
    }
}
