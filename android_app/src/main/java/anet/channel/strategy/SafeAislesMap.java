package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.strategy.l.b;
import anet.channel.strategy.l.c;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.LruCache;
import java.io.Serializable;

/* compiled from: Taobao */
class SafeAislesMap implements Serializable {
    public static final String NO_RESULT = "No_Result";
    private LruCache<String, String> a = null;
    private transient StrategyInfoHolder b = null;

    SafeAislesMap() {
        a();
    }

    void a(StrategyInfoHolder strategyInfoHolder) {
        this.b = strategyInfoHolder;
    }

    void a() {
        if (this.a == null) {
            this.a = new LruCache(128);
        }
        this.a.put("gw.alicdn.com", HttpConstant.HTTPS);
        this.a.put("h5.m.taobao.com", HttpConstant.HTTPS);
    }

    void a(c cVar) {
        if (cVar.c != null) {
            synchronized (this.a) {
                for (b bVar : cVar.c) {
                    if (bVar.m) {
                        this.a.remove(bVar.a);
                    } else if (bVar.o) {
                        continue;
                    } else if (HttpConstant.HTTP.equalsIgnoreCase(bVar.c) || HttpConstant.HTTPS.equalsIgnoreCase(bVar.c)) {
                        this.a.put(bVar.a, bVar.c);
                    } else {
                        this.a.put(bVar.a, NO_RESULT);
                    }
                }
            }
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.SafeAislesMap", toString(), null, new Object[0]);
            }
        }
    }

    String a(String str) {
        if (TextUtils.isEmpty(str) || !anet.channel.util.c.b(str)) {
            return null;
        }
        synchronized (this.a) {
            String str2 = (String) this.a.get(str);
            if (str2 == null) {
                this.a.put(str, NO_RESULT);
            }
        }
        if (str2 == null) {
            this.b.c().a(str, false);
        } else if (NO_RESULT.equals(str2)) {
            str2 = null;
        }
        if (str2 == null && a.d(str)) {
            return HttpConstant.HTTPS;
        }
        return str2;
    }

    public String toString() {
        String str;
        synchronized (this.a) {
            str = "SafeAislesMap: " + this.a.toString();
        }
        return str;
    }
}
