package com.ta.utdid2.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.analytics.pro.bt;
import com.umeng.message.MsgConstant;

public class NetworkUtils {
    public static final String DEFAULT_WIFI_ADDRESS = "00-00-00-00-00-00";
    private static final String TAG = "NetworkUtils";
    private static final int[] WEAK_NETWORK_GROUP = new int[]{4, 7, 2, 1};
    public static final String WIFI = "Wi-Fi";
    private static ConnectivityManager sConnManager = null;

    public static boolean isConnected(Context context) {
        ConnectivityManager connManager = getConnManager(context);
        if (connManager != null) {
            try {
                NetworkInfo activeNetworkInfo = connManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    return activeNetworkInfo.isConnected();
                }
            } catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        } else {
            Log.e(TAG, "connManager is null!");
        }
        return false;
    }

    public static boolean isConnectedToWeakNetwork(Context context) {
        ConnectivityManager connManager = getConnManager(context);
        if (connManager != null) {
            try {
                NetworkInfo activeNetworkInfo = connManager.getActiveNetworkInfo();
                if (activeNetworkInfo != null) {
                    int subtype = activeNetworkInfo.getSubtype();
                    if (DebugUtils.DBG) {
                        Log.d(TAG, "subType:" + subtype + ": name:" + activeNetworkInfo.getSubtypeName());
                    }
                    for (int i : WEAK_NETWORK_GROUP) {
                        if (i == subtype) {
                            return true;
                        }
                    }
                    return false;
                }
                Log.e(TAG, "networkInfo is null!");
                return false;
            } catch (Exception e) {
                Log.e(TAG, e.toString());
                return false;
            }
        }
        Log.e(TAG, "connManager is null!");
        return false;
    }

    public static ConnectivityManager getConnManager(Context context) {
        if (context == null) {
            Log.e(TAG, "context is null!");
            return null;
        }
        if (sConnManager == null) {
            sConnManager = (ConnectivityManager) context.getSystemService("connectivity");
        }
        return sConnManager;
    }

    public static String[] getNetworkState(Context context) {
        String[] strArr = new String[]{"Unknown", "Unknown"};
        try {
            if (context.getPackageManager().checkPermission(MsgConstant.PERMISSION_ACCESS_NETWORK_STATE, context.getPackageName()) != 0) {
                strArr[0] = "Unknown";
                return strArr;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager == null) {
                strArr[0] = "Unknown";
                return strArr;
            }
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            if (networkInfo == null || networkInfo.getState() != State.CONNECTED) {
                NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
                if (networkInfo2 != null && networkInfo2.getState() == State.CONNECTED) {
                    strArr[0] = bt.c;
                    strArr[1] = networkInfo2.getSubtypeName();
                    return strArr;
                }
                return strArr;
            }
            strArr[0] = "Wi-Fi";
            return strArr;
        } catch (Exception e) {
        }
    }

    public static String getWifiAddress(Context context) {
        if (context == null) {
            return DEFAULT_WIFI_ADDRESS;
        }
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
        if (connectionInfo == null) {
            return DEFAULT_WIFI_ADDRESS;
        }
        String macAddress = connectionInfo.getMacAddress();
        if (StringUtils.isEmpty(macAddress)) {
            return DEFAULT_WIFI_ADDRESS;
        }
        return macAddress;
    }

    private static String _convertIntToIp(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    public static String getWifiIpAddress(Context context) {
        if (context != null) {
            try {
                WifiInfo connectionInfo = ((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).getConnectionInfo();
                if (connectionInfo != null) {
                    return _convertIntToIp(connectionInfo.getIpAddress());
                }
                return null;
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static boolean isWifi(Context context) {
        if (context == null) {
            return false;
        }
        try {
            if (getNetworkState(context)[0].equals("Wi-Fi")) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
