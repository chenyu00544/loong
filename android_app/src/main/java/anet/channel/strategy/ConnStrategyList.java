package anet.channel.strategy;

import anet.channel.entity.ConnType;
import anet.channel.entity.EventType;
import anet.channel.entity.d;
import anet.channel.strategy.l.a;
import anet.channel.strategy.l.b;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* compiled from: Taobao */
public abstract class ConnStrategyList {

    /* compiled from: Taobao */
    private static class CDNStrategyList extends ConnStrategyList implements Serializable {
        private List<IPConnStrategy> a = new ArrayList();

        public String toString() {
            return this.a.toString();
        }

        public List<IConnStrategy> getStrategyList() {
            return new ArrayList(this.a);
        }

        public void update(b bVar) {
            IPConnStrategy iPConnStrategy;
            for (IPConnStrategy iPConnStrategy2 : this.a) {
                iPConnStrategy2.isToRemove = true;
            }
            for (String str : bVar.e) {
                for (a aVar : bVar.f) {
                    int a = ConnStrategyList.a(this.a, new b(this, aVar, ConnType.valueOf(aVar), str));
                    if (a != -1) {
                        iPConnStrategy2 = (IPConnStrategy) this.a.get(a);
                        iPConnStrategy2.isToRemove = false;
                        iPConnStrategy2.resetConnStatus();
                    } else {
                        iPConnStrategy2 = a.a(str, aVar);
                        if (iPConnStrategy2 != null) {
                            this.a.add(iPConnStrategy2);
                        }
                    }
                }
            }
            ListIterator listIterator = this.a.listIterator();
            while (listIterator.hasNext()) {
                if (((IPConnStrategy) listIterator.next()).isToRemove) {
                    listIterator.remove();
                }
            }
        }

        public void resetStatus() {
            for (IPConnStrategy resetConnStatus : this.a) {
                resetConnStatus.resetConnStatus();
            }
        }

        public boolean isUnavailable() {
            for (IPConnStrategy isAvailable : this.a) {
                if (isAvailable.isAvailable()) {
                    return false;
                }
            }
            return true;
        }

        public void notifyConnEvent(IConnStrategy iConnStrategy, EventType eventType, d dVar) {
            if (this.a.indexOf(iConnStrategy) != -1) {
                iConnStrategy.notifyEvent(eventType, dVar);
                Collections.sort(this.a);
            }
        }
    }

    /* compiled from: Taobao */
    private static class IDCStrategyList extends ConnStrategyList implements Serializable {
        public volatile transient List<IConnStrategy> cache = null;
        public final List<String> ips = new ArrayList();
        public final List<RawConnStrategy> strategies = new ArrayList();

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.ips).append(' ').append(this.strategies);
            return stringBuilder.toString();
        }

        public IDCStrategyList(String[] strArr, RawConnStrategy... rawConnStrategyArr) {
            this.ips.addAll(Arrays.asList(strArr));
            Collections.shuffle(this.ips);
            this.strategies.addAll(Arrays.asList(rawConnStrategyArr));
            a();
        }

        public List<IConnStrategy> getStrategyList() {
            if (this.cache == null) {
                synchronized (this) {
                    if (this.cache == null) {
                        a();
                    }
                }
            }
            return new ArrayList(this.cache);
        }

        public void update(b bVar) {
            RawConnStrategy rawConnStrategy;
            this.ips.clear();
            this.ips.addAll(Arrays.asList(bVar.e));
            for (RawConnStrategy rawConnStrategy2 : this.strategies) {
                rawConnStrategy2.isToRemove = true;
            }
            for (a aVar : bVar.f) {
                int a = ConnStrategyList.a(this.strategies, new c(this, aVar, ConnType.valueOf(aVar)));
                if (a != -1) {
                    rawConnStrategy2 = (RawConnStrategy) this.strategies.get(a);
                    rawConnStrategy2.isToRemove = false;
                    rawConnStrategy2.resetConnStatus();
                } else {
                    rawConnStrategy2 = a.a(aVar);
                    if (rawConnStrategy2 != null) {
                        this.strategies.add(rawConnStrategy2);
                    }
                }
            }
            ListIterator listIterator = this.strategies.listIterator();
            while (listIterator.hasNext()) {
                if (((RawConnStrategy) listIterator.next()).isToRemove) {
                    listIterator.remove();
                }
            }
            a();
        }

        private void a() {
            if (this.cache == null) {
                this.cache = new ArrayList();
            } else {
                this.cache.clear();
            }
            for (String str : this.ips) {
                for (RawConnStrategy a : this.strategies) {
                    this.cache.add(a.a(str, a));
                }
            }
        }

        public void resetStatus() {
            for (RawConnStrategy resetConnStatus : this.strategies) {
                resetConnStatus.resetConnStatus();
            }
        }

        public boolean isUnavailable() {
            for (RawConnStrategy isAvailable : this.strategies) {
                if (isAvailable.isAvailable()) {
                    return false;
                }
            }
            return true;
        }

        public void notifyConnEvent(IConnStrategy iConnStrategy, EventType eventType, d dVar) {
            if (iConnStrategy instanceof IPConnStrategy) {
                Object obj;
                for (RawConnStrategy rawConnStrategy : this.strategies) {
                    if (((IPConnStrategy) iConnStrategy).rawConnStrategy == rawConnStrategy) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj != null) {
                    iConnStrategy.notifyEvent(eventType, dVar);
                    Collections.sort(this.strategies);
                }
            }
        }
    }

    /* compiled from: Taobao */
    private interface Predicate<T> {
        boolean apply(T t);
    }

    public abstract List<IConnStrategy> getStrategyList();

    public abstract boolean isUnavailable();

    public abstract void notifyConnEvent(IConnStrategy iConnStrategy, EventType eventType, d dVar);

    public abstract void resetStatus();

    public abstract void update(b bVar);

    public static ConnStrategyList createForIDC() {
        return new IDCStrategyList();
    }

    public static ConnStrategyList createForIDC(String[] strArr, RawConnStrategy... rawConnStrategyArr) {
        if (strArr == null) {
            return null;
        }
        return new IDCStrategyList(strArr, rawConnStrategyArr);
    }

    public static ConnStrategyList createForCDN() {
        return new CDNStrategyList();
    }

    static <T> int a(Collection<T> collection, Predicate<T> predicate) {
        if (collection == null) {
            return -1;
        }
        int i = 0;
        Iterator it = collection.iterator();
        while (it.hasNext() && !predicate.apply(it.next())) {
            i++;
        }
        if (i == collection.size()) {
            i = -1;
        }
        return i;
    }
}
