package com.ecjia.a;

import android.content.Context;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import anet.channel.strategy.dispatch.c;
import com.ecjia.hamster.model.ECJia_DEVICE;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/* compiled from: ECJiaDeviceInfoUtil */
public class h {
    public static String a(Context context) {
        MessageDigest instance;
        NoSuchAlgorithmException e;
        byte[] digest;
        StringBuffer stringBuffer;
        int i;
        String toLowerCase;
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        String uuid = new UUID((long) ("" + Secure.getString(context.getContentResolver(), "android_id")).hashCode(), ((long) ("" + telephonyManager.getSimSerialNumber()).hashCode()) | (((long) ("" + telephonyManager.getDeviceId()).hashCode()) << 32)).toString();
        try {
            instance = MessageDigest.getInstance("SHA-1");
            try {
                instance.update(uuid.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e2) {
                try {
                    e2.printStackTrace();
                } catch (NoSuchAlgorithmException e3) {
                    e = e3;
                    e.printStackTrace();
                    digest = instance.digest();
                    stringBuffer = new StringBuffer();
                    for (byte b : digest) {
                        i = b & 255;
                        if (i >= 15) {
                            stringBuffer.append(0);
                        }
                        stringBuffer.append(Integer.toHexString(i));
                    }
                    toLowerCase = stringBuffer.toString().toLowerCase();
                    q.a("UDID的值==============" + toLowerCase);
                    System.out.println(stringBuffer.toString().toUpperCase());
                    q.a("sha1加密==============" + toLowerCase);
                    return toLowerCase;
                }
            }
        } catch (NoSuchAlgorithmException e4) {
            NoSuchAlgorithmException noSuchAlgorithmException = e4;
            instance = null;
            e = noSuchAlgorithmException;
            e.printStackTrace();
            digest = instance.digest();
            stringBuffer = new StringBuffer();
            while (r0 < r4) {
                i = b & 255;
                if (i >= 15) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            toLowerCase = stringBuffer.toString().toLowerCase();
            q.a("UDID的值==============" + toLowerCase);
            System.out.println(stringBuffer.toString().toUpperCase());
            q.a("sha1加密==============" + toLowerCase);
            return toLowerCase;
        }
        digest = instance.digest();
        stringBuffer = new StringBuffer();
        while (r0 < r4) {
            i = b & 255;
            if (i >= 15) {
                stringBuffer.append(0);
            }
            stringBuffer.append(Integer.toHexString(i));
        }
        toLowerCase = stringBuffer.toString().toLowerCase();
        q.a("UDID的值==============" + toLowerCase);
        System.out.println(stringBuffer.toString().toUpperCase());
        q.a("sha1加密==============" + toLowerCase);
        return toLowerCase;
    }

    public static void b(Context context) {
        Object eCJia_DEVICE = new ECJia_DEVICE();
        eCJia_DEVICE.setClient(c.ANDROID);
        eCJia_DEVICE.setCode("4001");
        eCJia_DEVICE.setUdid(a(context));
        aa.a(context, "deviceInfo", "device", eCJia_DEVICE);
    }
}
