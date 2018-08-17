package com.unionpay.utils;

import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public final class c {
    private static byte[] a(int i, byte[] bArr, byte[] bArr2) {
        if (bArr == null || !(bArr.length == 8 || bArr.length == 16 || bArr.length == 24)) {
            throw new IllegalArgumentException();
        } else if (bArr2 == null) {
            throw new IllegalArgumentException();
        } else {
            Cipher instance = Cipher.getInstance("DESede/ECB/NoPadding");
            Object obj = new byte[24];
            if (bArr.length == 8) {
                System.arraycopy(bArr, 0, obj, 0, 8);
                System.arraycopy(bArr, 0, obj, 8, 8);
                System.arraycopy(bArr, 0, obj, 16, 8);
            } else if (bArr.length == 16) {
                System.arraycopy(bArr, 0, obj, 0, 16);
                System.arraycopy(bArr, 0, obj, 16, 8);
            } else {
                System.arraycopy(bArr, 0, obj, 0, 24);
            }
            if (bArr2.length % 8 != 0) {
                Object obj2 = new byte[(((bArr2.length / 8) + 1) * 8)];
                System.arraycopy(bArr2, 0, obj2, 0, bArr2.length);
                Arrays.fill(obj2, bArr2.length, obj2.length, (byte) 0);
                bArr2 = obj2;
            }
            instance.init(i != 0 ? 1 : 2, new SecretKeySpec(obj, "DESede"));
            return instance.doFinal(bArr2);
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        return a(1, bArr, bArr2);
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        return a(0, bArr, bArr2);
    }
}
