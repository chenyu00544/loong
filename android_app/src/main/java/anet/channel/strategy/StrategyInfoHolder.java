package anet.channel.strategy;

import android.text.TextUtils;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.c.c;
import anet.channel.statist.StrategyCountObject;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.status.NetworkStatusHelper.INetworkStatusChangeListener;
import anet.channel.status.NetworkStatusHelper.NetworkStatus;
import anet.channel.strategy.dispatch.a;
import anet.channel.strategy.l.b;
import anet.channel.util.LruCache;
import anet.channel.util.StringUtils;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: Taobao */
class StrategyInfoHolder implements INetworkStatusChangeListener {
    Map<String, StrategyTable> a = new LURStrategyMap();
    UnitMap b = null;
    SafeAislesMap c = null;
    HorseRideStrategyMap d = null;
    final d e = new d();
    final ConcurrentHashMap<String, String> f = new ConcurrentHashMap();
    private final StrategyTable g = new StrategyTable("Unknown");
    private final Object h = new Object();
    private final Set<String> i = new HashSet();
    private volatile String j = "";

    /* compiled from: Taobao */
    private static class ConfigInfoWrapper implements Serializable {
        UnitMap a = null;
        SafeAislesMap b = null;
        HorseRideStrategyMap c = null;

        ConfigInfoWrapper(StrategyInfoHolder strategyInfoHolder) {
            this.a = strategyInfoHolder.b;
            this.b = strategyInfoHolder.c;
            this.c = strategyInfoHolder.d;
        }

        void a(StrategyInfoHolder strategyInfoHolder) {
            strategyInfoHolder.b = this.a;
            strategyInfoHolder.c = this.b;
            strategyInfoHolder.d = this.c;
        }
    }

    /* compiled from: Taobao */
    private static class LURStrategyMap extends LruCache<String, StrategyTable> {
        public LURStrategyMap() {
            super(3);
        }

        protected boolean a(Entry<String, StrategyTable> entry) {
            c.a(new i(this, entry), 8);
            return true;
        }
    }

    public static StrategyInfoHolder a() {
        return new StrategyInfoHolder();
    }

    private StrategyInfoHolder() {
        try {
            d();
            f();
        } catch (Exception e) {
        } finally {
            e();
        }
    }

    private void d() {
        NetworkStatusHelper.a((INetworkStatusChangeListener) this);
        this.j = a(NetworkStatusHelper.a());
    }

    private void e() {
        for (Entry value : this.a.entrySet()) {
            ((StrategyTable) value.getValue()).a();
        }
        if (this.b == null) {
            this.b = new UnitMap();
        } else {
            this.b.a();
        }
        if (this.c == null) {
            this.c = new SafeAislesMap();
        } else {
            this.c.a();
        }
        this.c.a(this);
        if (this.d == null) {
            this.d = new HorseRideStrategyMap();
        } else {
            this.d.a();
        }
    }

    private void f() {
        String b = b(this.j);
        if (!TextUtils.isEmpty(this.j)) {
            a(b, this.j);
        }
        AppMonitor.getInstance().commitCount(StrategyCountObject.get(this.a.containsKey(this.j)));
        ConfigInfoWrapper configInfoWrapper = (ConfigInfoWrapper) m.b("config");
        if (configInfoWrapper != null) {
            configInfoWrapper.a(this);
        }
        c.a(new g(this, b));
    }

    private void a(String str, String str2) {
        synchronized (this.i) {
            boolean contains = this.i.contains(str);
            if (!contains) {
                this.i.add(str);
            }
        }
        if (!contains) {
            StrategyTable strategyTable = (StrategyTable) m.b(str);
            if (strategyTable != null) {
                strategyTable.a();
            } else if (!TextUtils.isEmpty(str2)) {
                strategyTable = new StrategyTable(str2);
            }
            if (strategyTable != null) {
                synchronized (this.a) {
                    this.a.put(strategyTable.a, strategyTable);
                }
            }
            synchronized (this.i) {
                this.i.remove(str);
            }
        }
    }

    void b() {
        synchronized (this.a) {
            for (StrategyTable strategyTable : this.a.values()) {
                m.a(strategyTable, b(strategyTable.a));
            }
        }
        synchronized (this.h) {
            m.a(new ConfigInfoWrapper(this), "config");
        }
    }

    StrategyTable c() {
        StrategyTable strategyTable = this.g;
        if (TextUtils.isEmpty(this.j)) {
            return strategyTable;
        }
        StrategyTable strategyTable2;
        synchronized (this.a) {
            strategyTable2 = (StrategyTable) this.a.get(this.j);
            if (strategyTable2 == null) {
                if (this.a.isEmpty()) {
                    strategyTable2 = strategyTable;
                } else {
                    strategyTable2 = (StrategyTable) this.a.values().iterator().next();
                }
            }
        }
        return strategyTable2;
    }

    private static String b(String str) {
        Object md5ToHex = StringUtils.md5ToHex(str);
        return !TextUtils.isEmpty(md5ToHex) ? md5ToHex : "DefaultStrategy";
    }

    private String a(NetworkStatus networkStatus) {
        String str = "";
        if (networkStatus.isWifi()) {
            Object e = NetworkStatusHelper.e();
            if (TextUtils.isEmpty(e)) {
                return str;
            }
            return StringUtils.concatString(networkStatus.getType(), "$", e);
        } else if (networkStatus.isMobile()) {
            return networkStatus.getType();
        } else {
            return str;
        }
    }

    void a(l.c cVar) {
        if (cVar.g != 0) {
            a.a(cVar.g, cVar.h);
        }
        b(cVar);
        c().update(cVar);
        synchronized (this.h) {
            this.c.a(cVar);
            this.b.a(cVar);
            this.d.a(cVar);
        }
    }

    private void b(l.c cVar) {
        if (cVar.c != null) {
            for (b bVar : cVar.c) {
                if (TextUtils.isEmpty(bVar.d)) {
                    this.f.remove(bVar.a);
                } else {
                    this.f.put(bVar.a, bVar.d);
                }
            }
        }
    }

    public void onNetworkStatusChanged(NetworkStatus networkStatus) {
        this.j = a(networkStatus);
        if (!TextUtils.isEmpty(this.j)) {
            synchronized (this.a) {
                if (!this.a.containsKey(this.j)) {
                    c.a(new h(this, this.j));
                }
            }
        }
    }
}
