package com.ecjia.a;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: ECJiaHttpsUtil */
public class n {
    static final /* synthetic */ boolean a = (!n.class.desiredAssertionStatus());

    public static String a(String str) {
        return a(str, "5b09836f2a8d15d3c246f588cb506514");
    }

    private static String a(String str, String str2) {
        GeneralSecurityException e;
        Key secretKeySpec = new SecretKeySpec(str2.getBytes(), "HmacMD5");
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacMD5");
            mac.init(secretKeySpec);
        } catch (NoSuchAlgorithmException e2) {
            e = e2;
            e.printStackTrace();
            if (!a) {
            }
            return a(mac.doFinal(str.getBytes()));
        } catch (InvalidKeyException e3) {
            e = e3;
            e.printStackTrace();
            if (a) {
            }
            return a(mac.doFinal(str.getBytes()));
        }
        if (a || mac != null) {
            return a(mac.doFinal(str.getBytes()));
        }
        throw new AssertionError();
    }

    private static String a(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            if (toHexString.length() == 1) {
                str = str + "0" + toHexString;
            } else {
                str = str + toHexString;
            }
        }
        return str;
    }

    public static String a() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            stringBuilder.append(new Random().nextInt(10));
        }
        return stringBuilder.toString();
    }

    public static String b() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        return (instance.getTimeInMillis() / 1000) + "";
    }
}
