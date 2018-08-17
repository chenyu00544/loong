package org.android.agoo.common;

import anet.channel.security.ISecurity;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: Taobao */
public final class c {
    private static byte[] a = new byte[]{(byte) 82, (byte) 22, (byte) 50, (byte) 44, (byte) -16, (byte) 124, (byte) -40, (byte) -114, (byte) -87, (byte) -40, (byte) 37, (byte) 23, (byte) -56, (byte) 23, (byte) -33, (byte) 75};
    private static ThreadLocal<Cipher> b = new ThreadLocal();
    private static final AlgorithmParameterSpec c = new IvParameterSpec(a);
    private static final SecureRandom d = new SecureRandom();

    public static final byte[] a(byte[] bArr, SecretKeySpec secretKeySpec, byte[] bArr2) throws IllegalArgumentException {
        try {
            return a(secretKeySpec, bArr2, 2).doFinal(bArr);
        } catch (Throwable e) {
            throw new IllegalArgumentException("AES decrypt error:" + e.getMessage(), e);
        } catch (Throwable e2) {
            throw new IllegalArgumentException("AES decrypt error:" + e2.getMessage(), e2);
        }
    }

    private static final Cipher a(SecretKeySpec secretKeySpec, byte[] bArr, int i) {
        Cipher a = a();
        try {
            a.init(i, secretKeySpec, new IvParameterSpec(bArr), d);
            return a;
        } catch (Throwable e) {
            throw new RuntimeException("init Chipher error:" + e.getMessage(), e);
        } catch (Throwable e2) {
            throw new RuntimeException("init Chipher error:" + e2.getMessage(), e2);
        } catch (Throwable e22) {
            throw new RuntimeException("init Chipher error:" + e22.getMessage(), e22);
        }
    }

    private static final Cipher a() {
        Cipher cipher = (Cipher) b.get();
        if (cipher == null) {
            try {
                cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                b.set(cipher);
            } catch (Throwable e) {
                throw new RuntimeException("get Chipher error:" + e.getMessage(), e);
            } catch (Throwable e2) {
                throw new RuntimeException("get Chipher error:" + e2.getMessage(), e2);
            }
        }
        return cipher;
    }

    public static final byte[] a(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5);
            instance.update(bArr);
            return instance.digest();
        } catch (Throwable th) {
            RuntimeException runtimeException = new RuntimeException("md5 value Throwable", th);
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        Key secretKeySpec = new SecretKeySpec(bArr, "HmacSHA1");
        try {
            Mac instance = Mac.getInstance("HmacSHA1");
            instance.init(secretKeySpec);
            return instance.doFinal(bArr2);
        } catch (Throwable th) {
            RuntimeException runtimeException = new RuntimeException("HmacSHA1 Throwable", th);
        }
    }

    public static byte[] a(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }
}
