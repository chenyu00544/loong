package anet.channel.strategy.dispatch;

import android.os.Build.VERSION;
import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.status.NetworkStatusHelper.NetworkStatus;
import java.util.Map;
import java.util.Set;

/* compiled from: Taobao */
class e {
    e() {
    }

    public static Map<String, Object> a(Map<String, Object> map) {
        CharSequence appKey = GlobalAppRuntimeInfo.getAppKey();
        if (TextUtils.isEmpty(appKey)) {
            return null;
        }
        map.put("appkey", appKey);
        if (GlobalAppRuntimeInfo.getSecurity() == null) {
            return null;
        }
        map.put(c.VERSION, c.VER_CODE);
        map.put("platform", c.ANDROID);
        map.put(c.PLATFORM_VERSION, VERSION.RELEASE);
        if (!TextUtils.isEmpty(GlobalAppRuntimeInfo.getUserId())) {
            map.put("sid", GlobalAppRuntimeInfo.getUserId());
        }
        if (!TextUtils.isEmpty(GlobalAppRuntimeInfo.getUtdid())) {
            map.put("deviceId", GlobalAppRuntimeInfo.getUtdid());
        }
        NetworkStatus a = NetworkStatusHelper.a();
        map.put(c.NET_TYPE, a.toString());
        if (a.isWifi()) {
            map.put(c.BSSID, NetworkStatusHelper.e());
        } else if (a.isMobile()) {
            map.put("carrier", NetworkStatusHelper.d());
        }
        map.put("lat", String.valueOf(a.a));
        map.put("lng", String.valueOf(a.b));
        b(map);
        Set<String> set = (Set) map.remove(c.HOSTS);
        StringBuilder stringBuilder = new StringBuilder();
        for (String append : set) {
            stringBuilder.append(append).append(' ');
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        map.put(c.DOMAIN, stringBuilder.toString());
        f.a((Map) map);
        if (TextUtils.isEmpty((String) map.get("sign"))) {
            return null;
        }
        return map;
    }

    private static void b(Map<String, Object> map) {
        try {
            String ttid = GlobalAppRuntimeInfo.getTtid();
            if (!TextUtils.isEmpty(ttid)) {
                int indexOf = ttid.indexOf("@");
                if (indexOf != -1) {
                    map.put("channel", ttid.substring(0, indexOf));
                }
                ttid = ttid.substring(indexOf + 1);
                indexOf = ttid.lastIndexOf("_");
                if (indexOf != -1) {
                    map.put("appName", ttid.substring(0, indexOf));
                    map.put("appVersion", ttid.substring(indexOf + 1));
                    return;
                }
                map.put("appName", ttid);
            }
        } catch (Exception e) {
        }
    }
}
