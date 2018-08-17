package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.entity.ENV;
import anet.channel.util.ALog;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: Taobao */
public class a {
    private static final String[] a = new String[]{"acs.m.taobao.com", "acs.wapa.taobao.com", "acs.waptest.taobao.com"};
    private static Map<String, Integer> b = new HashMap();
    private static Set<String> c = new HashSet();

    private a() {
    }

    public static String a(ENV env) {
        return a[env.getEnvMode()];
    }

    public static synchronized Set<String> a() {
        Set hashSet;
        synchronized (a.class) {
            hashSet = new HashSet(b.keySet());
        }
        return hashSet;
    }

    public static synchronized void a(String str) {
        synchronized (a.class) {
            if (TextUtils.isEmpty(str)) {
                throw new RuntimeException("host cannot be empty");
            }
            ALog.i("awan.AccsHostManager", "addAccsHost", null, "host", str);
            Integer num = (Integer) b.get(str);
            if (num == null) {
                b.put(str, Integer.valueOf(1));
            } else {
                b.put(str, Integer.valueOf(num.intValue() + 1));
            }
        }
    }

    public static synchronized void b(String str) {
        synchronized (a.class) {
            Integer num = (Integer) b.get(str);
            if (num != null) {
                ALog.i("awan.AccsHostManager", "removeHost", null, "host", str);
                num = Integer.valueOf(num.intValue() - 1);
                if (num.intValue() == 0) {
                    b.remove(str);
                } else {
                    b.put(str, num);
                }
            }
        }
    }

    public static synchronized boolean c(String str) {
        boolean z;
        synchronized (a.class) {
            if (TextUtils.isEmpty(str)) {
                z = false;
            } else {
                z = b.containsKey(str);
            }
        }
        return z;
    }

    public static synchronized boolean d(String str) {
        boolean z;
        synchronized (a.class) {
            if (TextUtils.isEmpty(str)) {
                z = false;
            } else if (b.containsKey(str) || c.contains(str)) {
                z = true;
            } else {
                if (str.startsWith("un")) {
                    for (String endsWith : b.keySet()) {
                        if (str.endsWith(endsWith)) {
                            c.add(str);
                            z = true;
                            break;
                        }
                    }
                }
                z = false;
            }
        }
        return z;
    }

    public static synchronized void b() {
        synchronized (a.class) {
            for (String forceRefreshStrategy : b.keySet()) {
                StrategyCenter.getInstance().forceRefreshStrategy(forceRefreshStrategy);
            }
        }
    }
}
