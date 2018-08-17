package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.b.b;
import anet.channel.entity.EventType;
import anet.channel.entity.d;
import anet.channel.strategy.dispatch.c;
import anet.channel.util.ALog;
import anet.channel.util.StringUtils;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/* compiled from: Taobao */
class StrategyCollection implements Serializable {
    String a;
    ConnStrategyList b = null;
    volatile long c = 0;
    volatile String d = null;
    volatile long e = 0;
    boolean f = false;

    protected StrategyCollection(String str) {
        boolean z = false;
        this.a = str;
        if (a.c(str) || c.a(str)) {
            z = true;
        }
        this.f = z;
    }

    public synchronized List<IConnStrategy> queryStrategyList() {
        List<IConnStrategy> list;
        if (this.b == null) {
            list = Collections.EMPTY_LIST;
        } else {
            list = this.b.getStrategyList();
        }
        return list;
    }

    public synchronized void notifyConnEvent(IConnStrategy iConnStrategy, EventType eventType, d dVar) {
        if (eventType == EventType.HORSE_RIDE) {
            this.e = System.currentTimeMillis();
        }
        if (this.b != null) {
            this.b.notifyConnEvent(iConnStrategy, eventType, dVar);
            if ((eventType == EventType.CONNECT_FAIL || eventType == EventType.AUTH_FAIL) && this.b.isUnavailable()) {
                b.a().a(1, this.a);
            }
        }
    }

    public String getHostWithEtag() {
        if (TextUtils.isEmpty(this.d)) {
            return this.a;
        }
        return StringUtils.concatString(this.a, ":", this.d);
    }

    public boolean isExpired() {
        return System.currentTimeMillis() > this.c;
    }

    public synchronized void update(l.b bVar) {
        this.c = System.currentTimeMillis() + (((long) bVar.b) * 1000);
        if (!bVar.a.equalsIgnoreCase(this.a)) {
            ALog.e("StrategyCollection", "update error!", null, "host", this.a, "dnsInfo.host", bVar.a);
        } else if (bVar.o) {
            if (this.b != null) {
                this.b.resetStatus();
            }
        } else if (TextUtils.isEmpty(bVar.d)) {
            this.d = bVar.n;
            if (bVar.e == null || bVar.e.length == 0 || bVar.f == null || bVar.f.length == 0) {
                this.b = null;
            } else {
                if (this.b == null) {
                    this.b = bVar.l ? ConnStrategyList.createForIDC() : ConnStrategyList.createForCDN();
                }
                this.b.update(bVar);
            }
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(32);
        if (this.b == null) {
            stringBuilder.append("[]");
        } else {
            stringBuilder.append(this.b.toString());
        }
        return stringBuilder.toString();
    }
}
