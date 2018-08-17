package com.unionpay.mobile.android.e;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public final class a {
    public static String a(String str, String str2) {
        try {
            byte[] bArr;
            byte[] bytes = str2.getBytes("UTF-8");
            if (str == null || str.equals("")) {
                bArr = null;
            } else {
                String toUpperCase = str.toUpperCase();
                int length = toUpperCase.length() / 2;
                char[] toCharArray = toUpperCase.toCharArray();
                bArr = new byte[length];
                for (int i = 0; i < length; i++) {
                    int i2 = i * 2;
                    bArr[i] = (byte) (((byte) "0123456789ABCDEF".indexOf(toCharArray[i2 + 1])) | (((byte) "0123456789ABCDEF".indexOf(toCharArray[i2])) << 4));
                }
            }
            Key generateSecret = SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec(bytes));
            Cipher instance = Cipher.getInstance("DESEDE/ECB/PKCS5Padding");
            instance.init(2, generateSecret);
            byte[] doFinal = instance.doFinal(bArr);
            return doFinal != null ? new String(doFinal, "UTF-8") : "";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }

    public static String b(String str, String str2) {
        byte[] bArr = null;
        try {
            byte[] bytes = str2.getBytes("UTF-8");
            byte[] bytes2 = str.getBytes();
            Key generateSecret = SecretKeyFactory.getInstance("desede").generateSecret(new DESedeKeySpec(bytes));
            Cipher instance = Cipher.getInstance("desede/ECB/PKCS5Padding");
            instance.init(1, generateSecret);
            bArr = instance.doFinal(bytes2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a(bArr);
    }
}
