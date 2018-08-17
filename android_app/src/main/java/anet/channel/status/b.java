package anet.channel.status;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Pair;
import anet.channel.status.NetworkStatusHelper.NetworkStatus;
import anet.channel.util.ALog;
import com.taobao.accs.utl.UtilityImpl;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.Locale;

/* compiled from: Taobao */
class b {
    static Context a = null;
    static volatile NetworkStatus b = NetworkStatus.NONE;
    static volatile String c = "unknown";
    static volatile String d = "";
    static volatile String e = "";
    static volatile String f = "";
    static volatile String g = "unknown";
    static volatile Pair<String, Integer> h = null;
    private static volatile boolean i = false;
    private static ConnectivityManager j = null;
    private static TelephonyManager k = null;
    private static WifiManager l = null;
    private static BroadcastReceiver m = new NetworkStatusMonitor$1();

    b() {
    }

    static void a() {
        if (!i && a != null) {
            synchronized (a) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                try {
                    a.registerReceiver(m, intentFilter);
                } catch (Exception e) {
                    ALog.e("awcn.NetworkStatusMonitor", "registerReceiver failed", null, new Object[0]);
                }
            }
            b(a);
        }
    }

    private static void b(Context context) {
        ALog.d("awcn.NetworkStatusMonitor", "[checkNetworkStatus]", null, new Object[0]);
        NetworkStatus networkStatus = b;
        String str = d;
        String str2 = e;
        if (context != null) {
            try {
                NetworkInfo b = b();
                if (b == null || !b.isConnected()) {
                    a(NetworkStatus.NO, "no network");
                    ALog.i("awcn.NetworkStatusMonitor", "checkNetworkStatus", null, "NO NETWORK");
                } else {
                    ALog.i("awcn.NetworkStatusMonitor", "checkNetworkStatus", null, "info.isConnected", Boolean.valueOf(b.isConnected()), "info.isAvailable", Boolean.valueOf(b.isAvailable()));
                    if (b.getType() == 0) {
                        Object subtypeName = b.getSubtypeName();
                        String replace = !TextUtils.isEmpty(subtypeName) ? subtypeName.replace(" ", "") : "";
                        a(a(b.getSubtype(), replace), replace);
                        d = a(b.getExtraInfo());
                        g = b(d);
                    } else if (b.getType() == 1) {
                        a(NetworkStatus.WIFI, UtilityImpl.NET_TYPE_WIFI);
                        WifiInfo c = c();
                        if (c != null) {
                            f = c.getBSSID();
                            e = c.getSSID();
                        }
                        h = d();
                    } else {
                        a(NetworkStatus.NONE, "unknown");
                    }
                }
                if (b != networkStatus || !d.equalsIgnoreCase(str) || !e.equalsIgnoreCase(str2)) {
                    if (ALog.isPrintLog(2)) {
                        NetworkStatusHelper.j();
                    }
                    NetworkStatusHelper.a(b);
                }
            } catch (Throwable e) {
                ALog.e("awcn.NetworkStatusMonitor", "checkNetworkStatus", null, e, new Object[0]);
            }
        }
    }

    private static void a(NetworkStatus networkStatus, String str) {
        b = networkStatus;
        c = str;
        d = "";
        e = "";
        f = "";
        h = null;
        g = "unknown";
    }

    private static NetworkStatus a(int i, String str) {
        switch (i) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return NetworkStatus.G2;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return NetworkStatus.G3;
            case 13:
                return NetworkStatus.G4;
            default:
                if (str.equalsIgnoreCase("TD-SCDMA") || str.equalsIgnoreCase("WCDMA") || str.equalsIgnoreCase("CDMA2000")) {
                    return NetworkStatus.G3;
                }
                return NetworkStatus.NONE;
        }
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "unknown";
        }
        String toLowerCase = str.toLowerCase(Locale.US);
        if (toLowerCase.contains("cmwap")) {
            return "cmwap";
        }
        if (toLowerCase.contains("uniwap")) {
            return "uniwap";
        }
        if (toLowerCase.contains("3gwap")) {
            return "3gwap";
        }
        if (toLowerCase.contains("ctwap")) {
            return "ctwap";
        }
        if (toLowerCase.contains("cmnet")) {
            return "cmnet";
        }
        if (toLowerCase.contains("uninet")) {
            return "uninet";
        }
        if (toLowerCase.contains("3gnet")) {
            return "3gnet";
        }
        if (toLowerCase.contains("ctnet")) {
            return "ctnet";
        }
        return "unknown";
    }

    private static String b(String str) {
        try {
            if (str.startsWith(SocializeProtocolConstants.PROTOCOL_KEY_COMMENT_COUNT)) {
                return NetworkStatusHelper.CHINA_MOBILE;
            }
            if (str.startsWith(SocializeProtocolConstants.PROTOCOL_KEY_COMMENT_TEXT)) {
                return NetworkStatusHelper.CHINA_TELE_COM;
            }
            if (str.startsWith(UtilityImpl.NET_TYPE_3G)) {
                return NetworkStatusHelper.CHINA_UNI_COM;
            }
            if (k == null) {
                k = (TelephonyManager) a.getSystemService("phone");
            }
            String simOperator = k.getSimOperator();
            if (simOperator.startsWith("46000") || simOperator.startsWith("46002")) {
                return NetworkStatusHelper.CHINA_MOBILE;
            }
            if (simOperator.startsWith("46001")) {
                return NetworkStatusHelper.CHINA_UNI_COM;
            }
            if (simOperator.startsWith("46003")) {
                return NetworkStatusHelper.CHINA_TELE_COM;
            }
            return "unknown";
        } catch (Exception e) {
            return "unknown";
        }
    }

    static NetworkInfo b() {
        try {
            if (j == null) {
                j = (ConnectivityManager) a.getSystemService("connectivity");
            }
            return j.getActiveNetworkInfo();
        } catch (Throwable th) {
            ALog.e("awcn.NetworkStatusMonitor", "getNetworkInfo", null, th, new Object[0]);
            return null;
        }
    }

    private static WifiInfo c() {
        try {
            if (l == null) {
                l = (WifiManager) a.getSystemService(UtilityImpl.NET_TYPE_WIFI);
            }
            return l.getConnectionInfo();
        } catch (Throwable th) {
            ALog.e("awcn.NetworkStatusMonitor", "getWifiInfo", null, th, new Object[0]);
            return null;
        }
    }

    private static Pair<String, Integer> d() {
        try {
            CharSequence property = System.getProperty("http.proxyHost");
            if (!TextUtils.isEmpty(property)) {
                return Pair.create(property, Integer.valueOf(Integer.parseInt(System.getProperty("http.proxyPort"))));
            }
        } catch (NumberFormatException e) {
        }
        return null;
    }
}
