package anet.channel.strategy.dispatch;

import android.text.TextUtils;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.security.ISecurity;
import anet.channel.util.ALog;
import anet.channel.util.StringUtils;
import com.taobao.accs.antibrush.b;
import java.util.Map;

/* compiled from: Taobao */
class f {
    f() {
    }

    static void a(Map<String, String> map) {
        map.put(c.TIMESTAMP, String.valueOf(System.currentTimeMillis()));
        map.put(c.SIGNTYPE, GlobalAppRuntimeInfo.getSecurity().isSecOff() ? "noSec" : b.KEY_SEC);
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append(StringUtils.stringNull2Empty((String) map.get("appkey"))).append("&").append(StringUtils.stringNull2Empty((String) map.get(c.DOMAIN))).append("&").append(StringUtils.stringNull2Empty((String) map.get("appName"))).append("&").append(StringUtils.stringNull2Empty((String) map.get("appVersion"))).append("&").append(StringUtils.stringNull2Empty((String) map.get(c.BSSID))).append("&").append(StringUtils.stringNull2Empty((String) map.get("channel"))).append("&").append(StringUtils.stringNull2Empty((String) map.get("deviceId"))).append("&").append(StringUtils.stringNull2Empty((String) map.get("lat"))).append("&").append(StringUtils.stringNull2Empty((String) map.get("lng"))).append("&").append(StringUtils.stringNull2Empty((String) map.get(c.MACHINE))).append("&").append(StringUtils.stringNull2Empty((String) map.get(c.NET_TYPE))).append("&").append(StringUtils.stringNull2Empty((String) map.get(c.OTHER))).append("&").append(StringUtils.stringNull2Empty((String) map.get("platform"))).append("&").append(StringUtils.stringNull2Empty((String) map.get(c.PLATFORM_VERSION))).append("&").append(StringUtils.stringNull2Empty((String) map.get(c.PRE_IP))).append("&").append(StringUtils.stringNull2Empty((String) map.get("sid"))).append("&").append(StringUtils.stringNull2Empty((String) map.get(c.TIMESTAMP))).append("&").append(StringUtils.stringNull2Empty((String) map.get(c.VERSION))).append("&").append(StringUtils.stringNull2Empty((String) map.get(c.SIGNTYPE)));
        map.put("sign", a(stringBuilder.toString()));
    }

    static String a(String str) {
        String str2 = "";
        try {
            ISecurity security = GlobalAppRuntimeInfo.getSecurity();
            if (security == null) {
                ALog.e("awcn.DispatchSecurityUtil", "[getSign]iSecurity null", null, new Object[0]);
            } else {
                Object appKey = GlobalAppRuntimeInfo.getAppKey();
                if (TextUtils.isEmpty(appKey)) {
                    ALog.e("awcn.DispatchSecurityUtil", "[getSign]appkey null", null, new Object[0]);
                } else {
                    str2 = security.sign(GlobalAppRuntimeInfo.getContext(), ISecurity.SIGN_ALGORITHM_HMAC_SHA1, appKey, str);
                }
            }
        } catch (Throwable th) {
            ALog.e("awcn.DispatchSecurityUtil", "getSign", null, th, new Object[0]);
        }
        return str2;
    }
}
