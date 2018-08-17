package com.unionpay.mobile.android.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.taobao.accs.utl.UtilityImpl;
import com.unionpay.mobile.android.d.a;
import com.unionpay.mobile.android.f.c;
import java.io.File;
import java.net.NetworkInterface;
import java.util.Locale;
import java.util.TimeZone;

public final class f {
    public static String a() {
        return Locale.getDefault().toString().startsWith("zh") ? "zh_CN" : "en_US";
    }

    public static String a(Context context) {
        Activity activity = (Activity) context;
        try {
            String str = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 4160).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return c.bD.a;
    }

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

    public static String b() {
        return new File("/system/bin/su").exists() ? "1" : "0";
    }

    public static String b(Context context) {
        String packageName = ((Activity) context).getPackageName();
        return packageName != null ? packageName : "";
    }

    public static String c() {
        String trim = Build.MODEL.trim();
        if (trim != null) {
            trim.replace(" ", "");
        }
        return trim;
    }

    public static final String c(Context context) {
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

    public static String d() {
        return (a.I + "*" + a.t).trim();
    }

    public static String d(Context context) {
        String c;
        try {
            c = (new File("/system/bin/su").exists() ? 1 : null) != null ? c(context) : ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        } catch (Exception e) {
            c = "";
        }
        if (c == null || c.length() == 0) {
            c = PreferenceUtils.a(context);
        }
        k.a("uppay", "user=" + c);
        return c;
    }

    public static String e() {
        String str = "";
        try {
            Class cls = Class.forName("android.os.SystemProperties");
            Object newInstance = cls.newInstance();
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(newInstance, new Object[]{"gsm.version.baseband", "no message"});
        } catch (Exception e) {
            return str;
        }
    }

    public static String e(Context context) {
        try {
            String subscriberId = ((TelephonyManager) context.getSystemService("phone")).getSubscriberId();
            return subscriberId == null ? "" : subscriberId;
        } catch (Exception e) {
            return "";
        }
    }

    public static String f() {
        return TimeZone.getDefault().getDisplayName(false, 0);
    }

    public static String f(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo == null ? "disConnect" : activeNetworkInfo.isAvailable() ? activeNetworkInfo.getType() == 0 ? activeNetworkInfo.getState() == State.CONNECTED ? "mobile:" + activeNetworkInfo.getExtraInfo() : "mobile" : activeNetworkInfo.getType() == 1 ? UtilityImpl.NET_TYPE_WIFI : anet.channel.strategy.dispatch.c.OTHER : "disConnect";
    }

    public static Location g(Context context) {
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

    public static String h(Context context) {
        try {
            return ((TelephonyManager) context.getSystemService("phone")).getLine1Number();
        } catch (Exception e) {
            return "";
        }
    }
}
