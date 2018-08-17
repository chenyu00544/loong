package com.unionpay.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.taobao.accs.utl.UtilityImpl;
import java.net.NetworkInterface;

public final class d {
    public static String a() {
        String str = "";
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            Object newInstance = cls.newInstance();
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(newInstance, new Object[]{"gsm.version.baseband", "no message"});
        } catch (Exception e) {
            return str;
        }
    }

    public static final String a(Context context) {
        String macAddress;
        String str = "";
        if (VERSION.SDK_INT < 23) {
            try {
                WifiInfo connectionInfo = ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
                macAddress = (connectionInfo == null || connectionInfo.getMacAddress() == null) ? str : connectionInfo.getMacAddress();
                if (macAddress == null || macAddress.length() == 0) {
                    macAddress = a("wlan0");
                }
            } catch (Exception e) {
            }
            return (macAddress != null || macAddress == "") ? "" : macAddress.replaceAll(":", "");
        }
        macAddress = a("wlan0");
        if (macAddress != null) {
        }
    }

    @SuppressLint({"NewApi"})
    private static String a(String str) {
        try {
            byte[] hardwareAddress = NetworkInterface.getByName(str).getHardwareAddress();
            if (hardwareAddress == null) {
                return "";
            }
            StringBuilder stringBuilder = new StringBuilder();
            int length = hardwareAddress.length;
            for (int i = 0; i < length; i++) {
                stringBuilder.append(String.format("%02x:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
            }
            if (stringBuilder.length() > 0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public static String b(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            return "";
        }
    }

    public static String c(Context context) {
        try {
            String subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
            return subscriberId == null ? "" : subscriberId;
        } catch (Exception e) {
            return "";
        }
    }

    public static Location d(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        try {
            if (!locationManager.isProviderEnabled("gps")) {
                return locationManager.isProviderEnabled("network") ? locationManager.getLastKnownLocation("network") : null;
            } else {
                Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
                if (lastKnownLocation == null) {
                    try {
                        if (locationManager.isProviderEnabled("network")) {
                            return locationManager.getLastKnownLocation("network");
                        }
                    } catch (Exception e) {
                        return lastKnownLocation;
                    }
                }
                return lastKnownLocation;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    public static String e(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getLine1Number();
        } catch (Exception e) {
            return "";
        }
    }
}
