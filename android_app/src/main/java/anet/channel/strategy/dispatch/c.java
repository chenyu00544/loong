package anet.channel.strategy.dispatch;

import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;

/* compiled from: Taobao */
public class c {
    public static final String ANDROID = "android";
    public static final String APPKEY = "appkey";
    public static final String APP_NAME = "appName";
    public static final String APP_VERSION = "appVersion";
    public static final String BSSID = "bssid";
    public static final String CARRIER = "carrier";
    public static final String CHANNEL = "channel";
    public static final String CONFIG_VERSION = "cv";
    public static final String CONN_MSG = "connMsg";
    public static final String DEVICEID = "deviceId";
    public static final String DOMAIN = "domain";
    public static final String HOSTS = "hosts";
    public static final String LATITUDE = "lat";
    public static final String LONGTITUDE = "lng";
    public static final String MACHINE = "machine";
    public static final String NET_TYPE = "netType";
    public static final String OTHER = "other";
    public static final String PLATFORM = "platform";
    public static final String PLATFORM_VERSION = "platformVersion";
    public static final String PRE_IP = "preIp";
    public static final String SID = "sid";
    public static final String SIGN = "sign";
    public static final String SIGNTYPE = "signType";
    public static final String TIMESTAMP = "t";
    public static final String VERSION = "v";
    public static final String VER_CODE = "3.1";
    public static String[] a = new String[]{"h5.m.taobao.com", "gw.alicdn.com", "g.alicdn.com", "wwc.alicdn.com", "g.tbcdn.cn", "img.alicdn.com", "dorangesource.alicdn.com", "api.m.taobao.com", "upload.m.taobao.com", "mobilegw.alipay.com", "ynuf.alipay.com"};
    public static String[] b = new String[]{"amdc.m.taobao.com", "amdc.wapa.taobao.com", "amdc.taobao.net"};
    public static final String serverPath = "/amdc/mobileDispatch";

    public static String a() {
        return b[GlobalAppRuntimeInfo.getEnv().getEnvMode()];
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.equalsIgnoreCase(a());
    }
}
