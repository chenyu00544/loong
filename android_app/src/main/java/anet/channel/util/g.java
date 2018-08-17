package anet.channel.util;

import anet.channel.strategy.a;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Taobao */
public class g {
    private static Map<String, Integer> a = new HashMap();

    static {
        a.put("jar", Integer.valueOf(2));
        a.put("json", Integer.valueOf(3));
        a.put("html", Integer.valueOf(4));
        a.put("htm", Integer.valueOf(4));
        a.put("css", Integer.valueOf(5));
        a.put("js", Integer.valueOf(5));
        a.put("webp", Integer.valueOf(6));
        a.put("png", Integer.valueOf(6));
        a.put("jpg", Integer.valueOf(6));
        a.put("do", Integer.valueOf(6));
        a.put("zip", Integer.valueOf(9));
        a.put("bin", Integer.valueOf(9));
    }

    public static int a(URL url) {
        if (url == null) {
            throw new IllegalArgumentException("url is null!");
        } else if (a.d(url.getHost())) {
            return 1;
        } else {
            String a = c.a(url);
            if (a == null) {
                return 6;
            }
            Integer num = (Integer) a.get(a);
            return num != null ? num.intValue() : 6;
        }
    }
}
