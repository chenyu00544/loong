package com.baidu.platform.comapi.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.telephony.TelephonyManager;
import com.baidu.platform.comjni.engine.AppEngine;
import com.taobao.accs.utl.UtilityImpl;

public class c {
    public static boolean a = false;
    public static String b = "";
    public static int c = 0;

    public static void a(Context context) {
        NetworkInfo b = b(context);
        if (b != null && b.isAvailable()) {
            String toLowerCase = b.getTypeName().toLowerCase();
            if (toLowerCase.equals(UtilityImpl.NET_TYPE_WIFI) && b.isConnected()) {
                AppEngine.SetProxyInfo(null, 0);
                a = false;
            } else if (toLowerCase.equals("mobile") || (toLowerCase.equals(UtilityImpl.NET_TYPE_WIFI) && !a(b))) {
                String extraInfo = b.getExtraInfo();
                a = false;
                if (extraInfo != null) {
                    extraInfo = extraInfo.toLowerCase();
                    if (extraInfo.startsWith("cmwap") || extraInfo.startsWith("uniwap") || extraInfo.startsWith("3gwap")) {
                        b = "10.0.0.172";
                        c = 80;
                        a = true;
                    } else if (extraInfo.startsWith("ctwap")) {
                        b = "10.0.0.200";
                        c = 80;
                        a = true;
                    } else if (extraInfo.startsWith("cmnet") || extraInfo.startsWith("uninet") || extraInfo.startsWith("ctnet") || extraInfo.startsWith("3gnet")) {
                        a = false;
                    }
                } else {
                    extraInfo = Proxy.getDefaultHost();
                    int defaultPort = Proxy.getDefaultPort();
                    if (extraInfo != null && extraInfo.length() > 0) {
                        if ("10.0.0.172".equals(extraInfo.trim())) {
                            b = "10.0.0.172";
                            c = defaultPort;
                            a = true;
                        } else if ("10.0.0.200".equals(extraInfo.trim())) {
                            b = "10.0.0.200";
                            c = 80;
                            a = true;
                        }
                    }
                }
                if (a) {
                    AppEngine.SetProxyInfo(b, c);
                } else {
                    AppEngine.SetProxyInfo(null, 0);
                }
            }
        }
    }

    private static boolean a(NetworkInfo networkInfo) {
        boolean z = true;
        if (networkInfo != null) {
            try {
                if (!(1 == networkInfo.getType() && networkInfo.isConnected())) {
                    z = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        z = false;
        return z;
    }

    public static NetworkInfo b(Context context) {
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            return null;
        }
    }

    public static String c(Context context) {
        int i = 1;
        NetworkInfo b = b(context);
        if (b != null) {
            if (b.getType() != 1) {
                switch (((TelephonyManager) context.getSystemService("phone")).getNetworkType()) {
                    case 1:
                    case 2:
                        i = 6;
                        break;
                    case 3:
                    case 9:
                    case 10:
                    case 15:
                        i = 9;
                        break;
                    case 4:
                        i = 5;
                        break;
                    case 5:
                    case 6:
                    case 7:
                    case 12:
                        i = 7;
                        break;
                    case 8:
                        i = 8;
                        break;
                    case 11:
                        i = 2;
                        break;
                    case 13:
                        i = 4;
                        break;
                    case 14:
                        i = 10;
                        break;
                }
            }
            return Integer.toString(i);
        }
        i = 0;
        return Integer.toString(i);
    }
}
