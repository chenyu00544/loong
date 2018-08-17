package anet.channel.status;

import android.content.Context;
import android.net.NetworkInfo;
import android.util.Pair;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.util.ALog;
import com.baidu.location.h.c;
import com.taobao.accs.utl.BaseMonitor;
import java.util.concurrent.CopyOnWriteArraySet;

/* compiled from: Taobao */
public class NetworkStatusHelper {
    public static final String CHINA_MOBILE = "中国移动";
    public static final String CHINA_TELE_COM = "中国电信";
    public static final String CHINA_UNI_COM = "中国联通";
    private static CopyOnWriteArraySet<INetworkStatusChangeListener> a = new CopyOnWriteArraySet();

    /* compiled from: Taobao */
    public interface INetworkStatusChangeListener {
        void onNetworkStatusChanged(NetworkStatus networkStatus);
    }

    /* compiled from: Taobao */
    public enum NetworkStatus {
        NONE,
        NO,
        G2,
        G3,
        G4,
        WIFI;

        public boolean isMobile() {
            return this == G2 || this == G3 || this == G4;
        }

        public boolean isWifi() {
            return this == WIFI;
        }

        public String getType() {
            if (this == G2) {
                return c.h;
            }
            if (this == G3) {
                return c.c;
            }
            if (this == G4) {
                return c.if;
            }
            return toString();
        }
    }

    public static synchronized void a(Context context) {
        synchronized (NetworkStatusHelper.class) {
            b.a = context;
            b.a();
        }
    }

    public static void a(INetworkStatusChangeListener iNetworkStatusChangeListener) {
        a.add(iNetworkStatusChangeListener);
    }

    public static void b(INetworkStatusChangeListener iNetworkStatusChangeListener) {
        a.remove(iNetworkStatusChangeListener);
    }

    static void a(NetworkStatus networkStatus) {
        anet.channel.c.c.a(new a(networkStatus));
    }

    public static NetworkStatus a() {
        return b.b;
    }

    public static String b() {
        return b.c;
    }

    public static String c() {
        return b.d;
    }

    public static String d() {
        return b.g;
    }

    public static String e() {
        return b.f;
    }

    public static boolean f() {
        if (b.b != NetworkStatus.NO) {
            return true;
        }
        NetworkInfo b = b.b();
        if (b == null || !b.isConnected()) {
            return false;
        }
        return true;
    }

    public static boolean g() {
        NetworkStatus networkStatus = b.b;
        String str = b.d;
        if ((networkStatus != NetworkStatus.WIFI || i() == null) && (!networkStatus.isMobile() || (!str.contains("wap") && GlobalAppRuntimeInfo.getProxySetting() == null))) {
            return false;
        }
        return true;
    }

    public static String h() {
        NetworkStatus networkStatus = b.b;
        if (networkStatus == NetworkStatus.WIFI && i() != null) {
            return "proxy";
        }
        if (networkStatus.isMobile() && b.d.contains("wap")) {
            return "wap";
        }
        if (!networkStatus.isMobile() || GlobalAppRuntimeInfo.getProxySetting() == null) {
            return "";
        }
        return BaseMonitor.ALARM_POINT_AUTH;
    }

    public static Pair<String, Integer> i() {
        if (b.b != NetworkStatus.WIFI) {
            return null;
        }
        return b.h;
    }

    public static void j() {
        try {
            NetworkStatus networkStatus = b.b;
            StringBuilder stringBuilder = new StringBuilder(128);
            stringBuilder.append("\nNetwork detail*******************************\n");
            stringBuilder.append("Status: ").append(networkStatus.getType()).append('\n');
            stringBuilder.append("Subtype: ").append(b.c).append('\n');
            if (networkStatus != NetworkStatus.NO) {
                if (networkStatus.isMobile()) {
                    stringBuilder.append("Apn: ").append(b.d).append('\n');
                    stringBuilder.append("Carrier: ").append(b.g).append('\n');
                } else {
                    stringBuilder.append("BSSID: ").append(b.f).append('\n');
                    stringBuilder.append("SSID: ").append(b.e).append('\n');
                }
            }
            if (g()) {
                stringBuilder.append("Proxy: ").append(h()).append('\n');
                Pair i = i();
                if (i != null) {
                    stringBuilder.append("ProxyHost: ").append((String) i.first).append('\n');
                    stringBuilder.append("ProxyPort: ").append(i.second).append('\n');
                }
            }
            stringBuilder.append("*********************************************");
            ALog.i("awcn.NetworkStatusHelper", stringBuilder.toString(), null, new Object[0]);
        } catch (Exception e) {
        }
    }
}
