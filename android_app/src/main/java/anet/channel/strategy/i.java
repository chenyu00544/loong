package anet.channel.strategy;

import java.io.Serializable;
import java.util.Map.Entry;

/* compiled from: Taobao */
class i implements Runnable {
    final /* synthetic */ Entry a;
    final /* synthetic */ LURStrategyMap b;

    i(LURStrategyMap lURStrategyMap, Entry entry) {
        this.b = lURStrategyMap;
        this.a = entry;
    }

    public void run() {
        m.a((Serializable) this.a.getValue(), StrategyInfoHolder.b(((StrategyTable) this.a.getValue()).a));
    }
}
