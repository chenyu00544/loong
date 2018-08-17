package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.entity.ConnType;
import anet.channel.util.ALog;
import anet.channel.util.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: Taobao */
class d {
    static final IPConnStrategy c = a.a("", a.a(0, ConnType.HTTP));
    final ConcurrentHashMap<String, IPConnStrategy> a = new ConcurrentHashMap();
    final HashMap<String, Object> b = new HashMap();

    d() {
    }

    public List a(String str) {
        if (TextUtils.isEmpty(str) || !c.b(str)) {
            return Collections.EMPTY_LIST;
        }
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.LocalDnsStrategyTable", "try resolve ip with local dns", null, "host", str);
        }
        List list = Collections.EMPTY_LIST;
        if (!this.a.containsKey(str)) {
            Object obj;
            synchronized (this.b) {
                if (this.b.containsKey(str)) {
                    obj = this.b.get(str);
                } else {
                    Object obj2 = new Object();
                    this.b.put(str, obj2);
                    a(str, obj2);
                    obj = obj2;
                }
            }
            if (obj != null) {
                try {
                    synchronized (obj) {
                        obj.wait(500);
                    }
                } catch (InterruptedException e) {
                }
            }
        }
        IPConnStrategy iPConnStrategy = (IPConnStrategy) this.a.get(str);
        if (iPConnStrategy == null || iPConnStrategy == c) {
            return list;
        }
        list = new ArrayList();
        list.add(iPConnStrategy);
        return list;
    }

    private void a(String str, Object obj) {
        anet.channel.c.c.b(new e(this, str, obj));
    }
}
