package com.ta.utdid2.android.utils;

import android.content.Context;
import android.telephony.TelephonyManager;
import java.util.Random;

public class PhoneInfoUtils {
    public static final String getUniqueID() {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        int nanoTime = (int) System.nanoTime();
        int nextInt = new Random().nextInt();
        int nextInt2 = new Random().nextInt();
        Object bytes = IntUtils.getBytes(currentTimeMillis);
        Object bytes2 = IntUtils.getBytes(nanoTime);
        Object bytes3 = IntUtils.getBytes(nextInt);
        Object bytes4 = IntUtils.getBytes(nextInt2);
        Object obj = new byte[16];
        System.arraycopy(bytes, 0, obj, 0, 4);
        System.arraycopy(bytes2, 0, obj, 4, 4);
        System.arraycopy(bytes3, 0, obj, 8, 4);
        System.arraycopy(bytes4, 0, obj, 12, 4);
        return Base64.encodeToString(obj, 2);
    }

    public static String getImei(Context context) {
        String deviceId;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    deviceId = telephonyManager.getDeviceId();
                    if (StringUtils.isEmpty(deviceId)) {
                        return deviceId;
                    }
                    return getUniqueID();
                }
            } catch (Exception e) {
                deviceId = null;
            }
        }
        deviceId = null;
        if (StringUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        return getUniqueID();
    }

    public static String getImsi(Context context) {
        String subscriberId;
        if (context != null) {
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                if (telephonyManager != null) {
                    subscriberId = telephonyManager.getSubscriberId();
                    if (StringUtils.isEmpty(subscriberId)) {
                        return subscriberId;
                    }
                    return getUniqueID();
                }
            } catch (Exception e) {
                subscriberId = null;
            }
        }
        subscriberId = null;
        if (StringUtils.isEmpty(subscriberId)) {
            return subscriberId;
        }
        return getUniqueID();
    }
}
