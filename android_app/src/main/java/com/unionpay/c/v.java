package com.unionpay.c;

import android.util.Base64;
import java.security.Key;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

final class v {
    private static final byte[] a = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};

    static String a(String str) {
        try {
            Key c = c(d.a(d.c));
            byte[] bArr = a;
            byte[] bytes = str.getBytes("UTF-8");
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
            instance.init(1, c, new IvParameterSpec(bArr));
            return Base64.encodeToString(instance.doFinal(bytes), 2);
        } catch (Throwable th) {
            return null;
        }
    }

    static String b(String str) {
        try {
            String a = z.a();
            Key c = a == null ? c(d.a(d.c)) : new SecretKeySpec(Base64.decode(a, 2), "AES");
            byte[] decode = Base64.decode(str, 2);
            byte[] bArr = a;
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
            instance.init(2, c, new IvParameterSpec(bArr));
            return new String(instance.doFinal(decode), "UTF-8");
        } catch (Throwable th) {
            return null;
        }
    }

    private static SecretKeySpec c(String str) {
        String a = z.a();
        if (a != null) {
            return new SecretKeySpec(Base64.decode(a, 2), "AES");
        }
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        byte[] bytes = str.getBytes("UTF-8");
        instance.update(bytes, 0, bytes.length);
        bytes = instance.digest();
        String encodeToString = Base64.encodeToString(bytes, 2);
        if (d.c != null) {
            aj.a(d.c, "UPpref_longtime", "UPaes_key", encodeToString);
        }
        return new SecretKeySpec(bytes, "AES");
    }
}
